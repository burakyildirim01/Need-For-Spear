package ui.editMode;

import domain.controllers.*;
import domain.editMode.*;
import domain.playMode.ILooper;
import domain.playMode.PauseHandler;
import ui.UILoop;
import ui.painters.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EditModeWindow extends JPanel{

    ArrayList<IGameObjectPainter> painters;
 
    public EditModeWindow(JFrame frame) {

        EditMode editMode = (new EditModeController(frame)).StartEditMode();

        painters = (new EditModePainter(editMode)).InitPainters();

        ILooper UILoop = new UILoop(e -> repaint());
        PauseHandler.getInstance().addLoop(UILoop);
        UILoop.StartLoop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        painters.forEach(e -> e.paintComponent(g));
    }
    
}
