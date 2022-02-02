package test;
//import Jun

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.Vector2;
import domain.abilities.Cannon;
import domain.playMode.Noble;
import domain.playMode.Sphere;

public class BurakTest {

    
    private Noble noble;
    private Sphere sphere;
    private Vector2 normalVector;
    private Vector2 speedVector;

    @Before
    public void setup() {
        Cannon[] cannons = { new Cannon(0, 0), new Cannon(0, 0) };
        normalVector = new Vector2(0, 0);
        speedVector = new Vector2(-1, 1);
        noble = new Noble(cannons);
        sphere = new Sphere(noble);
        sphere.setSpeedVector(speedVector);
    }
    
    

    @Test
    public void check_if_both_zero() {
        normalVector.x = 0.0;
        normalVector.y = 0.0;
        sphere.Bounce(normalVector);
        speedVector = sphere.getSpeedVector();
        assertEquals(speedVector.x, -7.46, 0.01);
        assertEquals(speedVector.y, 7.46, 0.01);
    }

    @Test
    public void check_if_both_greaterthan_zero() {
        normalVector.x = 1.0;
        normalVector.y = 1.0;
        sphere.Bounce(normalVector);
        speedVector = sphere.getSpeedVector();
        assertEquals(speedVector.x, 7.46, 0.01);
        assertEquals(speedVector.y, 7.46, 0.01);
    }

    @Test
    public void check_if_both_less_zero() {
        normalVector.x = -1.0;
        normalVector.y = -1.0;
        sphere.Bounce(normalVector);
        speedVector = sphere.getSpeedVector();
        assertEquals(speedVector.x, -7.46, 0.01);
        assertEquals(speedVector.y, -7.46, 0.01);
    }

    @Test
    public void check_if_y_greaterthan_zero_x_less_zero() {
        normalVector.x = -1.0;
        normalVector.y = 1.0;
        sphere.Bounce(normalVector);
        speedVector = sphere.getSpeedVector();
        assertEquals(speedVector.x, -7.46, 0.01);
        assertEquals(speedVector.y, 7.46, 0.01);
    }

    

    @Test
    public void check_if_x_greaterthan_zero_y_less_zero() {
        normalVector.x = 1.0;
        normalVector.y = -1.0;
        sphere.Bounce(normalVector);
        speedVector = sphere.getSpeedVector();
        assertEquals(speedVector.x, 7.46, 0.01);
        assertEquals(speedVector.y, -7.46, 0.01);
    }

    @Test
    public void check_if_x_greaterthan_zero_and_y_zero() {
        normalVector.x = 1.0;
        normalVector.y = 0.0;
        sphere.Bounce(normalVector);
        speedVector = sphere.getSpeedVector();
        assertEquals(speedVector.x, 7.46, 0.01);
        assertEquals(speedVector.y, 7.46, 0.01);
    }

    @Test
    public void check_if_y_greaterthan_zero_and_x_zero() {
        normalVector.x = 0.0;
        normalVector.y = 1.0;
        sphere.Bounce(normalVector);
        speedVector = sphere.getSpeedVector();
        assertEquals(speedVector.x, -7.46, 0.01);
        assertEquals(speedVector.y, 7.46, 0.01);
    }

    @Test
    public void check_if_x_less_zero_and_y_zero() {
        normalVector.x = -1.0;
        normalVector.y = 0.0;
        sphere.Bounce(normalVector);
        speedVector = sphere.getSpeedVector();
        assertEquals(speedVector.x, -7.46, 0.01);
        assertEquals(speedVector.y, 7.46, 0.01);
    }

    @Test
    public void check_if_y_less_zero_and_x_zero() {
        normalVector.x = 0.0;
        normalVector.y = -1.0;
        sphere.Bounce(normalVector);
        speedVector = sphere.getSpeedVector();
        assertEquals(speedVector.x, -7.46, 0.01);
        assertEquals(speedVector.y, -7.46, 0.01);
    }
}
