package fileprocessing;

import java.io.File;
import java.io.FileFilter;

import fileprocessing.filters.BadFilterException;
import fileprocessing.filters.FilterFactory;
import fileprocessing.order.Order;
public class Section {
    private String filterLine, orderLine;

    private File sourceDir;
    public Section(String filterLine, String orderLine, File sourceDir){
        this.filterLine = filterLine;
        this.orderLine = orderLine;
        this.sourceDir = sourceDir;
    }

    public void process(){
        FileFilter filter;
        try{
            filter = FilterFactory.create(filterLine);
        }
        catch(BadFilterException e){
            System.err.println("WARNING!"); //TODO add line
            filter = FilterFactory.defaultFilter();
        }
        Order order = null; //TODO
        File[] filteredFiles = sourceDir.listFiles(filter);

        for (File file : filteredFiles) {
            System.out.println(file.getName());            
        }
    }
}
