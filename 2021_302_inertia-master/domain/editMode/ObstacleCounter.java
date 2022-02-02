package domain.editMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import domain.obstacles.*;

public class ObstacleCounter {
    public static int simpleNum;
    public static int firmNum;
    public static int explosiveNum;
    public static int giftNum;
    public static int totalNum;
    static int L;
    static Random rand = new Random();

    public ObstacleCounter(int L, int simpleNum, int firmNum, int explosiveNum, int giftNum) {
        ObstacleCounter.simpleNum = simpleNum;
        ObstacleCounter.firmNum = firmNum;
        ObstacleCounter.explosiveNum = explosiveNum;
        ObstacleCounter.giftNum = giftNum;
        ObstacleCounter.L = L;
    }

    public static ArrayList<ObstacleTypes> placeRandom() {
        // MODIFIES: creates and modifies the ArrayList of ObstacleTypes, tempObstacles
        // EFFECTS: creates obstacle types and adds to the tempObstacles array according
        // to given
        // inputs from user. then randomly adds obstacles to the game layout.
        // REQUIRES: valid numbers of simpleNum >= 75, firmNum >= 10, explosiveNum >= 5
        // and giftNum >= 10

        ObstacleStorage.getInstance().DeleteAllObstacles();
        totalNum = simpleNum + firmNum + explosiveNum + giftNum;
        ObstacleTypes type = ObstacleTypes.SIMPLE;
        int obsLife = 1;
        int defaultX = (int) (1 * L / 5);
        int x = defaultX;
        int y = 45;
        ArrayList<ObstacleTypes> tempObstacles = new ArrayList<ObstacleTypes>();

        for (int i = 1; i < totalNum + 1; i++) {
            if (i <= simpleNum) {
                tempObstacles.add(type);
            } else if (simpleNum < i && i <= simpleNum + firmNum) {
                type = ObstacleTypes.FIRM;
                tempObstacles.add(type);
            } else if (simpleNum + firmNum < i && i <= simpleNum + firmNum + explosiveNum) {
                type = ObstacleTypes.EXPLOSIVE;
                tempObstacles.add(type);
            } else {
                type = ObstacleTypes.GIFT;
                tempObstacles.add(type);
            }
        }

        Collections.shuffle(tempObstacles);
        for (int j = 0; j < tempObstacles.size(); j++) {
            var obsType = tempObstacles.get(j);
            var isFirm = obsType.equals(ObstacleTypes.FIRM);
            obsLife = isFirm ? rand.nextInt(5) + 1 : 1;
            ObstacleGenerator.getInstance().create(new ObstacleInfo(x, y, obsType, obsLife));
            if (x > 10 * L - 2 * L / 5 - 1.5 * defaultX) {
                y += 2 * L / 5;
                x = defaultX;
            } else
                x += 2 * L / 5;
        }
        return tempObstacles;
    }
}
