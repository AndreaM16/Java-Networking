import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int n=createNumber();
    private int num;

    public int getN(){
        return n;
    }

    // Once a thread owns a lock, it locks the processing to other threads until it unlocks
    private Lock lock = new ReentrantLock();

    //Needed in order to Use signal and await, like notifyAll, like synchronize
    private Condition cond = lock.newCondition();

    private int createNumber(){
        Random rand = new Random();
        int i;
        i = rand.nextInt(100);
        System.out.println("Generator made: "+i);
        return i;
    }

    private void evenChecker(int n){
        if (this.n%2==0){
            System.out.println(n+" is even");
        } else {
            System.out.println(n+" is odd");
        }
    }

    //Requires the lock
    public void generatorThread() throws InterruptedException{

        System.out.println("Generator Starting, Requiring the Lock");
        //Requires lock
        lock.lock();
        System.out.println("Generator has the lock!");

        //Unlocks and waits
        System.out.println("Generator waits for Even Checker");
        cond.await();

        System.out.println("Generator has awakened");

        try {
            n =createNumber();
        } finally {
            //Unlocks the thread, finally guarantees that it will be released
            lock.unlock();
        }
    }

    public void evenCheckerThread() throws InterruptedException{

        //Sleeps for 1000 ms and requires the lock
        //Since T1 is awaiting it will gain the lock
        System.out.println("EvenChecker going to sleep");
        Thread.sleep(1000);
        lock.lock();
        System.out.println("EvenChecker has awakened and has the lock");

        //Unlocks the lock and T1 can work
        System.out.println("EvenChecker releasing the lock");
        cond.signal();

        try {
            evenChecker(n);
        } finally {
            lock.unlock();
        }
    }

}