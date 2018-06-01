package fileprocessing.filters;

import java.io.File;
import java.io.FileFilter;

class WritableFilter implements FileFilter {

    private final boolean writable;

    public WritableFilter(boolean writable){
        this.writable = writable;
    }

    @Override
    public boolean accept(File file) {
        if (!this.writable) return ! file.canWrite();
        else return file.canWrite();
    }
}
