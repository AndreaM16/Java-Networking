import java.io.*;
import java.net.URL;
import java.util.stream.Stream;

public class SaveTextToFile {

    public static void main(String[] args) throws IOException {

        // Creating a new File into project's folder
        File path = new File("hello.txt");

        System.out.println("We got a file:" + path);
        System.out.println("Does it Exist?" + path.exists());
        System.out.println("Which Path?" + path.isDirectory());

        /* What to write in the file */
        String whatToWrite = "Hello World!";

        OutputStream outStream = new FileOutputStream(path);

        // Encodes this String into a sequence of bytes using the named charset, storing the result into a new byte array.
        outStream.write(whatToWrite.getBytes());
        outStream.close();


        /* Reading first line from the file */
        BufferedReader fileReader = new BufferedReader( new InputStreamReader( new FileInputStream(path)));

        String firstLine = fileReader.readLine();
        fileReader.close();
        System.out.println("First Line:"+ firstLine);


        /* Read from an URL */
        URL url = new URL("http://www.gutenberg.org/cache/epub/2265/pg2265.txt");
        InputStream stream = url.openStream();
        BufferedReader reader = new BufferedReader( new InputStreamReader(stream));

        String line = reader.readLine();
        while (line != null){

            System.out.println(line);
            line = reader.readLine();
        }
    }
}
