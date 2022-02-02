package domain.obstacles;

import java.util.ArrayList;

import domain.Constants;
import domain.Vector2;
import domain.playMode.GameLoop;

public class ObstacleMover {
    ArrayList<Obstacle> obstacles = ObstacleManager.getInstance().getObstacles();
    private Obstacle obstacle;

    double R = 0.15 * Constants.L;
    double angle = 0;
    int dir = 1;
    private Vector2 center;

    public ObstacleMover(Obstacle obstacle) {
        this.obstacle = obstacle;
        center = new Vector2(obstacle.x, obstacle.y);
    }

    public boolean isCollide(Obstacle target) {

        boolean intersectsWithObstacle = obstacle.getBounds().intersects(target.getBounds());

        return intersectsWithObstacle;
    }

    public void Update() {
        var intersectsWithWall = obstacle.x < 0 || obstacle.x > Constants.PLAY_WIDTH - Obstacle.WIDTH;
        boolean intersectsWithObstacle = false;

        for (Obstacle obs : obstacles) {
            if (obs.equals(obstacle))
                continue;
            if (isCollide(obs)) {
                intersectsWithObstacle = true;
                break;
            }

        }
        if (intersectsWithObstacle || intersectsWithWall)
            dir *= -1;

        obstacle.x += Math.ceil(Constants.L / 4 * GameLoop.DELTA_TIME) * dir;

    }

    public void circularUpdate() {
        for (Obstacle obs : obstacles) {
            if (obs.equals(obstacle))
                continue;
            if (isCollide(obs))
                dir *= -1;

        }
        int speed = 90;
        angle += speed * GameLoop.DELTA_TIME * dir;

        obstacle.x = (int) (Math.cos(Math.toRadians(angle)) * R + center.x);
        obstacle.y = (int) (Math.sin(Math.toRadians(angle)) * R + center.y);

    }

}
