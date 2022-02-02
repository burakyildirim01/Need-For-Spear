package domain.observers;

import java.util.*;
import java.util.function.*;

public class PlayModeObserver {
    public enum EventName {
        CHANCE_INCREASED,
        CHANCE_DECREASED,
        UPDATE_CHANCES,
        PAUSE_GAME,
        UPDATE_SCORE,
        LOAD_GAME,
        UPDATE_OBSTACLES,
        SAVE_GAME,
        UPDATE_HEX,
        UPDATE_UNSTOP,
        UPDATE_EXP,
        SAVE_DATABASE,
        ADD_SCORE,
        PLAYER_DEATH,
        ABILITY_ACHIEVED,
        ADD_OBSTACLE,
        YMIR_ABILITY_TEXT,
        FORCE_PAUSE_GAME
    }

    HashMap<EventName, ArrayList<Consumer<Object>>> eventSubscribers = new HashMap<>();

    private static PlayModeObserver instance;

    private PlayModeObserver() {

    }

    public static PlayModeObserver getInstance() {
        if (instance == null)
            instance = new PlayModeObserver();

        return instance;

    }

    public void subscribe(EventName eventName, Consumer<Object> func) {

        if (!eventSubscribers.containsKey(eventName)) {
            eventSubscribers.put(eventName, new ArrayList<>());
        }
        eventSubscribers.get(eventName).add(func);
    }

    public void notify(EventName eventName, Object obj) {
        var subs = eventSubscribers.get(eventName);
        if (subs == null)
            return;

        subs.stream().forEach(c -> c.accept(obj));
    }

}
