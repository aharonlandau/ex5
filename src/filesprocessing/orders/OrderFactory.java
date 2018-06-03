package filesprocessing.orders;

import java.util.Comparator;

import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;

public abstract class OrderFactory {

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
            if (idx == 0) {
                return "";
            }
            else {
                return filename.substring(idx,filename.length());
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


    public static Comparator<File> create(String orderLine) throws BadOrderException{
        String[] params = orderLine.split("#");
        if (params.length > 2) {
            throw new BadOrderException();
        }
        if (params.length == 2 && !params[1].equals("REVERSE")) {
            throw new BadOrderException();
        }
        Comparator<File> order = null;
        switch(params[0]){
            case "abs":
                order = absOrder;
                break;
            case "type":
                order = typeOrder;
                break;
            case "size":
                order = sizeOrder;
                break;
            default:
                throw new BadOrderException();
        }
        if (params.length == 2 && params[1].equals("REVERSE")) {
            order = new ReverseDecorator(order);
        }
        return order;
    }

    public static Comparator<File> defaultOrder(){
        return absOrder;
    }
}