package fileprocessing.filters;

import java.io.File;
import java.io.FileFilter;

class NameFilter implements FileFilter {

    private final String wantedName;
    public NameFilter(String wantedName){
        this.wantedName = wantedName;
    }


    @Override
    public boolean accept(File file) {
        return file.getName().equals(wantedName);
    }
}
