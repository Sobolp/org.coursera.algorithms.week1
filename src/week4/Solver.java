package week4;

import edu.princeton.cs.algorithms.MinPQ;
//edu.princeton.cs.algs4.MinPQ;

import java.util.*;

/**
 * Created by SoBoLp on 2/20/16.
 */
public class Solver {
    private int steps;
    private final Board initial;
    private final boolean isSolvable;
    private List<Board> solution;

    /**
     * find a solution to the initial board (using the A* algorithm)
     *
     * @param initial
     */
    public Solver(Board initial) {
        this.initial = initial;
        this.steps = 0;
        solution = getSolution();
        if (solution == null) {
            isSolvable = false;
            steps = 0;
        } else isSolvable = true;

    }

    /**
     * is the initial board solvable?
     *
     * @return
     */
    public boolean isSolvable() {
        return isSolvable;
    }

    /**
     * min number of moves to solve initial board; -1 if unsolvable
     *
     * @return
     */
    public int moves() {
        return steps - 1;
    }

    /**
     * sequence of boards in a shortest solution; null if unsolvable
     *
     * @return
     */
    public Iterable<Board> solution() {
        return solution;
    }

    private List<Board> getSolution() {
        Board tmpBpard = initial;
        HashMap<Board, Board> parents = new HashMap<>();
        HashSet<Board> visited = new HashSet<>();
        MinPQ<QueueElement> pq = new MinPQ<>();
        pq.insert(new QueueElement(tmpBpard, steps));
        while (!pq.isEmpty()) {
            Board curr = pq.delMin().getBoard();
            if (!visited.contains(curr)) {
                visited.add(curr);
                steps++;
                if (curr.isGoal()) {
                    return getPath(parents, curr);
                }
            }
            for (Board neighbor : curr.neighbors()) {
                if (!visited.contains(neighbor)) {
                    if (neighbor.hamming() < curr.hamming()) {
                        parents.put(neighbor, curr);
                        pq.insert(new QueueElement(neighbor, steps));
                    }
                }
            }
        }

        return null;
    }

    private List<Board> getPath(HashMap<Board, Board> parent, Board goal) {
        List<Board> result = new LinkedList<>();
        Board next = goal;
        do {
            result.add(next);
            next = parent.get(next);
        } while ((next != null));
        Collections.reverse(result);
        return result;
    }

    private class QueueElement implements Comparable<QueueElement> {
        private final Board board;
        private final int step;

        public QueueElement(Board board, int step) {
            this.board = board;
            this.step = step;
        }

        @Override
        public int compareTo(QueueElement that) {
            if (this.board == that.board)
                return 0;
            if (this.board.manhattan() + step < that.board.manhattan() + that.step)
                return -1;
            else
                return 1;
        }

        public Board getBoard() {
            return board;
        }
    }

    /**
     * solve a slider puzzle (given below)
     *
     * @param args
     */
    public static void main(String[] args) {

        int[][] three = new int[3][3];
        three[0][0] = 0;
        three[0][1] = 1;
        three[0][2] = 3;
        three[1][0] = 4;
        three[1][1] = 2;
        three[1][2] = 5;
        three[2][0] = 7;
        three[2][1] = 8;
        three[2][2] = 6;
        Board board3x3 = new Board(three);
        Solver solv = new Solver(board3x3);
        if (solv.isSolvable())
            for (Board b : solv.solution()) {
                System.out.println(b.toString());
            }
        System.out.println(solv.moves());
        int[][] three_unsolveble = new int[3][3];
        three_unsolveble[0][0] = 1;
        three_unsolveble[0][1] = 2;
        three_unsolveble[0][2] = 3;
        three_unsolveble[1][0] = 4;
        three_unsolveble[1][1] = 5;
        three_unsolveble[1][2] = 6;
        three_unsolveble[2][0] = 8;
        three_unsolveble[2][1] = 7;
        three_unsolveble[2][2] = 0;
        Board board3x3Unsolveble = new Board(three_unsolveble);
        Solver solvUnsolveble = new Solver(board3x3.twin());
        System.out.println(solvUnsolveble.moves());
        if (solvUnsolveble.isSolvable())
            for (Board b : solvUnsolveble.solution()) {
                System.out.println(b.toString());
            }
    }
}