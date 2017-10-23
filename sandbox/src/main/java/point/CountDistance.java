package point;

/**
 * Created by a.oreshnikova on 23.10.17.
 */

public class CountDistance {

    public static void main(String[] args) {
        Point p1 = new Point(-5, 6);
        Point p2 = new Point(2, 7);

        System.out.println("Расстояние между двумя точками = " + distance(p1, p2));


        System.out.println("Расстояние между двумя точками (" + p1.x + ", " + p1.y + ") и (" + p2.x + ", " + p2.y+ ") = "
                + p1.distance(p2));
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
    }

}
