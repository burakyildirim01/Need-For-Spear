package test;
//import Jun

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Vector2;

public class MainTest {

    
    private Vector2 vector;

    @Before
    public void setup() {
        vector = new Vector2(0, 0);
    }
    
    

    @Test
    public void check_if_magnitude_is_five() {
        vector.x = 3.0;
        vector.y = 4.0;
        assertTrue(vector.repOk());
        assertEquals(vector.getMagnituide(), 5, 0.01);
    }

    @Test
    public void check_if_rotation_is_working() {
        vector.x = 0.0;
        vector.y = -1.0;
        assertTrue(vector.repOk());
        vector = vector.rotateVector(90);
        assertEquals(vector.x, 1, 0.01);
        assertEquals(vector.y, 0, 0.01);
    }

    @Test
    public void check_if_get_degree_is_working() {
        vector.x = 1.0;
        vector.y = 1.0;
        assertTrue(vector.repOk());
        double deg = vector.GetDegree();
        assertEquals(deg, 45.0, 0.01);
    }

    @Test
    public void check_if_get_radian_is_working() {
        vector.x = 1.0;
        vector.y = 1.0;
        assertTrue(vector.repOk());
        double rad = vector.GetRadian();
        assertEquals(rad, 0.785398163, 0.01);
    }

    @Test
    public void check_if_rotation_from_point_is_working() {
        vector.x = 0.0;
        vector.y = -1.0;
        assertTrue(vector.repOk());
        Vector2 rotatePoint = new Vector2(0, -2);
        vector = vector.rotateVector(rotatePoint, 90);
        assertEquals(vector.x, -1, 0.01);
        assertEquals(vector.y, -2, 0.01);

    }

}
