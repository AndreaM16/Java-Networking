import java.util.Random;
import java.util.concurrent.Callable;

public class GeneratorThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Random rand = new Random();

        int j = rand.nextInt(100);
        return j;
    }
}
