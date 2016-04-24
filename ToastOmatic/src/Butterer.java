import java.util.Random;

public class Butterer implements Runnable {

    private ToastQueue dryQueue, butteredQueue;

    public Butterer(ToastQueue dry, ToastQueue buttered){
        dryQueue = dry;
        butteredQueue = buttered;
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {

                //Taking the toast from toastQueue and inserting it into butteredQueue
                Toast toast = (Toast) dryQueue.take();
                //Change toast status
                toast.buttered();
                System.out.println("Toast "+toast.getId()+" has been buttered");
                butteredQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Butterer interrupted");
        }

        System.out.println("Butterer off");

    }
}
