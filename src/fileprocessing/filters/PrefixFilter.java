package fileprocessing.filters;

import java.io.File;
import java.io.FileFilter;

class PrefixFilter implements FileFilter {

    private final String prefix;

    public PrefixFilter(String prefix){
        this.prefix = prefix;
    }


    @Override
    public boolean accept(File file) {
        return file.getName().startsWith(prefix);
    }
}
