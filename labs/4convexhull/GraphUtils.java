import java.util.Collection;
import java.util.Iterator;

public class GraphUtils {

    /*
     * finds the bottom most, left most point
     */
    static Point getMinY(Collection<? extends Point> points) {

        Iterator<? extends Point> it = points.iterator();
        Point min = it.next();
        while (it.hasNext()) {
            Point point = it.next();
            if (point.y <= min.y) {
                if (point.y < min.y) {
                    min = point;
                } else if (point.x < min.x) { // point.y==min.y, pick left most one
                    min = point;
                }
            }
        }

        return min;
    }
    /*
     * Line drawn from 'a' to 'b' to 'c'. We make use of the cross product, which calculates the area
     * of the parallelogram. If vector ab is on the left of vector ac, then
     *
     * 1 if counter-clockwise, -1 if clockwise, 0 if collinear
     */
    static int ccw(Point a, Point b, Point c) {
        /*
         * determinant (the cross product) calculates the signed area of parallelogram
         */
        double area = Math.round((b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x));

        if (area < 0) return -1; // clockwise

        if (area > 0) return 1; // counter-clockwise

        return 0; // collinear
    }


    static double dist(Point a, Point b) {
        return Math.sqrt(((a.x - b.x) * (a.x - b.x)) + ((a.y - b.y) * (a.y - b.y)));
    }

}

