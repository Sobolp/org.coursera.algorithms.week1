package week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

import java.util.TreeSet;

/**
 * Created by SoBoLp on 3/4/16.
 */
public class PointSET {
    private TreeSet<Point2D> point2DTreeSet;

    /**
     * construct an empty set of points
     */
    public PointSET() {
        point2DTreeSet = new TreeSet<>();
    }

    /**
     * is the set empty?
     *
     * @return
     */
    public boolean isEmpty() {
        return point2DTreeSet.isEmpty();
    }

    /**
     * number of points in the set
     *
     * @return
     */
    public int size() {
        return point2DTreeSet.size();
    }

    /**
     * add the point to the set (if it is not already in the set)
     *
     * @param p
     */
    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException("argument is null");
        point2DTreeSet.add(p);
    }

    /**
     * does the set contain point p?
     *
     * @param p
     * @return
     */
    public boolean contains(Point2D p) {
        return point2DTreeSet.contains(p);
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        for (Point2D p : point2DTreeSet) {
            StdDraw.point(p.x(), p.y());
        }
    }

    /**
     * all points that are inside the rectangle
     *
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> stack = new Stack<>();
        for (Point2D p : point2DTreeSet) {
            if (rect.contains(p))
                stack.push(p);
        }
        return stack;
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     *
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p) {
        if (this.size() == 0) return null;
        Point2D nearestPoint = new Point2D(20.0, 20.0);
        for (Point2D thisP : point2DTreeSet) {
            if (p.distanceTo(thisP) < p.distanceTo(nearestPoint)) {
                nearestPoint = thisP;
            }
        }
        return nearestPoint;
    }

    /**
     * unit testing of the methods (optional)
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}