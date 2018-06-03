package filesprocessing.filters;

import java.io.File;
import java.io.FileFilter;

class GreaterThanFilter implements FileFilter{

    private double minSize;
    public GreaterThanFilter(double minSize){
        this.minSize = minSize;
    }

    @Override
    public boolean accept(File file) {
        return ((double)file.length() / Parser.KILOBYTE_BYTES) > minSize;
    }
}
