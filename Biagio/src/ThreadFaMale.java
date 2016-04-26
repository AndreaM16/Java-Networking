import java.io.*;
import java.nio.file.StandardOpenOption;

public class ThreadFaMale extends Thread{

    private String path;

    public ThreadFaMale(String path, boolean append){
        this.path=path;
    }

    public void run(){

        String whatToWrite = "Fa Maleeeeee";

        OutputStream outStream = null;
        try {
            outStream = new FileOutputStream(path, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Encodes this String into a sequence of bytes using the named charset, storing the result into a new byte array.
        try {
            outStream.write(whatToWrite.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
