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
        Point[] tmpPoints = localPoints.clone();
        for (int i = 1; i < localPoints.length; i++) {
            if (localPoints[i] == null) throw new NullPointerException("Point: " + localPoints[i] + " is null");
            if (localPoints[i - 1].compareTo(localPoints[i]) == 0)
                throw new IllegalArgumentException("Point: " + localPoints[i - 1] + " is equal to point: " + localPoints[i]);
        }
        pointMatrix = new Point[localPoints.length * localPoints.length][2];

        if (localPoints.length > 2) {
            for (int p = 0; p < localPoints.length - 1; p++) {
                Arrays.sort(localPoints, p, localPoints.length, tmpPoints[p].slopeOrder());
                int count = 2;
                int idx = 1 + p;
                double lastSlope = localPoints[p].slopeTo(localPoints[idx]);
                idx++;
                double currSlope;
                while (idx < localPoints.length) {
                    currSlope = localPoints[p].slopeTo(localPoints[idx]);
                    if (lastSlope == currSlope)
                        count++;
                    else {

                        if (count > 3) {
                            addLine(count, localPoints, idx, p, lastSlope);
                        }
                        count = 2;
                    }
                    lastSlope = currSlope;
                    idx++;
                }
                if (count > 3)
                    addLine(count, localPoints, idx, p, lastSlope);
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

    private void addLine(int count, Point[] localPoints, int idx, int p, double lastSlope) {
        Point[] related = new Point[count];
        related[0] = localPoints[p];
        int leftIdx = p - 1;
        double leftCurrSlope;
        boolean isNew = true;
        while (leftIdx >= 0) {
            leftCurrSlope = localPoints[p].slopeTo(localPoints[leftIdx]);
            if (lastSlope == leftCurrSlope) {
                isNew = false;
                break;
            }
            leftIdx--;
        }
        if (isNew) {

            for (int a = 1; a < count; a++) {
                related[a] = localPoints[idx - count + a];
//            if (related[0].compareTo(related[a]) > 0) {
//                isSorted = false;
//                break;
//            }
            }
            Arrays.sort(related);
//        if (isSorted) {
            if (!isFound(new Point[]{related[0], related[related.length - 1]})) {
                pointMatrix[index][0] = related[0];
                pointMatrix[index][1] = related[related.length - 1];
                index++;
            }
//        }
        }
    }

}