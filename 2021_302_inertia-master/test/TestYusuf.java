package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.abilities.Cannon;
import domain.playMode.Player;

import domain.playMode.InputManager;
import domain.playMode.Noble;


public class TestYusuf {

    private static Noble noble;
    private Player player;
    private boolean[] leftRightPressed;
    private boolean[] horizontalMovement;


    @Before
    public void setup() {
        Cannon[] cannons = { new Cannon(0, 0), new Cannon(0, 0) };
        noble = new Noble(cannons);
        player = new Player(noble);
        leftRightPressed = new boolean[] { false, false };
        horizontalMovement = leftRightPressed;
       
    }
    
    

    @Test
    public void check_if_horizantal_movement_towards_right() {
        leftRightPressed = new boolean[] { false, true };
        horizontalMovement = leftRightPressed;
        InputManager.getInstance().setLeftRightMovement(horizontalMovement);
        player.HandleMovement();
        assertEquals(noble.getNobleDirection(), +1, 0.01);
     
    }

    @Test
    public void check_if_horizantal_movement_towards_left() {
        leftRightPressed = new boolean[] { true, false };
        horizontalMovement = leftRightPressed;
        InputManager.getInstance().setLeftRightMovement(horizontalMovement);
        player.HandleMovement();
        assertEquals(noble.getNobleDirection(), -1, 0.01);
        
    }

    @Test
    public void check_if_horizantal_movement_stopped() {
        leftRightPressed = new boolean[] { false, false };
        horizontalMovement = leftRightPressed;
        InputManager.getInstance().setLeftRightMovement(horizontalMovement);
        player.HandleMovement();
        assertEquals(noble.getNobleDirection(), 0, 0.01);
        
    }

    @Test
    public void check_if_horizantal_movement_done_by_pressing_leftandright_atthesametime() {
        leftRightPressed = new boolean[] { true, true };
        horizontalMovement = leftRightPressed;
        InputManager.getInstance().setLeftRightMovement(horizontalMovement);
        player.HandleMovement();
        assertEquals(noble.getNobleDirection(), -1, 0.01);
        
    }
    @Test
    public void check_if_horizantal_movement_done_by_false_key_enters() {
        leftRightPressed = new boolean[] { false, false };
        horizontalMovement = leftRightPressed;
        InputManager.getInstance().setLeftRightMovement(horizontalMovement);
        player.HandleMovement();
        assertEquals(noble.getNobleDirection(), 0, 0.01);
        
    }
    



    
}
