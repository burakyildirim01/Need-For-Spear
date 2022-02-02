package domain.observers;

import java.util.*;
import java.util.function.*;

import domain.obstacles.Obstacle;

public class EditModeObserver {
    private static EditModeObserver instance;
    private ArrayList<Consumer<ArrayList<Obstacle>>> obsChangeSubscribers = new ArrayList<>();

    private EditModeObserver() {
    }

    public static EditModeObserver getInstance() {
        if (instance == null)
            instance = new EditModeObserver();

        return instance;
    }

    public void subscribeObsChange(Consumer<ArrayList<Obstacle>> function) {
        obsChangeSubscribers.add(function);
    }

    // Call this method when any change in obstacles
    public void notifyObsChange(ArrayList<Obstacle> obstacles) {
        obsChangeSubscribers.forEach(sub -> sub.accept(obstacles));

    }
}
