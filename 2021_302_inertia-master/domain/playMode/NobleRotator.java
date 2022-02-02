package domain.playMode;

public class NobleRotator {

    private double rotationSpeed = (2) * 20 * GameLoop.DELTA_TIME; // 20degree per second
    private double backRotationSpeed = 45 * GameLoop.DELTA_TIME; // 45degree per second
    private double currentDegree = 0;

    private int rotation = 0;
    private double maxDegree = 45.0;

    public void Update() {
        // MODIFIES: currentDegree
        // EFFECTS: sets the currentDegree based on the input rotation value a.k.a player input
        if (rotation == 1)
            setCurrentDegree(currentDegree + rotationSpeed);
        else if (rotation == -1)
            setCurrentDegree(currentDegree - rotationSpeed);
        else {
            if (Math.abs(currentDegree) > 1) {
                setCurrentDegree(currentDegree + backRotationSpeed * -Math.signum(currentDegree));
            } else {
                setCurrentDegree(0);
            }
        }
    }



    public double getCurrentDegree() {
        return currentDegree;
    }

    public void setCurrentDegree(double deg) {
        currentDegree = deg;
        if (Math.abs(currentDegree) > maxDegree) {
            currentDegree = maxDegree * Math.signum(currentDegree);
        }

    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public double getBackRotationSpeed() {
        return backRotationSpeed;
    }

    public void SetNobleRotation(int rotation) {
        this.rotation = rotation;

    }
}
