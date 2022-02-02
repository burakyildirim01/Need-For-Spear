package domain.abilities;


import javax.swing.Timer;

import domain.*;

public abstract class Ability extends GameObject {

    protected int abilityTime = 10 * 1000;
    public AbilityTypes type;
    public boolean isAcquired;
    private Timer timer = new Timer(abilityTime, e -> deactivateAbility());

    protected AbilityManager manager = AbilityManager.getInstance();

    public Ability() {
        super();
    }   
    public void performAbility() {
        timer.setRepeats(false);
        timer.start();

    }

    protected void deactivateAbility() {
        timer.stop();
    }

}
