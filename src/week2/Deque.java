package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by SoBoLp on 2/4/16.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;

        public Node(Node prev, Item item, Node next) {
            if (item == null)
                throw new NullPointerException ();
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * construct an empty deque
     */
    public Deque() {
        size = 0;

    }

    /**
     * is the deque empty?
     *
     * @return
     */
    public boolean isEmpty() {
        return (size() == 0);
    }

    /**
     * return the number of items on the deque
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * add the item to the front
     *
     * @param item
     */
    public void addFirst(Item item) {
        Node oldFirst = first;
        first = new Node(null, item, oldFirst);
        if (oldFirst == null)
            last = first;
        else
            oldFirst.prev = first;
        size++;
    }

    /**
     * add the item to the end
     *
     * @param item
     */
    public void addLast(Item item) {
        Node oldLast = last;
        last = new Node(oldLast, item, null);
        if (oldLast == null)
            first = last;
        else
            oldLast.next = last;
        size++;
    }

    /**
     * remove and return the item from the front
     *
     * @return item
     */
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (first != null)
            first.prev = null;
        else
            last = first;
        size--;
        return item;
    }

    /**
     * remove and return the item from the end
     *
     * @return
     */
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        if (last != null)
            last.next = null;
        else
            first = last;
        size--;
        return item;
    }

    /**
     * return an iterator over items in order from front to end
     *
     * @return
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unsupported the remove() method in the iterator");

        }
    }

    /**
     * unit testing
     *
     * @param args
     */
    public static void main(String[] args) {
        Deque<String> testStr = new Deque<String>();
        testStr.addFirst("1");
        testStr.addFirst("2");
//        testStr.addFirst("3");
//        testStr.removeFirst();
        System.out.println(testStr.removeFirst());
//        testStr.addFirst(null);
//        testStr.removeFirst();
        testStr.addLast("3");
        System.out.println(testStr.removeLast());
        System.out.println(testStr.removeLast());
//        System.out.println(testStr.removeLast());
        System.out.println("size: " + testStr.size());
        String list = "{ ";
        for (String s : testStr) {
            list += s;
            list += " ";
        }
        list += "}";
        System.out.println(list);
    }
}
