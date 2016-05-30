import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class IpReaderThread implements Callable{

    String myFile = null;

    public IpReaderThread(String file){
        myFile = file;
    }

    @Override
    public Character call() throws Exception {
        return getAscii(myFile);
    }

    public Character getAscii(String index) throws IOException {

        int count = 0;
        BufferedReader dataIn = null;
        String line;

        dataIn = new BufferedReader(new FileReader(index));

        while ((line = dataIn.readLine())!= null){

            Integer k = Integer.valueOf(line.split("\\.")[0]);

            if (k >= 128 && k<= 191){
                count++;
            }
        }

        char c = (char) count;

        dataIn.close();
        return  c;
    }
}
