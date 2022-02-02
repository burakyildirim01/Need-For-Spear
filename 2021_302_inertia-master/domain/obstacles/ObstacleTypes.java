package domain.obstacles;
import java.util.*;

public enum ObstacleTypes {
    SIMPLE(0), FIRM(1), EXPLOSIVE(2), GIFT(3), HOLLOW(4);

    private int i;

    private static final Map<Integer, ObstacleTypes> map = new HashMap<>(values().length, 1);

    static {
        for (ObstacleTypes otype : values())
            map.put(otype.i, otype);
    }

    private ObstacleTypes(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public ObstacleTypes getObstacleType(int i) {
        return map.get(i);
    }

}
