import java.io.IOException;
import java.net.*;

public class Client {

    public final static int PORT = 3000;
    public final static String host = "localhost";
    public final static int MAX_PACKET_SIZE = 65507;

    public static void main(String[] args){

        String file = "D:\\Programming\\Java\\Java-Networking\\UDPCommunicate\\myFile.txt";

        try(DatagramSocket clientSocket = new DatagramSocket()){
            InetAddress server = InetAddress.getByName(host);

            try{
               byte[] data = file.getBytes();
                //Preparing Sender and Receiver Datagram Packets
                DatagramPacket sender = new DatagramPacket(data, data.length, server, PORT);
                DatagramPacket receiver = new DatagramPacket(new byte[MAX_PACKET_SIZE], MAX_PACKET_SIZE);

                //Sending Data to Server
                clientSocket.send(sender);
                System.out.println("Client sending data to Server");

                System.out.println("Client waiting for response . . .");
                //Receiving Data From Server
                clientSocket.receive(receiver);

                //Formatting data from bytes to string
                String response = new String(receiver.getData(), 0, receiver.getLength());
                System.out.println("Got Response "+response);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}