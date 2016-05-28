import java.net.*;
import java.io.*;
import java.util.Random;

public class Client {

    public final static int PORT = 3000;
    public final static String hostname = "localhost";
    public final static int MAX_PACKET_SIZE = 65507;

    public static void main(String[] args) {

        String[] days = {"LUN", "MAR", "MER", "GIO", "VEN", "SAB", "DOM"};
        int idx = new Random().nextInt(days.length);
        String day = (days[idx]);

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress server = InetAddress.getByName(hostname);

            try {
                byte[] data = day.getBytes();
                DatagramPacket theOutput = new DatagramPacket(data, data.length, server, PORT);
                DatagramPacket receiver = new DatagramPacket(new byte[MAX_PACKET_SIZE], MAX_PACKET_SIZE);

                System.out.println("Sending "+day+" to server");
                clientSocket.send(theOutput);
                clientSocket.receive(receiver);

                String response = new String(receiver.getData(), 0, receiver.getLength());
                System.out.println(day+" "+response);
            } catch (IOException ex){
                System.out.println(ex);
            }// end while

        } catch (IOException ex) {
            System.err.println(ex);
        }



    }
}