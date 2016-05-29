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
        Future<Integer[]> rowColumns = new Future<Integer[]>() {
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
            public Integer[] get() throws InterruptedException, ExecutionException {
                return new Integer[0];
            }

            @Override
            public Integer[] get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return new Integer[0];
            }
        };
        Integer[] rowCols = null;
        Future<String> sentence = new Future<String>() {
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
        String response = null;

        try(DatagramSocket serverSocket = new DatagramSocket(PORT)){

            //Receiver
            DatagramPacket receiverPacket = new DatagramPacket(buffer, buffer.length);

            try {

                while (true){

                    System.out.println("Server waiting . . .");
                    serverSocket.receive(receiverPacket);
                    String file = new String(receiverPacket.getData(), 0, receiverPacket.getLength());
                    System.out.println("Got "+file+" from client");

                    ExecutorService rcExec = Executors.newFixedThreadPool(50);
                        rowColumns = rcExec.submit(new RowColumnsCounterThread(file));
                    rcExec.shutdown();

                    rowCols = rowColumns.get();
                    System.out.println("Got "+rowCols[0]+" rows and "+rowCols[1]+" columns from RowColumnsCounterThread");

                    ExecutorService printer = Executors.newFixedThreadPool(50);
                        printer.execute(new PrinterThread(file, rowCols));
                    printer.shutdown();

                    ExecutorService coderExec = Executors.newFixedThreadPool(50);
                        sentence = coderExec.submit(new CoderThread(file));
                    coderExec.shutdown();

                    response = sentence.get();
                    System.out.println("Got "+response+" from CoderThread");

                    //Preparing sender
                    byte[] resp = response.getBytes();
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