import java.util.*;
import java.util.List;

/**
 * The Convex Hull of a set of N points is the smallest perimeter fence that encloses all of the points.
 * Alt: The smallest area convex polygon enlosing all of the points.
 *
 * Implementation of "Graham scan" algorithm:
 * 1. find the point p with the smallest y-coordinate
 * 2. sort the point by polar angle from p
 * 3. iterate over the points in sorted order, discard unless a counter clockwise turn is created
 * 3.1 make sure that the sort places collinear points in expected sequence
 *
 * video tutorial at: https://youtu.be/B2AJoQSZf4M
 * @author Andre Violentyev
 */
public class GrahamScan {

    /**
     * The comparator just compares the cross product of two vectors to see which one is on the
     * left side and which is on the right side. Actual angles don't need to be calculated.
     *
     * @param points
     * @param ref
     */
    private void sortByAngle(List<? extends Point> points, Point ref) {
        Collections.sort(points, (b, c) -> {
            /*
             * the ref point should always be pushed to the beginning
             */
            if (b == ref) return -1;
            if (c == ref) return 1;

            int ccw = GraphUtils.ccw(ref, b, c);
            if (ccw == 0) {
                /*
                 * Handle collinear points. We can just use the x coordinate and not
                 * bother with the y since the ratio of y/x is going to be the same
                 */
                if (Float.compare(b.x, c.x) == 0) {
                    /*
                     * rare case of floats matching up in a vertical line, we want
                     * the closer point to be first
                     */
                    return b.y < c.y ? -1 : 1;
                } else {
                    return b.x < c.x ? -1 : 1;
                }
            } else {
                return ccw * -1;
            }
        });
    }
    /**
     * The main algorithm.
     *
     * @param points
     * @return
     */
    public List<Point> scan(List<? extends Point> points) {
        Deque<Point> stack = new ArrayDeque<>();

        /*
         * bottom most, left most point is guaranteed to be on the hull
         */
        Point minYPoint = GraphUtils.getMinY(points);
        sortByAngle(points, minYPoint); // sort by angle with respect to minYPoint

        stack.push(points.get(0)); // 1st point is guaranteed to be on the hull
        stack.push(points.get(1)); // don't know about this one yet
        for (int i = 2, size = points.size(); i < size; i++) {
            Point next = points.get(i);
            Point p = stack.pop();

            while (stack.peek() != null && GraphUtils.ccw(stack.peek(), p, next) <= 0) {
                p = stack.pop(); // delete points that create clockwise turn
            }

            stack.push(p);
            stack.push(points.get(i));
        }

        /*
         * the very last point pushed in could have been collinear, so we check for that
         */
        Point p = stack.pop();
        if (GraphUtils.ccw(stack.peek(), p, minYPoint) > 0) {
            stack.push(p); // put it back, everything is fine
        }

        return new ArrayList<>(stack);
    }

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();

       /* points.add(new Point(2, 2));
        points.add(new Point(-2, 3));
        points.add(new Point(1, 1));*/

        Scanner scan = new Scanner(System.in);

        scan.nextInt(); //dimension, always 2
        int h = scan.nextInt();
        System.out.println("h: " + h);
        scan.nextLine();

        while(scan.hasNext()){

            String[] input = scan.nextLine().split(" ");
            // OMVANDLING TILL FLOAT??
            points.add(new Point(Float.valueOf(input[3]), Float.valueOf(input[4])));
            System.out.println("x: " + input[3] + ", y: " + input[4]);
        }
        GrahamScan hull = new GrahamScan();

        List<Point> res = hull.scan(points);
        System.out.println(res.size());


        for(Point p : res) {
            print(p.x, p.y); //Allmänt lite olika i outputen. Om talet är 0000, gör till int.
            //Annars avrunda till några decimaler.
        }

    }
    private static void print(float x, float y) {
        if (x % 1 == 0) {
            System.out.println((int)x + " " + (int)y);
        }
        else {
            System.out.format("%.3f", x);
            System.out.print(" ");
            System.out.format("%.3f", y);
        }
    }
}