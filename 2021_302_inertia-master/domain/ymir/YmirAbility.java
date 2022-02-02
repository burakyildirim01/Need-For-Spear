package domain.ymir;

import domain.abilities.Ability;
import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;

public class YmirAbility extends Ability {

    public YmirAbility() {
        super();
    }

    @Override
    public void performAbility() {
        super.performAbility();
        PlayModeObserver.getInstance().notify(EventName.YMIR_ABILITY_TEXT, toString());
    }

    @Override
    protected void deactivateAbility() {
        super.deactivateAbility();
        PlayModeObserver.getInstance().notify(EventName.YMIR_ABILITY_TEXT, "");
    }

}