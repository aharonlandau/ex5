package fileprocessing.filters;

import java.io.File;
import java.io.FileFilter;

public class FilterFactory {

    private static final int FILTER_PLACE = 0;

    private static final FileFilter all = new FileFilter(){
        @Override
        public boolean accept(File file) {
            return true;
        }
    };

    public static FileFilter create(String filterLine) throws BadFilterException{
        String[] params = filterLine.split("#");
        FileFilter filter = null;
            try{
                switch (params[FILTER_PLACE]){
                case "greater_than":
                    filter = new GreaterThanFilter(Parser.StringToDouble(params));
                    break;

                case "between":
                    double[] parsed = Parser.StringTo2Doubles(params);
                    if(parsed[0] > parsed[1]) {
                        throw new BadFilterException();
                    }
                    filter = new BetweenFilter(parsed[0], parsed[1]);
                    break;

                case "smaller_than":
                    filter = new SmallerThanFilter(Parser.StringToDouble(params));
                    break;

                case "file":
                    String wantedName = Parser.stringToString(params);
                    filter = new NameFilter(wantedName);
                    break;

                case "contains":
                    String wantedStr = Parser.stringToString(params);
                    filter = new ContainsFilter(wantedStr);
                    break;

                case "prefix":
                    String prefix = Parser.stringToString(params);
                    filter = new PrefixFilter(prefix);
                    break;

                case "suffix":
                    String suffix = Parser.stringToString(params);
                    filter = new SuffixFilter(suffix);
                    break;

                case "writable":
                    filter = new WritableFilter(Parser.stringToBoolean(params));
                    break;

                case "executable":
                    filter = new ExecutableFilter(Parser.stringToBoolean(params));
                    break;

                case "hidden":
                    filter = new HiddenFilter(Parser.stringToBoolean(params));
                    break;
                    
                case "all":
                    filter = all;
                    break;

                default:
                    throw new BadFilterException();
            }
        }
        catch(NumberFormatException e){
            throw new BadFilterException();
        }

        if (Parser.containsNOT(params)){
            filter = new NotDecorator(filter);
        }
        filter = new NoDirectoryDecorator(filter);
        return filter;
    }


    public static FileFilter defaultFilter(){
        return new NoDirectoryDecorator(all);
    }
}
