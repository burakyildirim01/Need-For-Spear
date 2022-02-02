package domain.abilities;

import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;

public class ChanceGivingAbility extends Ability {

    public ChanceGivingAbility() {
        super();
        super.type = AbilityTypes.INCREASECHANCE;
    }

    @Override
    public void performAbility() {
        PlayModeObserver.getInstance().notify(EventName.CHANCE_INCREASED, null);
    }

}
