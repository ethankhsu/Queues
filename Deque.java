import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // size of deque
    private int size;
    // pointer at front of deque
    private Node front;
    // pointer at back of deque
    private Node back;

    // Node class for doubly linked link implementation
    private class Node {
        // the value of the Node
        private Item item;
        // pointer pointing to the next node from this one
        private Node next;
        // pointer pointing to the node before this one
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add a null item");
        Node fresh = new Node();
        fresh.item = item;
        if (isEmpty())
            back = fresh;
        else
            front.prev = fresh;
        size++;
        fresh.next = front;
        front = fresh;

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add a null item");
        Node fresh = new Node();
        fresh.item = item;
        if (isEmpty())
            front = fresh;
        else
            back.next = fresh;
        size++;
        fresh.prev = back;
        back = fresh;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is Empty");
        Item ret = front.item;
        if (size == 1) {
            front = null;
            back = null;
        }
        else {
            front = front.next;
            front.prev = null;
        }
        size--;
        return ret;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is Empty");
        Item ret = back.item;
        if (size == 1) {
            front = null;
            back = null;
        }
        else {
            back = back.prev;
            back.next = null;
        }
        size--;
        return ret;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    // FROM THE LECTURE SLIDES
    private class LinkedIterator implements Iterator<Item> {
        // setting the current node to the front of the linked list
        private Node current = front;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("Deque is empty");

            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> test = new Deque<Integer>();
        test.addFirst(1);
        test.addFirst(3);
        test.addFirst(5);
        StdOut.println("");
        StdOut.println("Is the Deque empty (False): " + test.isEmpty());
        for (int i : test) {
            StdOut.print(i + " ");
        }
        StdOut.println("Should print 5 3 1");
        StdOut.println("Size of deque should be 3: " + test.size());
        StdOut.println("Remove the first (should be 5): "
                               + test.removeFirst());
        StdOut.println("Remove the last (should be 1): " + test.removeLast());
        StdOut.println("Remove the last (should be 3): " + test.removeLast());
        StdOut.println("Is Deque empty? (should be true): " + test.isEmpty());
        test.addLast(1);
        test.addLast(3);
        test.addLast(5);
        for (int i : test) {
            StdOut.print(i + " ");
        }

        StdOut.println("");
        StdOut.println("");
        StdOut.println("");
        Deque<Integer> deque = new Deque<>();
        StdOut.println(deque.size());
        deque.addFirst(3);
        StdOut.println(deque.removeLast());
        StdOut.println(deque.size());
        deque.addFirst(7);
        StdOut.println(deque.isEmpty());
        deque.addFirst(9);
        StdOut.println(deque.removeLast());
        for (int i : deque) {
            StdOut.print(i + " ");
        }
    }

}
