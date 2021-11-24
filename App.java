import java.io.*;
import java.nio.charset.Charset;

import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Read an EBCDIC File!");
        ReadEBCDICFile();
    }

    private static void ReadEBCDICFile() {
        try { 
            
            String inputfile;

            System.out.println("++++++++++ EBCDIC FIMA READER ++++++++++");
            System.out.print("File Name: ");
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String userChoice = myObj.nextLine();  // Read user input
            
            
            if(userChoice.length()>0) {
                inputfile = userChoice;
            }
            
            
            File inputFileName = new File(inputfile);

            String outName = inputFileName.getName() + ".txt";
            File outputFileName = new File(outName);
            InputStream inputStream = new FileInputStream(inputFileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("IBM280"));
            
            OutputStream outStream = new FileOutputStream(outputFileName);
            
            OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream);
            
            char[] record = new char[6000];
            
            int data = inputStreamReader.read(record);
            int counter = 0;
            outStreamWriter.write("FileName;RecordNumber;RecordData\r\n");

            long start = System.currentTimeMillis();

            while(data != -1) {
                data = inputStreamReader.read(record);
                //System.out.println("letti : " + data + " bytes\r\n");
                outStreamWriter.write(inputfile + ";" + counter + ";");
                outStreamWriter.write(record);
                outStreamWriter.write(";\r\n");
                counter++;

            }
            
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;

            System.out.println("Elapsed Time: " + timeElapsed + " ms");

            inputStreamReader.close();
            outStreamWriter.close();

        }
        catch(IOException e) {
            System.out.println("errore");
        }
    }
}

