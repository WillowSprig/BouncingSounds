//package musicPars;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BarTest {
//
//    Bar bar;
//
//    @org.junit.jupiter.api.Test
//    void getAndIncBarNo() {
//        bar = new Bar(3,4,16,1);
//
//        assertTrue(bar.getBarNo()==1);
//        bar.incBarNo();
//        assertTrue(bar.getBarNo()==2);
//    }
//
//    @org.junit.jupiter.api.Test
//    void randomiseAndGetRhythmSequence() {
//        bar = new Bar(3,4,16,1);
//
//        bar.randomiseRhythmSequence();
//        List<Integer> rhSeq = bar.getRhythmSequence();
//        assertTrue(rhSeq.get(0)==1);
//        double[] rhSeqS = bar.getRhythmSequenceSec(60);
//
//    }
//
//    @org.junit.jupiter.api.Test
//    void getTimeSign() {
//        bar = new Bar(3,4,16,1);
//
//        Bar.TimeSign ts = bar.getTimeSign();
//        assertEquals(3,ts.getTimeSignT());
//        assertEquals(4,ts.getTimeSignB());
//    }
//
//}