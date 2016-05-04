import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* IP Info, switch IP to Hostname*/
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        File path = new File("D:\\Programming\\Java\\Java-Networking\\Server");
        File myFile = null;
        ArrayList<Future<String>> hostsList = new ArrayList<Future<String>>();
        ArrayList<Future<String>> otherList = new ArrayList<Future<String>>();
        Lock lock = new ReentrantLock();
        /*String k, j = null;
        ArrayList<String> hostStrings = new ArrayList<String>();
        ArrayList<String> otherStrings = new ArrayList<String>();*/

        for (File file: path.listFiles()){
            if (file.toString().endsWith(".txt")){
                myFile = file;
            }
        }

        ExecutorService hostsExec = Executors.newCachedThreadPool();
        lock.lock();
        try {
            hostsList.add(hostsExec.submit(new HostsThread(myFile)));
        } finally {
            hostsExec.shutdown();
            lock.unlock();
        }

        ExecutorService otherExec = Executors.newCachedThreadPool();
        lock.lock();
        try {
            otherList.add(otherExec.submit(new OtherThread(myFile)));
        } finally {
            otherExec.shutdown();
            lock.unlock();
        }

        /*
        for (int i=0; i<=hostsList.size();i++){
            k= hostsList.get(i).get();
            System.out.print("Getting "+i+" "+k);
            j= otherList.get(i).get();
            System.out.println(", "+j);
            hostStrings.add(k);
            otherStrings.add(j);
        }*/

        ExecutorService replacerExec = Executors.newCachedThreadPool();
        lock.lock();
        try {
            replacerExec.execute(new ReplacerThread(myFile, hostsList, otherList));
        } finally {
            replacerExec.shutdown();
            lock.unlock();
        }
    }
}
