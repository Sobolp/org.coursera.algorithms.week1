package week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import loaders.Loaders;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by SoBoLp on 3/4/16.
 */
public class KdTreeTest {
    KdTree empty;
    KdTree circle4;
    KdTree circle10;

    @Before
    public void setUp() throws Exception {
        empty = new KdTree();
        circle4 = new KdTree();
        for (Point2D p : Loaders.loadPoints("./txt/week5/circle4.txt"))
            circle4.insert(p);
        circle10 = new KdTree();
        for (Point2D p : Loaders.loadPoints("./txt/week5/circle10.txt"))
            circle10.insert(p);
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue("empty", empty.isEmpty());
        assertFalse("circle4.txt", circle4.isEmpty());
        assertFalse("circle10.txt", circle10.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals("empty", 0, empty.size());
        assertEquals("circle4.txt", 4, circle4.size());
        assertEquals("circle10.txt", 10, circle10.size());

    }

    @Test
    public void testInsert() throws Exception {
        try {
            empty.insert(null);
            fail("Check out of bounds");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testContains() throws Exception {
        assertTrue("circle4.txt contains Point (0.5,1)", circle4.contains(new Point2D(0.5, 1)));
        assertFalse("circle4.txt not contains Point (0.6,0.6)", circle4.contains(new Point2D(0.6, 0.6)));
        assertTrue("circle10.txt contains Point (0.5,1)", circle10.contains(new Point2D(0.5, 1)));
        assertFalse("circle10.txt not contains Point (0.6,0.6)", circle10.contains(new Point2D(0.6, 0.6)));
    }

    @Test
    public void testRange() throws Exception {
        assertEquals("circle4.txt containt all 4 points in rect((0.0,0.0),(1.0,1.0))"
                , "(0.5, 1.0) (1.0, 0.5) (0.0, 0.5) (0.5, 0.0) ", circle4.range(new RectHV(0.0, 0.0, 1.0, 1.0)).toString());
        assertEquals("circle10.txt containt 3 points in rect((0.0,0.0),(0.4,0.4))"
                , "(0.02447200007736683, 0.34549200534820557) (0.2061070054769516, 0.09549199789762497) "
                , circle10.range(new RectHV(0.0, 0.0, 0.4, 0.4)).toString());
    }

    @Test
    public void testNearest() throws Exception {
//        assertEquals("circle4.txt nearest point to Point(0.1,0.6) is Point(0.0,0.5)",
//                "(0.0, 0.5)", circle4.nearest(new Point2D(0.1, 0.6)).toString());
        assertEquals("circle10.txt nearest point to Point(0.98 0.66) is Point(0.975528 0.654508)",
                "(0.9755280017852783, 0.6545079946517944)", circle10.nearest(new Point2D(0.98, 0.66)).toString());

    }
}