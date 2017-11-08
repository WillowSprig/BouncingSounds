package musicPars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeSignTest {

    TimeSign timeSign;

    @Test
    void getTimeSignT() {
        timeSign = new TimeSign(3,4);
        assertTrue(timeSign.getTimeSignB()==4);
    }

    @Test
    void getTimeSignB() {
        timeSign = new TimeSign(3,4);
        assertTrue(timeSign.getTimeSignT()==3);
    }

    @Test
    void setTimeSign() {
        timeSign = new TimeSign(3,4);
        timeSign.setTimeSign(5,8);
        assertTrue(timeSign.getTimeSignT()==5);
        assertTrue(timeSign.getTimeSignB()==8);
    }

}