/* *****************************************************************************
 *  Name:              Zhiwen Xie
 *  Coursera User ID:  097065c1102dcb2a999d46201d460c8e
 *  Last modified:     7/27/2020
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf, ufBackwash;
    private final int size;
    private final int top, bottom;
    private int openSitesCount;
    private boolean[][] openFlag;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be greater than 0");
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufBackwash = new WeightedQuickUnionUF(n * n + 2);
        size = n;
        top = n * n;
        bottom = n * n + 1;
        for (int j = 1; j <= size; ++j) {
            uf.union(getId(1, j), top);
            ufBackwash.union(getId(1, j), top);
            ufBackwash.union(getId(size, j), bottom);
        }
        openFlag = new boolean[n][n];
        openSitesCount = 0;
    }

    private boolean inRange(int row, int col) {
        return row > 0 && row <= size && col > 0 && col <= size;
    }

    private void checkInRange(int row, int col) {
        if (!inRange(row, col))
            throw new IllegalArgumentException("row and col argument must in range");
    }

    private int getId(int row, int col) {
        return (row - 1) * size + col - 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkInRange(row, col);
        if (isOpen(row, col)) return;
        openFlag[row - 1][col - 1] = true;
        ++openSitesCount;
        int id = getId(row, col);
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] offset : offsets) {
            int neighborRow, neighborCol;
            neighborRow = row + offset[0];
            neighborCol = col + offset[1];
            if (inRange(neighborRow, neighborCol) && isOpen(neighborRow, neighborCol)) {
                int neighborId = getId(neighborRow, neighborCol);
                uf.union(id, neighborId);
                ufBackwash.union(id, neighborId);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkInRange(row, col);
        return openFlag[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkInRange(row, col);
        return isOpen(row, col) && uf.find(getId(row, col)) == uf.find(top);
    }


    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return numberOfOpenSites() > 0 && ufBackwash.find(top) == ufBackwash.find(bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        // use PercolationVisualizer to test, so skip implementing this method.
    }
}
