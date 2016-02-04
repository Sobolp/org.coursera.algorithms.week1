package week2;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
//
//import edu.princeton.cs.algs4.StdIn;
//import edu.princeton.cs.algs4.StdOut;


//import java.util.NoSuchElementException;


/**
 * Created by SoBoLp on 2/4/16.
 */
public class Subset {

    public static void main(String[] args) {

        int k;
        int N = 0;
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
//        if (args.length > 0)
            k = Integer.parseInt(args[0]);
//        else
//            throw new NoSuchElementException();
//        String str = StdIn.readString();
        while (!StdIn.isEmpty() || !StdIn.hasNextChar()) {
            String str = StdIn.readString();
            queue.enqueue(str);
            N++;
        }
//        if (N < k || k < 0)
//            throw new NullPointerException("0 ≤ k ≤ N");
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
