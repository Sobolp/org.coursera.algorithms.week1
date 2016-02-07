package week2;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;
//
//import edu.princeton.cs.algs4.StdIn;
//import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.StdRandom;
//
//import java.util.NoSuchElementException;


/**
 * Created by SoBoLp on 2/4/16.
 */
public class Subset {

    public static void main(String[] args) {

        int k;
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        k = Integer.parseInt(args[0]);
        if (k > 0) {
            int count = 0;
            while (!StdIn.isEmpty()) {
                count++;
                String str = StdIn.readString();
                if (count <= k) {
                    queue.enqueue(str);
                } else if (StdRandom.uniform(count) < k) {
                    queue.dequeue();
                    queue.enqueue(str);
                }
            }
            for (int i = 0; i < k; i++)
                StdOut.println(queue.dequeue());
        }
    }
}
