package filesprocessing;

import java.io.*;
import java.util.LinkedList;

public class DirectoryProcessor {


    //indexes of the parameters in args
    private static final int SOURCE_DIR = 0;
    
    private static final int COMMAND_FILE = 1;

    private static final String FILTER_TITLE = "FILTER";
    
    private static final String ORDER_TITLE = "ORDER";


    private File sourceDir;
    private BufferedReader commandFile;

    
    private DirectoryProcessor(File sourceDir, FileReader commandFile){
        this.commandFile = new BufferedReader(commandFile);
        this.sourceDir = sourceDir;
    }

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


        for (Section section:sections) {
            section.process();
        }
    }

    public static void main(String[] args) throws IOException {
        if(args.length != 2){
            System.err.println("ERROR: Wrong usage. Should recieve 2 arguments");
            return;
        }

        FileReader commandFile = null;
        try {
            commandFile = new FileReader(args[COMMAND_FILE]);
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: could not find commands file.");
            return;
        }

        File sourceDir = new File(args[SOURCE_DIR]);
        if(!sourceDir.exists()){
            System.out.println("ERROR: No files in sourcedir");
            return;
        }

        DirectoryProcessor processor = new DirectoryProcessor(sourceDir, commandFile);

        try{
            processor.process();
        }
        catch(BadFormatException e) {
            System.err.println("ERROR: Bad format of Commands File");
        }
        catch(BadSubsectionException e) {
            System.err.println("ERROR: Bad subsection name");
        }

    }
}
