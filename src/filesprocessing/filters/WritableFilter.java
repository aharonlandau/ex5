package filesprocessing.filters;

import java.io.File;
import java.io.FileFilter;

class WritableFilter implements FileFilter {

    private final boolean writable;

    public WritableFilter(boolean writable){
        this.writable = writable;
    }

    @Override
    public boolean accept(File file) {
        return writable ? file.canWrite() : !file.canWrite();
    }
}
