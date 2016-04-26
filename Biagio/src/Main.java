import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //Picking folder's path
        String path = "C:\\Users\\Andrea-Toshiba\\Documents\\Java-Networking\\Biagio\\Biagio.txt";

        ThreadSale sale = new ThreadSale(path);
        sale.start();
        ThreadFaMale male = new ThreadFaMale(path, true);
        male.start();

    }

}
