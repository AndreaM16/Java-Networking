import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

            String day = "LUN";
            Socket clientSocket = null;

            try {

                //INIZIALIZZA CONNESSIONE
                clientSocket = new Socket("localhost", 5000);
                clientSocket.setSoTimeout(30000);

                //SCRIVI AL CLIENT
                OutputStream out = clientSocket.getOutputStream();
                Writer writer = new OutputStreamWriter(out, "UTF-8");
                writer = new BufferedWriter(writer);
                writer.write(day+"\n");
                System.out.println("Sending "+day+" to server");
                writer.flush();

                //PRENDO RISPOSTA DAL CLIENT
                InputStream in = clientSocket.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                //BUILDER PER LA RESPONSE
                StringBuilder response = new StringBuilder();

                while (true){
                    System.out.println("Waiting . . .");

                    //STOP A END STRING
                    int c = reader.read();
                    if (c == '\r' || c == '\n' || c == -1){
                        break;
                    }
                    response.append((char) c);
                }

                System.out.println("Got response from Server: " + response);

            } catch (IOException e){
                System.err.println(e);
            } finally {
                if (clientSocket != null){
                    try {
                        clientSocket.close();
                    } catch (IOException e){
                        //Ignore
                    }
                }
            }
        }
}
