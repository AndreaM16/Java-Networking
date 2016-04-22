public class Contenitore {

    private int contenuto;
    //Used to check if a product has been created
    boolean available = false;

    //Synchronized used to share a same object between n threads
    public synchronized void put(int value) {
        while(available==true){
            try{
                wait();
            } catch (InterruptedException e){};
        }
        int contentenuto = value;
        available=true;
        notifyAll();
    }

    public synchronized int get() {

        while(available==false){
            try{
                wait();
            } catch (InterruptedException e){};
        }
        available=false;
        notifyAll();
        return contenuto;
    }

    public static void main(String[] args){

        Contenitore cont = new Contenitore();

        Produttore p = new Produttore();
        Consumatore c = new Consumatore();
        c.Consumer(cont);
        p.Producer(cont);

        p.start();
        c.start();
    }
}


