/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<>();
        while (true) {
            try {
                String s = StdIn.readString();
                q.enqueue(s);
            } catch (NoSuchElementException e) {
                break;
            }
        }
        for (int i = 0; i < k; ++i) {
            StdOut.println(q.dequeue());
        }
    }
}
