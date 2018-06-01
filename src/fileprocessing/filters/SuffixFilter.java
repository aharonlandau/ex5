package fileprocessing.filters;

import java.io.File;
import java.io.FileFilter;

class SuffixFilter implements FileFilter {

    private final String suffix;

    public SuffixFilter(String suffix){
        this.suffix = suffix;
    }


    @Override
    public boolean accept(File file) {
        return file.getName().endsWith(suffix);
    }
}
