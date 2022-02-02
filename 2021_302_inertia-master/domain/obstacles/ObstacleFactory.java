package domain.obstacles;
import java.util.*;



public class ObstacleFactory {

    private static ObstacleFactory instance;

    private ObstacleFactory() {

    }

    public static ObstacleFactory getInstance() {
        if (instance == null)
            instance = new ObstacleFactory();

        return instance;
    }

    public Obstacle createObstacle(ObstacleInfo info) {
        switch (info.type) {
            case HOLLOW:
                return new HollowObstacle(info);
            case EXPLOSIVE:
                return new ExplosiveObstacle(info);
            case FIRM:
                return new FirmObstacle(info);
            case GIFT:
                return new GiftObstacle(info);
            default:
            case SIMPLE:
                return new SimpleObstacle(info);

        }

    }

    public ArrayList<Obstacle> createObstacles(ArrayList<ObstacleInfo> infos) {
        var obstacles = new ArrayList<Obstacle>();
        for (var info : infos) {
            obstacles.add(createObstacle(info));
        }

        return obstacles;
    }

}
