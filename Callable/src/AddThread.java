import java.util.Random;
import java.util.concurrent.Callable;

public class AddThread implements Callable<Integer>{

    int i;
    int j;

    @Override
    public Integer call() throws Exception {
        Random rand = new Random();
        i = rand.nextInt(100);
        j= rand.nextInt(100);

        int sum = i + j;
        return sum;
    }
}
