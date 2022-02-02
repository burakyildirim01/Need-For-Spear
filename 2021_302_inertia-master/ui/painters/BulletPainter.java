package ui.painters;

import java.awt.Graphics;
import java.awt.*;
import domain.abilities.Bullet;

public class BulletPainter implements IGameObjectPainter {

    public BulletPainter() {

    }

    @Override
    public void paintComponent(Graphics g) {
        var bullets = Bullet.bullets;
        g.setColor(Color.RED);
        for (Bullet bullet : bullets) {
            g.fillRect(bullet.getBounds().x, bullet.getBounds().y, bullet.getBounds().width, bullet.getBounds().height);
        }

    }

}
