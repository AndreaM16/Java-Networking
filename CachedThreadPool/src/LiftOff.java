
public class LiftOff extends Thread {

    int countDown;

    public LiftOff(){}

    public LiftOff(int countDown) {

        this.countDown = countDown;
    }

    public synchronized void print(){
       System.out.println("LiftOff");

    }

    @Override
    public void run() {

        print();
    }
}
