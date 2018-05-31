package fileprocessing.filters;

import java.io.File;

public class FilterFactory {

    private static final int FILTER_PLACE = 0;

    private static final int FIRST_PARAM_INDEX = 1;

    private static final int SECOND_PARAM_INDEX = 2;

    private final boolean NO = false;

    private static final Filter all = new Filter(){
        @Override
        public boolean isFilePass(File file) {
            return true;
        }
    };

    public static Filter create(String filterLine){
        String[] params = filterLine.split("#");
        Filter filter=null;
        switch (params[FILTER_PLACE]){
            case "all":
                filter = all;
                break;
            case "greater_than":
                filter = new GreaterThanFilter(Double.parseDouble(params[FIRST_PARAM_INDEX]));
                break;
            case "between":
                double minVal = Double.parseDouble(params[FIRST_PARAM_INDEX]);
                double maxVal = Double.parseDouble(params[SECOND_PARAM_INDEX]);
                if(maxVal >= minVal) {
                    //TODO
                }
                filter = new BetweenFilter(minVal, maxVal);
                break;
            case "smaller_than":
                maxVal = Double.parseDouble(params[FIRST_PARAM_INDEX]);
                filter = new SmallerThhenFilter(maxVal);
                break;
            case "file":
                String wantedName = params[FIRST_PARAM_INDEX];
                filter = new FileFilter(wantedName);
            case "contains":
                String wantedStr = params[FIRST_PARAM_INDEX];
                filter = new ContainsFilter(wantedStr);
            case "prefix":
                String prefix = params[FIRST_PARAM_INDEX];
                filter = new PrefixFilter(prefix);
            case "suffix":
                String suffix = params[FIRST_PARAM_INDEX];
                filter = new SuffixFilter(suffix);
            case "writable":
                filter = new WritableFilter(params[FIRST_PARAM_INDEX]);
            case "executable":
                filter = new ExecutableFilter(params[FIRST_PARAM_INDEX]);
        }

        if (params[params.length - 1].equals("NOT")){
            filter = new NotDecorator(filter);
        }
        return filter;
    }
}
