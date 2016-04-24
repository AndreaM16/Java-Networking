import java.util.Scanner;

public class Synchronizer {

    public void firstThread() throws InterruptedException{
        synchronized (this){
            System.out.println("Producer thread is running");
            System.out.println("Producer Waiting for Consumer to consume . . .");
            //wait can only be used inside synchronized and waits for a notify
            wait();
            System.out.println("Producer has resumed");
        }
    }

    public void secondThread() throws InterruptedException{

        Scanner scanner = new Scanner(System.in);
        //Sleeping to make firstThread running first
        System.out.println("Consumer thread is sleeping . . .");
        Thread.sleep(2000);

        synchronized (this){
            System.out.println("Consumer Waiting for return key to be pressed . . .");
            scanner.nextLine();
            System.out.println("Consumer got the key pressed . . .");
            System.out.println("Consumer unlocks . . .");
            //notifying the waiting thread that it can resume
            notify();
            Thread.sleep(2000);
        }

    }
}
