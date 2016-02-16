package week3;

import java.util.Arrays;

/**
 * Created by SoBoLp on 2/14/16.
 */
public class FastCollinearPoints {

    private Point[][] pointMatrix;
    private LineSegment[] lineSegments;
    private int index = 0;

    /**
     * finds all line segments containing 4 or more points
     *
     * @param points
     */
    public FastCollinearPoints(Point[] points) {
        Point[] localPoints = points.clone();
        if (localPoints.length == 0) throw new NullPointerException("Point[] is null");
        Arrays.sort(localPoints);
        Point[] tmpPoints = new Point[localPoints.length];
        if (localPoints[0] == null) throw new NullPointerException("Point: " + localPoints[0] + " is null");
        tmpPoints[0] = localPoints[0];
        for (int i = 1; i < localPoints.length; i++) {
            if (localPoints[i] == null) throw new NullPointerException("Point: " + localPoints[i] + " is null");
            if (localPoints[i - 1].compareTo(localPoints[i]) == 0)
                throw new IllegalArgumentException("Point: " + localPoints[i - 1] + " is equal to point: " + localPoints[i]);
            tmpPoints[i] = localPoints[i];
        }
        pointMatrix = new Point[localPoints.length * localPoints.length][2];

        if (localPoints.length > 3) {
            for (int p = 0; p < localPoints.length; p++) {
                Arrays.sort(localPoints, tmpPoints[p].slopeOrder());
                // Check if any 3 (or more) adjacent localPoints in the sorted order have equal slopes with respect to p.
                // If so, these localPoints, together with p, are collinear.
                int count = 2;
                int idx = 1;
                while (idx < localPoints.length) {
//                for (; idx < localPoints.length; idx++) {
                    idx++;
                    while (idx < localPoints.length && localPoints[0].slopeTo(localPoints[idx - 1])
                            == localPoints[0].slopeTo(localPoints[idx])) {
                        count++;
                        idx++;
                    }
                    //Check if idx-2 > 3 that
                    // if !isFound add to pointMatrix
                    if (count > 3) {
                        Point[] related = new Point[count];
                        related[0] = localPoints[0];
                        for (int a = 1; a < count; a++)
                            related[a] = localPoints[idx - a];
                        Arrays.sort(related);
                        if (!isFound(new Point[]{related[0], related[count - 1]})) {
                            pointMatrix[index][0] = related[0];
                            pointMatrix[index][1] = related[count - 1];
                            index++;
                        }
                    }
                    count = 2;
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