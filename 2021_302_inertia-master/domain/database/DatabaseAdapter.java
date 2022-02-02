package domain.database;

import java.util.*;

import domain.abilities.AbilityTypes;
import domain.obstacles.ObstacleInfo;

public interface DatabaseAdapter {
    public void saveToDatabase(String username, int score, int chancesRemaining,
            HashMap<AbilityTypes, Boolean> MagicalAbilitiesMap, ArrayList<ObstacleInfo> obstacleInfosList);

    public DatabaseObject loadFromDatabase(String username);
}
