package domain.ymir;

import java.util.Random;

import domain.Constants;
import domain.observers.PlayModeObserver.EventName;
import domain.observers.*;
import domain.obstacles.*;
import java.awt.*;

public class HollowPurple extends YmirAbility {

    @Override
    public void performAbility() {
        super.performAbility();

        var obstacles = ObstacleManager.getInstance().getObstacles();

        for (int i = 0; i < 8; i++) {
            int x, y;
            while (true) {
                y = (new Random()).nextInt(Constants.PLAY_HEIGHT - Constants.NOBLE_DISTANCE - 50 - Obstacle.HEIGHT);
                x = (new Random()).nextInt(Constants.PLAY_WIDTH - Obstacle.WIDTH);
                var rect = new Rectangle(x, y, Obstacle.WIDTH, Obstacle.HEIGHT);

                var isIntersects = false;
                for (Obstacle obstacle : obstacles) {
                    if (obstacle.getBounds().intersects(rect)) {
                        isIntersects = true;
                        break;
                    }
                }
                if (!isIntersects)
                    break;

            }

            var info = new ObstacleInfo(x, y, ObstacleTypes.HOLLOW);
            var obs = ObstacleFactory.getInstance().createObstacle(info);
            PlayModeObserver.getInstance().notify(EventName.ADD_OBSTACLE, obs);
        }

    }

    @Override
    protected void deactivateAbility() {
        super.deactivateAbility();
    }

    @Override
    public String toString() {
        return "Hollow Purple";
    }

}
