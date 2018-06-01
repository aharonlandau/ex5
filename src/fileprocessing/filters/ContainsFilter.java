package fileprocessing.filters;

import java.io.File;
import java.io.FileFilter;

class ContainsFilter implements FileFilter {

    private final String wantedStr;

    public ContainsFilter(String wantedStr) {
        this.wantedStr = wantedStr;
    }


    @Override
    public boolean accept(File file) {
        return file.getName().contains(wantedStr);
    }
}