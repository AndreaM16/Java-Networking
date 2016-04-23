public class Sleeper extends Thread{

    private int duration = 0;

    public Sleeper(String name, int sleepTime){
        super(name);
        duration = sleepTime;
        start();
    }

    public void run(){
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println("Thread "+getName()+" has awakened!");
            return;
        }
        System.out.println("Thread "+getName()+" has awakened!");
    }

}
