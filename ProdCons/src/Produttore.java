public class Produttore extends Thread {

    private Contenitore contenitore;

    public Contenitore Producer(Contenitore c){
        contenitore = c;
        return contenitore;
    }

    public void run(){
        for (int i=0; i<10; i++){
            contenitore.put(i);
            System.out.println("Metti la cera");

            try {
                sleep((int)(Math.random()*10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
