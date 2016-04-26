import java.io.*;

public class DifferetTypesFileWriteAndRead {

    public static void main(String [] args) throws IOException {

        double[] prezzi={15.0,30.0,50.0};
        int [] unita={4,10,2};
        String [] descr={"maglietta","jeans","cravatta"};
    // The name of the file to open.

    try {
        // Assume default encoding.
        DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(new File("test.txt")));


                       // Always wrap FileWriter in BufferedWriter

        // Note that write() does not automatically
        // append a newline character.
        for(int i=0;i<3;i++)
        {

            dataOut.writeUTF(descr[i]);
            dataOut.writeInt(unita[i]);
            dataOut.writeDouble(prezzi[i]);
        // Always close files.

        }
        dataOut.close();
    }
    catch(IOException ex) {
        System.out.println(
                "Error writing to file");
        // Or we could just do this:
        // ex.printStackTrace();
    }
        DataInputStream dataIn = null;
        try {
            dataIn = new DataInputStream(new FileInputStream("test.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(dataIn.available()>0) {


                String k = dataIn.readUTF();
                int inte=dataIn.readInt();
                double doubl= dataIn.readDouble();
                System.out.print(k + " ");
                System.out.print("prezzo: "+doubl+" ");
                System.out.print("quantita: "+inte);
                System.out.print("\n");
            }
        }




}

