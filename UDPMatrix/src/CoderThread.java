import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

public class CoderThread implements Callable{

    String myFile = null;

    public CoderThread(String file){
        myFile = file;
    }

    @Override
    public String call() throws Exception {
        return coder(myFile);
    }

    public String coder(String myFile) throws IOException {

        String line = null;
        BufferedReader dataIn = null;
        dataIn = new BufferedReader(new FileReader(myFile));
        StringBuilder builder = new StringBuilder();

        while ((line = dataIn.readLine())!= null){

            String[] splitted = line.split("\\s+");
            ArrayList<Integer> myList = new ArrayList<>();

            for (int i=0; i<splitted.length; i++){
               int k = Integer.valueOf(splitted[i]);
                myList.add(k);
            }
            int result = mostCommon(myList);
            char c = (char) result;

            builder.append("").append(c).toString();
        }

        dataIn.close();
        System.out.println("Coder Thread made "+String.valueOf(builder));
        return String.valueOf(builder);

    }

    public static <T> T mostCommon(List<T> list) {
        Map<T, Integer> map = new HashMap<>();

        for (T t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        Map.Entry<T, Integer> max = null;

        for (Map.Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }
        return max.getKey();
    }

}