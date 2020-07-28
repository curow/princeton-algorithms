/* *****************************************************************************
 *  Name:              Zhiwen Xie
 *  Coursera User ID:  097065c1102dcb2a999d46201d460c8e
 *  Last modified:     7/28/2020
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double[] samples;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n and trials must be greater than zero");
        samples = new double[trials];
        for (int i = 0; i < trials; ++i) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row, col;
                row = StdRandom.uniform(1, n + 1);
                col = StdRandom.uniform(1, n + 1);
                p.open(row, col);
            }
            samples[i] = (double) p.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(samples);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(samples);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(samples.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(samples.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, t);
        System.out.println(String.format("mean                    = %f", stats.mean()));
        System.out.println(String.format("stddev                  = %f", stats.stddev()));
        System.out.println(String.format("95%% confidence interval = [%f, %f]",
                                         stats.confidenceLo(), stats.confidenceHi()));
    }
}
