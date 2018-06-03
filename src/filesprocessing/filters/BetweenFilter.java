package filesprocessing.filters;

import java.io.File;
import java.io.FileFilter;

class BetweenFilter implements FileFilter {

    private final double minValue, maxValue;

    public BetweenFilter(double minValue, double maxValue){

        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public boolean accept(File file) {
        double fileSize = (double)file.length() / Parser.KILOBYTE_BYTES;
        return (fileSize <= maxValue) && (fileSize >= minValue);
    }
}
