import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class MyThread implements Callable<ArrayList<String>>{

    File myFile;
    String j;
    ArrayList<String> myList = new ArrayList<String>();

    public MyThread(File file){

        myFile = file;
    }


    @Override
    public ArrayList<String> call() throws Exception {

        BufferedReader dataIn = null;
        String line;

        dataIn = new BufferedReader(new FileReader(this.myFile));

        while ((line = dataIn.readLine())!= null) {
            myList.add(splitter(line));

        }
        dataIn.close();
        return myList;
    }


    public String splitter(String line) throws IOException {


            String ip = line.split("\\s")[0];
            String other = line.split("\\s")[1];

            InetAddress addr = InetAddress.getByName(ip);
            System.out.println("Working on "+addr);
            String host = addr.getHostName();
            System.out.println("Done "+host);
            j = "Made " + host + " " + other;
            return j;
    }

}
