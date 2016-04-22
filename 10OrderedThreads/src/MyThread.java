import java.util.ArrayList;

/**
 * Ten threads that make a countdown
 */


// MyThread will be used to refer to Thread type
public class MyThread extends Thread{

    //Doing a mythread list
    private ArrayList<MyThread> mylist;

    //Constructor
    public MyThread(ArrayList<MyThread> mylist) {
        this.mylist = mylist;
    }

        //Every Thread has to be run
        @Override
        public void run (){
            for (int i = 0; i<=mylist.size(); i++) {
                //Printing thread's index
                System.out.print(i);
            }
        }
}
