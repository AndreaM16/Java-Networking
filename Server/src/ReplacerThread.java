import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ReplacerThread implements Runnable{

    ArrayList<Future<String>> hostsList = new ArrayList<Future<String>>();
    ArrayList<Future<String>> otherList = new ArrayList<Future<String>>();
    File file;

    public ReplacerThread(File myFile, ArrayList<Future<String>> hosts, ArrayList<Future<String>> other){
        file = myFile;
        hostsList = hosts;
        otherList = other;
    }

    @Override
    public void run() {

        try {
            replacer(file, hostsList, otherList);
        } catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void replacer(File file, ArrayList<Future<String>> hostsList, ArrayList<Future<String>> otherList) throws IOException, ExecutionException, InterruptedException {

        FileOutputStream fos = new FileOutputStream(file);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i=0; i<=hostsList.size(); i++){

          //String myHost = hostsList.get(i).get();
          //String myOther = otherList.get(i).get();

            bw.write(hostsList.get(i).get()+" "+otherList.get(i).get()+" modified");
            bw.newLine();

        }

        bw.close();
        System.out.println("k");
    }
}
