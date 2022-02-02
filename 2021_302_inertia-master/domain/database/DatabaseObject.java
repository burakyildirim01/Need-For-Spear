package domain.database;

import java.util.*;

import domain.abilities.AbilityTypes;
import domain.obstacles.*;

public class DatabaseObject {

    private String username;
    private int score;
    private int chancesRemaining;
    private HashMap<AbilityTypes, Boolean> magicalAbilitiesMap;
    private ArrayList<ObstacleInfo> obstacleInfosList;

    private static DatabaseObject dbr = null;

    private DatabaseObject() {
    }

    public static DatabaseObject getInstance() {
        if (dbr == null) {
            dbr = new DatabaseObject();
        }
        return dbr;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getChancesRemaining() {
        return this.chancesRemaining;
    }

    public void setChancesRemaining(int chancesRemaining) {
        this.chancesRemaining = chancesRemaining;
    }

    public HashMap<AbilityTypes, Boolean> getMagicalAbilitiesHashMap() {
        return this.magicalAbilitiesMap;
    }

    public void setMagicalAbilitiesArray(HashMap<AbilityTypes, Boolean> magicalAbilitiesArray) {
        this.magicalAbilitiesMap = magicalAbilitiesArray;
    }

    public void setValue(HashMap<AbilityTypes, Boolean> map) {
        this.magicalAbilitiesMap = map;
    }

    public ArrayList<ObstacleInfo> getObstacleInfosList() {
        return this.obstacleInfosList;
    }

    public void setObstacleInfosList(ArrayList<ObstacleInfo> obstacleInfosList) {
        this.obstacleInfosList = obstacleInfosList;
    }
}