/* *****************************************************************************
 *  Name: Zhiwen Xie
 *  Date: 2020-08-04
 *  Description: remove item randomly
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;

    // construct an empty randomized queue
    public RandomizedQueue() {
        N = 0;
        a = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; ++i)
            temp[i] = a[i];
        a = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("item shouldn't be null");
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("queue is already empty!");
        int idx = StdRandom.uniform(0, N);
        Item item = a[idx];
        a[idx] = a[N - 1];
        a[N - 1] = null;
        --N;
        if (N > 0 && N == a.length / 4) resize(a.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("queue is already empty!");
        int idx = StdRandom.uniform(0, N);
        return a[idx];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }
    private class RandomIterator implements Iterator<Item> {
        private RandomizedQueue<Item> q;

        public RandomIterator() {
            q = new RandomizedQueue<>();
            for (int i = 0; i < N; ++i)
                q.enqueue(a[i]);
        }
        public boolean hasNext() {
            return !q.isEmpty();
        }
        public void remove() {
            throw new UnsupportedOperationException("remove is not supported");
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("done!");
            return q.dequeue();
        }
    }

    private void print() {
        for (Item item : this) {
            StdOut.print(item + " ");
        }
        StdOut.println();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        assert q.isEmpty();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);
        // should be 1 2 3 4 5 6 after above operation
        assert q.size() == 6;
        q.print();

        int x = q.sample();
        StdOut.println(x + " is sampled");
        assert q.size() == 6;

        // remove all remain elements
        for (int i = 0; i < 6; ++i) {
            assert q.size() == 6 - i;
            x = q.dequeue();
            StdOut.println(x + " is removed");
            q.print();
        }
        assert q.isEmpty();
    }
}
