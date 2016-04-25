import java.util.Random;

public class Philosopher implements Runnable{

    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor;
    private Random rand = new Random();


    private void pause() throws InterruptedException {
        if(ponderFactor == 0){
            return;
        } else {
            Thread.sleep(rand.nextInt(5000));
        }
    }

    public Philosopher(Chopstick left, Chopstick right, int ident, int ponder){
        this.id = ident;
        this.ponderFactor = ponder;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                System.out.println(this+" thinking");
                pause();
                System.out.println(this+" Becomes hungry and grabs right chopstick");
                right.take();
                System.out.println(this+" Becomes hungry and grabs left chopstick");
                left.take();
                System.out.println(this+" Eating");
                pause();
                right.drop();
                left.drop();
            }
        } catch (InterruptedException e) {
            System.out.println(this+" Exiting via interrupt");
        }
    }

    public String toString(){
        return "Philosoper "+id;
    }
}
