package domain.playMode;

public interface ILooper {
    void StartLoop();

    int getElapsedTimeMs();

    void ResumeTimer();

    void PauseTimer();

    boolean IsPaused();
}
