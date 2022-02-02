package domain.ymir;

import domain.playMode.Sphere;

public class DoubleAccel extends YmirAbility {

    private Sphere sphere;
    private double defaultSpeed;

    public DoubleAccel(Sphere sphere) {
        super();
        this.sphere = sphere;
        abilityTime = 15 * 1000;
    }

    @Override
    public void performAbility() {
        super.performAbility();
        defaultSpeed = sphere.getCurrentSpeed();
        sphere.setCurrentSpeed(defaultSpeed / 2);
        sphere.setBaseSpeed(sphere.getBaseSpeed() / 2);
    }

    @Override
    protected void deactivateAbility() {
        super.deactivateAbility();
        sphere.setCurrentSpeed(defaultSpeed);
        sphere.setBaseSpeed(sphere.getBaseSpeed() * 2);

    }
    @Override
    public String toString() {
        return "Double Accel";
    }

}
