package filesprocessing.orders;

import java.util.Comparator;
import java.io.File;


class ReverseDecorator implements Comparator<File>{
    
    private Comparator<File> order;

    public ReverseDecorator(Comparator<File> order){
        this.order = order;
    }
    
    @Override
    public int compare(File o1, File o2) {
        return (-1)*order.compare(o1, o2);
    }
}