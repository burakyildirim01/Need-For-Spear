package domain.controllers;

import javax.swing.*;

import domain.editMode.*;

public class EditModeController {
    private JFrame frame;

    public EditModeController(JFrame frame) {

        this.frame = frame;

    }

    public EditMode StartEditMode() {
        EditMode editMode = new EditMode(frame);
        editMode.StartEditMode();
        return editMode;
    }
}
