package week3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by SoBoLp on 2/14/16.
 */
public class BruteCollinearPoints {

    private Point[][] pointMatrix;
    private int index = 0;

    /**
     * finds all line segments containing 4 points
     *
     * @param points
     */
    public BruteCollinearPoints(Point[] points) {

        if (points.length == 0) throw new NullPointerException("Point[] is null");
        Arrays.sort(points);
        if (points[0] == null) throw new NullPointerException("Point: " + points[0] + " is null");
        for (int i = 1; i < points.length; i++) {
            if (points[i] == null) throw new NullPointerException("Point: " + points[i] + " is null");
            if (points[i - 1].compareTo(points[i]) == 0)
                throw new IllegalArgumentException("Point: " + points[i - 1] + " is equal to point: " + points[i]);
        }

        pointMatrix = new Point[points.length][2];
        for (int p1 = 0; p1 < points.length; p1++) {
            for (int p2 = p1 + 1; p2 < points.length; p2++) {
                for (int p3 = p2 + 1; p3 < points.length; p3++) {
                    if (isEqual(points[p1].slopeOrder(), points[p2], points[p3]))
                        for (int p4 = points.length - 1; p4 > p3; p4--) {
                            if (isEqual(points[p1].slopeOrder(), points[p2], points[p4])) {
                                if (!isFound((new Point[]{points[p2], points[p3]}))) {
                                    pointMatrix[index][0] = points[p1];
                                    pointMatrix[index][1] = points[p4];
                                    index++;
                                    break;
                                }
                            }
                        }
                }
            }
        }
    }

    /**
     * the number of line segments
     *
     * @return
     */
    public int numberOfSegments() {
        return index;
    }

    /**
     * the line segments
     *
     * @return
     */
    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[index];
        for (int i = 0; i < index; i++)
            lineSegments[i] = new LineSegment(pointMatrix[i][0], pointMatrix[i][1]);
        return lineSegments;
    }

    private boolean isEqual(Comparator<Point> c, Point v, Point w) {
        return c.compare(v, w) == 0;
    }

    private boolean isFound(Point[] parr) {
        if (index <= 0)
            return false;
        for (int i = 0; i < index; i++) {
            if (isEqual(pointMatrix[i][0].slopeOrder(), parr[0], pointMatrix[i][1]) &&
                    isEqual(pointMatrix[i][0].slopeOrder(), parr[1], pointMatrix[i][1]))
                return true;
        }
        return false;
    }
}
