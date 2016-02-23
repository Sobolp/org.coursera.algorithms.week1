package week4;

import edu.princeton.cs.algorithms.Stack;

//import edu.princeton.cs.algs4.Stack;

/**
 * Created by SoBoLp on 2/20/16.
 */
public class Board {

    private final int[] blocks;
    private final int N;
    private int[] blank;
    private int hamming;
    private int manhattan;
//    private MinPQ<Board> neighborsQ;

    /**
     * construct a board from an N-by-N array of blocks
     * (where blocks[i][j] = block in row i, column j)
     *
     * @param blocks
     */
    public Board(int[][] blocks) {

        this.N = blocks[0].length;
        this.blocks = new int[N * N];
        this.hamming = 0;
        this.manhattan = 0;
        for (int x = 0; x < N; x++)
            for (int y = 0; y < N; y++) {
                int index = getIndexfrom2D(x, y);
                this.blocks[index] = blocks[x][y];
                if (blocks[x][y] == 0) {
                    blank = new int[]{x, y};
                }
                //hamming
                if (index == N * N - 1) {
                    if (this.blocks[index] != 0)
                        hamming++;
                } else if (index != this.blocks[index] - 1)
                    hamming++;
                //manhattan
                if (blocks[x][y] != 0) {
                    int[] goal = get2Dcord(blocks[x][y]);
                    manhattan += (Math.abs(goal[0] - x) + Math.abs(goal[1] - y));
                }
            }


    }

    /**
     * board dimension N
     *
     * @return
     */
    public int dimension() {
        return this.N;
    }

    /**
     * number of blocks out of place
     *
     * @return
     */
    public int hamming() {
        /*int count = 0;
        for (int x = 0; x < N * N; x++)
            if (x == N * N - 1) {
                if (blocks[x] != 0)
                    count++;
            } else if (x != blocks[x] - 1)
                count++;
        return count;*/
        return hamming;
    }

    /**
     * sum of Manhattan distances between blocks and goal
     *
     * @return
     */
    public int manhattan() {
        /*int count = 0;
        for (int x = 0; x < N * N; x++)
            if (blocks[x] != 0) {
                int[] goal = get2Dcord(blocks[x]);
                int[] real = get2Dcord(x);
                count += (Math.abs(goal[0] - real[0]) + Math.abs(goal[1] - real[1]));
            }
        return count;*/
        return manhattan;
    }

    /**
     * is this board the goal board?
     *
     * @return
     */
    public boolean isGoal() {
        return hamming() == 0;
    }

    /**
     * a board that is obtained by exchanging any pair of blocks
     *
     * @return
     */
    public Board twin() {
        int[][] newArr = to2Darray();
        for (int i = 0; i < N * N; i++) {
            if (this.blocks[i] != 0) {
                int newX = get2Dcord(i)[0];
                int newY = get2Dcord(i)[1];
//                x+1
                if (newArr[newX + 1][newY] != 0 && newX + 1 < N) {
                    int tmp = newArr[newX + 1][newY];
                    newArr[newX + 1][newY] = newArr[newX][newY];
                    newArr[newX][newY] = tmp;
                    return new Board(newArr);
                } else
//                y+1
                    if (newArr[newX][newY + 1] != 0 && newY + 1 < N) {
                        int tmp = newArr[newX][newY + 1];
                        newArr[newX][newY + 1] = newArr[newX][newY];
                        newArr[newX][newY] = tmp;
                        return new Board(newArr);

                    }
            }
        }
        return null;
    }

    /**
     * does this board equal y?
     *
     * @param y
     * @return
     */
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (that.dimension() != this.dimension()) return false;
        for (int i = 0; i < N * N; i++)
            if (that.blocks[i] != this.blocks[i]) return false;

        return true;
    }

    /**
     * all neighboring boards
     *
     * @return
     */
    public Iterable<Board> neighbors() {
        Stack<Board> neighborsQ = new Stack<>();
        int newX = blank[0];
        int newY = blank[1];

        //x-1
        if (newX - 1 >= 0) {
            int[][] newNeigb = to2Darray();
            newNeigb[newX][newY] = newNeigb[newX - 1][newY];
            newNeigb[newX - 1][newY] = 0;
            neighborsQ.push(new Board(newNeigb));
        }
        //x+1
        if (newX + 1 < N) {
            int[][] newNeigb = to2Darray();
            newNeigb[newX][newY] = newNeigb[newX + 1][newY];
            newNeigb[newX + 1][newY] = 0;
            neighborsQ.push(new Board(newNeigb));
        }
        //y-1
        if (newY - 1 >= 0) {
            int[][] newNeigb = to2Darray();
            newNeigb[newX][newY] = newNeigb[newX][newY - 1];
            newNeigb[newX][newY - 1] = 0;
            neighborsQ.push(new Board(newNeigb));
        }
        //y+1
        if (newY + 1 < N) {
            int[][] newNeigb = to2Darray();
            newNeigb[newX][newY] = newNeigb[newX][newY + 1];
            newNeigb[newX][newY + 1] = 0;
            neighborsQ.push(new Board(newNeigb));
        }
        return neighborsQ;
    }

    /**
     * string representation of this board (in the output format specified below)
     *
     * @return
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", blocks[getIndexfrom2D(i, j)]));
            }
            s.append("\n");
        }

        return s.toString();
    }

    private int getIndexfrom2D(int x, int y) {
        return N * x + y;
    }

    private int[] get2Dcord(int index) {
        int[] result = new int[2];
        result[0] = index / N;
        result[1] = index % N;
        return result;
    }

    private int[][] to2Darray() {
        int[][] result = new int[N][N];
        int[] newcoord;
        for (int i = 0; i < N * N; i++) {
            newcoord = get2Dcord(i);
            result[newcoord[0]][newcoord[1]] = this.blocks[i];
        }
        return result;
    }

    /**
     * unit tests (not graded)
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] two = new int[2][2];
        two[0][0] = 0;
        two[0][1] = 1;
        two[1][0] = 2;
        two[1][1] = 3;

        int[][] three = new int[3][3];
        three[0][0] = 2;
        three[0][1] = 1;
        three[0][2] = 3;
        three[1][0] = 4;
        three[1][1] = 0;
        three[1][2] = 5;
        three[2][0] = 7;
        three[2][1] = 8;
        three[2][2] = 6;
        Board board2x2 = new Board(two);
        Board board3x3 = new Board(three);
        System.out.println(board2x2.toString());
        System.out.println(board2x2.twin());
        System.out.print(board3x3.toString());
        System.out.println(board3x3.twin());

        for (Board board : board3x3.neighbors()) {
            System.out.println(board);
        }


    }
}