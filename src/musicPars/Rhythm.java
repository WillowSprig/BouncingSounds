package musicPars;

import java.util.ArrayList;
import java.util.List;

public class Rhythm {

    private List<Bar> bars;
    private int numOfBars;
    private TimeSign currTimeSign;
    private int rhythmSeqLength;
    private List<Integer> rhythmSequence;
    private int baseUnit;
    private int noteSum;

    public Rhythm(int inBaseUnit, int inTimeSignT, int inTimeSignB){
        bars = new ArrayList<>(numOfBars);
        baseUnit = inBaseUnit;
        noteSum = 0;
        currTimeSign = new TimeSign(inTimeSignT, inTimeSignB);
        rhythmSeqLength = currTimeSign.getTimeSignT()*baseUnit/currTimeSign.getTimeSignB();
        rhythmSequence = new ArrayList<>(1);
        rhythmSequence.set(0, 1);
    }

    public List<Integer> getRhythmSequence(){
        return this.rhythmSequence;
    }

    public void setRhythmSequence(int[] inRhythmSequence) {

    }

    public void randomiseRhythmSequence() {
        for (Bar currBar : bars){
            currBar.randomiseRhythmSequence(baseUnit);
            noteSum+=currBar.getNoteCntr();
        }
    }

    public double[] getRhythmSequenceSec(int tempo) {
        double[] rhythmSeqSec = new double[noteSum];
        int j = 0, sumBeats = 1;
        for (int currRhythmVal : rhythmSequence){
            if (currRhythmVal == 1)
                rhythmSeqSec[j++] = sumBeats * 60 / tempo;
            else
                sumBeats++;
            //endif
        }   //for
        return rhythmSeqSec;
    }



}
