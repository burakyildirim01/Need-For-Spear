package ui;

import domain.playMode.ILooper;
import javax.swing.*;
import java.awt.event.*;

public class UILoop implements ILooper {

    private Timer timer;
    public static final int FPS = 30;
    public static final int LOOP_DELAY = 1000 / FPS;
    public static final double DELTA_TIME = LOOP_DELAY / 1000.0;
    private int elapsedTime;
    private ActionListener repaint;

    public UILoop(ActionListener repaint) {
        this.repaint = repaint;
    }

    @Override
    public void StartLoop() {

        elapsedTime = 0;
        timer = new Timer(LOOP_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                repaint.actionPerformed(ae);
                elapsedTime += LOOP_DELAY;
            }

        });

        ResumeTimer();

    }

    @Override
    public int getElapsedTimeMs() {
        return elapsedTime;
    }

    @Override
    public void ResumeTimer() {
        if (timer == null) {
            System.out.println("Timer not initialized");
            return;
        }
        timer.start();

    }

    @Override
    public void PauseTimer() {
        if (timer == null) {
            System.out.println("Timer not initialized");
            return;
        }
        timer.stop();

    }

    @Override
    public boolean IsPaused() {
        return !timer.isRunning();
    }

}
