import java.io.*;

public class PrinterThread implements Runnable {


    String myFile = null;
    Integer row, col = 0;

    public PrinterThread(String file, Integer[] rowsColumns){
        myFile = file;
        row=rowsColumns[0];
        col=rowsColumns[1];
    }

    @Override
    public void run() {
        try {
            printer(myFile, row, col);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printer(String file, Integer row, Integer col) throws IOException {

        String line = null;
        BufferedReader dataIn = null;
        dataIn = new BufferedReader(new FileReader(file));
        String[][] matrix = new String[row][col];
        int i = 0;

        System.out.println("\nMatrix\n");

        while ((line = dataIn.readLine())!= null){
            String[] splitted = line.split("\\s+");
            for (int j=0; j<splitted.length; j++){
                matrix[i][j] = splitted[j];
            }
            i++;
        }

        for (int k=0; k<matrix.length; k++){
            for (int j=0; j<matrix[0].length; j++){
                System.out.print(matrix[k][j]+" ");
            }
            System.out.println("\r");
        }

        System.out.println("\n");
        dataIn.close();
    }
}
