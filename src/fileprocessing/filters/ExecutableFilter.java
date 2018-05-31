package fileprocessing.filters;

import java.io.File;

public class ExecutableFilter implements Filter {

    private final String writable;

    public ExecutableFilter(String writable){
        this.writable = writable;
    }

    @Override
    public boolean isFilePass(File file) {
        if (writable.equals("NO")) return ! file.canExecute();
        else return file.canExecute();
    }
}
