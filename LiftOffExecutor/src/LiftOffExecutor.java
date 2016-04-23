public  class LiftOffExecutor implements Runnable{

    public int countDown;

    public LiftOffExecutor(int countDown){
        this.countDown = countDown;
    }

    @Override
    public void run() {
        while (countDown != 0){
            System.out.println("CountDown: "+countDown);
            countDown--;
        }
    }
}
