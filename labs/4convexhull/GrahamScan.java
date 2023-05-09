import java.util.*;
import java.util.List;

public class GrahamScan {

    private void sortByAngle(List<Point> points, Point ref) {
        Collections.sort(points, (b, c) -> {
            /*
             * the ref point should always be pushed to the beginning
             */
            if (b == ref) return -1;
            if (c == ref) return 1;

            int ccw = GraphUtils.ccw(ref, b, c);
            //Handle collinear points.
            if (ccw == 0) {
                double distB = GraphUtils.dist(b, ref);
                double distC = GraphUtils.dist(c, ref);
                return distB > distC ? 1 : -1;

            } else {
                return ccw * -1;
            }
        });
    }
    public List<Point> scan(List<Point> points) {
        Deque<Point> stack = new ArrayDeque<>();

        /*
         * bottom most, left most point is guaranteed to be on the hull
         */
        Point minYPoint = GraphUtils.getMinY(points);
        sortByAngle(points, minYPoint); // sort by angle with respect to minYPoint
        for(Point p: points) {
            //System.out.println("x: " + p.x + " y: " + p.y);
        }


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

        Scanner scan = new Scanner(System.in);

        scan.nextInt(); //dimension, always 2
        int h = scan.nextInt();
        scan.nextLine();

        while(scan.hasNext()){

            String[] input = scan.nextLine().split(" ");
            points.add(new Point(Double.valueOf(input[3]), Double.valueOf(input[4])));

        }
        GrahamScan hull = new GrahamScan();

        List<Point> res = hull.scan(points);
        System.out.println(res.size());


        for(Point p : res) {
            print(p.x, p.y);
        }

        scan.close();

    }

    // Printa olika beroende på decimalerna
    private static void print(double x, double y) {
        if (x % 1 == 0) {
            System.out.println((int)x + " " + (int)y);
        }
        else {
            System.out.println(String.format(Locale.US, "%.3f", x) + " " + String.format(Locale.US, "%.3f", y));
        }
    }
}