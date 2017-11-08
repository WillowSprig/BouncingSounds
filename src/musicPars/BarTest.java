package musicPars;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BarTest {

    Bar bar;
    TimeSign timeSign;

    @BeforeEach
    void setUp() {
        timeSign = new TimeSign(3,4);
        bar = new Bar(timeSign,1);
    }

    @Test
    void incAndGetBarNo() {
        bar.incBarNo();
        assertTrue(bar.getBarNo()==2);
    }

    @Test
    void setAndGetTimeSign() {
        bar.setTimeSign(5,8);
        TimeSign testTimeSign = new TimeSign(5,8);
        assertEquals(testTimeSign.getTimeSignB(), bar.getTimeSign().getTimeSignB());
        assertEquals(testTimeSign.getTimeSignT(), bar.getTimeSign().getTimeSignT());
    }

    @Test
    void randomiseRhythmSequence() {

        List<Integer> rhSeq = bar.randomiseRhythmSequence(16);
        assertFalse(rhSeq.isEmpty());
        assertTrue(bar.getNoteCntr()>0);
    }

    @AfterEach
    void tearDown() {
    }

}