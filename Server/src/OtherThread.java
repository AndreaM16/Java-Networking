import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class OtherThread implements Callable {

    File myFile;
    ArrayList<String> otherList = new ArrayList<String>();

    public OtherThread(File file){
        myFile = file;
    }

    @Override
    public  ArrayList<String> call() throws IOException {

        return otherSplitter(myFile);
    }

    public ArrayList<String> otherSplitter(File myFile) throws IOException {

        BufferedReader dataIn = null;
        String line;

        dataIn = new BufferedReader(new FileReader(this.myFile));

        while ((line = dataIn.readLine())!= null){

            String other = line.split("\\s")[1];
            otherList.add(other);
        }

        dataIn.close();
        return otherList;
    }

}
