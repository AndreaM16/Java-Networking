import java.io.*;
import java.util.concurrent.Callable;

public class RowColumnsCounterThread implements Callable{

    String myFile = null;
    int cols = 0;
    int rows = 0;
    Integer[] rowCols = new Integer[2];

    public RowColumnsCounterThread(String file){
        myFile = file;
    }

    @Override
    public Integer[] call() throws Exception {
        return rowColCounter(myFile);
    }

    public Integer[] rowColCounter(String myFile) throws IOException {

        String line = null;
        BufferedReader dataIn = null;
        dataIn = new BufferedReader(new FileReader(myFile));

        while ((line = dataIn.readLine())!= null){

            String[] columns = line.split("\\s+");
            cols = columns.length;
            rows++;
        }

        rowCols[0]=rows;
        rowCols[1]=cols;

        System.out.println("Got "+rowCols[0]+" rows and "+rowCols[1]+" columns");
        dataIn.close();
        return rowCols;

    }
}