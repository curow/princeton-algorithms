/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private final List<LineSegment> lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
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
        lineSegments = new ArrayList<>();
        for (int p = 0; p < n; ++p) {
            for (int q = p + 1; q < n; ++q) {
                double slope1 = aux[p].slopeTo(aux[q]);
                for (int r = q + 1; r < n; ++r) {
                    double slope2 = aux[p].slopeTo(aux[r]);
                    if (slope1 != slope2) continue;
                    for (int s = r + 1; s < n; ++s) {
                        double slope3 = aux[p].slopeTo(aux[s]);
                        if (slope1 == slope3) {
                            lineSegments.add(new LineSegment(aux[p], aux[s]));
                            break;
                        }
                    }
                }
            }
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
