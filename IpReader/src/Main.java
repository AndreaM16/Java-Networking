import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        File path = new File("C:\\Users\\Andrea-Toshiba\\Documents\\Java-Networking\\IpReader");
        ArrayList<Future<Integer>> list = new ArrayList<Future<Integer>>();
        ArrayList<File> FileList = new ArrayList<File>();

       int j = 0;

        //Check if txt files exist
        for (File file : path.listFiles()){
            if(file.getName().endsWith(".txt")){
                j+=1;
            }
        }

        if(j>0) {
            // Add al txt files into a list
            for (File file : path.listFiles()) {
                if (file.getName().endsWith(".txt")) {
                    FileList.add(file);
                    //System.out.println("Adding " + file + " to file list");
                }
            }
        }

        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i =0; i<FileList.size(); i++){

            File index = FileList.get(i);
            //System.out.println("Reading "+index);

            list.add(exec.submit(new MyThread(index)));
        }

        exec.shutdown();

        for(int i=0; i<list.size(); i++){

            int count = list.get(i).get();
            char c = (char) count;

            System.out.println("File Test"+i+" has ASCII count "+c  );
        }
    }
}
