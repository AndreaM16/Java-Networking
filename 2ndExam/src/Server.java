/*
* Implementare in Java il seguente protocollo:
*
* Client: chiede al server le condizioni metereologiche relative ad un determinato giorno della settimana.
* La richiesta è composta da una stringa che identifica il giorno della settimana: LUN, .., DOM,
* seguita da un ritorno a capo ("/n"). Dopo aver effettuato la richiesta il client legge la risposta inviata
* dal server e la stampa su schermo.
*
*
* Server: a partire dai dati contenuti in un file (meteoData.txt) e dal giorno specificato dal client
* il server invia le relative condizioni metereologiche.
*
* Usare TCP: Socket
* */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

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

        File file = new File("D:\\Programming\\Java\\Java-Networking\\2ndExam\\meteoData.txt");

        try(ServerSocket welcomeSocket = new ServerSocket(5000)){
            while (true){
                System.out.println("Waiting . . .");
                try(Socket connectionSocket = welcomeSocket.accept()){

                    Reader inFromClient = new InputStreamReader(connectionSocket.getInputStream(), "UTF-8");
                    Writer out = new OutputStreamWriter(connectionSocket.getOutputStream(),"UTF-8");

                    StringBuilder clientSentence = new StringBuilder();
                    while (true) {
                        int c = inFromClient.read();
                        if (c == '\r' || c == '\n' || c == -1) {
                            break;
                        }
                        clientSentence.append((char) c);
                    }

                    System.out.println("Received: " + clientSentence);

                    ExecutorService exec = Executors.newFixedThreadPool(50);
                        res = exec.submit(new ServerThread(file, clientSentence.toString()));
                    exec.shutdown();

                    out.write((res.get())+"\n");
                    out.flush();
                    System.out.println("Sending data to Client");
                }
            }
        }
    }
}
