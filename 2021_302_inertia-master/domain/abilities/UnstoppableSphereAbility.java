package domain.abilities;

import domain.playMode.*;

public class UnstoppableSphereAbility extends Ability {

    private Sphere sphere;

    public UnstoppableSphereAbility(Sphere sphere) {
        super();
        super.type = AbilityTypes.UNSTOPPABLE;
        this.sphere = sphere;
    }

    @Override
    public void performAbility() {
        super.performAbility();
        sphere.enableUnstoppable();
    }

    @Override
    protected void deactivateAbility() {
        super.deactivateAbility();
        sphere.disableUnstoppable();
    }

}
