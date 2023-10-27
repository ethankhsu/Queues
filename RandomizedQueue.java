import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // size of the queue
    private int size;
    // item array for where I am storing my randomized queue
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        // from resizing array stack in the booksite
        size = 0;
        queue = (Item[]) new Object[8];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        // corner case
        if (item == null)
            throw new IllegalArgumentException("Null can not be enqueued");
        if (size == queue.length)
            resize(2 * queue.length);
        queue[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is Empty");
        int index = StdRandom.uniformInt(size);
        // swaps last position with random item so there is a null space at
        // the end.
        Item ret = queue[index];
        queue[index] = queue[size - 1];
        // decrement put before as mentioned in lecture
        queue[--size] = null;
        // shrink size of array if size is too little
        if (size > 0 && size == queue.length / 4)
            resize(queue.length / 2);
        return ret;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        // corner case
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is Empty");
        }
        int index = StdRandom.uniformInt(size);
        return queue[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    //  based off of lecture slides, reverse array iterator
    private class RandomizedIterator implements Iterator<Item> {

        // size of my iterator, need to edit this variable so I can't use size
        // instance variable
        private int i = size;
        // storing my random order
        private int[] newOrder;

        // constructor for the Randomized Iterator
        public RandomizedIterator() {
            newOrder = new int[i];
            for (int j = 0; j < i; j++) {
                newOrder[j] = j;
            }
            StdRandom.shuffle(newOrder);
        }

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Deque is Empty");
            }
            return queue[newOrder[--i]];
        }

    }

    // resizing method to resize arrays as discussed in the lecture
    private void resize(int newSize) {
        Item[] temp = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
        StdOut.println("Is the Queue empty (Should be empty): "
                               + test.isEmpty());
        test.enqueue(1);
        StdOut.println("Size: " + test.size());
        test.enqueue(3);
        StdOut.println("Size: " + test.size());
        test.enqueue(5);
        StdOut.println("Size: " + test.size());

        for (int i : test) {
            StdOut.print(i + " ");
        }

        test.dequeue();
        test.dequeue();
        test.dequeue();
        StdOut.println("");
        for (int i = 0; i < 10; i++) {
            test.enqueue(i);
        }

        StdOut.println("Print a random value: " + test.sample());
        StdOut.println("Print a random value: " + test.sample());
        StdOut.println("Print a random value: " + test.sample());


    }

}
