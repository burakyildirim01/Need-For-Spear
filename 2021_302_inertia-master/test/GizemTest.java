package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import domain.editMode.ObstacleCounter;
import domain.obstacles.ObstacleTypes;


public class GizemTest {
    private int simpleNum = 75;
    private int firmNum = 10;
    private int explosiveNum = 5;
    private int giftNum = 10;
    private ArrayList<ObstacleTypes> array;

    @Before
    public void setup() {
        ObstacleCounter.simpleNum = simpleNum;
        ObstacleCounter.firmNum = firmNum;
        ObstacleCounter.explosiveNum = explosiveNum;
        ObstacleCounter.giftNum = giftNum;
        array = ObstacleCounter.placeRandom();
    }


    @Test
    public void check_total_obstacles_number() {
        assertEquals(simpleNum + firmNum + explosiveNum + giftNum, array.size());
    }

    @Test
    public void check_if_simple() {
        int simple = 0;
        for (int i = 0; i < array.size(); i++) {
            var obsType = array.get(i);
            if(obsType.equals(ObstacleTypes.SIMPLE)) {
                simple++;
            }
        }
        assertEquals(simpleNum, simple);
    }

    @Test
    public void check_if_firm() {
        int firm = 0;
        for (int i = 0; i < array.size(); i++) {
            var obsType = array.get(i);
            if(obsType.equals(ObstacleTypes.FIRM)) {
                firm++;
            }
        }
        assertEquals(firmNum, firm);
    }

    @Test
    public void check_if_explosive() {
        int explosive = 0;
        for (int i = 0; i < array.size(); i++) {
            var obsType = array.get(i);
            if(obsType.equals(ObstacleTypes.EXPLOSIVE)) {
                explosive++;
            }
        }
        assertEquals(explosiveNum, explosive);
    }

    @Test
    public void check_if_gift() {
        int gift = 0;
        for (int i = 0; i < array.size(); i++) {
            var obsType = array.get(i);
            if(obsType.equals(ObstacleTypes.GIFT)) {
                gift++;
            }
        }
        assertEquals(giftNum, gift);
    }
}
