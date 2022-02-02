package domain.abilities;

import domain.playMode.*;

public class NobleExpansionAbility extends Ability {
    Noble noble;

    public NobleExpansionAbility(Noble noble) {
        super();
        super.type = AbilityTypes.EXPANSION;
        this.noble = noble;
    }

    @Override
    public void performAbility() {
        super.performAbility();
        noble.EnableExpand();
    }

    @Override
    protected void deactivateAbility() {
        super.deactivateAbility();
        noble.DisableExpand();
    }

}
