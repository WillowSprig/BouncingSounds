package musicPars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeSignTest {

    TimeSign timeSign;

    @Test
    void getTimeSign() {
        timeSign = new TimeSign(3,4);
        assertTrue(timeSign.getTimeSignB()==4);
        assertTrue(timeSign.getTimeSignT()==3);

        //destruktor timeSign;

        timeSign = new TimeSign(0,4);
        assertTrue(timeSign.errorOcc);

        //destruktor timeSign;

        timeSign = new TimeSign(2,-3);
        assertTrue(timeSign.errorOcc);
    }

    @Test
    void setTimeSign() {
        timeSign = new TimeSign(3,4);
        timeSign.setTimeSign(5,8);
        assertTrue(timeSign.getTimeSignT()==5);
        assertTrue(timeSign.getTimeSignB()==8);

        timeSign.setTimeSign(5,0);
        assertTrue(timeSign.errorOcc);
    }

}