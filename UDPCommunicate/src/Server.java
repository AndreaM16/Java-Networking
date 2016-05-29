import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.*;

public class Server {

    public final static int PORT = 3000;
    public final static String host = "localhost";
    public final static int MAX_PACKET_SIZE = 65507;

    public static void main(String[] args){

        //Preparing Reader
        byte[] buffer = new byte[MAX_PACKET_SIZE];
        Future<String> res = new Future<String>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public String get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
        String threadResponse = null;

        try(DatagramSocket serverSocket = new DatagramSocket(PORT)){

            //Receiver
            DatagramPacket receiverPacket = new DatagramPacket(buffer, buffer.length);

            try {

                while (true){
                    
                    System.out.println("Server waiting . . .");
                    serverSocket.receive(receiverPacket);
                    String response = new String(receiverPacket.getData(), 0, receiverPacket.getLength());
                    System.out.println("Got "+response+" from client");

                    ExecutorService exec = Executors.newFixedThreadPool(50);
                        res = exec.submit(new MyThread(response));
                    exec.shutdown();

                    threadResponse = (String) res.get();
                    System.out.println("Got sentence "+threadResponse);

                    //Preparing sender
                    byte[] resp = threadResponse.getBytes();
                    DatagramPacket sender = new DatagramPacket(resp, resp.length, receiverPacket.getAddress(), receiverPacket.getPort());

                    serverSocket.send(sender);
                    System.out.println("Sending "+new String(sender.getData(),0, sender.getLength())+" to client");

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
