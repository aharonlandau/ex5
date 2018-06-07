package filesprocessing.orders;

import java.util.Comparator;
import java.io.File;


/**
 * static class that manages Comparator<File> object creation
 * and parsing order lines.
 */
public abstract class OrderFactory {

    private static final String REVERSE_SUFFIX = "REVERSE";

    private static final String ABS_ORDER = "abs";

    private static final String TYPE_ORDER = "type";

    private static final String SIZE_ORDER = "size";

    


    private static final Comparator<File> absOrder = new Comparator<File>(){
        @Override
        public int compare(File o1, File o2){
            String file1Name = o1.getAbsolutePath();
            String file2Name = o2.getAbsolutePath();
            return file1Name.compareTo(file2Name);
        }
    };

    private static final Comparator<File> typeOrder = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            String file1Type = getType(o1.getName());
            String file2Type = getType(o2.getName());
            if(file1Type.equals(file2Type)) {
                return absOrder.compare(o1, o2);
            }
            else {
                return file1Type.compareTo(file2Type);
            }
        }

        private String getType(String filename) {
            int idx = filename.lastIndexOf('.');
            if (idx <= 0) { // dot at start or no dots
                return "";
            }
            else {
                return filename.substring(idx + 1);
            }
        }
    };

    private static final Comparator<File> sizeOrder = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            long file1size = o1.length();
            long file2size = o2.length();
            long dSize = file1size - file2size;
            if (dSize == 0){
                return absOrder.compare(o1, o2);
            }
            else return (int)dSize;
        }
    
    };

    /**
     * creates objects of Comparator<File>, by parsing an string that contains a line
     * from commands file.
     * @param orderLine the line from file that represent the type of order
     * @return an Comparator object that compare by the way required.
     */
    public static Comparator<File> create(String orderLine) throws BadOrderException{
        String[] params = orderLine.split("#");
        if (params.length > 2) {
            throw new BadOrderException();
        }
        if (params.length == 2 && !params[1].equals(REVERSE_SUFFIX)) {
            throw new BadOrderException();
        }
        Comparator<File> order = null;
        switch(params[0]){
            case ABS_ORDER:
                order = absOrder;
                break;
            case TYPE_ORDER:
                order = typeOrder;
                break;
            case SIZE_ORDER:
                order = sizeOrder;
                break;
            default:
                throw new BadOrderException();
        }
        if (params.length == 2 && params[1].equals(REVERSE_SUFFIX)) {
            order = new ReverseDecorator(order);
        }
        return order;
    }

    public static Comparator<File> defaultOrder(){
        return absOrder;
    }
}