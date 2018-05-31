package fileprocessing.filters;

import java.io.File;

public class SuffixFilter implements Filter {

    private final String suffix;

    public SuffixFilter(String suffix){
        this.suffix = suffix;
    }


    @Override
    public boolean isFilePass(File file) {
        return file.getName().endsWith(suffix);
    }
}
