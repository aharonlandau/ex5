package fileprocessing.filters;

import java.io.File;
import java.io.FileFilter;

/**
 * NoDirectoryDecorator
 */
class NoDirectoryDecorator implements FileFilter {

    private FileFilter filter;

    public NoDirectoryDecorator(FileFilter filter){
        this.filter = filter;
    }

    @Override
    public boolean accept(File file) {
        return filter.accept(file) && file.isFile();
    }
}