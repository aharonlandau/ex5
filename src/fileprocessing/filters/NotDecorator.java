package fileprocessing.filters;

import java.io.File;

public class NotDecorator implements Filter {

    private Filter filter;
    public NotDecorator(Filter filter) {
        this.filter = filter;
    }


    @Override
    public boolean isFilePass(File file) {
        return !filter.isFilePass(file);
    }
}
