import java.util.concurrent.ThreadFactory;

public class DaemonPoolFactory implements ThreadFactory, Runnable {
    private Runnable r;

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        System.out.println("Is is a deamon? "+t.isDaemon());
        return t;
    }

   public synchronized void print(Thread t){
       System.out.println("Launching daemon: "+t.getId());

   }

    @Override
    public void run() {
        print(newThread(r));
    }
}
