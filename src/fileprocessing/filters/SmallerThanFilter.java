package fileprocessing.filters;

import java.io.File;
import java.io.FileFilter;

class SmallerThanFilter implements FileFilter {

    private final double maxValue;

    public SmallerThanFilter(double maxValue){
        this.maxValue = maxValue;
    }


    @Override
    public boolean accept(File file) {
        double fileSize = (double)file.length() / Parser.KILOBYTE_BYTES;
        return fileSize < maxValue;
    }
}
