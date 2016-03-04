package loaders;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by SoBoLp on 3/4/16.
 */
public class Loaders {
    public static Iterable<Point2D> loadPoints(String fileName) throws FileNotFoundException {
        Queue<Point2D> points = new Queue<>();
        File inFile = new File(fileName);
        Scanner sc = new Scanner(inFile);
        while (sc.hasNext()) {
            float x = sc.nextFloat();
            float y = sc.nextFloat();
            points.enqueue(new Point2D(x, y));
        }
        return points;
    }
}
