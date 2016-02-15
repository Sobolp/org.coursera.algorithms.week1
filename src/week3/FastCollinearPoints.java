package week3;

import java.util.Arrays;

/**
 * Created by SoBoLp on 2/14/16.
 */
public class FastCollinearPoints {

    private Point[][] pointMatrix;
    private int index = 0;

    /**
     * finds all line segments containing 4 or more points
     *
     * @param points
     */
    public FastCollinearPoints(Point[] points) {
        if (points.length == 0) throw new NullPointerException("Point[] is null");
        Arrays.sort(points);
        if (points[0] == null) throw new NullPointerException("Point: " + points[0] + " is null");
        for (int i = 1; i < points.length; i++) {
            if (points[i] == null) throw new NullPointerException("Point: " + points[i] + " is null");
            if (points[i - 1].compareTo(points[i]) == 0)
                throw new IllegalArgumentException("Point: " + points[i - 1] + " is equal to point: " + points[i]);
        }
        pointMatrix = new Point[points.length][2];
        if (points.length > 3) {
            for (int p = 0; p < points.length; p++) {
                Arrays.sort(points, points[p].slopeOrder());
                // Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p.
                // If so, these points, together with p, are collinear.
                int count = 2;
                for (int idx = 2; idx < points.length; idx++) {
                    if (points[0].slopeTo(points[idx - 1]) == points[0].slopeTo(points[idx]))
                        count++;
                    else count = 2;
                    //Check if idx-2 > 3 that
                    // if !isFound add to pointMatrix
                    if (count > 3) {
                        Point[] related = new Point[count];
                        related[0] = points[0];
                        for (int a = 1; a < count; a++)
                            related[a] = points[idx - a + 1];
                        Arrays.sort(related);
                        if (!isFound(new Point[]{related[0], related[count - 1]})) {
                            pointMatrix[index][0] = related[0];
                            pointMatrix[index][1] = related[count - 1];
                            index++;
                        }
                    }
//                        count = 2;
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

    private boolean isEqual(Point v, Point w) {
        return v.compareTo(w) == 0;
    }

    private boolean isFound(Point[] parr) {
        if (index <= 0)
            return false;
        for (int i = 0; i < index; i++) {
            if (isEqual(parr[0], pointMatrix[i][0]) &&
                    isEqual(parr[1], pointMatrix[i][1]))
                return true;
        }
        return false;
    }

}