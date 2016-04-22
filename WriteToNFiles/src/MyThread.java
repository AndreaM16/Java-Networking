import java.io.*;

/**
 * Created by Andrea-Toshiba on 22/04/2016.
 */
public class MyThread extends Thread {

    private File path;

    public MyThread(File path){
        this.path=path;

    }

    @Override
    public String toString() {
        return path.getName()+" id: "+getId();
    }

    public void run(){

        String whatToWrite = "Hello World!";

        OutputStream outStream = null;
        try {
            outStream = new FileOutputStream(path);
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
