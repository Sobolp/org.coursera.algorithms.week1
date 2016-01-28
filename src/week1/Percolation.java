//package week1;


//import edu.princeton.cs.algorithms.WeightedQuickUnionUF;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by SoBoLp on 1/27/16.
 */

public class Percolation {
    private int pN;
    private int[][] grid;
    private WeightedQuickUnionUF unions;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        this.pN = N + 2;
        this.unions = new WeightedQuickUnionUF(N * N);
        if (N <= 0) throw new IllegalArgumentException("N ≤ 0");
        grid = new int[pN][pN];
        int count = 1;
        for (int i = 0; i < pN; i++) {
            for (int j = 0; j < pN; j++) {
                if (i == 0 || i == pN - 1 || j == 0 || j == pN - 1) {
                    grid[i][j] = 0;
                } else {
                    grid[i][j] = count;
                    count++;
                }
            }
        }
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        if (!this.isOpen(i, j)) {
            int point = grid[i][j] - 1;
            grid[i][j] *= -1;

            if (i > 1)
                if (this.isOpen(i - 1, j)) {
                    unions.union(point, point - pN + 2);
                }
            if (i < pN)
                if (this.isOpen(i + 1, j)) {
                    unions.union(point, point + pN - 2);
                }
            if (j > 1)
                if (this.isOpen(i, j - 1)) {
                    unions.union(point, point - 1);
                }
            if (j < pN)
                if (this.isOpen(i, j + 1)) {
                    unions.union(point, point + 1);
                }
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        boolean result = false;
        if (grid[i][j] < 0)
            result = true;
        return result;
    }

    // is site (row i, column j) full?

    public boolean isFull(int i, int j) {
        for (int x = 0; x < pN - 2; x++)
            for (int y = (pN - 2) * (pN - 2) - (pN - 2); y < (pN - 2) * (pN - 2); y++) {
                if (unions.connected(x, Math.abs(grid[i][j]) - 1) &&
                        unions.connected(y, Math.abs(grid[i][j]) - 1))
                    return true;
            }
        return false;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 1; i < pN - 1; i++)
            if (isFull(1, i))
                return true;
        return false;
    }

/*
    @Override
    public String toString() {
        String result ="";
        for (int i=0;i<pN;i++) {
            for (int j = 0; j < pN; j++) {
                result += grid[i][j]+"\t ";
            }
            result +="\n";
        }
        return result;
    }
*/

    // test client (optional)
    public static void main(String[] args) {
        Percolation a = new Percolation(2);


        a.open(1, 2);
        a.open(2, 2);
//        a.open(3,3);
//        a.open(3,4);
//        a.open(4,4);
//        a.open(5,4);

/*
        a.open(3,2);
        a.open(2,3);
        a.open(3,3);
//        a.open(2,2);
        a.open(2,1);
        a.open(2,2);
        a.open(4,4);
        a.open(4,3);
        a.open(5,4);
*/
        System.out.println(a);
        //       System.out.println(a.isFull(5,5));
        System.out.println(a.percolates());

    }
}