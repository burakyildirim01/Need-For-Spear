package ui.playMode;

import domain.controllers.*;
import domain.database.DatabaseObject;
import ui.UILoop;
import ui.painters.*;
import domain.playMode.*;
import domain.playMode.PlayMode;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayModeWindow extends JPanel {

    ArrayList<IGameObjectPainter> painters;
    private JFrame frame;

    public PlayModeWindow(JFrame frame) {
        this.frame = frame;
        // super();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        painters.forEach(e -> e.paintComponent(g));

    }

    public void FrameAdded(DatabaseObject databaseObject) {
        PlayMode playMode = (new PlayModeController(frame)).StartPlayMode(databaseObject);

        painters = (new PlayModePainter(playMode)).InitPainters();

        ILooper UILoop = new UILoop(e -> repaint());

        UILoop.StartLoop();
    }

}
