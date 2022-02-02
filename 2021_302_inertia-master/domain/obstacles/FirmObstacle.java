package domain.obstacles;

import java.util.Random;

public class FirmObstacle extends Obstacle {
    private int startLife;
    ObstacleMover om;
    private static final double movePercent = 0.25;

    public FirmObstacle(ObstacleInfo info) {
        super(info);
        om = new ObstacleMover(this);
        startLife = info.life;
        if (new Random().nextDouble() < movePercent) {
            isMoving = true;
        } else {
            isMoving = false;
        }

    }

    @Override
    int getScore() {
        return startLife * 10;
    }

    @Override
    public void Update() {
        if (isMoving)
            om.Update();
    }

}
