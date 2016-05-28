import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;


public class CheckRow implements Callable<Character> {


    private Character[][] matrix;
    private int row;
    final int ROWS = 10;
    final int COLS = 9;

    public CheckRow(Character[][] matrix, int row) {
        this.matrix= matrix;
        this.row = row;
    }

    @Override
    public Character call() throws Exception {

        HashMap<Character, Integer> hash = new HashMap();

        for (int i = 0; i<COLS; i++ ) {
            if ( hash.containsKey(matrix[row][i]) == false ) {
                hash.put(matrix[row][i],1);
                //  System.out.println("Not found! added Char = " + matrix[row][i]);
            } else {
                hash.put(matrix[row][i], hash.get(matrix[row][i])+1);
                //  System.out.println("Found! Char = " + matrix[row][i] + " - Value = " + hash.get(matrix[row][i]) );
            }
        }

        return calcResults(hash);
    }

    private synchronized Character calcResults(HashMap<Character, Integer> hash) {
        Map.Entry<Character,Integer> temp = null;
        Character result = null;

        for (Map.Entry<Character, Integer> entry : hash.entrySet()) {
            if (temp != null) {
                if (entry.getValue() > temp.getValue()) {
                    result = entry.getKey();
                    temp = entry;
                }
            } else {
                temp = entry;
                result = entry.getKey();
            }
        }
        return result;
    }
}
