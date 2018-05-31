package fileprocessing.filters;

import java.io.File;

public class HiddenFilter implements Filter {

    private final String writable;

    public HiddenFilter(String writable){
        this.writable = writable;
    }

    @Override
    public boolean isFilePass(File file) {
        if (writable.equals("NO")) return ! file.isHidden();
        else return file.isHidden();
    }
}
