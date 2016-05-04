import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class HostsThread implements Callable {

    File myFile;
    ArrayList<String> hostNames = new ArrayList<String>();

    public HostsThread(File file){
        myFile = file;
    }

    @Override
    public  ArrayList<String> call() throws IOException {

        return splitter(myFile);
    }

    public ArrayList<String> splitter(File myFile) throws IOException {

        BufferedReader dataIn = null;
        String line;

        dataIn = new BufferedReader(new FileReader(this.myFile));

        while ((line = dataIn.readLine())!= null){

            String ip = line.split("\\s")[0];

            InetAddress addr = InetAddress.getByName(ip);
            System.out.println("Working on "+addr);
            String host = addr.getHostName();
            System.out.println("Done "+host);

            hostNames.add(host);
        }

        dataIn.close();
        return hostNames;
    }

}
