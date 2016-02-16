package week3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by SoBoLp on 2/14/16.
 */
public class BruteCollinearPoints {

    private Point[][] pointMatrix;
    private LineSegment[] lineSegments;
    private int index = 0;

    /**
     * finds all line segments containing 4 points
     *
     * @param points
     */
    public BruteCollinearPoints(Point[] points) {
        Point[] tmpPoints = points.clone();
        if (tmpPoints.length == 0) throw new NullPointerException("Point[] is null");
        Arrays.sort(tmpPoints);
        if (tmpPoints[0] == null) throw new NullPointerException("Point: " + tmpPoints[0] + " is null");
        for (int i = 1; i < tmpPoints.length; i++) {
            if (tmpPoints[i] == null) throw new NullPointerException("Point: " + tmpPoints[i] + " is null");
            if (tmpPoints[i - 1].compareTo(tmpPoints[i]) == 0)
                throw new IllegalArgumentException("Point: " + tmpPoints[i - 1] + " is equal to point: " + tmpPoints[i]);

        }

        pointMatrix = new Point[tmpPoints.length][2];
        for (int p1 = 0; p1 < tmpPoints.length; p1++) {
            for (int p2 = p1 + 1; p2 < tmpPoints.length; p2++) {
                for (int p3 = p2 + 1; p3 < tmpPoints.length; p3++) {
                    if (isEqual(tmpPoints[p1].slopeOrder(), tmpPoints[p2], tmpPoints[p3]))
                        for (int p4 = tmpPoints.length - 1; p4 > p3; p4--) {
                            if (isEqual(tmpPoints[p1].slopeOrder(), tmpPoints[p2], tmpPoints[p4])) {
                                if (!isFound((new Point[]{tmpPoints[p2], tmpPoints[p3]}))) {
                                    pointMatrix[index][0] = tmpPoints[p1];
                                    pointMatrix[index][1] = tmpPoints[p4];
                                    index++;
                                    break;
                                }
                            }
                        }
                }
            }
        }
        lineSegments = new LineSegment[index];
        for (int i = 0; i < index; i++)
            lineSegments[i] = new LineSegment(pointMatrix[i][0], pointMatrix[i][1]);
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
        return lineSegments.clone();
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
