package week3;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by SoBoLp on 2/15/16.
 */
public class FastCollinearPointsTest {
    FastCollinearPoints FCPNull;
    FastCollinearPoints FCP1;
    FastCollinearPoints FCP4;
    FastCollinearPoints FCP6;
    FastCollinearPoints FCP8;
    FastCollinearPoints FCP9;
    FastCollinearPoints FCP10;
    FastCollinearPoints FCP20;
    FastCollinearPoints FCP40;
    FastCollinearPoints FCP200;
    FastCollinearPoints FCP400;

    private Point[] loadPoints(String fileName) throws FileNotFoundException {
        Point[] points;
        File inFile = new File (fileName);
        Scanner sc = new Scanner (inFile);
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
//        FCP1 = new FastCollinearPoints(loadPoints("./txt/week3/input1.txt"));
//        FCP4 = new FastCollinearPoints(loadPoints("./txt/week3/input4.txt"));
//        FCP6 = new FastCollinearPoints(loadPoints("./txt/week3/input6.txt"));
        FCP8 = new FastCollinearPoints(loadPoints("./txt/week3/input8.txt"));
//        FCP9 = new FastCollinearPoints(loadPoints("./txt/week3/input9.txt"));
//        FCP10 = new FastCollinearPoints(loadPoints("./txt/week3/input10.txt"));
//        FCP20 = new FastCollinearPoints(loadPoints("./txt/week3/input20.txt"));
//        FCP40 = new FastCollinearPoints(loadPoints("./txt/week3/input40.txt"));
//        FCP200 = new FastCollinearPoints(loadPoints("./txt/week3/input200.txt"));
//        FCP400 = new FastCollinearPoints(loadPoints("./txt/week3/input400.txt"));

    }

    @Test
    public void testFastCollinearPointsExeption() throws Exception {
        try {
            FCPNull = new FastCollinearPoints(null);
            fail("Check out of bounds");
        } catch (NullPointerException e) {
        }

        try {
            FCPNull = new FastCollinearPoints(new Point[]{new Point(1, 2), new Point(2, 2), null, new Point(3, 2)});
            fail("Check out of bounds");
        } catch (NullPointerException e) {
        }

        try {
            FCPNull = new FastCollinearPoints(new Point[]{new Point(2, 2), new Point(2, 2), new Point(3, 2)});
            fail("Check out of bounds");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testNumberOfSegments() throws Exception {
//        assertEquals("input1.txt",0,FCP1.numberOfSegments());
//        assertEquals("input4.txt",1,FCP4.numberOfSegments());
//        assertEquals("input6.txt",1,FCP6.numberOfSegments());
        assertEquals("input8.txt",2,FCP8.numberOfSegments());
//        assertEquals("input9.txt",1,FCP9.numberOfSegments());
//        assertEquals("input10.txt",2,FCP10.numberOfSegments());
//        assertEquals("input20.txt",5,FCP20.numberOfSegments());
//        assertEquals("input40.txt",4,FCP40.numberOfSegments());
//        assertEquals("input200.txt",4,FCP200.numberOfSegments());
//        assertEquals("input400.txt",7,FCP400.numberOfSegments());

    }

    @Test
    public void testSegments() throws Exception {
//        assertEquals("input1.txt",0,FCP1.segments().length);
//        assertEquals("input4.txt",1,FCP4.segments().length);
//        assertEquals("input6.txt",1,FCP6.segments().length);
        assertEquals("input8.txt",2,FCP8.segments().length);
//        assertEquals("input9.txt",1,FCP9.segments().length);
//        assertEquals("input10.txt",2,FCP10.segments().length);
//        assertEquals("input20.txt",5,FCP20.segments().length);
//        assertEquals("input40.txt",4,FCP40.segments().length);
//        assertEquals("input200.txt",4,FCP200.segments().length);
//        assertEquals("input400.txt",7,FCP400.segments().length);

    }
}