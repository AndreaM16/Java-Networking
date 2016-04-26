import java.util.ArrayList;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args){

        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> list = new ArrayList<Future<Integer>>();
        Future<Integer> inter = new Future<Integer>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Integer get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public Integer get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };

        for (int i=0; i<10; i++){
            list.add(exec.submit(new AddThread()));
            try {
                System.out.println("list "+list.get(i).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            inter = exec.submit(new AddThread());
            try {
                System.out.println("inter "+inter.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        exec.shutdown();
    }
}
