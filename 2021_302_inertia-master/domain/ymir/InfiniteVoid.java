package domain.ymir;

import java.util.ArrayList;
import java.util.Random;

import domain.obstacles.Obstacle;
import domain.obstacles.ObstacleManager;

public class InfiniteVoid extends YmirAbility {

    private ArrayList<Obstacle> frozenObstacle;

    public InfiniteVoid() {
        super();
        frozenObstacle = new ArrayList<Obstacle>();
        abilityTime = 15 * 1000;
    }

    @Override
    public void performAbility() {
        super.performAbility();
        var obstacles = ObstacleManager.getInstance().getObstacles();
        var randomNumbers = new ArrayList<Integer>();
        int N = obstacles.size();

        // IF LESS OR EQUALS 8
        if (N <= 8) {
            frozenObstacle.addAll(obstacles);
            return;
        }

        // IF HIGHER THAN 8

        for (int i = 0; i < 8; i++) {
            int rand;
            while (true) {
                rand = (new Random()).nextInt(N);
                if (!randomNumbers.contains(rand))
                    break;
            }

            frozenObstacle.add(obstacles.get(rand));
        }

        frozenObstacle.forEach(fObs -> fObs.setFrozen(true));

    }

    @Override
    protected void deactivateAbility() {
        super.deactivateAbility();
        frozenObstacle.forEach(fObs -> fObs.setFrozen(false));

    }

    @Override
    public String toString() {
        return "Infinite Void";
    }
}
