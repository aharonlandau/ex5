package filesprocessing;

/**
 * BadFormatException. thrown when the commands file is in bad format, which means
 * the last section do not have ORDER line(otherwise bad subsection will be thrown).
 */
public class BadFormatException extends Exception{ 
    private static final long serialVersionUID = 1L;
}