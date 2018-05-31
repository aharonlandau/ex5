package fileprocessing.filters;

import java.io.File;

public interface Filter {
    boolean isFilePass(File file);
}
