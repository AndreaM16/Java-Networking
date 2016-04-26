import java.util.ArrayList;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> gen1 = new ArrayList<Future<Integer>>();
        ArrayList<Future<Integer>> gen2 = new ArrayList<Future<Integer>>();
        AdderThread adderThread;

        for (int i=0; i<10; i++){
            gen1.add(exec.submit(new GeneratorThread1()));
            System.out.print("Added: "+gen1.get(i).get()+" to first list");
            gen2.add(exec.submit(new GeneratorThread1()));
            System.out.print("; Added: "+gen2.get(i).get()+" to second list");

            adderThread = new AdderThread(gen1.get(i), gen2.get(i));
            System.out.println("; Sum between "+gen1.get(i).get()+" and "+gen2.get(i).get()+" is "+adderThread.getSum());
            adderThread.run();

        }
        exec.shutdown();
    }
}
