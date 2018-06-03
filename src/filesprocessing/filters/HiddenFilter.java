package filesprocessing.filters;

import java.io.File;
import java.io.FileFilter;

class HiddenFilter implements FileFilter {

    private final boolean hidden;

    public HiddenFilter(boolean hidden){
        this.hidden = hidden;
    }

    @Override
    public boolean accept(File file) {
        return hidden ? file.isHidden() : !file.isHidden();
    }
}
