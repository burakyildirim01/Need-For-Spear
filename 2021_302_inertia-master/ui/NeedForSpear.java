package ui;

import java.awt.Dimension;
import javax.swing.*;
import domain.Constants;
import domain.GameObject;
import domain.editMode.EditModeInputManager;
import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;
import domain.playMode.*;
import ui.editMode.EditModeButtons;
import ui.editMode.EditModeTextFields;
import ui.editMode.EditModeWindow;
import ui.playMode.PlayModeWindow;
import ui.playMode.TopBar;

public class NeedForSpear {

    private static JFrame frame = new JFrame("Need For Spear");
    private static JPanel playModePanel;

    public static void main(String[] args) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        StartEditMode();

        // display frame

        // PlayModeObserver.getInstance().subscribe(EventName.PLAYER_DEATH, a -> StartEditModeClearPlay());

    }

    private static void StartEditMode() {

        var playModeWindow = new PlayModeWindow(frame);

        playModePanel = InitPlayMode(frame, playModeWindow);

        InputManager.getInstance().setFrame(frame);
        EditModeInputManager.getInstance().setFrame(frame);

        var editModeWindow = new EditModeWindow(frame);
        new EditModeButtons(editModeWindow, playModePanel, playModeWindow, frame);
        new EditModeTextFields(editModeWindow, frame);
        frame.add(editModeWindow);
        frame.setVisible(true);
    }

    private static void StartEditModeClearPlay() {
        frame.remove(playModePanel);
        playModePanel = null;
        GameObject.DestroyAll();
        StartEditMode();
    }

    private static JPanel InitPlayMode(JFrame frame, PlayModeWindow playModeWindow) {
        var topBar = new TopBar(frame);

        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        setSizeTopBar(topBar);

        panel.add(topBar);
        panel.add(playModeWindow);

        var playModeWindowPanel = panel;
        return playModeWindowPanel;
    }

    private static void setSizeTopBar(TopBar topBar) {
        topBar.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.TOP_BAR_HEIGHT));
        topBar.setMaximumSize(new Dimension(Constants.SCREEN_WIDTH, Constants.TOP_BAR_HEIGHT));

    }

}