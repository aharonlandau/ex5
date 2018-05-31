package fileprocessing.filters;

import java.io.File;

public class WritableFilter implements Filter {

    private final String writable;

    public WritableFilter(String writable){
        this.writable = writable;
    }

    @Override
    public boolean isFilePass(File file) {
        if (writable.equals("NO")) return ! file.canWrite();
        else return file.canWrite();
    }
}
