import java.io.*;
import java.util.concurrent.Callable;

/**
 * Created by Andrea-Toshiba on 30/04/2016.
 */
public class Thread implements Callable {

    File index;

    public Thread(File ind){

        index = ind;
    }

    @Override
    public Integer call() throws Exception {

        return getAscii(index);
    }

    public int getAscii(File index) throws IOException {

        int count = 0;
        BufferedReader dataIn = null;
        String line;

        dataIn = new BufferedReader(new FileReader(this.index));

        while ((line = dataIn.readLine())!= null){

            Integer k = Integer.valueOf(line.split("\\.")[0]);

            if (k >= 128 && k<= 191){
                count++;
            }
        }

        dataIn.close();
        return count;
    }
}
