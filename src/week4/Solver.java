package week4;

import edu.princeton.cs.algorithms.MinPQ;

//import edu.princeton.cs.algs4.MinPQ;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;





/**
 * Created by SoBoLp on 2/20/16.
 */
public class Solver {
    private int moves;
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
        solution = getSolution();
        if (solution == null) {
            isSolvable = false;
            moves = 0;
        } else {
            isSolvable = true;
            moves = solution.size();
        }

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
        return moves - 1;
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
        Board tmpBoard = initial;
        Board twinBoard = initial.twin();
//        ArrayList<Board> visited = new ArrayList<>();
        TreeSet<QueueElement> visited = new TreeSet<>();
        MinPQ<QueueElement> pq = new MinPQ<>();
        QueueElement first = new QueueElement(tmpBoard, null, 0);
        QueueElement twinFirst = new QueueElement(twinBoard, null, 0);
        pq.insert(first);
        pq.insert(twinFirst);
        Board prev = initial;
        while (!pq.isEmpty()) {
            QueueElement curr = pq.delMin();
//            if (!visited.contains(curr.getBoard())) {
//                visited.add(curr.getBoard());
            visited.add(curr);
            if (curr.getG() == 0) {
                return getPath(curr, first);
            }
//            }
            for (Board neighbor : curr.getBoard().neighbors()) {
                if (!prev.equals(neighbor)) {
//                    if (!visited.contains(neighbor)) {
                    QueueElement newQE = new QueueElement(neighbor, curr, curr.getStep() + 1);
                    if (!visited.contains(newQE)) {
                        pq.insert(newQE);
                    }
                }
            }
            prev = curr.getBoard();
        }
        return null;
    }


    private List<Board> getPath(QueueElement goal, QueueElement start) {
        List<Board> result = new LinkedList<>();
        QueueElement next = goal;
        QueueElement last = null;
        do {
            result.add(next.getBoard());
            last = next;
            next = last.getPerent();
        } while ((next != null));
        if (last != start)
            return null;
        Collections.reverse(result);
        return result;
    }

    private class QueueElement implements Comparable<QueueElement> {
        private final Board board;
        private final QueueElement perent;
        private final int step;
        private final int g;
        private final int h;
        private final int priority;

        public QueueElement(Board board, QueueElement perent, int step) {
            this.board = board;
            this.perent = perent;
            this.step = step;
            this.g = board.hamming();
            this.h = board.manhattan();
            this.priority = this.h + this.step;

        }

        public int getStep() {
            return step;
        }

        public int getG() {
            return g;
        }

        public QueueElement getPerent() {
            return perent;
        }
/*

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            QueueElement that = (QueueElement) o;

            return board.equals(that.board);

        }
*/


        @Override
        public int compareTo(QueueElement that) {
            if (this.priority < that.priority)
                return -1;
            else if (this.priority > that.priority)
                return 1;
            else if (this.board.equals(that.board))
                return 0;
            else if (this.h < that.h)
                return -1;
//            else if (this.board.equals(that.board))
//                return 0;
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
        three[0][0] = 1;
        three[0][1] = 2;
        three[0][2] = 3;
        three[1][0] = 0;
        three[1][1] = 7;
        three[1][2] = 6;
        three[2][0] = 5;
        three[2][1] = 4;
        three[2][2] = 8;
        Board board3x3 = new Board(three);
        Solver solv = new Solver(board3x3);
        if (solv.isSolvable())
            for (Board b : solv.solution()) {
                System.out.println(b.toString());
            }
        System.out.println(solv.moves());

    }
}