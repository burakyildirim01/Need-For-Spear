package domain.abilities;

import java.awt.*;

import domain.playMode.*;
import domain.GameObject;

public class Cannon extends GameObject {
    private int x;
    private int y;
    private int width = 10;
    private int height = 15;
    private int fireDelay = 1000 / 1;
    private int elapsedTime = 0;

    public boolean isVisible;

    public Cannon(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        isVisible = false;
    }

    public Bullet createBullet() {
        Bullet bullet = new Bullet(x + width / 2, y);
        return bullet;
    }

    @Override
    public void Update() {
        if (!isVisible)
            return;
        elapsedTime += GameLoop.LOOP_DELAY;
        if (elapsedTime > fireDelay) {
            createBullet();
            elapsedTime = 0;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void setBounds(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean getIsVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
