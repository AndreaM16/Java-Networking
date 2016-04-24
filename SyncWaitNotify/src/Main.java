public class Main {

    public static void main(String[] args){

        final Synchronizer sync = new Synchronizer();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sync.firstThread();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sync.secondThread();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
