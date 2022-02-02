package domain.playMode;

import java.awt.event.*;
import domain.*;
import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;
import domain.obstacles.ObstacleManager;

import javax.swing.JOptionPane;
import javax.swing.Timer;


public class GameLoop implements ILooper {

    private Timer timer;
    public static final int FPS = 30;
    public static final int LOOP_DELAY = 1000 / FPS;
    public static final double DELTA_TIME = LOOP_DELAY / 1000.0;
    private int elapsedTime;


    public GameLoop() {

        elapsedTime = 0;
        timer = new Timer(LOOP_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                var gameObjects = GameObject.getGameObjects();
                var size = GameObject.getGameObjects().size();

                for (int i = size - 1; i >= 0; i--) {
                    gameObjects.get(i).Update();
                }

                elapsedTime += LOOP_DELAY;

            }

        });

        PlayModeObserver.getInstance().subscribe(EventName.PLAYER_DEATH, e -> DeleteLoop());

    }

    @Override
    public void StartLoop() {
        if (timer == null) {
            System.err.println("Timer not initialized");
            return;
        }
        GameObject.getGameObjects().forEach(e -> e.Awake());
        GameObject.getGameObjects().forEach(e -> e.Start());

        ResumeTimer();
    }

    @Override
    public int getElapsedTimeMs() {
        return elapsedTime;
    }

    public double getElapsedTimeSeconds() {
        return elapsedTime / 1000.0;
    }

    public int getLoopDelayMs() {
        return LOOP_DELAY;
    }

    @Override
    public void ResumeTimer() {
        if (timer == null) {
            System.err.println("Timer not initialized");
            return;
        }
        timer.start();

    }

    @Override
    public void PauseTimer() {
        if (timer == null) {
            System.err.println("Timer not initialized");
            return;
        }
        timer.stop();

    }

    @Override
    public boolean IsPaused() {
        return !timer.isRunning();
    }

    public int getElapsedTime() {
        return this.elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    private void DeleteLoop() {
        PauseTimer();
        timer = null;
    }

    
}
