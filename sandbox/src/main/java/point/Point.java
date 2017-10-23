package point;

/**
 * Created by a.oreshnikova on 23.10.17.
 */

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point otherPoint) {
        return Math.sqrt(Math.pow((this.x - otherPoint.x), 2) + Math.pow((this.y - otherPoint.y), 2));
    }
}
