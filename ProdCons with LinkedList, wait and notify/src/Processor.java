import java.util.LinkedList;
import java.util.Random;

public class Processor {

    // List containing max 10 integers, ordered. We are going to delete the last one and add new elements in first pos
    private LinkedList<Integer> list = new LinkedList<Integer>();
    // Limiting list size
    final int limit = 10;
    // New Lock Object used for priorities
    private Object lock = new Object();

    public void producer() throws InterruptedException{

        //init value
        int value = 0;

        //adding values
        while (true){
            //sync block around lock obj
            synchronized (lock){
                //if we already have 10 items, we wait for one to be deleted before inserting a new one
                while (list.size()==10){
                    lock.wait();
                }
                //Inserting at the end of the list and then value++
                list.add(value++);
                //notify the consumer
                lock.notify();
            }
        }
    }

    public void consumer() throws InterruptedException{

        Random random = new Random();

        while (true){
            synchronized (lock){
                //If we have 0 items in the list, we wait for the producer
                while (list.size()==0){
                    lock.wait();
                }
                System.out.print("List size is: "+list.size()+", list made by: "+list);
                //We remove the first
                int value = list.removeFirst();
                System.out.println("; Removing: "+value);
                //We notify the producer
                lock.notify();
            }

            //Randomly sleeps before producer's turn
            Thread.sleep(random.nextInt(1000));
        }
    }

}
