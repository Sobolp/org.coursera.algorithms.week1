package week3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SoBoLp on 2/14/16.
 */
public class PointTest {
    Point p0;
    Point p1;
    Point p1_1;
    Point p2;
    Point p3;
    Point p4;

    @Before
    public void setUp() throws Exception {
        p0 = new Point(0, 0);
        p1 = new Point(1, 1);
        p1_1 = new Point(1, 1);
        p2 = new Point(1, 2);
        p3 = new Point(2, 1);
        p4 = new Point(2, 2);
    }

    @Test
    public void testSlopeTo() throws Exception {
        assertEquals("+0.0 if the line segment connecting the two points is horizontal",0.0,p1.slopeTo(p3),0.0);  //"+0.0 if the line segment connecting the two points is horizontal"
        assertEquals("Double.POSITIVE_INFINITY if the line segment is vertical",Double.POSITIVE_INFINITY,p1.slopeTo(p2),0.0);
        assertEquals("Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal",Double.NEGATIVE_INFINITY,p1.slopeTo(p1_1),0.0);
        assertEquals("p0 - p1 have slope",1,p0.slopeTo(p1),0.0);
        assertEquals("p4 - p1 have slope",1,p4.slopeTo(p1),0.0);
    }

    @Test
    public void testCompareTo() throws Exception {
        assertEquals("Test p1 < p2", -1, p1.compareTo(p2)); // p1 < p2
        assertEquals("Test p2 > p1", 1, p2.compareTo(p1)); //p2 > p1
        assertEquals("Test p1_1 = p1", 0, p1.compareTo(p1_1)); //p1_1 = p1
        assertEquals("Test p1 < p3", -1, p1.compareTo(p3)); //p1 < p3
        assertEquals("Test p2 > p3", 1, p2.compareTo(p3)); //p2 > p3
    }

    @Test
    public void testSlopeOrder() throws Exception {

    }
}