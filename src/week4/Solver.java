package week4;

import edu.princeton.cs.algorithms.MinPQ;
//import edu.princeton.cs.algs4.MinPQ;

//import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;


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
        int steps = 0;
        Board tmpBoard = initial;
        Board twinBoard = initial.twin();
//        HashMap<Board, Board> parents = new HashMap<>();
        HashMap<QueueElement, QueueElement> parents = new HashMap<>();
        ArrayList<Board> visited = new ArrayList<>();
        ArrayList<Board> twinVisited = new ArrayList<>();
        MinPQ<QueueElement> pq = new MinPQ<>();
        MinPQ<QueueElement> twinPq = new MinPQ<>();
        QueueElement first = new QueueElement(tmpBoard, steps);
        QueueElement twinFirst = new QueueElement(twinBoard, steps);
        pq.insert(first);
        twinPq.insert(twinFirst);
        parents.put(first, null);
        while (!pq.isEmpty()) {
            QueueElement curr = pq.delMin();
            QueueElement twinCurr = twinPq.delMin();
            if (!visited.contains(curr.getBoard())) {
                visited.add(curr.getBoard());
                steps++;
                if (curr.getBoard().isGoal()) {
                    return getPath(parents, curr);
                }
            }
            if (!twinVisited.contains(twinCurr.getBoard())) {
                twinVisited.add(twinCurr.getBoard());
                if (twinCurr.getBoard().isGoal()) {
                    return null;
                }
            }

            for (Board neighbor : curr.getBoard().neighbors()) {
                if (!visited.contains(neighbor)) {
//                    if (neighbor.hamming() < curr.getBoard().hamming()) {
                    QueueElement newQE = new QueueElement(neighbor, steps);
                    parents.put(newQE, curr);
                    pq.insert(newQE);
//                    }
                }
            }

            for (Board neighbor : twinCurr.getBoard().neighbors()) {
                if (!twinVisited.contains(neighbor)) {
//                    if (neighbor.hamming() < curr.getBoard().hamming()) {
                    QueueElement newQE = new QueueElement(neighbor, steps);
                    twinPq.insert(newQE);
//                    }
                }
            }
        }

        return null;
    }


    private List<Board> getPath(HashMap<QueueElement, QueueElement> parent, QueueElement goal) {
        List<Board> result = new LinkedList<>();
        QueueElement next = goal;
        do {
            result.add(next.getBoard());
            next = parent.get(next);
        } while ((next != null));
        Collections.reverse(result);
        return result;
    }

    private class QueueElement implements Comparable<QueueElement> {
        private final Board board;
        private final int step;
        private final int g;
        private final int h;

        public QueueElement(Board board, int step) {
            this.board = board;
            this.step = step;
            this.g = board.hamming();
            this.h = board.manhattan();
        }

        @Override
        public int compareTo(QueueElement that) {
            int result = 1;
            if (this.h + this.step < that.h + that.step)
                result = -1;
            else if (this.h + this.step > that.h + that.step)
                result = 1;
            else if (this.g > that.g)
                result = -1;

            return result;
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
//        int[][] three_unsolveble = new int[3][3];
//        three_unsolveble[0][0] = 1;
//        three_unsolveble[0][1] = 2;
//        three_unsolveble[0][2] = 3;
//        three_unsolveble[1][0] = 4;
//        three_unsolveble[1][1] = 5;
//        three_unsolveble[1][2] = 6;
//        three_unsolveble[2][0] = 8;
//        three_unsolveble[2][1] = 7;
//        three_unsolveble[2][2] = 0;
//        Board board3x3Unsolveble = new Board(three_unsolveble);
//        Solver solvUnsolveble = new Solver(board3x3.twin());
//        System.out.println(solvUnsolveble.moves());
//        if (solvUnsolveble.isSolvable())
//            for (Board b : solvUnsolveble.solution()) {
//                System.out.println(b.toString());
//            }
    }
}