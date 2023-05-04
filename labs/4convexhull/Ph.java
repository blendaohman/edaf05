public class Ph {

    public double k(Point p, Point q){
        return(q.y - p.y)/(q.x-p.x);
    }

    public double [] computeK(Point[] c){
        int n = c.length;
        double [] a = new double[n];
        for(int i = 0; i < n; i++){
            a[i] = k(c[i], c[i + 1 % n]);
        }
        return a;
    }

    public int add(int k, int from, int to, double [] q, double [] p, int n){
        int j = from;
        int i = j;
        while(i != to){
        q[k] = p[j];
        k = k + 1;
        i = j;
        j = (j + 1) % n;
    }
        return k;
    }
    

    public int leftmost(Point[] c){
        int n = c.length;
        int i = 0;
        for(int k = 0; k < n; k++){
            if(c[k].x < c[i].x){
                i = k;
            }
        }
        if(i == 0){
            i = 1;
        }
        return i;
    }

    //determine if p is on the line segment between q and r.
    public boolean lineSegment(Point p, Point q, Point r){
        if ccw(p, q, r) == 0 ? true : false;
    }
    /*
    function line_segment (p, q, r)
begin
/* determine if p is on the line segment between q and r.
u ← qp
v ← qr
w = u × v
return w = 0 and u · v > 0 and u · v < v · v
end
*/

    public int include_points(Point[] p, Point[] q, int j, int n){
        int i = 0;
        for(int k = 0; k < n; k++){
            int u = (n + k + j - 1) % n;
            int v = (n + k + j - 0) % n;
            int w = (n + k + j + 1) % n;
            if (!lineSegment(q[v], q[u], q[w])){
                p[i] = q[v];
                i = i + 1;
            }
        }
        return i;
    }

    /*
    function preparata_hong (p)
begin
sort p in order of increasing y coordinate
(n, iL
, CH) ← dc (p)
return (n, CH)
end
     */

    public prepHong(Point[] p){

    }

}
