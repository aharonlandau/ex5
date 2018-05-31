package fileprocessing.filters;

import java.io.File;

public class FileFilter implements Filter {

    private final String wantedName;
    public FileFilter(String wantedName){
        this.wantedName = wantedName;
    }


    @Override
    public boolean isFilePass(File file) {
        return file.getName().equals(wantedName);
    }
}
