class Point {
    final double x;
    final double y;
    double angle; // calculated later on in reference to some other point

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
//		return "(" + x + ", " + y + ") angle = " + angle;
        return "(" + x + ", " + y + ")";
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Point) {
            Point p = (Point) o;
            return p.x == this.x && p.y == this.y;
        } else {
            return false;
        }
    }
}


