package domain.obstacles;

public class HollowObstacle extends Obstacle {

    public HollowObstacle(ObstacleInfo info) {
        super(info);
        life = 1;
    }

    @Override
    int getScore() {
        return 0;
    }

}
