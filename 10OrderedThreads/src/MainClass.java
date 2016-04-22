import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Andrea-Toshiba on 19/04/2016.
 */
public class MainClass {

    public static void main(String args[]) throws InterruptedException {

    ArrayList<MyThread> mylist = new ArrayList<MyThread>();

        for(int i=0; i<=10; i++) {
            //Creating a new thread
            MyThread thread = new MyThread(mylist);
            mylist.add(thread);

            System.out.print("Thread" + i + "\n");
        }

        for(int i=0; i<mylist.size(); i++){
            mylist.get(i).start();

                //Join: if a thread is already in execution, new thread waits for its end before starting
                mylist.get(i).join();
            System.out.print("Starting Thread" + i + "\n");
        }
    }
}
