package domain.abilities;

import java.awt.*;
import java.util.*;
import domain.*;
import domain.playMode.*;
import domain.obstacles.*;

public class Bullet extends GameObject implements IObstacleCollision {
    private int x;
    private int y;
    private int width = 5;
    private int height = 5;

    public static ArrayList<Bullet> bullets = new ArrayList<>();

    private final double speed = 3 * 35 * GameLoop.DELTA_TIME;;

    public Bullet(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        AddBullet(this);
        CollisionManager.getInstance().addCollider(this);
    }

    public void goUp() {
        y -= speed;
        if (y < 0) {
            Death();
        }
    }

    @Override
    public void Update() {
        goUp();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public static void AddBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public static void RemoveBullet(Bullet bullet) {
        bullets.remove(bullet);
    }

    @Override
    public void collide(Obstacle obstacle) {
        obstacle.ReduceLife();
        Death();
    }

    private void Death() {
        CollisionManager.getInstance().removeCollider(this);
        RemoveBullet(this);
        Destroy();
    }

    @Override
    public boolean isCollide(Obstacle obstacle) {
        return obstacle.getBounds().intersects(getBounds());
    }

}
