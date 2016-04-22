import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Random;

import static java.lang.Thread.currentThread;

public class WriteToFile {

    public static void main(String[] args) throws IOException {

        //Picking folder's path
        File path = new File("C:\\Users\\Andrea-Toshiba\\IdeaProjects\\Java-Networking\\WriteToNFiles");
        int j = 0;
        ArrayList<File> FileList = new ArrayList<File>();

        //Check if txt files exist
        for (File file : path.listFiles()){
            if(file.getName().endsWith(".txt")){
                j+=1;
            }
        }

        if (j==0){
            //Generate Random Files
            for(int i = 0; i<Math.random()*20; i++){
                File f = new File("Hello"+i+".txt");

                /* What to write in the file */
                String whatToWrite = "Hello Gianny!";

                OutputStream outStream = new FileOutputStream(f);
            }
        } else {
            System.out.println("Files alredy exist");
        }

        /*
        //If txt files exist
        if (j>0){

            int i = 0;

            for (File file : path.listFiles()){
                if(file.getName().endsWith(".txt")){

                    i++;

                    MyThread thread = new MyThread(file);
                    thread.start();
                    System.out.println("Writing"+i);
                }
            }
        }*/

        if(j>0){

            // Add al txt files into a list
            for (File file : path.listFiles()){
                if(file.getName().endsWith(".txt")) {
                    FileList.add(file);
                    System.out.println("Adding "+file+" to file list");
                }
            }

            //Getting file list size for next operations
            int listSize = FileList.size();

            while (FileList.size()!=0){

                Random randomizer = new Random();

                File index = FileList.get(randomizer.nextInt(FileList.size()));
                System.out.println(index);

                MyThread thread = new MyThread(index);
                thread.start();
                System.out.println("Writing "+thread);

                FileList.remove(index);
                listSize = FileList.size();

            }
        }

    }
}
