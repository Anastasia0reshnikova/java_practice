package point;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by a.aoreshnikova on 28.10.17.
 */
public class DistanceTests {

    @Test
    public void distanceTest1() {
        Point point1 = new Point(10, -5);
        Point point2 = new Point(0, 1);
        Assert.assertNotEquals(point1.distance(point2), 0);
        Assert.assertEquals(point1.distance(point2), 11.661903789690601);
    }

    @Test
    public void distanceTest2() {
        Point point1 = new Point(0, 0);
        Point point2 = new Point(1, 1);
        Assert.assertEquals(point1.distance(point2), 1.4142135623730951);
        Assert.assertFalse(point1.distance(point2) < 1);
    }

    @Test
    public void distanceTest3() {
        Point point1 = new Point(-1, -1);
        Point point2 = new Point(9, -9);
        Assert.assertEquals((int)(Math.round(point1.distance(point2))), 13);
        Assert.assertTrue(point1.distance(point2) > 12);
    }
}
