package ui.painters;

import java.awt.*;
import java.util.*;
import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;
import domain.obstacles.*;
import domain.observers.*;

public class ObstaclesPainter implements IGameObjectPainter {

    private ArrayList<Obstacle> obstacles;

    public ObstaclesPainter(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
        EditModeObserver.getInstance().subscribeObsChange(this::updateObstacles);
        PlayModeObserver.getInstance().subscribe(EventName.UPDATE_OBSTACLES,
                _obstacles -> updateObstacles((ArrayList<Obstacle>) _obstacles));
        PlayModeObserver.getInstance().subscribe(EventName.ADD_OBSTACLE,
                obs -> addObstacle((Obstacle) obs));
    }

    @Override
    public void paintComponent(Graphics g) {
        for (Obstacle obs : obstacles) {
            switch (obs.type) {

                case EXPLOSIVE:
                    explosivePaint(obs, g);
                    break;
                case FIRM:
                    firmPaint(obs, g);
                    break;
                case GIFT:
                    giftPaint(obs, g);
                    break;
                case HOLLOW:
                    hollowPaint(obs, g);
                    break;
                default:
                case SIMPLE:
                    simplePaint(obs, g);
                    break;

            }
        }

    }

    private void hollowPaint(Obstacle obs, Graphics g) {
        if (!obs.IsAlive())
            return;

        var bounds = obs.getBounds();

        setObstacleColor(g, obs, Color.decode("#8031A7"));
        g.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 5, 5);
    }

    private void simplePaint(Obstacle obs, Graphics g) {
        if (!obs.IsAlive())
            return;

        var bounds = obs.getBounds();

        setObstacleColor(g, obs, Color.GREEN);
        g.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 5, 5);

    }

    private void setObstacleColor(Graphics g, Obstacle obs, Color normalColor) {
        if (obs.isFrozen())
            g.setColor(Color.decode("#C8E9E9"));
        else
            g.setColor(normalColor);

    }

    private void firmPaint(Obstacle obs, Graphics g) {

        if (!obs.IsAlive())
            return;

        var bounds = obs.getBounds();

        setObstacleColor(g, obs, Color.orange);
        g.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 5, 5);

        DrawLives(obs.getLife(), g, obs.getBounds(), Color.black);
    }

    private void DrawLives(int life, Graphics g, Rectangle rect, Color txtColor) {
        String lifeStr = String.valueOf(life);

        Graphics2D g2 = (Graphics2D) g;
        int fontSize = 10;
        Font f = new Font("MS Gothic", Font.PLAIN, fontSize);

        FontMetrics metrics = g.getFontMetrics(f);
        int x = rect.x + (rect.width - metrics.stringWidth(lifeStr)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2.setFont(f);
        g2.setColor(txtColor);
        g2.drawString(lifeStr, x, y);
    }

    private void explosivePaint(Obstacle obs, Graphics g) {
        if (!obs.IsAlive())
            return;
        var bounds = obs.getBounds();

        setObstacleColor(g, obs, Color.BLACK);
        g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    private void giftPaint(Obstacle obs, Graphics g) {
        if (!obs.IsAlive())
            return;

        var bounds = obs.getBounds();

        setObstacleColor(g, obs, Color.BLUE);
        g.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 5, 5);

    }

    private void updateObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    private void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

}
