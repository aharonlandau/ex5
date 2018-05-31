package fileprocessing.filters;

import java.io.File;

public class GreaterThanFilter implements Filter{

    private double minSize;
    public GreaterThanFilter(double minSize){
        this.minSize = minSize;
    }

    @Override
    public boolean isFilePass(File file) {
        return (double)file.length()/1000 > minSize;
    }
}
