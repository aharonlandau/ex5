package fileprocessing.filters;

import java.io.File;

public class FilterFactory {

    private static final int FILTER_PLACE = 0;

    private static final int FIRST_PARAM_INDEX = 1;

    private static final int SECOND_PARAM_INDEX = 2;

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
                double maxVal = Double.parseDouble(params[])
                filter = new BetweenFilter()
        }

        if (params[params.length - 1] == "NOT"){
            filter = new NotDecorator(filter);
        }
        return filter;
    }
}
