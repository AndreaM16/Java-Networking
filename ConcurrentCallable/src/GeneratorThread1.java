import java.util.Random;
import java.util.concurrent.Callable;

public class GeneratorThread1 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Random rand = new Random();

        int i = rand.nextInt(100);
        return i;
    }
}
