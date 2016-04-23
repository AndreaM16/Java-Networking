import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Andrea-Toshiba on 23/04/2016.
 */
public class ThreadPool extends Thread {

    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i =0; i<5; i++){
            exec.execute(new DaemonFactory());

        }
        exec.shutdown();
    }
}
