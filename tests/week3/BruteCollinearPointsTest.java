package week3;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by SoBoLp on 2/14/16.
 */
public class BruteCollinearPointsTest {

    BruteCollinearPoints BCP1;
    BruteCollinearPoints BCP4;
    BruteCollinearPoints BCP6;
    BruteCollinearPoints BCP8;
    BruteCollinearPoints BCP9;
    BruteCollinearPoints BCP10;
    BruteCollinearPoints BCP20;

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
//        BCP1 = new BruteCollinearPoints(loadPoints("./txt/week3/input1.txt"));
//        BCP4 = new BruteCollinearPoints(loadPoints("./txt/week3/input4.txt"));
//        BCP6 = new BruteCollinearPoints(loadPoints("./txt/week3/input6.txt"));
//        BCP8 = new BruteCollinearPoints(loadPoints("./txt/week3/input8.txt"));
//        BCP9 = new BruteCollinearPoints(loadPoints("./txt/week3/input9.txt"));
//        BCP10 = new BruteCollinearPoints(loadPoints("./txt/week3/input10.txt"));
        BCP20 = new BruteCollinearPoints(loadPoints("./txt/week3/input20.txt"));

    }

    @Test
    public void testNumberOfSegments() throws Exception {
//        assertEquals("input1.txt",0,BCP1.numberOfSegments());
//        assertEquals("input4.txt",1,BCP4.numberOfSegments());
//        assertEquals("input6.txt",1,BCP6.numberOfSegments());
//        assertEquals("input8.txt",2,BCP8.numberOfSegments());
//        assertEquals("input9.txt",1,BCP9.numberOfSegments());
//        assertEquals("input10.txt",2,BCP10.numberOfSegments());
        assertEquals("input20.txt",5,BCP20.numberOfSegments());
    }

    @Test
    public void testSegments() throws Exception {

    }
}