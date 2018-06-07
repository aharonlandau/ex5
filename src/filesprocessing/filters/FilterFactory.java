package filesprocessing.filters;

import java.io.File;
import java.io.FileFilter;


/**
 * class that managing creation of FileFilter objects.
 */
public class FilterFactory {

    private static final int FILTER_PLACE = 0;

    private static final String GREATER_THAN_FILTER = "greater_than";

    private static final String BETWEEN_FILTER = "between";

    private static final String SMALLER_THAN_FILTER = "smaller_than";
    
    private static final String FILENAME_FILTER = "file";
    
    private static final String NAME_CONTAINS_FILTER = "contains";
    
    private static final String PREFIX_FILTER = "prefix";
    
    private static final String SUFFIX_FILTER = "suffix";
    
    private static final String WRAITABLE_FILTER = "writable";

    private static final String EXECUTABLE_FILTER = "executable";

    private static final String HIDDEN_FILTER = "hidden";

    private static final String ALL_FILTER = "all";


    private static final FileFilter all = new FileFilter(){
        @Override
        public boolean accept(File file) {
            return true;
        }
    };


    /**
     * creates objects of FileFilter, by parsing an string that contains a line
     * from commands file.
     * @param filterLine the line from the command file that represent the filter
     * @return an Filefilter object that represent the filter.
     */
    public static FileFilter create(String filterLine) throws BadFilterException{
        String[] params = filterLine.split("#");
        FileFilter filter = null;
        try{
            switch (params[FILTER_PLACE]){
            case GREATER_THAN_FILTER:
                filter = new GreaterThanFilter(Parser.StringToDouble(params));
                break;

            case BETWEEN_FILTER:
                double[] parsed = Parser.StringTo2Doubles(params);
                filter = new BetweenFilter(parsed[0], parsed[1]);
                break;

            case SMALLER_THAN_FILTER:
                filter = new SmallerThanFilter(Parser.StringToDouble(params));
                break;

            case FILENAME_FILTER:
                String wantedName = Parser.stringToString(params);
                filter = new NameFilter(wantedName);
                break;

            case NAME_CONTAINS_FILTER:
                String wantedStr = Parser.stringToString(params);
                filter = new ContainsFilter(wantedStr);
                break;

            case PREFIX_FILTER:
                String prefix = Parser.stringToString(params);
                filter = new PrefixFilter(prefix);
                break;

            case SUFFIX_FILTER:
                String suffix = Parser.stringToString(params);
                filter = new SuffixFilter(suffix);
                break;

            case WRAITABLE_FILTER:
                filter = new WritableFilter(Parser.stringToBoolean(params));
                break;

            case EXECUTABLE_FILTER:
                filter = new ExecutableFilter(Parser.stringToBoolean(params));
                break;

            case HIDDEN_FILTER:
                filter = new HiddenFilter(Parser.stringToBoolean(params));
                break;
                
            case ALL_FILTER:
                filter = all;
                break;

            default:
                throw new BadFilterException();
            }
        }
        catch(NumberFormatException e){//casting string to double can cause excetion, which mean bad filter line.
            throw new BadFilterException();
        }

        if (Parser.containsNOT(params)){
            filter = new NotDecorator(filter);
        }
        filter = new NoDirectoryDecorator(filter); //adding no directories to all kinds of filters.
        return filter;
    }


    public static FileFilter defaultFilter(){
        return new NoDirectoryDecorator(all);
    }
}
