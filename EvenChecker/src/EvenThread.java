public class EvenThread extends Thread {

    int n;
    boolean even = false;

    public EvenThread(Integer n){
        this.n=n;
    }

    @Override
    public void run() {
        isItEven(n);
        System.out.println(n+" Even? "+even+"\n");
    }

    public boolean isItEven(Integer n){

        if (n%2==0){
            return even = true;
        }
        else {
            return even = false;
        }
    }
}
