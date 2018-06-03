package filesprocessing.filters;

import java.io.File;
import java.io.FileFilter;

class NotDecorator implements FileFilter {

    private FileFilter filter;
    public NotDecorator(FileFilter filter) {
        this.filter = filter;
    }


    @Override
    public boolean accept(File file) {
        return !filter.accept(file);
    }
}
