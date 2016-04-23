
public class SimpleThread extends Thread{

    private int countDown = 5;
    private static int threadCount = 0;

    public SimpleThread(){
        threadCount++;
        start();

    }

    public void run(){
        while (true){
            System.out.println(this);
            if(--countDown == 0){
                return;
            }
        }
    }

    public String toString(){
        return "#"+getId()+"("+countDown+")\n";
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++)
            new SimpleThread();
    }
}

