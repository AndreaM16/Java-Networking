/* Connect to time.nist.gov:13
*  Output -> response*/

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {

        String message = "";

        try{
            Socket socket = new Socket("time.nist.gov", 13);
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            String userInput;
            while ((userInput = in.readLine()) != null) {
                System.out.println("echo: " + in.readLine());
            }
        } catch (Exception e){
            return;
        }

        System.out.println("PEzzente");
    }

}
