package domain.abilities;

import domain.playMode.*;

public class MagicalHexAbility extends Ability {

    private Noble noble;

    public MagicalHexAbility(Noble noble) {
        super();
        super.type = AbilityTypes.HEX;
        this.noble = noble;
    }

    @Override
    public void performAbility() {
        super.performAbility();
        noble.makeHex();
    }

    @Override
    protected void deactivateAbility() {
        super.deactivateAbility();
        noble.disableHex();
    }

}
