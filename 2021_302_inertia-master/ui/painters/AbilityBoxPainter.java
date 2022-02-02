package ui.painters;

import java.awt.Graphics;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import domain.abilities.AbilityBox;
import domain.abilities.AbilityTypes;

import java.awt.*;

public class AbilityBoxPainter implements IGameObjectPainter {

    private ArrayList<AbilityBox> abilityBoxes;
    private BufferedImage[] images;

    public AbilityBoxPainter(ArrayList<AbilityBox> abilityBoxes) {
        this.abilityBoxes = abilityBoxes;
        images = new BufferedImage[4];
        for (int i = 0; i < images.length; i++) {
            try {
                images[i] = ImageIO
                        .read(new File(getPath(i)));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void paintComponent(Graphics g) {

        for (AbilityBox ab : abilityBoxes) {
            var bounds = ab.getBounds();
            g.setColor(Color.WHITE);
            if (ab.getVisible()) {
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
                var idx = ab.getAbilityType().getI();
                g.drawImage(images[idx], bounds.x, bounds.y, bounds.width, bounds.height, null);

            }

        }

        // writeAbilityNames(g);

    }

    private void writeAbilityNames(Graphics g) {
        for (AbilityBox ab : abilityBoxes) {
            var bounds = ab.getBounds();
            g.setColor(Color.WHITE);
            if (ab.getVisible()) {
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
                writeAbilityNames(ab.getAbilityType(), g, bounds, Color.BLACK);
            }

        }
    }

    private void writeAbilityNames(AbilityTypes type, Graphics g, Rectangle rect, Color txtColor) {

        var name = type.getString();
        Graphics2D g2 = (Graphics2D) g;
        int fontSize = 10;
        Font f = new Font("MS Gothic", Font.PLAIN, fontSize);

        FontMetrics metrics = g.getFontMetrics(f);
        int x = rect.x + (rect.width - metrics.stringWidth(name)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2.setFont(f);
        g2.setColor(txtColor);
        g2.drawString(name, x, y);
    }

    private String getPath(AbilityBox abilityBox) {

        return getPath(abilityBox.getAbilityType().getI());
    }

    private String getPath(int i) {

        String path = new File("Assets/Abilities/" + i + ".png")
                .getAbsolutePath();
        return path;
    }

}
