import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        File path = new File("D:\\Programming\\Java\\Java-Networking\\IpReaderV2");
        ArrayList<File> list = new ArrayList<File>();
        ArrayList<Future<Integer>> res = new ArrayList<Future<Integer>>();
        File index;

        for (File file : path.listFiles()){

            if (file.getName().endsWith(".txt")){
                list.add(file);
            }
        }

        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i<list.size(); i++){

            index = list.get(i);
            res.add(exec.submit(new Thread(index)));

        }

        exec.shutdown();

        for (int i=0; i<res.size(); i++){

            int count = res.get(i).get();
            char y = (char) count;

            System.out.println(y);
        }
    }
}
