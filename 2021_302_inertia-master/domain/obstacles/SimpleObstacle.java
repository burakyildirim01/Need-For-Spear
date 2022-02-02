package domain.obstacles;

import java.util.Random;

public class SimpleObstacle extends Obstacle {
    private static final double movePercent = 0.25;
    ObstacleMover om;

    public SimpleObstacle(ObstacleInfo info) {
        super(info);
        om = new ObstacleMover(this);
        if (new Random().nextDouble() < movePercent) {
            isMoving = true;
        } else {
            isMoving = false;
        }
    }

    @Override
    int getScore() {
        return 10;
    }

    @Override
    public void Update() {
        if (isMoving)
            om.Update();
    }

}
