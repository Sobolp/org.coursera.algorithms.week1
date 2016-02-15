package week3;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by SoBoLp on 2/14/16.
 */
public class BruteCollinearPointsTest {

    BruteCollinearPoints BCPNull;
    BruteCollinearPoints BCP1;
    BruteCollinearPoints BCP4;
    BruteCollinearPoints BCP6;
    BruteCollinearPoints BCP8;
    BruteCollinearPoints BCP9;
    BruteCollinearPoints BCP10;
    BruteCollinearPoints BCP20;
    BruteCollinearPoints BCP40;
    BruteCollinearPoints BCP200;
    BruteCollinearPoints BCP400;

    private Point[] loadPoints(String fileName) throws FileNotFoundException {
        Point[] points;
        File inFile = new File(fileName);
        Scanner sc = new Scanner(inFile);
        int N = Integer.parseInt(sc.nextLine());
        points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            points[i] = new Point(x, y);
        }
        return points;
    }

    @Before
    public void setUp() throws Exception {

        BCP1 = new BruteCollinearPoints(loadPoints("./txt/week3/input1.txt"));
        BCP4 = new BruteCollinearPoints(loadPoints("./txt/week3/input4.txt"));
        BCP6 = new BruteCollinearPoints(loadPoints("./txt/week3/input6.txt"));
        BCP8 = new BruteCollinearPoints(loadPoints("./txt/week3/input8.txt"));
        BCP9 = new BruteCollinearPoints(loadPoints("./txt/week3/input9.txt"));
        BCP10 = new BruteCollinearPoints(loadPoints("./txt/week3/input10.txt"));
        BCP20 = new BruteCollinearPoints(loadPoints("./txt/week3/input20.txt"));
        BCP40 = new BruteCollinearPoints(loadPoints("./txt/week3/input40.txt"));
        BCP200 = new BruteCollinearPoints(loadPoints("./txt/week3/input200.txt"));
        BCP400 = new BruteCollinearPoints(loadPoints("./txt/week3/input400.txt"));
    }

    @Test
    public void testBruteCollinearPointsExeption() throws Exception {
        try {
            BCPNull = new BruteCollinearPoints(null);
            fail("Check out of bounds");
        } catch (NullPointerException e) {
        }

        try {
            BCPNull = new BruteCollinearPoints(new Point[]{new Point(1, 2), new Point(2, 2), null, new Point(3, 2)});
            fail("Check out of bounds");
        } catch (NullPointerException e) {
        }

        try {
            BCPNull = new BruteCollinearPoints(new Point[]{new Point(2, 2), new Point(2, 2), new Point(3, 2)});
            fail("Check out of bounds");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testNumberOfSegments() throws Exception {

        assertEquals("input1.txt", 0, BCP1.numberOfSegments());
        assertEquals("input4.txt", 1, BCP4.numberOfSegments());
        assertEquals("input6.txt", 1, BCP6.numberOfSegments());
        assertEquals("input8.txt", 2, BCP8.numberOfSegments());
        assertEquals("input9.txt", 1, BCP9.numberOfSegments());
        assertEquals("input10.txt", 2, BCP10.numberOfSegments());
        assertEquals("input20.txt", 5, BCP20.numberOfSegments());
        assertEquals("input40.txt", 4, BCP40.numberOfSegments());
        assertEquals("input200.txt", 4, BCP200.numberOfSegments());
        assertEquals("input400.txt", 7, BCP400.numberOfSegments());
    }

    @Test
    public void testSegments() throws Exception {
        assertEquals("input1.txt", 0, BCP1.segments().length);
        assertEquals("input4.txt", 1, BCP4.segments().length);
        assertEquals("input6.txt", 1, BCP6.segments().length);
        assertEquals("input8.txt", 2, BCP8.segments().length);
        assertEquals("input9.txt", 1, BCP9.segments().length);
        assertEquals("input10.txt", 2, BCP10.segments().length);
        assertEquals("input20.txt", 5, BCP20.segments().length);
        assertEquals("input40.txt", 4, BCP40.segments().length);
        assertEquals("input200.txt", 4, BCP200.segments().length);
        assertEquals("input400.txt", 7, BCP400.segments().length);

    }
}