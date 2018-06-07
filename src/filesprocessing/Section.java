package filesprocessing;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;

import filesprocessing.filters.BadFilterException;
import filesprocessing.filters.FilterFactory;
import filesprocessing.orders.OrderFactory;
import filesprocessing.orders.BadOrderException;

/**
 * class that represent a section in the commands file
 * each instance handle a single section process (filtering and ordering a folder).
 */
public class Section {

    private static final String WARNING_MESSAGE = "Warning in line %d";

    public static final String NO_ORDER_LINE = null;

    private String filterLine, orderLine;
    private int filterLineNum;
    private File sourceDir;

    public Section(String filterLine, String orderLine, File sourceDir, int filterLineNum){
        this.filterLine = filterLine;
        this.orderLine = orderLine;
        this.sourceDir = sourceDir;
        this.filterLineNum = filterLineNum;
    }

    private FileFilter getFilter(){
        FileFilter filter;
        try{
            filter = FilterFactory.create(filterLine);
        }
        catch(BadFilterException e){
            System.err.println(String.format(WARNING_MESSAGE, this.filterLineNum));
            filter = FilterFactory.defaultFilter();
        }
        return filter;
    }


    private Comparator<File> getOrder(){
        Comparator<File> order;
        if(orderLine != NO_ORDER_LINE) {
            try {
                order = OrderFactory.create(orderLine);
            }
            catch(BadOrderException e) {
                System.err.println(String.format(WARNING_MESSAGE, this.filterLineNum + 2));
                order = OrderFactory.defaultOrder();
            }
        }
        else{
            order = OrderFactory.defaultOrder();
        }
        return order;
    }

    /**
     * calls for each factory and gets an FileFilter amd Comparator instances,
     * filtering, ordering and then printing.
     */
    public void process(){
        FileFilter filter = this.getFilter(); 
        Comparator<File> order = this.getOrder();
        
        File[] filteredFiles = sourceDir.listFiles(filter);
        Arrays.sort(filteredFiles, order);

        for (File file : filteredFiles) {
            System.out.println(file.getName());            
        }
    }
}
