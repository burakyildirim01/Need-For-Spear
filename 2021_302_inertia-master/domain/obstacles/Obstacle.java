package domain.obstacles;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import domain.*;
import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;
import domain.playMode.*;

public abstract class Obstacle extends GameObject implements ISphereCollision {

    public static final int WIDTH = Constants.L / 5;
    public static final int HEIGHT = Constants.L / 5;
    protected int x;
    protected int y;
    protected ObstacleInfo obstacleInfo;
    protected boolean isFrozen;
    protected boolean isMoving;

    public ObstacleTypes type;
    protected int life;

    public Obstacle(ObstacleInfo info) {
        obstacleInfo = info;
        x = info.x;
        y = info.y;
        life = info.life;
        type = info.type;
        isFrozen = false;
        isMoving = false;

        ObstacleManager.getInstance().addObstacle(this);
        CollisionManager.getInstance().addCollider(this);
    }

    @Override
    public boolean isCollide(Sphere sphere) {
        return getBounds().intersects(sphere.getBounds());
    }

    public Rectangle getBounds() {

        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void ReduceLife() {
        if (isFrozen)
            return;
        life--;
        if (!IsAlive()) {
            death();
        }
    }

    protected void death() {
        Destroy();
        addScore();
    }

    private void addScore() {
        PlayModeObserver.getInstance().notify(EventName.ADD_SCORE, getScore());
    }

    @Override
    public void Destroy() {
        super.Destroy();
        ObstacleManager.getInstance().removeObstacle(this);
        CollisionManager.getInstance().removeCollider(this);

    }

    public boolean IsAlive() {
        return life > 0;
    }

    public void instantDeath() {
        if (isFrozen)
            life--;
        else
            life = 0;

        if (!IsAlive()) 
            death();
        
    }

    public int getLife() {
        return life;
    }

    @Override
    public void collide(Sphere sphere) {
        if (!sphere.CanBounce() || !IsAlive())
            return;
        sphere.ObstacleBounce(this);
        if (sphere.getIsUnstoppable())
            instantDeath();
        else
            ReduceLife();

    }

    public static ArrayList<ObstacleInfo> toObstacleInfos(ArrayList<Obstacle> obstacles) {
        ArrayList<ObstacleInfo> rv = new ArrayList<>();
        for (var obs : obstacles) {
            rv.add(obs.obstacleInfo);
        }
        return rv;
    }

    public void setFrozen(boolean isFrozen) {
        this.isFrozen = isFrozen;
    }
    
    public boolean isFrozen() {
        return isFrozen;
    }

    abstract int getScore();

}
