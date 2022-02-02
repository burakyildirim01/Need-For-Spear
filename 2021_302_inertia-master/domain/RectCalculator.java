package domain;

import java.awt.*;

public class RectCalculator {
    public enum RectPosition {
        RIGHT, LEFT, UP, DOWN

    }

    public static Vector2 GetNormalVector(Rectangle main, Rectangle dynamic, boolean CheckIntersect) {

        if (!main.intersects(dynamic) && CheckIntersect)
            return null;

        RectPosition h = null;
        RectPosition v = null;

        if (main.getMinY() >= dynamic.getMinY()) {
            v = RectPosition.UP;
        } else if (main.getMaxY() <= dynamic.getMaxY()) {
            v = RectPosition.DOWN;
        }
        if (main.getMinX() >= dynamic.getMinX()) {
            h = RectPosition.LEFT;

        } else if (main.getMaxX() <= dynamic.getMaxX()) {
            h = RectPosition.RIGHT;
        }

        var normal = new Vector2(0, 0);

        if (h != null) {
            normal.x = h == RectPosition.RIGHT ? 1 : -1;
        }
        if (v != null) {
            normal.y = v == RectPosition.UP ? -1 : 1;
        }
        return normal;

    }

    public static Point TopLeft(Rectangle r1) {
        return new Point(r1.x, r1.y);
    }

    public static Point TopRight(Rectangle r1) {
        return new Point(r1.x + r1.width, r1.y);
    }

    public static Point TopMiddle(Rectangle r1) {
        return new Point(r1.x + r1.width / 2, r1.y);
    }

    public static Point BottomLeft(Rectangle r1) {
        return new Point(r1.x, r1.y + r1.height);
    }

    public static Point BottomRight(Rectangle r1) {
        return new Point(r1.x + r1.width, r1.y + r1.height);
    }

    public static Point BottomMiddle(Rectangle r1) {
        return new Point(r1.x + r1.width / 2, r1.y + r1.height / 2);
    }

    public static Point MiddleRight(Rectangle r1) {
        return new Point(r1.x + r1.width, r1.y + r1.height / 2);
    }

    public static Point MiddleLeft(Rectangle r1) {
        return new Point(r1.x, r1.y + r1.height / 2);
    }


}
