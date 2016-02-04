package week2;

import edu.princeton.cs.introcs.StdRandom;
//
//import edu.princeton.cs.algs4.StdRandom;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by SoBoLp on 2/4/16.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int N = 0;

    /**
     * construct an empty randomized queue
     */
    public RandomizedQueue() {
        array = (Item[]) new Object[2];
    }

    /**
     * is the queue empty?
     *
     * @return
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * return the number of items on the queue
     *
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * add the item
     *
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        if (N == array.length)
            resize(2 * array.length);
        array[N++] = item;
    }

    /**
     * remove and return a random item
     *
     * @return
     */
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
//        StdRandom.shuffle(array,0,N-1);
        int index = getRendIndx(N);
//        int index = N-1;
        Item result = array[index];
        array[index] = array[--N];
        array[N] = null;
        if (N > 0 && N == array.length / 4)
            resize(array.length / 2);
        return result;
    }

    /**
     * return (but do not remove) a random item
     *
     * @return
     */
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
//        StdRandom.shuffle(array,0,N-1);
        int index = getRendIndx(N);
//        int index = N-1;
        return array[index];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
//        StdRandom.shuffle(array);
        for (int i = 0; i < N; i++)
            copy[i] = array[i];
        array = copy;
    }

    private int getRendIndx(int n) {
        return StdRandom.uniform(n);
    }

    /**
     * return an independent iterator over items in random order
     *
     * @return
     */
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            /* not supported */
            throw new UnsupportedOperationException("Unsupported the remove() method in the iterator");
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return array[--i];
        }
    }

    /**
     * unit testing
     *
     * @param args
     */
    public static void main(String[] args) {
        RandomizedQueue<String> testArr = new RandomizedQueue<String>();

        testArr.enqueue("1");

        testArr.enqueue("2");
        testArr.enqueue("3");
        testArr.enqueue("4");
        testArr.enqueue("5");
        testArr.enqueue("6");
        testArr.enqueue("7");
//        System.out.println("sam: " + testArr.sample());
        System.out.println("sam: " + testArr.sample());
//
        System.out.println(testArr.dequeue());
        System.out.println(testArr.dequeue());
//        System.out.println(testArr.dequeue());
//        System.out.println(testArr.dequeue());
//        testArr.enqueue(null);
        System.out.println("size: " + testArr.size());
        String list = "{ ";
        for (String s : testArr) {
            list += s;
            list += " ";
        }
        list += "}";
        System.out.println(list);


    }
}
