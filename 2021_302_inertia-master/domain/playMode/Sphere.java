package domain.playMode;

import java.awt.*;
import java.awt.event.*;

import domain.*;
import domain.observers.PlayModeObserver;
import domain.observers.PlayModeObserver.EventName;
import domain.obstacles.*;

public class Sphere extends GameObject {

    private double x;
    private double y;
    private int radius = 8;

    private double baseSpeed = (3.2) * 100 * GameLoop.DELTA_TIME; // 250px per second
    public double currentSpeed;
    private Vector2 speedVector;

    private Noble noble;

    private final int BOUNCE_SKIP_MS = 3 * GameLoop.LOOP_DELAY;
    private int currSkippedMs = 100;

    private boolean isAttached = true;
    private boolean isUnstoppable;

    public Sphere(Noble noble) {

        super();
        this.noble = noble;
        isAttached = true;
        currentSpeed = baseSpeed;
        isUnstoppable = false;

    }

    @Override
    public void Awake() {
        speedVector = new Vector2(0, 0);
    }

    @Override
    public void Update() {

        if (isAttached) {

            var nobleRect = noble.getBoundsRect();

            y = (nobleRect.getCenterY() - nobleRect.getHeight() - radius * 2);
            x = (nobleRect.getCenterX() - radius);

            InputManager.getInstance().ListenKeyPressed((e) -> {
                isAttached = false;
                speedVector.y = -baseSpeed;
                speedVector.x = 0;
                currentSpeed = baseSpeed;
                speedVector.rotateVector(noble.getCurrentDegree());
            }, KeyEvent.VK_SPACE);

        } else {
            InputManager.getInstance().UnlistenKeyPressed(KeyEvent.VK_SPACE);

            x += Math.ceil(Math.abs(speedVector.x)) * Math.signum(speedVector.x);
            y += Math.ceil(Math.abs(speedVector.y)) * Math.signum(speedVector.y);

            HandleFrameCollision();

            currSkippedMs += GameLoop.LOOP_DELAY;

        }

    }

    public void HandleNobleBounce(Polygon noblePoly, double rotDegree) {
       

        Vector2 normalVector = RectCalculator.GetNormalVector(noblePoly.getBounds(), getBounds(), false);
        Bounce(normalVector);

        setSpeedVector(speedVector.rotateVector(rotDegree));

        ResetBounce();

    }

    public void ObstacleBounce(Obstacle obs) {
        if (!CanBounce() || getIsUnstoppable())
            return;
        Vector2 normalVector = RectCalculator.GetNormalVector(obs.getBounds(), getBounds(), false);
        Bounce(normalVector);
        ResetBounce();

    }

    private void HandleFrameCollision() {

        if (getBounds().getMinX() <= 0)
            Bounce(new Vector2(1, 0));
        else if (getBounds().getMaxX() >= Constants.PLAY_WIDTH)
            Bounce(new Vector2(-1, 0));
        else if (getBounds().getMinY() <= 0)
            Bounce(new Vector2(0, 1));
        else if (getBounds().getMaxY() >= Constants.PLAY_HEIGHT - getBounds().height) {
            Bounce(new Vector2(0, -1));
            collideWithBottom();
        }

    }

    private void collideWithBottom() {
        isAttached = true;
        var observer = PlayModeObserver.getInstance();
        observer.notify(EventName.CHANCE_DECREASED, null);
    }

    public void Bounce(Vector2 normalVector) {
        // MODIFIES: normalVector
        // EFFECTS: sets the speedVector according to the normal vector
        if (!Utils.IsZero(normalVector.y, 0.1)) {
            if (normalVector.y > 0) {
                speedVector.y = Math.abs(speedVector.y);
            } else {
                speedVector.y = -Math.abs(speedVector.y);
            }
        }
        if (!Utils.IsZero(normalVector.x, 0.1)) {
            if (normalVector.x > 0) {
                speedVector.x = Math.abs(speedVector.x);
            } else {
                speedVector.x = -Math.abs(speedVector.x);
            }
        }

        setSpeedVector(speedVector);

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 2 * radius, 2 * radius);
    }

    public boolean CanBounce() {
        return currSkippedMs >= BOUNCE_SKIP_MS;
    }

    private void ResetBounce() {
        currSkippedMs = 0;
    }

    public Vector2 getSpeedVector() {
        return speedVector;
    }

    public void setSpeedVector(Vector2 speedVector) {
        var mag = speedVector.getMagnituide();
        speedVector.x = speedVector.x / mag * getCurrentSpeed();
        speedVector.y = speedVector.y / mag * getCurrentSpeed();

        this.speedVector = speedVector;

    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
        setSpeedVector(speedVector);

    }

    public void disableUnstoppable() {
        isUnstoppable = false;
    }

    public void enableUnstoppable() {
        isUnstoppable = true;
    }

    public boolean getIsUnstoppable() {
        return isUnstoppable;
    }

    public void setBaseSpeed(double baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public double getBaseSpeed() {
        return baseSpeed;
    }
}
