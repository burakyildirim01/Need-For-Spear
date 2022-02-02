package domain.database;

import com.google.gson.*;

import domain.abilities.AbilityTypes;
import domain.obstacles.ObstacleInfo;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Database implements DatabaseAdapter {
    private static Database database = null;

    private Database() {
    }

    public static Database getInstance() {
        if (database == null)
            return new Database();
        return database;
    }

    public void saveToDatabase(String username, int score, int chancesRemaining,
            HashMap<AbilityTypes, Boolean> MagicalAbilitiesMap, ArrayList<ObstacleInfo> obstacleInfosList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        DatabaseObject saver = createDBObject(username, score, chancesRemaining,
                MagicalAbilitiesMap, obstacleInfosList);

        Path currentRelativePath = Paths.get("");
        String currentPath = currentRelativePath.toAbsolutePath().toString();
        String filePath = currentPath + "/DatabaseFiles/" + username + ".json";
        System.out.println(filePath);
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(saver, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToDatabase(String username, ArrayList<ObstacleInfo> obstacleInfosList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (!checkIfUserExist(username)) {
            saveToDatabase(username, 0, 3, new HashMap<AbilityTypes, Boolean>(),
                    obstacleInfosList);
            return;
        }
        var dObj = loadFromDatabase(username);
        dObj.setObstacleInfosList(obstacleInfosList);

        Path currentRelativePath = Paths.get("");
        String currentPath = currentRelativePath.toAbsolutePath().toString();
        String filePath = currentPath + "/DatabaseFiles/" + username + ".json";

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(dObj, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DatabaseObject loadFromDatabase(String username) {

        Path currentRelativePath = Paths.get("");
        String currentPath = currentRelativePath.toAbsolutePath().toString();
        String filePath = currentPath + "/DatabaseFiles/" + username + ".json";

        try (Reader reader = new FileReader(filePath)) {
            Gson gson = new GsonBuilder().create();
            DatabaseObject returner = gson.fromJson(reader, DatabaseObject.class);

            return returner;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkIfUserExist(String username) {

        Path currentRelativePath = Paths.get("");
        String currentPath = currentRelativePath.toAbsolutePath().toString();
        String filePath = currentPath + "/DatabaseFiles/" + username + ".json";

        try (Reader reader = new FileReader(filePath)) {
            Gson gson = new GsonBuilder().create();
            gson.fromJson(reader, DatabaseObject.class);

        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public DatabaseObject createDBObject(String username, int score, int chancesRemaining,
            HashMap<AbilityTypes, Boolean> MagicalAbilitiesMap, ArrayList<ObstacleInfo> obstacleInfosList) {

        DatabaseObject dbObject = DatabaseObject.getInstance();
        dbObject.setUsername(username);
        dbObject.setScore(score);
        dbObject.setChancesRemaining(chancesRemaining);
        dbObject.setMagicalAbilitiesArray(MagicalAbilitiesMap);
        dbObject.setObstacleInfosList(obstacleInfosList);

        return dbObject;
    }
}
