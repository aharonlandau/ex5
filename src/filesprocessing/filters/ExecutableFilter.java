package filesprocessing.filters;

import java.io.File;
import java.io.FileFilter;

class ExecutableFilter implements FileFilter {

    private final boolean executable;

    public ExecutableFilter(boolean executable){
        this.executable = executable;
    }

    @Override
    public boolean accept(File file) {
        return executable ? file.canExecute() : !file.canExecute();
    }
}
