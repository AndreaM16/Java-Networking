import java.lang.*;
import java.lang.Thread;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class Server {

    public final static int PORT = 3000;
    public final static int MAX_PACKET_SIZE = 65507;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Future res = new Future<String>() {
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
        byte[] buffer = new byte[MAX_PACKET_SIZE];
        File file = new File("D:\\Programming\\Java\\Java-Networking\\UDP\\meteoData.txt");

        try (DatagramSocket server = new DatagramSocket(PORT)) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (true) {
                try {
                    System.out.println("Waiting . . .");
                    server.receive(packet);

                    String day = new String( packet.getData(), 0, packet.getLength());
                    System.out.println("Got "+day+" from Client");

                    ExecutorService exec = Executors.newFixedThreadPool(50);
                        res = exec.submit(new MyThread(file, day));
                    exec.shutdown();

                    String response = (String) res.get();

                    System.out.println("Got response "+response+" from Thread");

                    byte[] resp = response.getBytes();

                    DatagramPacket sender = new DatagramPacket(resp, resp.length, packet.getAddress(), packet.getPort());
                    server.send(sender);

                    System.out.println("Sending "+new String( sender.getData(), 0, sender.getLength())+" to client");

                } catch (IOException ex) {
                    System.err.println(ex);
                }
            } // end while
        } catch (SocketException  ex) {
            System.err.println(ex);
        }
    }
}