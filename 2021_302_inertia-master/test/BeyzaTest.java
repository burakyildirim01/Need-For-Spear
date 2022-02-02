package test;
import static org.junit.Assert.*;

import org.junit.Test;
import ui.editMode.EditModeTextFields;

public class BeyzaTest {

    @Test
    public void inputTestingWrongSimpleNumber(){
        int simpleNumber = 70;
        int firmNumber = 10;
        int explosiveNumber = 5;
        int giftNumber = 10;
        assertFalse(EditModeTextFields.inputChecking(simpleNumber, firmNumber, explosiveNumber,giftNumber));
    }

    @Test
    public void inputTestingWrongFirmNumber(){
        int simpleNumber = 75;
        int firmNumber = 5;
        int explosiveNumber = 5;
        int giftNumber = 10;  
        assertFalse(EditModeTextFields.inputChecking(simpleNumber, firmNumber, explosiveNumber,giftNumber));
    }

    @Test
    public void inputTestingWrongExplosiveNumber(){
        int simpleNumber = 75;
        int firmNumber = 10;
        int explosiveNumber = 2;
        int giftNumber = 10;  
        assertFalse(EditModeTextFields.inputChecking(simpleNumber, firmNumber, explosiveNumber,giftNumber));
    }

    @Test
    public void inputTestingWrongGiftNumber(){
        int simpleNumber = 75;
        int firmNumber = 10;
        int explosiveNumber = 5;
        int giftNumber = 7;    
        assertFalse(EditModeTextFields.inputChecking(simpleNumber, firmNumber, explosiveNumber,giftNumber));
    }

    @Test
    public void inputTestingCorrect(){
        int simpleNumber = 75;
        int firmNumber = 10;
        int explosiveNumber = 5;
        int giftNumber = 10;
        assertTrue(EditModeTextFields.inputChecking(simpleNumber, firmNumber, explosiveNumber,giftNumber));
    }
}
