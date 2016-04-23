/**
 * Created by Andrea-Toshiba on 23/04/2016.
 */
public class Main {
    public static void main(String[] args){

        int countDown = 10;
        int taskCount = 0;

        LiftOffExecutor executor = new LiftOffExecutor(countDown);
        for (int i=0; i<countDown; i++){
            executor.run();
            System.out.println("Iteraring "+i);
        }
    }
}
