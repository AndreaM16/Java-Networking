import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Future;

public class Client {

    private static final String HOST = "localhost";
    private static final Integer PORT = 3000;

    public static void main(String[] args){

        File path = new File("D:\\Programming\\Java\\Java-Networking\\TCPIPReader");
        ArrayList<File> files = new ArrayList<>();
        Socket clientSocket = null;

        //BUILDER PER LA RESPONSE
        StringBuilder response = new StringBuilder();

        //ADDING FILES TO FILELIST
        for (File file : path.listFiles()){

            if (file.getName().endsWith(".txt")){
                files.add(file);
                System.out.println("Added "+file+" to file list");
            }
        }

        try {

            for (int i=0; i<files.size(); i++){

                //INIZIALIZZA CONNESSIONE
                clientSocket = new Socket(HOST, PORT);
                clientSocket.setSoTimeout(30000);

                //SCRIVI AL SERVER
                String currentFile = files.get(i).toString();

                OutputStream out = clientSocket.getOutputStream();
                Writer writer = new OutputStreamWriter(out, "UTF-8");
                writer = new BufferedWriter(writer);
                writer.write(currentFile+"\n");
                System.out.println("Sending "+currentFile+" to server");
                writer.flush();

                System.out.println("Client Waiting . . .");

                //PRENDO RISPOSTA DAL SERVER
                InputStream in = clientSocket.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                while (true){
                    System.out.println("Waiting . . .");

                    //STOP A END STRING
                    int c = reader.read();
                    if (c == '\r' || c == '\n' || c == -1){
                        break;
                    }
                    response.append((char) c);
                    System.out.println("\nClient Got response "+(char) c+" from server");
                }
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
