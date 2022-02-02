package ui.editMode;

import java.awt.event.*;
import javax.swing.*;
import domain.*;
import domain.database.DatabaseObject;
import domain.editMode.EditModeInputManager;
import domain.editMode.ObstaclePlacer;
import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;
import ui.playMode.PlayModeWindow;

public class EditModeButtons implements ActionListener {
    JButton playButton;
    JButton loadButton;
    JButton saveButton;
    JFrame frame;
    EditModeWindow editModeWindow;
    JPanel playModePanel;
    int width = Constants.SCREEN_WIDTH;
    int height = Constants.SCREEN_HEIGHT;
    private PlayModeWindow playModeWindow;
    private DatabaseObject databaseObject;

    public EditModeButtons(EditModeWindow editModeWindow, JPanel playModePanel, PlayModeWindow playModeWindow,
            JFrame frame) {
        this.editModeWindow = editModeWindow;
        this.playModePanel = playModePanel;
        this.playModeWindow = playModeWindow;
        this.frame = frame;

        databaseObject = null;
        PlayModeObserver.getInstance().subscribe(EventName.LOAD_GAME, obj -> setDatabaseObject((DatabaseObject) obj));

        playButton = new JButton();
        playButton.setBounds(85 * width / 100, 45 * height / 50 - width / 40, width / 8, width / 40);
        playButton.setText("Start the Game");
        playButton.setVisible(true);
        editModeWindow.setLayout(null);
        editModeWindow.add(playButton);
        playButton.addActionListener(this);

        loadButton = new JButton();
        loadButton.setBounds(85 * width / 100, 40 * height / 50 - width / 40, width / 8, width / 40);
        loadButton.setText("Load the Layout");
        loadButton.setVisible(true);
        editModeWindow.add(loadButton);
        loadButton.addActionListener(this);

        saveButton = new JButton();
        saveButton.setBounds(85 * width / 100, 35 * height / 50 - width / 40, width / 8, width / 40);
        saveButton.setText("Save the Layout");
        saveButton.setVisible(true);
        editModeWindow.add(saveButton);
        saveButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            frame.remove(editModeWindow);

            playModeWindow.FrameAdded(this.databaseObject);
            ObstaclePlacer.getInstance().unlistenSelectedObs();
            EditModeInputManager.getInstance().Disable();
            frame.add(playModePanel);
            frame.setVisible(true);
        } else if (e.getSource() == loadButton) {
            LoginScreen.getInstance().showLoadScreen(frame);
        } else if (e.getSource() == saveButton) {
            LoginScreen.getInstance().showSaveScreen(frame);
        }
    }

    public void setDatabaseObject(DatabaseObject databaseObject) {
        this.databaseObject = databaseObject;
    }

}
