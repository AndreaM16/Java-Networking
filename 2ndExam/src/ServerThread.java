import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class ServerThread implements Callable{

    File myFile;
    String myDay;

    public ServerThread(File file, String day){
        myFile = file;
        myDay = day;
    }

    @Override
    public String call() throws Exception {
        return lineReader(myFile, myDay);
    }

    public String lineReader(File myFile, String day) throws IOException {

        String myDay = day;
        BufferedReader dataIn = null;
        dataIn = new BufferedReader(new FileReader(myFile));
        String line;
        String response = null;

        while ((line = dataIn.readLine())!= null){

            String dayString = line.split("\\s")[0];
            System.out.println("I'm the Thread and I Got the day: "+dayString+" From Server");

            if(dayString.equalsIgnoreCase(myDay)){
                return response = line.split("\\s")[1];

            }
        }

        System.out.println("I'm the Thread and I Got a response: "+response);
        dataIn.close();
        System.out.println("I'm the Thread and I Got no response");
        return response = "None";

    }
}
