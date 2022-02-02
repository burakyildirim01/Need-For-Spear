package domain.editMode;

import java.util.ArrayList;

import domain.database.Database;
import domain.database.DatabaseObject;
import domain.observers.EditModeObserver;
import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;
import domain.obstacles.Obstacle;
import domain.obstacles.ObstacleFactory;
import domain.obstacles.ObstacleInfo;

public class ObstacleStorage {
    public ArrayList<Obstacle> obstacles = new ArrayList<>();

    private static ObstacleStorage instance;

    private ObstacleStorage() {
        PlayModeObserver.getInstance().subscribe(EventName.LOAD_GAME,
                dObj -> setObstacles(((DatabaseObject) dObj).getObstacleInfosList()));
        PlayModeObserver.getInstance().subscribe(EventName.SAVE_GAME,
                username -> SaveDatabase((String) username));
    }

    public static ObstacleStorage getInstance() {
        if (instance == null)
            instance = new ObstacleStorage();

        return instance;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public ArrayList<ObstacleInfo> getObstacleInfos() {
        return Obstacle.toObstacleInfos(obstacles);
    }

    public void addObstacle(Obstacle obs) {
        obstacles.add(obs);
    }

    public void addObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles.addAll(obstacles);
    }

    public void removeObstacle(Obstacle obs) {
        obstacles.remove(obs);
        obs.Destroy();
    }

    public void setObstacles(ArrayList<ObstacleInfo> infos) {

        this.obstacles = ObstacleFactory.getInstance().createObstacles(infos);
        EditModeObserver.getInstance().notifyObsChange(obstacles);
    }

    private void SaveDatabase(String username) {
        Database.getInstance().saveToDatabase(username, getObstacleInfos());
    }

    public void DeleteAllObstacles() {

        for (int i = obstacles.size() - 1; i >= 0; i--) {
            var obs = obstacles.get(i);
            obs.Destroy();
        }

        obstacles.clear();
    }

}
