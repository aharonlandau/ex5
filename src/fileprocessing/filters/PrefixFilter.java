package fileprocessing.filters;

import java.io.File;

public class PrefixFilter implements Filter {

    private final String prefix;

    public PrefixFilter(String prefix){
        this.prefix = prefix;
    }


    @Override
    public boolean isFilePass(File file) {
        return file.getName().startsWith(prefix);
    }
}
