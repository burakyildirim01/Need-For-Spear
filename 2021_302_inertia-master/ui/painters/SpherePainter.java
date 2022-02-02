package ui.painters;


import java.awt.*;
import domain.playMode.*;

public class SpherePainter implements IGameObjectPainter {

    private Sphere sphere;

    public SpherePainter(Sphere sphere) {
        this.sphere = sphere;
    }

    @Override
    public void paintComponent(Graphics g) {
        Rectangle bounds = sphere.getBounds();
        if (sphere.getIsUnstoppable())
            g.setColor(Color.ORANGE);
        else
            g.setColor(Color.red);
        g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
    }

}
