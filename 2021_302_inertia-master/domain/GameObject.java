package domain;

import java.util.*;

public abstract class GameObject {

    private static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    public GameObject() {
        GameObject.gameObjects.add(this);
    }

    public void Awake() {

    }

    public void Start() {

    }

    public void Update() {

    }

    public static ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void Destroy() {
        gameObjects.remove(this);
    }

    public static void DestroyAll(){
        gameObjects.clear();
    }

}