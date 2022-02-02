package domain.playMode;

import java.util.*;
import domain.*;
import domain.obstacles.*;

public class CollisionManager extends GameObject {
    private static CollisionManager instance;

    private static ArrayList<INobleCollision> nobleCollisions = new ArrayList<>();
    private static ArrayList<IObstacleCollision> obstacleCollisions = new ArrayList<>();
    private static ArrayList<ISphereCollision> sphereCollisions = new ArrayList<>();

    private Noble noble;

    private Sphere sphere;

    private boolean isInit = false;

    private CollisionManager() {
        isInit = false;
    }

    public static CollisionManager getInstance() {
        if (instance == null)
            instance = new CollisionManager();

        return instance;
    }

    public void InitGameObjects(Noble noble, Sphere sphere) {
        isInit = true;
        this.noble = noble;
        this.sphere = sphere;

    }

    public void addCollider(INobleCollision collider) {
        nobleCollisions.add(collider);
    }

    public void addCollider(ISphereCollision collider) {
        boolean isContains = sphereCollisions.contains(collider);
        if (!isContains)
            sphereCollisions.add(collider);
    }

    public void addCollider(IObstacleCollision collider) {
        obstacleCollisions.add(collider);
    }

    public void removeCollider(IObstacleCollision collider) {
        obstacleCollisions.remove(collider);
    }

    public void removeCollider(INobleCollision collider) {
        nobleCollisions.remove(collider);
    }

    public void removeCollider(ISphereCollision collider) {
        sphereCollisions.remove(collider);
    }

    public boolean checkCollider(INobleCollision collider) {
        return nobleCollisions.contains(collider);
    }

    public boolean checkCollider(ISphereCollision collider) {
        return sphereCollisions.contains(collider);
    }

    @Override
    public void Update() {
        if (!isInit)
            return;
        checkNobleCollison();
        checkSphereCollison();
        checkObstacleCollison();
    }

    private void checkObstacleCollison() {
        var obstacles = ObstacleManager.getInstance().getObstacles();
        var size = obstacles.size();

        for (int i = size - 1; i >= 0; i--) {
            var obs = obstacles.get(i);
            for (int j = obstacleCollisions.size() - 1; j >= 0; j--) {
                var collider = obstacleCollisions.get(j);
                var isCollide = collider.isCollide(obs);
                if (isCollide)
                    collider.collide(obs);
            }
        }

    }

    private void checkSphereCollison() {

        for (int i = sphereCollisions.size() - 1; i >= 0; i--) {
            var spCollider = sphereCollisions.get(i);
            var isCollide = spCollider.isCollide(sphere);
            if (isCollide) {
                spCollider.collide(sphere);
            }

        }

    }

    private void checkNobleCollison() {
        for (int i = nobleCollisions.size() - 1; i >= 0; i--) {
            var nobleCollider = nobleCollisions.get(i);
            var isCollide = nobleCollider.isCollide(noble);
            if (isCollide) {
                nobleCollider.collide(noble);
            }
        }

    }

}
