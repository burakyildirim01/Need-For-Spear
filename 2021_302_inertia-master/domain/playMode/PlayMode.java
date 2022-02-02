package domain.playMode;

import java.util.*;

import javax.swing.*;
import domain.abilities.*;
import domain.database.Database;
import domain.database.DatabaseObject;
import domain.editMode.ObstacleStorage;
import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;
import domain.obstacles.*;
import domain.ymir.Ymir;

public class PlayMode {

    private JFrame frame;
    private Noble noble;
    public Player player;
    private Sphere sphere;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Bullet> bullets;
    private Cannon[] cannons;
    private ILooper loop;
    private DatabaseObject databaseObject;

    public PlayMode(JFrame frame, DatabaseObject databaseObject) {

        this.frame = frame;
        this.databaseObject = databaseObject;

        PlayModeObserver.getInstance().subscribe(EventName.LOAD_GAME,
                dObj -> setObstacles(((DatabaseObject) dObj).getObstacleInfosList()));
        PlayModeObserver.getInstance().subscribe(EventName.SAVE_GAME,
                userName -> SaveDatabase((String) userName));

    }

    public void StartPlayMode() {
        cannons = createCannons();
        noble = new Noble(cannons);
        sphere = new Sphere(noble);
        player = new Player(noble);

        AbilityManager.getInstance().InitDependency(noble, sphere, player);
        CollisionManager.getInstance().InitGameObjects(noble, sphere);

        var infos = ObstacleStorage.getInstance().getObstacleInfos();
        ObstacleStorage.getInstance().DeleteAllObstacles();
        ObstacleStorage.getInstance().setObstacles(infos);
        obstacles = ObstacleStorage.getInstance().getObstacles();

        loop = new GameLoop();
        player.getScoreManager().InitDependency(loop);

        loop.StartLoop();
        PauseHandler.getInstance().addLoop(loop);

        if (databaseObject != null) {
            PlayModeObserver.getInstance().notify(EventName.LOAD_GAME, databaseObject);
        }

        new Ymir(sphere);
        new WinLoseManager(player.getScoreManager());

    }

    private void SaveDatabase(String userName) {
        System.out.println("test");
        int score = getPlayer().getScore();
        int chancesRemaining = getPlayer().getChances();
        ArrayList<ObstacleInfo> obstacleInfosList = Obstacle
                .toObstacleInfos(ObstacleManager.getInstance().getObstacles());
        HashMap<AbilityTypes, Boolean> MagicalAbilitiesMap = AbilityStorage.getInstance().getAcquiredAbilities();

        Database.getInstance().saveToDatabase(userName, score, chancesRemaining, MagicalAbilitiesMap,
                obstacleInfosList);
    }

    public Noble getNoble() {
        return noble;
    }

    public Sphere getSphere() {
        return sphere;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(ArrayList<ObstacleInfo> obstacleInfos) {
        ObstacleManager.getInstance().DeleteAllObstacles();
        obstacles = ObstacleFactory.getInstance().createObstacles(obstacleInfos);

        PlayModeObserver.getInstance().notify(EventName.UPDATE_OBSTACLES, obstacles);
    }

    public ArrayList<AbilityBox> getAbilityBoxes() {
        return AbilityBoxFactory.getInstance().getAbilityBoxes();
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public Cannon[] createCannons() {

        return new Cannon[] { new Cannon(0, 0), new Cannon(0, 0) };
    }

    public Cannon[] getCanons() {

        return cannons;
    }

    public Player getPlayer() {
        return player;
    }

    public ILooper getLoop() {
        return this.loop;
    }

    public void setLoop(ILooper loop) {
        this.loop = loop;
    }

}
