import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        File path = new File("D:\\Programming\\Java\\Java-Networking\\ServerLogV2");
        File myFile = null;
        ArrayList<Future<ArrayList<String>>> res = new ArrayList<>();


        for (File file: path.listFiles()){
            if (file.toString().endsWith(".txt")){
                myFile = file;
            }
        }

        ExecutorService exec = Executors.newFixedThreadPool(2);
        try {
           res.add(exec.submit(new MyThread(myFile)));
        } finally {
            exec.shutdown();
        }

        for (Future<ArrayList<String>> future : res)
        {
            ArrayList<String> k = future.get();
            for (int i=0; i<k.size(); i++){
                System.out.println(k.get(i)+"\n");
            }
        }


    }
}
