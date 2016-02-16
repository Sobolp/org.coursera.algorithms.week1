//package week3;

import java.util.Arrays;
import java.util.Comparator;

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
        boolean isOk = true;
        if (localPoints.length > 2) {
            for (int p = 0; p < localPoints.length - 1; p++) {
                localPoints = tmpPoints.clone();
//                Arrays.sort(localPoints);
//                Arrays.sort(localPoints, p, localPoints.length, tmpPoints[p].slopeOrder());
                sort(localPoints, 0, localPoints.length, tmpPoints[p].slopeOrder());
//                Arrays.sort(localPoints,tmpPoints[p].slopeOrder());
                int count = 2;
//                int idx = 1 + p;
                int idx = 1;
                double lastSlope = localPoints[0].slopeTo(localPoints[idx]);
                idx++;
                double currSlope;

                while (idx < localPoints.length) {
                    currSlope = localPoints[0].slopeTo(localPoints[idx]);
                    if (lastSlope == currSlope) {
                        if (localPoints[0].compareTo(localPoints[idx - 1]) > 0)
                            isOk = false;
                        count++;
                    } else {
                        if (count > 3 && isOk) {
                            addLine(count, localPoints, idx, p, lastSlope);
                        }
                        count = 2;
                        isOk = true;
                    }
                    lastSlope = currSlope;
                    idx++;
                }
                if (count > 3 && isOk)
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

/*
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
*/

    private void addLine(int count, Point[] localPoints, int idx, int p, double lastSlope) {
//        Point[] related = new Point[count];
//        related[0] = localPoints[p];
//        int leftIdx = p - 1;
//        double leftCurrSlope;
//        boolean isNew = true;
//        while (leftIdx >= 0) {
//            leftCurrSlope = localPoints[p].slopeTo(localPoints[leftIdx]);
//            if (lastSlope == leftCurrSlope) {
//                isNew = false;
//                break;
//            }
//            leftIdx--;
//        }
//        if (isNew) {

//            for (int a = 1; a < count; a++) {
//                related[a] = localPoints[idx - count + a];
//            }
//            Arrays.sort(related);
//            pointMatrix[index][0] = related[0];
//            pointMatrix[index][1] = related[related.length - 1];
//            pointMatrix[index][0] = localPoints[p];
        pointMatrix[index][0] = localPoints[0];
        pointMatrix[index][1] = localPoints[idx - 1];
        index++;
//        }
    }

    private static void merge(Point[] a, Point[] aux, int lo, int mid, int hi, Comparator<Point> c) {
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
//                aux[k] = a[j++];
            else if (j > hi)
                a[k] = aux[i++];
//                aux[k] = a[i++];
            else if (less(c, aux[j], aux[i]))
//            else if (less(c, a[j], a[i]))
                a[k] = aux[j++];
//                aux[k] = a[j++];
            else
                a[k] = aux[i++];
//                aux[k] = a[i++];
        }
    }

    private static boolean less(Comparator<Point> c, Point v, Point w) {
        return c.compare(v, w) < 0;
    }

    private static void sort(Point[] a, Point[] aux, int lo, int hi, Comparator<Point> c) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, c);
        sort(a, aux, mid + 1, hi, c);
//        sort(aux, a, lo, mid, c);
//        sort(aux, a, mid + 1, hi, c);
        if (!less(c, a[mid + 1], a[mid])) return;
        merge(a, aux, lo, mid, hi, c);
    }

    private static void sort(Point[] a, int lo, int hi, Comparator<Point> c) {
        Point[] aux = new Point[a.length];
//        Point[] aux = a.clone();
//        for (int i =0;i < a.length;i++)
//            aux[i] = a[i];
        sort(a, aux, lo, hi - 1, c);
    }
}