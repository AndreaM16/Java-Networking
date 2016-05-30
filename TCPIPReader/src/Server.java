import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Server {

    private static final int PORT = 3000;

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        ArrayList<Future<Character>> res = new ArrayList<>();
        int i = 0;

        try (ServerSocket welcomeSocket = new ServerSocket(PORT)) {

            while (res.size() < 11) {
                System.out.println("Server Waiting . . .");
                try (Socket connectionSocket = welcomeSocket.accept()) {

                    Reader inFromClient = new InputStreamReader(connectionSocket.getInputStream(), "UTF-8");
                    Writer out = new OutputStreamWriter(connectionSocket.getOutputStream(), "UTF-8");

                    StringBuilder clientSentence = new StringBuilder();

                    while (true) {
                        int c = inFromClient.read();
                        if (c == '\r' || c == '\n' || c == -1) {
                            break;
                        }
                        clientSentence.append((char) c);
                    }

                    System.out.println("Received: " + clientSentence);

                    String fileToSend = clientSentence.toString();
                    ExecutorService exec = Executors.newFixedThreadPool(50);
                    res.add(exec.submit(new IpReaderThread(fileToSend)));
                    exec.shutdown();

                    System.out.println("Sending " + res.get(i).get() + " to Client");
                    out.write(res.get(i).get() + "\n");
                    out.flush();
                    i++;
                }
            }
        }
    }
}