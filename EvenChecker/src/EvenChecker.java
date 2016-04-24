import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker {

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i=0; i<5; i++){
            exec.execute(new EvenThread(3));
        }
        exec.shutdown();
    }
}