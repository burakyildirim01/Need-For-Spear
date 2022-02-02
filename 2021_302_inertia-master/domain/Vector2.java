package domain;

public class Vector2 {

    public double x;
    public double y;

    public static final Vector2 ZERO = new Vector2(0, 0);

    public Vector2(double x, double y) {
        // OVERVIEW: Vectors consist of two real numbers. They have two cordinates such
        // x-axis and y-axis.
        // A typical vector consist of (x_1, y_1).

        // The abstraction function is
        // AF(c) = (c.x, c.y)}
        // where
        // x and y is a real number and c is a two dimensional vector.

        // The rep invariant is
        // all elements of vector are Real Numbers

        this.x = x;
        this.y = y;
    }

    public double GetRadian() {

        return Math.atan2(y + 0.001, x + 0.001);

    }

    public double GetDegree() {

        return Math.toDegrees(Math.atan2(y + 0.001, x + 0.001));

    }

    public Vector2 rotateVector(double angleDeg) {
        return rotateVector(Vector2.ZERO, angleDeg);
    }

    public Vector2 rotateVector(Vector2 center, double angleDeg) {
        double angleRad = (angleDeg / 180) * Math.PI;
        double cosAngle = Math.cos(angleRad);
        double sinAngle = Math.sin(angleRad);
        double dx = (x - center.x);
        double dy = (y - center.y);

        x = center.x + (int) (dx * cosAngle - dy * sinAngle);
        y = center.y + (int) (dx * sinAngle + dy * cosAngle);
        return this;
    }

    public double getMagnituide() {
        return Math.sqrt(x * x + y * y);
    }

    public boolean repOk() {
        // EFFECTS: Returns true if the rep invariant holds for the condition otherwise
        // returns false.

        if (Double.valueOf(x) instanceof Double && Double.valueOf(y) instanceof Double) {
            return true;
        }
        return false;
    }

}