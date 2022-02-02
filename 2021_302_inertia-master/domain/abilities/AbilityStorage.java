package domain.abilities;

import java.util.*;

import domain.database.DatabaseObject;
import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;

public class AbilityStorage {
    HashMap<AbilityTypes, Ability> abilitiesAcquired = new HashMap<>();

    private static AbilityStorage instance;

    private AbilityStorage() {
        PlayModeObserver.getInstance().subscribe(EventName.LOAD_GAME,
                dObj -> setAbilitiesAcquired(((DatabaseObject) dObj).getMagicalAbilitiesHashMap()));
    }

    public static AbilityStorage getInstance() {
        if (instance == null)
            instance = new AbilityStorage();

        return instance;
    }

    public void AbilityAcquired(Ability ability) {
        abilitiesAcquired.put(ability.type, ability);
        updateUI(ability.type, true);
        
    }

    private void updateUI(AbilityTypes type, boolean isAcquired) {
        if (type == AbilityTypes.HEX)
            PlayModeObserver.getInstance().notify(EventName.UPDATE_HEX, isAcquired);
        else if (type == AbilityTypes.UNSTOPPABLE)
            PlayModeObserver.getInstance().notify(EventName.UPDATE_UNSTOP, isAcquired);
        else if (type == AbilityTypes.EXPANSION)
            PlayModeObserver.getInstance().notify(EventName.UPDATE_EXP, isAcquired);
    }

    public void UseAbility(AbilityTypes type) {
        if (!isAcquired(type))
            return;
        abilitiesAcquired.get(type).performAbility();
        abilitiesAcquired.remove(type);
        updateUI(type, false);
    }

    public boolean isAcquired(AbilityTypes type) {
        return abilitiesAcquired.containsKey(type);
    }

    public HashMap<AbilityTypes, Boolean> getAcquiredAbilities() {
        var map = new HashMap<AbilityTypes, Boolean>();
        abilitiesAcquired.forEach((k, v) -> map.put(k, v == null ? false : true));
        return map;
    }

    public void setAbilitiesAcquired(HashMap<AbilityTypes, Boolean> abilitiesAcquired) {
        this.abilitiesAcquired = new HashMap<AbilityTypes, Ability>();

        updateUI(AbilityTypes.EXPANSION, false);
        updateUI(AbilityTypes.HEX, false);
        updateUI(AbilityTypes.UNSTOPPABLE, false);
        for (var key : abilitiesAcquired.keySet()) {
            if (abilitiesAcquired.get(key)) {
                this.abilitiesAcquired.put(key, AbilityManager.getInstance().getAbility(key));
                updateUI(key, true);
            }
        }
    }

}
