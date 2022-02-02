package domain.playMode;

import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;
import domain.*;
import domain.database.DatabaseObject;

public class ScoreManager extends GameObject {
    private int score;
    private ILooper loop;

    public ScoreManager() {
        PlayModeObserver.getInstance().subscribe(EventName.LOAD_GAME,
                dObj -> setScore(((DatabaseObject) dObj).getScore()));
        PlayModeObserver.getInstance().subscribe(EventName.ADD_SCORE,
                score -> AddScore((int) score));

    }

    public void InitDependency(ILooper loop) {
        this.loop = loop;

    }

    @Override
    public void Update() {

    }

    public int GetScore() {
        return score;
    }

    public void AddScore(int addScore) {
        setScore(GetScore() + addScore);
    }

    public void setScore(int score) {
        this.score = score;
        PlayModeObserver.getInstance().notify(EventName.UPDATE_SCORE, score);
    }

}
