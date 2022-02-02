package domain.controllers;

import javax.swing.*;

import domain.database.DatabaseObject;
import domain.playMode.*;

public class PlayModeController {
    private JFrame frame;

    public PlayModeController(JFrame frame) {

        this.frame = frame;

    }

    public PlayMode StartPlayMode(DatabaseObject databaseObject) {
        PlayMode playMode = new PlayMode(frame, databaseObject);
        playMode.StartPlayMode();
        return playMode;
    }
}
