package domain.playMode;

import java.util.*;

import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;

public class PauseHandler {
    ArrayList<ILooper> loops;
    public static PauseHandler instance;

    private PauseHandler() {
        loops = new ArrayList<>();
        SubscribeMethods();
    }

    public static PauseHandler getInstance() {
        if (instance == null)
            instance = new PauseHandler();
        return instance;
    }

    public void addLoop(ILooper loop) {
        loops.add(loop);
    }

    private void SubscribeMethods() {
        PlayModeObserver.getInstance().subscribe(EventName.PAUSE_GAME, e -> Pause());
        PlayModeObserver.getInstance().subscribe(EventName.FORCE_PAUSE_GAME, e -> Pause());
    }

    public void Pause() {
        for (ILooper e : loops) {
            if (e.IsPaused())
                ResumeGame(e);
            else
                PauseGame(e);
        }

    }

    public void ForcePause() {
        for (ILooper e : loops) {
            PauseGame(e);
        }
    }

    public void PauseGame(ILooper loop) {
        loop.PauseTimer();
    }

    public void ResumeGame(ILooper loop) {
        loop.ResumeTimer();
    }

}
