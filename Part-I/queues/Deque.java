/* *****************************************************************************
 *  Name: Zhiwen Xie
 *  Date: 2020-08-04
 *  Description: a linked list implementation of Deque
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        N = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the deque
    public int size() {
       return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("item shouldn't be null");
        Node oldFirst = first;
        first = new Node();
        first.setItem(item);
        first.setNext(oldFirst);
        first.setPrev(null);
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.setPrev(first);
        }
        ++N;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("item shouldn't be null");
        Node oldLast = last;
        last = new Node();
        last.setItem(item);
        last.setNext(null);
        last.setPrev(oldLast);
        if (N == 0) {
            first = last;
        } else {
            oldLast.setNext(last);
        }
        ++N;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is already empty!");
        --N;
        Node oldFirst = first;
        first = oldFirst.getNext();
        oldFirst.setNext(null);
        if (N == 0) {
            first = last;
        } else {
            first.setPrev(null);
        }
        return oldFirst.getItem();
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is already empty!");
        --N;
        Node oldLast = last;
        last = last.getPrev();
        if (N == 0) {
            first = last;
        } else {
            last.setNext(null);
        }
        oldLast.setPrev(null);
        return oldLast.getItem();
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException("remove is not supported");
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("done!");
            Item item = current.getItem();
            current = current.getNext();
            return item;
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
        Deque<Integer> q = new Deque<>();
        assert q.isEmpty();
        q.addFirst(1);
        q.addFirst(2);
        q.addFirst(3);
        q.addLast(4);
        q.addLast(5);
        q.addFirst(6);
        // should be 6 3 2 1 4 5 after above operation
        assert q.size() == 6;
        q.print();

        int x = q.removeFirst();
        // should be 3 2 1 4 5
        assert q.size() == 5;
        assert x == 6;
        q.print();

        x = q.removeLast();
        // should be 3 2 1 4
        assert q.size() == 4;
        assert x == 5;
        q.print();

        // remove all remain elements
        for (int i = 0; i < 4; ++i) {
            q.removeLast();
        }
        assert q.isEmpty();
        q.print();
    }

}