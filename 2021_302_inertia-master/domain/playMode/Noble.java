package domain.playMode;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import domain.*;
import domain.abilities.*;

public class Noble extends GameObject implements ISphereCollision {

    private int width = (int) ((1.5) * Constants.L);
    private int height = 20;
    private double x;
    private double y;
    private boolean cannonsVisible = false;
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    boolean isAvailable = true;
    Cannon[] cannons;

    private final double HORIZONTAL_SPEED = (1.75) * 4 * 35 * GameLoop.DELTA_TIME; // per second

    private int dir = 0;
    private NobleRotator rotator;
    private boolean isExpand;

    public Noble(Cannon[] cannons) {
        super();
        this.cannons = cannons;
        isExpand = false;
        x = 0;
        y = Constants.PLAY_HEIGHT - Constants.NOBLE_DISTANCE;
        CollisionManager.getInstance().addCollider(this);
        rotator = new NobleRotator();

    }

    public void setCannonsVisible(boolean cannonsVisible) {
        this.cannonsVisible = cannonsVisible;
        cannons[0].setVisible(cannonsVisible);
        cannons[1].setVisible(cannonsVisible);
    }

    public boolean getCannonsVisible() {
        return cannonsVisible;
    }

    @Override
    public void Update() {
        if (dir == 1)
            SetX((x + HORIZONTAL_SPEED));
        else if (dir == -1)
            SetX((x - HORIZONTAL_SPEED));

        rotator.Update();

        cannons[0].setBounds((int) x, (int) y);
        cannons[1].setBounds((int) x + width, (int) y);

    }

    public void SetX(double x) {
        this.x = x;
        if (this.x < 0)
            this.x = 0;
        else if (this.x + width > Constants.PLAY_WIDTH)
            this.x = Constants.PLAY_WIDTH - width;

    }

    public double GetX() {
        return x;
    }

    public Polygon getBounds() {

        return Utils.rotatePolygon(getBoundsPoly(), rotator.getCurrentDegree());

    }

    @Override
    public boolean isCollide(Sphere sphere) {
        return getBounds().intersects(sphere.getBounds());
    }

    public Rectangle getBoundsRect() {

        return getBounds().getBounds();

    }

    public Rectangle getBoundingRect() {
        var xPoints = getBounds().xpoints.clone();
        var yPoints = getBounds().ypoints.clone();
        Arrays.sort(xPoints);
        Arrays.sort(yPoints);
        Rectangle rect = new Rectangle(xPoints[0], yPoints[0], xPoints[3] - xPoints[0], yPoints[3] - yPoints[0]);
        return rect;
    }

    private Polygon getBoundsPoly() {

        int[] xpoints = new int[] { (int) x, (int) x, (int) (x + width), (int) (x + width) };
        int[] ypoints = new int[] { (int) y, (int) (y + height), (int) (y + height), (int) y };

        Polygon poly = new Polygon(xpoints, ypoints, 4);

        return poly;

    }

    public boolean IsMoving() {
        return dir != 0;
    }

    @Override
    public void collide(Sphere sphere) {
        if (!sphere.CanBounce())
            return;
        var speedVector = sphere.getSpeedVector();
        boolean isDifferentDir = dir * speedVector.x < 0;
        boolean isPerpendicular = Utils.IsZero(speedVector.x, 0.1);

        if (IsMoving()) {
            if (isPerpendicular) {
                double newSpeed = sphere.getCurrentSpeed() / Math.sqrt(2);
                speedVector.x = newSpeed * dir;
                speedVector.y = -newSpeed;
                sphere.setSpeedVector(speedVector);
            } else {
                if (isDifferentDir) {
                    speedVector.x *= -1;
                    speedVector.y *= -1;
                    sphere.setSpeedVector(speedVector);

                } else {
                    sphere.setCurrentSpeed(sphere.getCurrentSpeed() + 5 * GameLoop.DELTA_TIME);
                    sphere.HandleNobleBounce(getBounds(), rotator.getCurrentDegree());

                }
            }
        } else {
            sphere.HandleNobleBounce(getBounds(), rotator.getCurrentDegree());
        }

    }

    public void SetNobleDirection(int i) {

        dir = i;
    }

    public int getNobleDirection() {
        return dir;
    }

    public void SetNobleRotation(int i) {

        rotator.SetNobleRotation(i);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void EnableExpand() {
        if (isExpand)
            return;
        setWidth(getWidth() * 2);
        SetX(GetX() - getWidth() / 4);
        isExpand = true;
    }

    public void DisableExpand() {
        if (!isExpand)
            return;
        setWidth(getWidth() / 2);
        isExpand = false;
    }

    public int getWidth() {
        return width;
    }

    public void makeHex() {
        setCannonsVisible(true);
    }

    public void disableHex() {
        setCannonsVisible(false);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public double getCurrentDegree() {
        return rotator.getCurrentDegree();
    }

}
