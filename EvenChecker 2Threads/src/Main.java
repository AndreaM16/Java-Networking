import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String [] args) throws Exception{

        final Runner runner = new Runner();

        for (int i=0; i<4; i++){
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        runner.generatorThread();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        runner.evenCheckerThread();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();

            System.out.println("\n\n");
        }

    }
}