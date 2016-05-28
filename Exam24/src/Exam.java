import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Exam {

    private static int COLUMNS = 0;
    private static int ROWS = 0;
    private static Character[][] matrix;
    private static boolean isInitialized = false;

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {


        final File file = new File("D:\\Programming\\Java\\Java-Networking\\Exam24\\matrice.txt");

        System.out.println("Reading Matrix\n");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int rowCount = 0;
            while ((line = br.readLine()) != null) {

                if (ROWS != 0 && COLUMNS != 0 && isInitialized == false) {
                    matrix = new Character[ROWS][COLUMNS];
                    isInitialized = true;
                }

                switch (rowCount) {
                    case 0: {
                        ROWS = Integer.parseInt(line);
                        rowCount++;
                        break;
                    }
                    case 1: {
                        COLUMNS = Integer.parseInt(line);
                        rowCount++;

                        break;
                    }
                    default: {
                        for (int i = 0; i < COLUMNS; i++) {
                            matrix[rowCount - 2][i] = line.charAt(i);
                        }
                        rowCount++;
                        break;
                    }
                }
            }

            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println("");
            }

            br.close();
        }


        ExecutorService service = Executors.newFixedThreadPool(50);
        ArrayList<Future<Character>> resultsRow = new ArrayList<>();
        ArrayList<Future<Character>> resultsColumn = new ArrayList<>();

        for (int i = 0; i < ROWS; i++) {
            resultsRow.add(service.submit(new CheckRow(matrix, i)));
        }

        for (int i = 0; i < COLUMNS; i++) {
            resultsColumn.add(service.submit(new CheckColumn(matrix, i)));
        }
        service.shutdown();
        System.out.println("\n");
        System.out.println("From rows got");
        //RISULTATI
        for (Future<Character> result : resultsRow) {
            System.out.print(result.get());
        }
        System.out.println("\n");

        System.out.println("From columns got");
        for (Future<Character> result : resultsColumn) {
            System.out.print(result.get());
        }


    }


}
