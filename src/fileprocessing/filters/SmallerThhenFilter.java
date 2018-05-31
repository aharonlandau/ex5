package fileprocessing.filters;

import java.io.File;

public class SmallerThhenFilter implements Filter {

    private final double maxValue;

    public SmallerThhenFilter(double maxValue){
        this.maxValue = maxValue;
    }


    @Override
    public boolean isFilePass(File file) {
        double fileSize = file.length() / 1000;
        return fileSize < maxValue;
    }
}
