package domain.obstacles;

import java.util.Random;

import domain.*;
import domain.observers.*;
import domain.observers.PlayModeObserver.EventName;
import domain.playMode.*;

public class ExplosiveObstacle extends Obstacle implements INobleCollision {

    private double speed = 6 * 35 * GameLoop.DELTA_TIME;
    private boolean isFalling = false;
    private boolean isExplode;
    ObstacleMover om;
    private static final double movePercent = 0.25;

    public ExplosiveObstacle(ObstacleInfo info) {
        super(info);
        om = new ObstacleMover(this);
        CollisionManager.getInstance().addCollider((INobleCollision) this);
        isExplode = false;
        if (new Random().nextDouble() < movePercent) {
            isMoving = true;
        } else {
            isMoving = false;
        }
    }

    @Override
    public void Update() {
        if (isFalling) {
            y += Math.ceil(speed);
            if (y > Constants.PLAY_HEIGHT)
                death();
        } else if (isMoving)
            om.circularUpdate();
    }

    @Override
    public boolean IsAlive() {
        return !isExplode;
    }

    @Override
    public void collide(Sphere sphere) {
        sphere.ObstacleBounce(this);
        ReduceLife();
    }

    @Override
    public void ReduceLife() {
        if (isFrozen)
            return;
        isFalling = true;
        CollisionManager.getInstance().removeCollider((ISphereCollision) this);
        ObstacleManager.getInstance().removeObstacle(this);
    }

    @Override
    protected void death() {
        super.death();
        isFalling = false;
        isExplode = true;
    }

    @Override
    public void Destroy() {
        super.Destroy();
        CollisionManager.getInstance().removeCollider((INobleCollision) this);
    }

    @Override
    public void collide(Noble noble) {
        PlayModeObserver.getInstance().notify(EventName.CHANCE_DECREASED, null);
        death();
    }

    @Override
    public boolean isCollide(Noble noble) {
        return noble.getBounds().intersects(getBounds());
    }

    @Override
    int getScore() {
        return 15;
    }

}