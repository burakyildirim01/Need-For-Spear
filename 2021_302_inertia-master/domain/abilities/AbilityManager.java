package domain.abilities;

import domain.playMode.*;

public class AbilityManager {

    private static AbilityManager instance;
    private Noble noble;
    private Sphere sphere;
    private Player player;

    private AbilityManager() {

    }

    public static AbilityManager getInstance() {
        if (instance == null)
            instance = new AbilityManager();

        return instance;
    }

    public void InitDependency(Noble noble, Sphere sphere, Player player) {
        this.noble = noble;
        this.sphere = sphere;
        this.player = player;

    }

    public Noble getNoble() {
        return noble;
    }

    public Sphere getSphere() {
        return sphere;
    }

    public Player getPlayer() {
        return player;
    }

    public Ability getAbility(AbilityTypes types) {

        Ability ability;
        switch (types) {
            case EXPANSION:
                ability = new NobleExpansionAbility(noble);
                break;
            case HEX:
                ability = new MagicalHexAbility(noble);
                break;
            case INCREASECHANCE:
                ability = new ChanceGivingAbility();
                break;
            case UNSTOPPABLE:
                ability = new UnstoppableSphereAbility(sphere);
                break;
            default:
                ability = new NullAbility();
                System.out.println("Ability cannot generated.");
        }

        return ability;
    }

}
