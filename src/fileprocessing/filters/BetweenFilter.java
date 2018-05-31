package fileprocessing.filters;

import java.io.File;

public class BetweenFilter implements Filter {

    private final double minValue, maxValue;

    public BetweenFilter(double minValue, double maxValue){

        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public boolean isFilePass(File file) {
        double fileSize = (double)file.length() / 1000;
        return fileSize < maxValue && fileSize > minValue;
    }
}
