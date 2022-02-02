package domain.editMode;

import domain.obstacles.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class ObstaclePlacer {
    private static ObstaclePlacer instance;
    ObstacleTypes selectedObs = ObstacleTypes.SIMPLE;
    Random rand = new Random();
    int life;

    private ObstaclePlacer() {

    }

    public static ObstaclePlacer getInstance() {
        if (instance == null)
            instance = new ObstaclePlacer();

        return instance;
    }

    public boolean isIntersect(Obstacle createdObs) {
        for (Obstacle oldObs : ObstacleStorage.getInstance().getObstacles()) {
            if (createdObs.getBounds().intersects(oldObs.getBounds())) {
                return false;
            }
        }
        return true;
    }

    public void placeObs(int x, int y, ObstacleTypes type) {
        if (type == ObstacleTypes.FIRM) {
            life = rand.nextInt(5) + 1;
            ObstacleGenerator.getInstance().create(new ObstacleInfo(x, y, type, life));
        } else {
            ObstacleGenerator.getInstance().create(new ObstacleInfo(x, y, type));
        }

    }

    public void removeObs(int x, int y) {
        ArrayList<Obstacle> obstacles = ObstacleStorage.getInstance().getObstacles();
        int size = obstacles.size();
        for (int i = size - 1; i >= 0; i--) {
            Obstacle oldObs = obstacles.get(i);
            if (oldObs.getBounds().contains(x, y)) {
                ObstacleStorage.getInstance().removeObstacle(oldObs);
                return;
            }
        }

      
    }

    public ObstacleTypes selectType() {
        EditModeInputManager.getInstance().ListenKeyPressed(e -> selectedObs = ObstacleTypes.SIMPLE,
                KeyEvent.VK_S);
        EditModeInputManager.getInstance().ListenKeyPressed(e -> selectedObs = ObstacleTypes.FIRM,
                KeyEvent.VK_F);
        EditModeInputManager.getInstance().ListenKeyPressed(e -> selectedObs = ObstacleTypes.GIFT,
                KeyEvent.VK_G);
        EditModeInputManager.getInstance().ListenKeyPressed(e -> selectedObs = ObstacleTypes.EXPLOSIVE,
                KeyEvent.VK_E);
        return selectedObs;
    }

    public void unlistenSelectedObs() {
        EditModeInputManager.getInstance().unListenKeyPressed(
                KeyEvent.VK_S);
        EditModeInputManager.getInstance().unListenKeyPressed(
                KeyEvent.VK_F);
        EditModeInputManager.getInstance().unListenKeyPressed(
                KeyEvent.VK_G);
        EditModeInputManager.getInstance().unListenKeyPressed(
                KeyEvent.VK_E);
    }

}
