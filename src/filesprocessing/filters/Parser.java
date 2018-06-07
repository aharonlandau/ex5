package filesprocessing.filters;


/**
 * a helper class that contains static method for parsing the filters parameters and 
 * check for validation. 
 */
abstract class Parser {
    private static final int FIRST_PARAM_INDEX = 1;

    private static final int SECOND_PARAM_INDEX = 2;

    private static final String NOT_SUFFIX = "NOT";

    /**
     * the number of bytes in kilobyte
     */
    public static final int KILOBYTE_BYTES = 1024;

    /**
     * checks if the amout of # matches the amout the filter required.
     */
    private static boolean isParamsNumberValid(int required, String[] params) {
        if(params.length != required + 1 && params.length != required + 2){
            return false;
        }
        if (params.length == required + 2 && !params[params.length - 1].equals(NOT_SUFFIX)){
            return false;
        }
        return true;
    }

    /**
     * almost all filters (except 1) require only 1 parameter, so we 
     * overloaded this method with 'default' parameter.
     */
    private static boolean isParamsNumberValid(String[] params) {
        return isParamsNumberValid(1, params);
    }

    public static boolean stringToBoolean(String[] params) throws BadFilterException {
        if(!isParamsNumberValid(params)){
            throw new BadFilterException();
        }
        switch(params[FIRST_PARAM_INDEX]){
            case "NO":
                return false;
            case "YES":
                return true;
            default:
                throw new BadFilterException();
        }
    }
    public static double StringToDouble(String[] params) throws BadFilterException {
        if(!isParamsNumberValid(params)) {
            throw new BadFilterException();
        }

        Double value = Double.parseDouble(params[FIRST_PARAM_INDEX]);
        if(value < 0){
            throw new BadFilterException();
        }
        return value;
    }

    public static double[] StringTo2Doubles(String[] params) throws BadFilterException {
        if(!isParamsNumberValid(2, params)){
            throw new BadFilterException();
        }
        double[] parsedParams = new double[2];
        parsedParams[0] = Double.parseDouble(params[FIRST_PARAM_INDEX]);
        parsedParams[1] = Double.parseDouble(params[SECOND_PARAM_INDEX]);
        if(parsedParams[0] < 0 || parsedParams[1] < 0) {
            throw new BadFilterException();
        }
        if(parsedParams[0] > parsedParams[1]) {
            throw new BadFilterException();
        }
        return parsedParams;
    }

    public static String stringToString(String[] params) throws BadFilterException{
        if(!isParamsNumberValid(params)) {
            throw new BadFilterException();
        }
        return params[FIRST_PARAM_INDEX];
    }

    public static boolean containsNOT(String[] params) {
        return params[params.length - 1].equals(NOT_SUFFIX);
    }
}