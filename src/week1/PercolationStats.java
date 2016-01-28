//package week1;

//import edu.princeton.cs.introcs.StdIn;
//import edu.princeton.cs.introcs.StdOut;
//import edu.princeton.cs.introcs.StdRandom;
//import edu.princeton.cs.introcs.StdStats;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by SoBoLp on 1/28/16.
 */
public class PercolationStats {
    private double[] fraction;
    private int T;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        this.T = T;
        fraction = new double[T];
        for (int i = 0; i < T; i++) {
            fraction[i] = ((double) experiment(N) / (double) (N * N));
        }
    }

    //method makes experiment
    private int experiment(int N) {
        Percolation percolation = new Percolation(N);
        int count = 0;
        while (!percolation.percolates()) {
            int i = StdRandom.uniform(1, N + 1);
            int j = StdRandom.uniform(1, N + 1);
            if (!percolation.isOpen(i, j)) {
                percolation.open(i, j);
                count++;
            }
        }
        return count;
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fraction);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fraction);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }


/*
    @Override
    public String toString() {
        String result = "{ ";
        for (double i:fraction)
            result +=i+" ";
        result+="}";
        return result;
    }
*/


    public static void main(String[] args) {
        // test client
        int N = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats ps = new PercolationStats(N, T);
        //2       System.out.println(ps);
        StdOut.println("mean = " + ps.mean());
        StdOut.println("stddev = " + ps.stddev());
        StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());

    }
}
