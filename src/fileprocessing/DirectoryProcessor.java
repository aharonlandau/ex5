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
        String filterLine = null, orderLine =null;
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
                    orderLine = line;
                    if (line == "FILTER"){
                        lineNum = 1;
                        orderLine = "";
                    }
                    sections.add(new Section(filterLine, orderLine, sourceDir));
                    break;

            }
            line = commandFile.readLine();
            lineNum = lineNum == 4? 1: lineNum + 1;
        }

        if(lineNum == 4){
            sections.add(new Section(filterLine, orderLine, sourceDir)); 
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
            System.out.println("ERROR: could not find dir");
            return;
        }
        DirectoryProcessor processor = new DirectoryProcessor(sourceDir, commandFile);
        processor.process();

    }
}
