package domain.playMode;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class InputManager {
    private static InputManager instance;
    private JFrame frame;

    public static boolean[] leftRightPressed = new boolean[] { false, false };
    private boolean[] upDownPressed = new boolean[] { false, false };
    private ArrayList<Consumer<Point>> mouseClickListeners = new ArrayList<>();
    private boolean tPressed = false;
    private boolean hPressed = false;
    private boolean uPressed = false;
    private int mouseX;
    private int mouseY;

    public InputManager() {

    }

    private void ListenHArrowKeys() {
        ListenKeyPressed((e) -> leftRightPressed[0] = true, KeyEvent.VK_LEFT);
        ListenKeyReleased((e) -> leftRightPressed[0] = false, KeyEvent.VK_LEFT);
        ListenKeyPressed((e) -> leftRightPressed[1] = true, KeyEvent.VK_RIGHT);
        ListenKeyReleased((e) -> leftRightPressed[1] = false, KeyEvent.VK_RIGHT);
    }

    private void ListenVArrowKeys() {
        ListenKeyPressed((e) -> upDownPressed[0] = true, KeyEvent.VK_DOWN);
        ListenKeyReleased((e) -> upDownPressed[0] = false, KeyEvent.VK_DOWN);
        ListenKeyPressed((e) -> upDownPressed[1] = true, KeyEvent.VK_UP);
        ListenKeyReleased((e) -> upDownPressed[1] = false, KeyEvent.VK_UP);
    }

    private void ListenAbilityKeys() {
        ListenKeyPressed((e) -> tPressed = true, KeyEvent.VK_T);
        ListenKeyReleased((e) -> tPressed = false, KeyEvent.VK_T);
        ListenKeyPressed((e) -> hPressed = true, KeyEvent.VK_H);
        ListenKeyReleased((e) -> hPressed = false, KeyEvent.VK_H);
        ListenKeyPressed((e) -> uPressed = true, KeyEvent.VK_U);
        ListenKeyReleased((e) -> uPressed = false, KeyEvent.VK_U);
    }

    public static InputManager getInstance() {
        if (instance == null)
            instance = new InputManager();

        return instance;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
        ListenHArrowKeys();
        ListenVArrowKeys();
        ListenAbilityKeys();
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                var mousePos = new Point(e.getX(), e.getY());
                mouseClickListeners.forEach(c -> c.accept(mousePos));
            }
        });
    }

    public void ListenKeyPressed(ActionListener action, int keyStroke) {
        frame.getRootPane().registerKeyboardAction(action, KeyStroke.getKeyStroke(keyStroke, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW

        );

    }

    public void UnlistenKeyPressed(int keyStroke) {
        frame.getRootPane().unregisterKeyboardAction(KeyStroke.getKeyStroke(keyStroke, 0));

    }

    public void ListenKeyReleased(ActionListener action, int keyStroke) {
        frame.getRootPane().registerKeyboardAction(action, KeyStroke.getKeyStroke(keyStroke, 0, true),
                JComponent.WHEN_IN_FOCUSED_WINDOW

        );

    }

    public void ListenMouseClick(Consumer<Point> func) {
        mouseClickListeners.add(func);
    }

    public void UnlistenMouseClick(Consumer<Point> func) {
        mouseClickListeners.remove(func);
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean getuPressed() {
        return uPressed;
    }

    public boolean gethPressed() {
        return hPressed;
    }

    public boolean gettPressed() {
        return tPressed;
    }

    public boolean[] getLeftRightPressed() {
        return leftRightPressed;
    }

    public boolean[] getUpDownPressed() {
        return upDownPressed;
    }

    public void setLeftRightMovement(boolean[] horizontalMovement) {
        InputManager.leftRightPressed = horizontalMovement;
    }

}
