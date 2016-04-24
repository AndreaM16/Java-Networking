import java.util.Random;

public class Toaster implements Runnable {

    private ToastQueue toastQueue;
    private int count;
    private Random random = new Random();

    public Toaster(ToastQueue tq){
        toastQueue = tq;
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {
                Thread.sleep(random.nextInt(1000));

                //At random intervals make a toast
                Toast toast = new Toast(count++);
                System.out.println("New toast "+toast.getId()+" has been created");

                //Inserting toast into the queue
                toastQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster interrupted");
        }

        System.out.println("Toaster off");
    }
}
