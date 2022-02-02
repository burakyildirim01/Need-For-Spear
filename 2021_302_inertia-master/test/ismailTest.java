package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import domain.database.Database;
import domain.database.DatabaseObject;
import domain.abilities.AbilityTypes;
import domain.obstacles.ObstacleInfo;
import domain.obstacles.ObstacleTypes;

public class ismailTest {
    private Database db;
    DatabaseObject dbObj;
    private String username;
    private int score = 5;
    private int chancesRemaining = 10;
    private HashMap<AbilityTypes, Boolean> MagicalAbilitiesMap;
    ArrayList<ObstacleInfo> obstacleInfosList;

    @Before
    public void setup() {
        db = Database.getInstance();
        dbObj = DatabaseObject.getInstance();
        username = "isoTest";
        score = 100;
        chancesRemaining = 3;
        MagicalAbilitiesMap = new HashMap<AbilityTypes, Boolean>();
        MagicalAbilitiesMap.put(AbilityTypes.HEX, true);
        obstacleInfosList = new ArrayList<ObstacleInfo>();
        ObstacleInfo obs = new ObstacleInfo(100, 200, ObstacleTypes.SIMPLE);
        obstacleInfosList.add(obs);
        db.saveToDatabase(username, score, chancesRemaining, MagicalAbilitiesMap, obstacleInfosList);
    }

    @Test
    public void check_file_creation() {
        String filePath = "/DatabaseFiles/" + username + ".json";
        File testFile = new File(filePath);
        boolean bool = testFile.exists();
        assertTrue(bool);
    }

    @Test
    public void check_username() {
        dbObj = db.loadFromDatabase(username);
        System.out.println(dbObj == null);
        String dbName = dbObj.getUsername();
        assertEquals(dbName, username);
    }

    @Test
    public void check_score_value() {
        DatabaseObject dbObj = DatabaseObject.getInstance();
        dbObj = db.loadFromDatabase(username);
        int testScore = dbObj.getScore();
        assertEquals(testScore, score);
    }

    @Test
    public void check_chances_remaining() {
        DatabaseObject dbObj = DatabaseObject.getInstance();
        dbObj = db.loadFromDatabase(username);
        int testChancesRemaining = dbObj.getChancesRemaining();
        assertEquals(testChancesRemaining, chancesRemaining);
    }

    @Test
    public void check_abilities_acquired() {
        DatabaseObject dbObj = DatabaseObject.getInstance();
        dbObj = db.loadFromDatabase(username);
        HashMap<AbilityTypes, Boolean> testMap = dbObj.getMagicalAbilitiesHashMap();
        assertEquals(testMap.get(AbilityTypes.HEX).equals(MagicalAbilitiesMap.get(
                AbilityTypes.HEX)), true);
    }

}
