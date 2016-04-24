import java.util.Random;

public class Jammer implements Runnable {

    private ToastQueue butteredQueue, finishedQueue;

    public Jammer(ToastQueue buttered, ToastQueue finished){
        butteredQueue = buttered;
        finishedQueue = finished;
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {

                //Taking the toast from butteredQueue and inserting it into finishedQueue
                Toast toast = (Toast) butteredQueue.take();
                //Change toast status
                toast.jam();
                System.out.println("Toast "+toast.getId()+" has been jammed");
                finishedQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Jammer interrupted");
        }

        System.out.println("Jammer off");

    }
}
