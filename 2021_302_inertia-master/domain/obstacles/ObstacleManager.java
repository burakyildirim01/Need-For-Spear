package domain.obstacles;
import java.util.*;

public class ObstacleManager {

    private static final ObstacleManager om = new ObstacleManager();
    private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

    private ObstacleManager() {
    };

    public static ObstacleManager getInstance() {
        return om;
    }

    public void removeObstacle(Obstacle obs) {
        if (obstacles.contains(obs))
            obstacles.remove(obs);
    }

    public void addObstacle(Obstacle obs) {
        obstacles.add(obs);
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }
    
    public void DeleteAllObstacles() {

        for (int i = obstacles.size()-1; i >=0; i--) {
            obstacles.get(i).Destroy();
        }
        obstacles = new ArrayList<Obstacle>();
        
    }

}
