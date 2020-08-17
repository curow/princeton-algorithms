import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
public class FastCollinearPoints {
    private final List<LineSegment> lineSegments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("points should not be null");
        int n = points.length;
        for (int i = 0; i < n; ++i) {
            if (points[i] == null) throw new IllegalArgumentException("point should not be null");
        }
        Point[] aux = points.clone();
        Arrays.sort(aux);
        for (int i = 1; i < n; ++i) {
            if (aux[i].compareTo(aux[i - 1]) == 0) {
                throw new IllegalArgumentException("no repeated points are allowed");
            }
        }
        Point[] temp = aux.clone();
        lineSegments = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            Point x = aux[i];
            Arrays.sort(temp, x.slopeOrder());
            int j = 2;
            while (j < n) {
                int collinear = 1;
                while (j < n && x.slopeTo(temp[j - 1]) == x.slopeTo(temp[j])) {
                    ++j;
                    ++collinear;
                }
                if (collinear >= 3) {
                    Point z = temp[j - collinear];
                    if (x.compareTo(z) < 0) {
                        lineSegments.add(new LineSegment(x, temp[j - 1]));
                    }
                }
                ++j;
            }
            System.arraycopy(aux, 0, temp, 0, n);
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

