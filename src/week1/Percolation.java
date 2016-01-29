package week1;


import edu.princeton.cs.algorithms.WeightedQuickUnionUF;


//import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by SoBoLp on 1/27/16.
 */

public class Percolation {
    private int pN;
    private MyGrid myGrid;
    private WeightedQuickUnionUF unions;
//    private WeightedQuickUnionUF unionsAfter;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("N ≤ 0");
        this.pN = N + 1;
        this.unions = new WeightedQuickUnionUF(N * N + 2);
//        this.unionsAfter = new WeightedQuickUnionUF(N * N + 1);
        myGrid = new MyGrid(N);
        for (int i = 1; i < pN; i++) {
            for (int j = 1; j < pN; j++) {
                myGrid.setGrid(i, j, (byte) 0);
            }
        }
        for (int i = 1; i <= N; i++) {
            unions.union(0, i);
            unions.union(N * N + 1, N * N + 1 - i);
//            unionsAfter.union(0, i);
        }
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        if (!this.isOpen(i, j)) {
            int point = myGrid.get1Dindex(i, j);
            if (i > 1)
                if (this.isOpen(i - 1, j)) {
                    unions.union(point, point - pN + 1);
//                    unionsAfter.union(point, point - pN + 1);
                }
            if (i < pN - 1)
                if (this.isOpen(i + 1, j)) {
                    unions.union(point, point + pN - 1);
//                    unionsAfter.union(point, point + pN - 1);
                }
            if (j > 1)
                if (this.isOpen(i, j - 1)) {
                    unions.union(point, point - 1);
//                    unionsAfter.union(point, point - 1);
                }
            if (j < pN - 1)
                if (this.isOpen(i, j + 1)) {
                    unions.union(point, point + 1);
//                    unionsAfter.union(point, point + 1);
                }
            if (i == 1)
                myGrid.setStatus(i, j, (byte) 3);
            else if (i == pN - 1)
                myGrid.setStatus(i, j, (byte) 2);
            else
                myGrid.setStatus(i, j, (byte) 1);
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        if (i <= 0 || i > pN - 1) throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > pN - 1) throw new IndexOutOfBoundsException("row index j out of bounds");
        return myGrid.isOpen(i, j);
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        if (isOpen(i, j)) {
            return myGrid.isFull(i, j);
        }
        return false;
    }

    // does the system percolate?
    public boolean percolates() {
        if (pN - 1 == 1 && !isOpen(1, 1))
            return false;
        return (unions.connected(0, ((pN - 1) * (pN - 1) + 1)));
    }

    private class MyGrid {
        // 0 - close
        // > 0 - is open
        // 3 - connected top
        // 2 - connected bottom
        // 1 - no top no bottom just oppend
        private byte[][] grid;

        private MyGrid(int N) {
            grid = new byte[N][N];
        }

        private int get1Dindex(int i, int j) {
            return (grid[0].length * (i - 1) + (j - 1)) + 1;
        }

        private byte getGrid(int i, int j) {
            return grid[i - 1][j - 1];
        }

        private void setGrid(int i, int j, byte val) {
            this.grid[i - 1][j - 1] = val;
        }

        private void setStatus(int i, int j, byte val) {
            setGrid(i, j, val);
            byte nStat;
            if (i > 1)
                if (isOpen(i - 1, j)) {
                    nStat = getGrid(i - 1, j);
                    if (nStat < val)
                        setStatus(i - 1, j, val);
                    else if (nStat > val)
                        setStatus(i, j, nStat);
                }
            if (i < pN - 1)
                if (isOpen(i + 1, j)) {
                    nStat = getGrid(i + 1, j);
                    if (nStat < val)
                        setStatus(i + 1, j, val);
                    else if (nStat > val)
                        setStatus(i, j, nStat);
                }
            if (j > 1)
                if (isOpen(i, j - 1)) {
                    nStat = getGrid(i, j - 1);
                    if (nStat < val)
                        setStatus(i, j - 1, val);
                    else if (nStat > val)
                        setStatus(i, j, nStat);
                }
            if (j < pN - 1)
                if (isOpen(i, j + 1)) {
                    nStat = getGrid(i, j + 1);
                    if (nStat < val)
                        setStatus(i, j + 1, val);
                    else if (nStat > val)
                        setStatus(i, j, nStat);
                }
        }

        private boolean isOpen(int i, int j) {
            boolean result = false;
            if (getGrid(i, j) > 0)
                result = true;
            return result;
        }

        private boolean isFull(int i, int j) {
            boolean resalt = false;
//            if (!percolates()) {
            if (getGrid(i, j) == 3)
                return true;
//            } else if (getGrid(i, j) == 1 || getGrid(i, j) == 2)
//                return true;
            return resalt;
        }
    }

/*    @Override
    public String toString() {
        String result = "";
        for (int i = 1; i < pN; i++) {
            for (int j = 1; j < pN; j++) {
                result += myGrid.getGrid(i, j) + "\t ";
            }
            result += "\n";
        }
        return result;
    }*/

    // test client (optional)
    public static void main(String[] args) {
        Percolation a = new Percolation(3);


//        a.open(1, 1);

//        a.open(3, 2);
//        a.open(4, 2);
//        a.open(5, 2);
//        a.open(6, 5);
        a.open(1, 1);
        a.open(1, 2);
        a.open(2, 2);

        a.open(2, 1);
        a.open(3, 1);
        System.out.println(a);
        System.out.println(a.isFull(1, 1));
        System.out.println(a.percolates());

    }
}