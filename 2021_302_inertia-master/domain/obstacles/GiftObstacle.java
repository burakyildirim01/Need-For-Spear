package domain.obstacles;

import java.util.*;
import domain.abilities.*;
import domain.playMode.*;

public class GiftObstacle extends Obstacle {
    private AbilityBox ab;
    Random rnd = new Random();

    public GiftObstacle(ObstacleInfo info, AbilityTypes abilityType) {
        super(info);
        ab = new AbilityBox(info.x, info.y, abilityType);
        AbilityBoxFactory.getInstance().AddAbilityBox(ab);
    }

    public GiftObstacle(ObstacleInfo info) {
        super(info);
        ab = new AbilityBox(info.x, info.y, AbilityTypes.getAbilityType(rnd.nextInt(4)));
        AbilityBoxFactory.getInstance().AddAbilityBox(ab);
    }

    public AbilityBox getAbilityBox() {
        return ab;
    }

    @Override
    protected void death() {
        ab.setVisible(true);
        super.death();

    }

    @Override
    public boolean isCollide(Sphere sphere) {
        return getBounds().intersects(sphere.getBounds());
    }

    @Override
    int getScore() {
        return 20;
    }

}
