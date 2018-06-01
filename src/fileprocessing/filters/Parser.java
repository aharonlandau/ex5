package fileprocessing.filters;

abstract class Parser {
    private static final int FIRST_PARAM_INDEX = 1;

    private static final int SECOND_PARAM_INDEX = 2;

    /**
     * the number of bytes in kilobyte
     */
    public static final int KILOBYTE_BYTES = 1024;


    private static boolean isParamsNumberValid(int required, String[] params) {
        if(params.length != required + 1 && params.length != required + 2){
            return false;
        }
        if (params.length == required + 2 && !params[params.length -1].equals("NOT")){
            return false;
        }
        return true;
    }


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
        //TODO - think if we should move 0<1 to here
        return parsedParams;
    }

    public static String stringToString(String[] params) throws BadFilterException{
        if(!isParamsNumberValid(params)) {
            throw new BadFilterException();
        }
        return params[FIRST_PARAM_INDEX];
    }

    public static boolean containsNOT(String[] params) {
        return params[params.length - 1].equals("NOT");
    }
}