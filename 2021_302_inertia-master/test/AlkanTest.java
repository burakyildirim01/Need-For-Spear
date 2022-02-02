package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.playMode.*;

public class AlkanTest {

    private NobleRotator rotator;
    private double rotSpeed;
    private double backRotSpeed;

    @Before
    public void setup() {
        rotator = new NobleRotator();
        rotSpeed = rotator.getRotationSpeed();
        backRotSpeed = rotator.getBackRotationSpeed();
    }

    @Test
    public void clockwise_rotation() {
        rotator.SetNobleRotation(1);
        rotator.Update();

        assertEquals(rotator.getCurrentDegree(), rotSpeed, 0.001);
    }

    @Test
    public void counterclockwise_rotation() {
        rotator.SetNobleRotation(-1);
        rotator.Update();

        assertEquals(rotator.getCurrentDegree(), -rotSpeed, 0.001);
    }

    @Test
    public void idle_rotation() {
        rotator.SetNobleRotation(0);
        rotator.Update();

        assertEquals(rotator.getCurrentDegree(), 0, 0.001);
    }

    @Test
    public void back_clockwise_rotation() {
        int deg = 30;
        rotator.SetNobleRotation(0);
        rotator.setCurrentDegree(deg);
        rotator.Update();

        assertEquals(rotator.getCurrentDegree(), deg - backRotSpeed, 0.001);
    }

    @Test
    public void back_counterclockwise_rotation() {
        int deg = -30;
        rotator.SetNobleRotation(0);
        rotator.setCurrentDegree(deg);
        rotator.Update();

        assertEquals(rotator.getCurrentDegree(), deg + backRotSpeed, 0.001);
    }

}
