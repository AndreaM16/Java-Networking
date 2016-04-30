import java.io.*;
import java.util.concurrent.Callable;

public class MyThread implements Callable {

    File index;

    public MyThread(File i) {

        index = i;
    }

    @Override
    public Integer call() throws Exception {

        Thread.sleep(1000);
        return readIps(index);

    }

    public int readIps(File index) throws IOException {

        int count =0;
        BufferedReader dataIn=null;
        String line;

        try {
            dataIn=new BufferedReader(new FileReader(this.index));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        while((line = dataIn.readLine()) != null) {
            int test = Integer.parseInt(line.substring(0,line.indexOf('.')));
            if (test >= 128 && test <= 191) count++;

        }
        //System.out.println("File "+this.index+" has "+count+" B Class IPs");
        dataIn.close();

        return count;
    }

}
