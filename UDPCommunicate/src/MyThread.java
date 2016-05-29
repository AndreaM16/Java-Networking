import java.io.*;
import java.util.concurrent.Callable;

public class MyThread implements Callable{

    String myFile = null;
    String response = null;

    public MyThread(String file){
        myFile = file;
    }

    @Override
    public String call() throws Exception {
        return appender(myFile);
    }


    public String appender(String myFile) throws IOException {

        String line = null;
        BufferedReader dataIn = null;
        dataIn = new BufferedReader(new FileReader(myFile));
        StringBuilder builder = new StringBuilder();

        while ((line = dataIn.readLine())!= null){

            String[] chars = line.split("\\s+");

            for (int i=0; i<chars.length; i++){
                builder.append(chars[i]);
            }
            response = builder.toString();
            return response;
        }

        System.out.println("I'm the Thread and I'm sending a response "+response+" to the Server");
        dataIn.close();
        System.out.println("I'm the Thread and I Got no response");
        return response = "None";
    }
}
