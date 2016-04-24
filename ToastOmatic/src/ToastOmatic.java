import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ToastOmatic {

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();

        ToastQueue dryQueue = new ToastQueue();
        ToastQueue butteredQueue = new ToastQueue();
        ToastQueue finishedQueue = new ToastQueue();

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butterer(dryQueue, butteredQueue));
        exec.execute(new Jammer(butteredQueue, finishedQueue));
        exec.execute(new Eater(finishedQueue));
        Thread.sleep(random.nextInt(1000));

        exec.shutdown();
    }
}
