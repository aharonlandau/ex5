package fileprocessing;

import java.io.*;
import java.util.LinkedList;

public class DirectoryProcessor {

    private static final int SOURCE_DIR = 0;
    private static final int COMMAND_FILE = 1;

    private File sourceDir;
    private BufferedReader commandFile;

    private DirectoryProcessor(File sourceDir, FileReader commandFile){
        this.commandFile = new BufferedReader(commandFile);
        this.sourceDir = sourceDir;
    }

    private void process() throws IOException{
        int lineNum = 1;
        String line = commandFile.readLine();
        String filterLine = null, orderLine ;
        LinkedList<Section> sections = new LinkedList<Section>();
        while(line != null){
            switch (lineNum){
                case 1:
                    if (!line.equals("FILTER")){
                        //TODO
                    }
                    break;
                case 2:
                    filterLine = line;
                    break;
                case 3:
                    if(!line.equals("ORDER")){

                    }
                    break;
                case 4:
                    if (line == "FILTER"){
                        lineNum = 1;
                        sections.add(new Section(filterLine, "abs"));
                        break;
                    }
                    orderLine = line;
                    sections.add(new Section(filterLine, orderLine));
                    break;

            }

            line = commandFile.readLine();
            lineNum = lineNum == 4? 1: lineNum+1;
        }
        for (Section section:sections) {
            //TODO filtering and ordering
        }
    }

    public static void main(String[] args) throws IOException {
        FileReader commandFile = null;
        try {
            commandFile = new FileReader(args[COMMAND_FILE]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File sourceDir = new File(args[SOURCE_DIR]);
        DirectoryProcessor processor = new DirectoryProcessor(sourceDir, commandFile);
        processor.process();
    }
}
