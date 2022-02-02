package domain.editMode;

import java.util.*;

import javax.swing.*;

import domain.*;
import domain.playMode.*;
import domain.obstacles.*;

public class EditMode {
    private JFrame frame;
    private Noble noble;
    private ArrayList<Obstacle> obstacles;

    public EditMode(JFrame frame) {

        this.frame = frame;
        obstacles = ObstacleStorage.getInstance().getObstacles();
    }

    public void StartEditMode() {

    }

    public Noble getNoble() {
        return noble;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

}
