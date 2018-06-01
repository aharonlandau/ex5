package fileprocessing.filters;

import java.io.File;
import java.io.FileFilter;

class HiddenFilter implements FileFilter {

    private final boolean hidden;

    public HiddenFilter(boolean hidden){
        this.hidden = hidden;
    }

    @Override
    public boolean accept(File file) {
        if (!hidden) return file.isHidden();
        else return !file.isHidden();
    }
}
