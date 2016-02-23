package week4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by SoBoLp on 2/20/16.
 */
public class BoardTest {
//    int[][] empty = null;
    int[][] one = new int[1][1];
    int[][] two = new int[2][2];
    int[][] three = new int[3][3];
    int[][] three_unsolveble = new int[3][3];
    Board emptyBoard;
    Board board1x1;
    Board board2x2;
    Board board3x3;
    Board board3x3Unsolveble;

    @Before
    public void setUp() throws Exception {

        one[0][0] = 0;

        two[0][0] = 0;
        two[0][1] = 1;
        two[1][0] = 2;
        two[1][1] = 3;

        three[0][0] = 0;
        three[0][1] = 1;
        three[0][2] = 3;
        three[1][0] = 4;
        three[1][1] = 2;
        three[1][2] = 5;
        three[2][0] = 7;
        three[2][1] = 8;
        three[2][2] = 6;

        three_unsolveble[0][0] = 1;
        three_unsolveble[0][1] = 2;
        three_unsolveble[0][2] = 3;
        three_unsolveble[1][0] = 4;
        three_unsolveble[1][1] = 5;
        three_unsolveble[1][2] = 6;
        three_unsolveble[2][0] = 8;
        three_unsolveble[2][1] = 7;
        three_unsolveble[2][2] = 0;

//        emptyBoard = new Board(empty);
        board1x1 = new Board(one);
        board2x2 = new Board(two);
        board3x3 = new Board(three);
        board3x3Unsolveble = new Board(three_unsolveble);


    }

    @Test
    public void testDimension() throws Exception {
        assertEquals("board1x1.dimension()",1,board1x1.dimension());
        assertEquals("board2x2.dimension()",2,board2x2.dimension());
        assertEquals("board3x3.dimension()",3,board3x3.dimension());
    }

    @Test
    public void testHamming() throws Exception {
        assertEquals("board1x1.hamming()",0,board1x1.hamming());
        assertEquals("board2x2.hamming()",4,board2x2.hamming());
        assertEquals("board3x3.hamming()",5,board3x3.hamming());
        assertEquals("board3x3Unsolveble.hamming()",2,board3x3Unsolveble.hamming());

    }

    @Test
    public void testManhattan() throws Exception {
        assertEquals("board3x3.manhattan()",10,board3x3.manhattan());

    }

    @Test
    public void testIsGoal() throws Exception {
        assertTrue("board1x1.isGoal()",board1x1.isGoal());
        assertFalse("board2x2.isGoal()",board2x2.isGoal());
        assertFalse("board3x3.isGoal()",board3x3.isGoal());
        assertFalse("board3x3Unsolveble.isGoal()",board3x3Unsolveble.isGoal());

    }

    @Test
    public void testTwin() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {
        assertFalse("board3x3.equals(board3x3Unsolveble)",board3x3.equals(board3x3Unsolveble));
        assertFalse("board3x3.equals(board2x2)",board3x3.equals(board2x2));
        assertTrue("board2x2.equals(board2x2)",board2x2.equals(board2x2));

    }

    @Test
    public void testNeighbors() throws Exception {

    }

    @Test
    public void testToString() throws Exception {
        assertEquals("board2x2.toString()","2\n 0  1 \n 2  3 \n",board2x2.toString());
        assertEquals("board3x3.toString()","3\n 0  1  3 \n 4  2  5 \n 7  8  6 \n",board3x3.toString());
    }
}