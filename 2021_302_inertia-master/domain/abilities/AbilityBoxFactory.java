package domain.abilities;

import java.util.ArrayList;

public class AbilityBoxFactory {
    private ArrayList<AbilityBox> abilityBoxes = new ArrayList<AbilityBox>();

    private static AbilityBoxFactory instance;

    private AbilityBoxFactory() {

    }

    public static AbilityBoxFactory getInstance() {
        if (instance == null)
            instance = new AbilityBoxFactory();

        return instance;
    }

    public void AddAbilityBox(AbilityBox abilityBox) {
        abilityBoxes.add(abilityBox);
    }

    public ArrayList<AbilityBox> getAbilityBoxes() {
        return abilityBoxes;
    }

}
