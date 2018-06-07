package filesprocessing;

import java.io.*;
import java.util.LinkedList;

/**
 * class that handle the whole directory processing, parsing the parameters,
 * parsing the command file and create instances of sections.
 */
public class DirectoryProcessor {


    //indexes of the parameters in args
    private static final int SOURCE_DIR = 0;
    
    private static final int COMMAND_FILE = 1;

    private static final String FILTER_TITLE = "FILTER";
    
    private static final String ORDER_TITLE = "ORDER";

    private static final String BAD_ARGUMENTS_ERROR = "ERROR: Wrong usage. Should recieve 2 arguments";

    private static final String BAD_COMMANDFILE_ERROR = "ERROR: could not find commands file.";

    private static final String BAD_SOURCEDIR_ERROR = "ERROR: No files in sourcedir";

    private static final String BAD_FORMAT_ERROR  = "ERROR: Bad format of Commands File";

    private static final String BAD_SUBSECTION_ERROR = "ERROR: Bad subsection name";


    private File sourceDir;
    private BufferedReader commandFile;

    
    private DirectoryProcessor(File sourceDir, FileReader commandFile){
        this.commandFile = new BufferedReader(commandFile);
        this.sourceDir = sourceDir;
    }
    
    /**
     * Reading the commandFile and parsing him, creates a linkedList (add - o(1) iterate - o(n))
     * of section, the process each section.
     */
    private void process() throws IOException, BadFormatException, BadSubsectionException{
        int sectionLineNum = 1, lineNum = 0, filterLineNum = 0;
        String line = commandFile.readLine();
        String filterLine = null, orderLine =null;
        LinkedList<Section> sections = new LinkedList<Section>();
        while(line != null){
            lineNum++;
            switch (sectionLineNum){
                case 1:
                    if (!line.equals(FILTER_TITLE)){
                        throw new BadSubsectionException();
                    }
                    break;
                case 2:
                    filterLine = line;
                    filterLineNum = lineNum;
                    break;
                case 3:
                    if(!line.equals(ORDER_TITLE)){
                        throw new BadSubsectionException();
                    }
                    break;
                case 4:
                    orderLine = line;
                    if (line.equals(FILTER_TITLE)){
                        sectionLineNum = 1;
                        orderLine = Section.NO_ORDER_LINE;
                    }
                    sections.add(new Section(filterLine, orderLine, sourceDir, filterLineNum));
                    break;

            }
            line = commandFile.readLine();
            sectionLineNum = sectionLineNum == 4 ? 1 : sectionLineNum + 1;
        }

        if(sectionLineNum == 4){
            sections.add(new Section(filterLine, Section.NO_ORDER_LINE, sourceDir, filterLineNum));
        }
        if(sectionLineNum == 2 || sectionLineNum == 3){
            throw new BadFormatException();
        }


        for (Section section : sections) {
            section.process();
        }
    }

    public static void main(String[] args) throws IOException {
        if(args.length != 2){
            System.err.println(BAD_ARGUMENTS_ERROR);
            return;
        }

        FileReader commandFile = null;
        try {
            commandFile = new FileReader(args[COMMAND_FILE]);
        } catch (FileNotFoundException e) {
            System.err.println(BAD_COMMANDFILE_ERROR);
            return;
        }

        File sourceDir = new File(args[SOURCE_DIR]);
        if(!sourceDir.exists()){
            System.out.println(BAD_SOURCEDIR_ERROR);
            commandFile.close();
            return;
        }

        DirectoryProcessor processor = new DirectoryProcessor(sourceDir, commandFile);

        try{
            processor.process();
        }
        catch(BadFormatException e) {
            System.err.println(BAD_FORMAT_ERROR);
        }
        catch(BadSubsectionException e) {
            System.err.println(BAD_SUBSECTION_ERROR);
        }
        commandFile.close();
    }
}
