import java.io.IOException;

import static java.lang.System.in;

public class myBytes {

    public static void main(String[] args) throws IOException {

        int bytesRead = 0;
        int bytesToRead = 1024;

        byte[] input = new byte[bytesToRead];

        while (bytesRead < bytesToRead){

            int result = in.read(input, bytesRead, bytesToRead - bytesRead);
            if (result == -1) break;
            System.out.println("N"+ bytesRead);
            bytesRead += result;
        }
    }
}
