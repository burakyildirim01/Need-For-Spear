package domain.obstacles;
import domain.observers.EditModeObserver;
import domain.editMode.*;

public class ObstacleGenerator {
    private static ObstacleGenerator instance;

    private ObstacleGenerator() {
    }

    public static ObstacleGenerator getInstance() {
        if (instance == null) {
            instance = new ObstacleGenerator();
        }
        return instance;
    }

    public void create(ObstacleInfo createdObstacle) {
        var obs = ObstacleFactory.getInstance().createObstacle(createdObstacle);
        addToStorage(obs);
        updateObstacles();
    }

    private void addToStorage(Obstacle obs) {
        ObstacleStorage.getInstance().addObstacle(obs);
    }

    private void updateObstacles() {
        var obstacles = ObstacleStorage.getInstance().getObstacles();
        EditModeObserver.getInstance().notifyObsChange(obstacles);
    }

}
