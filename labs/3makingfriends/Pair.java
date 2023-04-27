public class Pair {
    public int w, a, b;

    public Pair(int w, int a, int b) {
        this.w=w; // Weight aka time to get to know each other
        this.a=a; // int representing person a
        this.b=b; // Inte representing person b

    }

    public String toString() {
        return "" + this.w;
    }
}
