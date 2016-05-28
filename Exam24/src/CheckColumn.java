import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class CheckColumn implements Callable<Character> {


    private Character[][] matrix;
    private int column;
    final int ROWS = 10;
    final int COLS = 9;

    public CheckColumn(Character[][] matrix, int column) {
        this.matrix= matrix;
        this.column = column;
    }

    @Override
    public Character call() throws Exception {

        HashMap<Character, Integer> hash = new HashMap();

        for (int i = 0; i<ROWS; i++ ) {
            if ( hash.containsKey(matrix[i][column]) == false ) {
                hash.put(matrix[i][column],1);
            } else {
                hash.put(matrix[i][column], hash.get(matrix[i][column])+1);
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
