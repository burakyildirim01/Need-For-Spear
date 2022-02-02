package domain.playMode;

import domain.obstacles.Obstacle;

public interface IObstacleCollision {
    void collide(Obstacle obstacle);

    boolean isCollide(Obstacle obstacle);

}