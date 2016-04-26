import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Andrea-Toshiba on 26/04/2016.
 */
public class AdderThread implements Runnable{

  Future<Integer> i;
  Future<Integer> j;

    public AdderThread(Future<Integer> t1, Future<Integer> t2){
        this.i = t1;
        this.j = t2;
    }

    @Override
    public void run() {
        getSum();
    }

    public int getSum(){
        int sum = 0;
        try {
            sum = i.get() + j.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return sum;
    }
}


