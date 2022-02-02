package domain;

import java.awt.*;

public class Utils {

    public static boolean IsZero(double val, double threshold) {
        return Math.abs(val) < threshold;
    }

    public static void Print(Object... objects) {
        String print = "";

        for (Object object : objects) {
            print += object.toString();
            print += " ";
        }

        System.out.println(print);

    }

    public static Polygon rotatePolygon(Polygon poly, double degrees) {
        var bounds = poly.getBounds2D();
        var center = new Vector2(bounds.getCenterX(), bounds.getCenterY());

        for (int i = 0; i < poly.npoints; i++) {
            var point = new Vector2(poly.xpoints[i], poly.ypoints[i]);
            var newPoint = point.rotateVector(center, degrees);

            poly.xpoints[i] = (int) Math.ceil(newPoint.x);
            poly.ypoints[i] = (int) Math.ceil(newPoint.y);
        }

        return poly;

    }
}
