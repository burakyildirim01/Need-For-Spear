package domain.editMode;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

//import domain.observers.EditModeObserver;
import domain.obstacles.*;

public class EditModeInputManager implements MouseListener {
    private static EditModeInputManager instance;
    private JFrame frame;
    private boolean isDisable;

    public static ArrayList<Obstacle> obstacles = new ArrayList<>();

    private EditModeInputManager() {
        isDisable = false;
    }

    public static EditModeInputManager getInstance() {
        if (instance == null)
            instance = new EditModeInputManager();

        return instance;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
        frame.addMouseListener(this);
        Disable();
        ObstaclePlacer.getInstance().selectType();
    }

    // MOUSE EVENTS

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        if (isDisable)
            return;
        int x = e.getX();
        int y = e.getY() - Obstacle.HEIGHT;
        Obstacle createdObs = ObstacleFactory.getInstance().createObstacle(new ObstacleInfo(x, y,
                ObstaclePlacer.getInstance().selectedObs));

        if (SwingUtilities.isRightMouseButton(e)) {
            ObstaclePlacer.getInstance().removeObs(x, y);
        }

        if (ObstaclePlacer.getInstance().isIntersect(createdObs)) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                ObstaclePlacer.getInstance().placeObs(x, y, ObstaclePlacer.getInstance().selectedObs);
            }
        }
        createdObs.Destroy();
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void ListenKeyPressed(ActionListener action, int keyStroke) {
        frame.getRootPane().registerKeyboardAction(action, KeyStroke.getKeyStroke(keyStroke, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

    }

    public void unListenKeyPressed(int keyStroke) {
        frame.getRootPane().unregisterKeyboardAction(KeyStroke.getKeyStroke(keyStroke, 0));
    }

    public void Disable() {
        isDisable = true;
    }
    public void Able() {
        isDisable = false;
    }
}
