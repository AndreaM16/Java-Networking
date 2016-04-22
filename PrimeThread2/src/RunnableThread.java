public class RunnableThread implements Runnable{

    long minPrime;

    RunnableThread(long minPrime){
        this.minPrime = minPrime;
    }

    public void run(){
        while (minPrime < 211){
            if((minPrime%2 == 0 || minPrime%3 ==0 || minPrime%5 == 0 || minPrime%7 == 0)){
                System.out.println("This is not a prime number: " + minPrime);
                minPrime += 1;
                if (minPrime == 211){
                    break;
                }
            }
            else{
                System.out.println("This is a prime number: " + minPrime + "!!!");
                minPrime += 1;
            }
        }
    }

    public static void main(String[] arg){

        RunnableThread p = new RunnableThread(11);
        new Thread(p).start();

    }

}
