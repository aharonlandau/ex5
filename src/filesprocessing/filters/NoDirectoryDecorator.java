package filesprocessing.filters;

import java.io.File;
import java.io.FileFilter;

/**
 * After we finished all the filters, we found out we need to filter out directories,
 * we thought adding a decorator is better solution than a new superclass to all filters,
 * becuase it requires change of 1 line in the facotry method, and no change to all the filters.
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