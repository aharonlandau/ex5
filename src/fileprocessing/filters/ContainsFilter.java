package fileprocessing.filters;
import java.io.File;

public class ContainsFilter implements Filter {

    private final String wantedStr;

    public ContainsFilter(String wantedStr) {
        this.wantedStr = wantedStr;
    }


    @Override
    public boolean isFilePass(File file) {
        return file.getName().contains(wantedStr);
    }
}