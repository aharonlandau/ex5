package fileprocessing.filters;

import java.io.File;
import java.io.FileFilter;

class ExecutableFilter implements FileFilter {

    private final boolean executable;

    public ExecutableFilter(boolean executable){
        this.executable = executable;
    }

    @Override
    public boolean accept(File file) {
        if (!this.executable) return ! file.canExecute();
        else return file.canExecute();
    }
}
