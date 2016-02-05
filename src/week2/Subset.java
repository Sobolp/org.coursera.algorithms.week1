package week2;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;
//
//import edu.princeton.cs.algs4.StdIn;
//import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.StdRandom;

//import java.util.NoSuchElementException;


/**
 * Created by SoBoLp on 2/4/16.
 */
public class Subset {

    public static void main(String[] args) {

        int k;
//        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        Deque<String> deque = new Deque<String>();
        k = Integer.parseInt(args[0]);
        if (k > 0) {
            int count = 0;
            while (!StdIn.isEmpty()) {
                count++;
                String str = StdIn.readString();
                if (count <= k) {
                    if (StdRandom.uniform() == 0)
                        deque.addLast(str);
                    else
                        deque.addFirst(str);
                }
                else if (StdRandom.uniform() == 0) {
                    deque.removeFirst();
                    deque.addFirst(str);
//                } else {
//                    deque.removeLast();
//                    deque.addFirst(str);
                }
                //            queue.enqueue(str);
            }
            for (int i = 0; i < k; i++) {
                if (StdRandom.uniform() == 0)
                    StdOut.println(deque.removeLast());
                else
                    StdOut.println(deque.removeFirst());

            }
        }
    }
}
