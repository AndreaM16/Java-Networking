public class Consumatore extends Thread {

    private Contenitore contenitore;

    public Contenitore Consumer(Contenitore c){
        contenitore = c;
        return contenitore;
    }

    public void run(){
        for (int i=0; i<10; i++){
            contenitore.get();
            System.out.println("Togli la cera");

            try {
                sleep((int)(Math.random()*10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
