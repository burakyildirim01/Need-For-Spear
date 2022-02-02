package ui.painters;

import java.awt.Graphics;
import java.awt.*;
import domain.abilities.Cannon;

public class CannonPainter implements IGameObjectPainter {

    private Cannon[] cannons;

    public CannonPainter(Cannon[] cannons) {
        this.cannons = cannons;
    }

    @Override
    public void paintComponent(Graphics g) {
        if (!cannons[0].getIsVisible())
            return;

        g.setColor(Color.ORANGE);

        for (int i = 0; i < cannons.length; i++) {
            var bound = cannons[i].getBounds();
            g.fillRect(bound.x, bound.y, bound.width, bound.height);
        }

    }

}
