package domain.playMode;

import javax.swing.*;

import domain.GameObject;
import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;
import domain.obstacles.ObstacleManager;

public class WinLoseManager extends GameObject {

    private ScoreManager scoreManager;

    public WinLoseManager(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
        PlayModeObserver.getInstance().subscribe(EventName.PLAYER_DEATH, e -> loseGameWindow());
    }

    @Override
    public void Update() {

        if (ObstacleManager.getInstance().getObstacles().isEmpty()) {
            wonGameWindow();
        }
    }

    public void wonGameWindow() {
        Object[] message = {
                "CONGRATULATIONS! You have obtained the Spear of Power!",
        };
        int option = JOptionPane.showConfirmDialog(null, message, "You Won!", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION || option == JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }
    }

    public void loseGameWindow() {
        Object[] message = {
                "GAME OVER!", "Score:", scoreManager.GetScore(),
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Game Over", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.ERROR_MESSAGE);
        if (option == JOptionPane.OK_OPTION || option == JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }
    }

}
