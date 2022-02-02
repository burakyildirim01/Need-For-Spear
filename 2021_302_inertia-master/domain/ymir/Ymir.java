package domain.ymir;

import domain.GameObject;
import domain.playMode.*;
import javax.swing.Timer;

import java.awt.event.*;
import java.util.Random;

public class Ymir extends GameObject {

    private static final double successPercent = 0.5;
    private static final int COIN_TOSS_INTERVAL_SECONDS = 30;
    private Sphere sphere;

    public Ymir(Sphere sphere) {
        super();
        this.sphere = sphere;

        CreateCoinTossTimer(COIN_TOSS_INTERVAL_SECONDS * 1000, e -> {
            if (new Random().nextDouble() > successPercent) {
                System.out.println("Coin Toss Failed");
                return;
            }

            var random = new Random().nextInt(3);
            YmirAbility ability = GetAbility(random);

            System.out.println("Coin Toss Success. Ymir Ability is " + ability.toString());
            ability.performAbility();

        });
    }

    private void CreateDelayer(int delay, ActionListener action) {
        var timer = new Timer(delay, action);
        timer.setRepeats(false);
        timer.start();
    }

    private void CreateCoinTossTimer(int delay, ActionListener action) {
        var timer = new Timer(delay, action);
        timer.setInitialDelay(5000);
        timer.start();
    }

    private YmirAbility GetAbility(int number) {
        return switch (number) {
            case 0 -> new DoubleAccel(sphere);
            case 1 -> new InfiniteVoid();
            case 2 -> new HollowPurple();
            default -> new DoubleAccel(sphere);
        };
    }

}
