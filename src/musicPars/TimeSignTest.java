package musicPars;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class TimeSignTest {

    TimeSign timeSign;
    boolean exceptionThrown;

    @Test
    void getTimeSign() {
        timeSign = new TimeSign(3,4);
        assertTrue(timeSign.getTimeSignB()==4);
        assertTrue(timeSign.getTimeSignT()==3);
    }

    @Test
    void setTimeSign() {
        exceptionThrown = false;
        timeSign = new TimeSign(3,4);
        timeSign.setTimeSign(5,8);
        assertTrue(timeSign.getTimeSignT()==5);
        assertTrue(timeSign.getTimeSignB()==8);
        try {
            timeSign.setTimeSign(0, 4);
        }
        catch (IllegalArgumentException iae){
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
        exceptionThrown = false;
        try {
            timeSign.setTimeSign(2, -3);
        }
        catch (IllegalArgumentException iae){
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

}