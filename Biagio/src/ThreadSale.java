import java.io.*;

public class ThreadSale extends Thread{

    private String path;

    public ThreadSale(String path){
        this.path=path;

    }

    public void run(){

        String whatToWrite = "Lei è saaaaaaaleeeeee, ";

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

    }
}
