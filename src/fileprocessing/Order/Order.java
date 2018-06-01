package fileprocessing.order;

import java.io.File;

public interface Order {

    File[] sortFiles(File unorderedFiles);
}
