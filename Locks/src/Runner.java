import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int count = 0;
    // Once a thread owns a lock, it locks the processing to other threads until it unlocks
    private Lock lock = new ReentrantLock();

    //Needed in order to Use signal and await, like notifyAll, like synchronize
    private Condition cond = lock.newCondition();

    private void increment(){
        for(int i=0; i<1000; i++){
            count++;
        }
    }

    //Requires the lock
    public void firstThread() throws InterruptedException{

        System.out.println("T1 Starting, Requiring the Lock");
        //Requires lock
        lock.lock();
        System.out.println("T1 has the lock!");

        //Unlocks and waits
        System.out.println("T1 needs to have a rest, unlocking the lock");
        System.out.println("Wait . . . . ");
        cond.await();

        System.out.println("T1 Woken up!");

        try {
            increment();
        } finally {
            //Unlocks the thread, finally guarantees that it will be released
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException{

        //Sleeps for 1000 ms and requires the lock
        //Since T1 is awaiting it will gain the lock
        System.out.println("T2 is going to sleep");
        Thread.sleep(1000);
        System.out.println("T2 is sleeping");
        lock.lock();
        System.out.println("T2 has awakened and has the lock");

        //Wait for a new line to get pressed
        System.out.println("T2 asks to Press return key!");
        new Scanner(System.in).nextLine();
        System.out.println("T2 Got the Key");

        //Unlocks the lock and T1 can work
        System.out.println("T2 releasing the lock");
        cond.signal();


        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void finished(){

        System.out.println("Count is: "+count);
    }
}
