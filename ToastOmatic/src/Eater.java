public class Eater implements Runnable {

    private ToastQueue finishedQueue;
    private int counter = 0;

    public Eater(ToastQueue finished){
        finishedQueue = finished;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                Toast toast = (Toast) finishedQueue.take();
                if (toast.getId() != counter || toast.getStatus() != Toast.Status.JAMMED){
                    System.out.println("Error "+toast.getId());
                } else {
                    System.out.println("Chomp Chomp "+toast.getId());
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Eater Interrupted");
        }
        System.out.print("Eater off");
    }
}
