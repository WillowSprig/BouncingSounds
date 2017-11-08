package musicPars;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Rhythm {

    private List<Bar> bars;
    private int numOfBars;
    private TimeSign currTimeSign;
    private int tempo;
    //private int rhythmSeqLength;
    private Vector<Integer> rhythmSequence;
    private int baseUnit;
    private int noteSum;

    public Rhythm(int inBaseUnit, int inTimeSignT, int inTimeSignB, int inTempo){
        baseUnit = inBaseUnit;
        noteSum = 0;
        tempo = inTempo;
        currTimeSign = new TimeSign(inTimeSignT, inTimeSignB);
        //rhythmSeqLength = currTimeSign.getTimeSignT()*baseUnit/currTimeSign.getTimeSignB();
        bars = new ArrayList<>(numOfBars);
        int currBarNo = 1;
        for (Bar bar : bars) {
            bar = new Bar(currTimeSign, currBarNo++);
        }
        rhythmSequence = new Vector<>(1);
    }

    public Vector<Integer> getRhythmSequence(){
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

    public double[] getRhythmSequenceSec() {
        double[] rhythmSeqSec = new double[noteSum];
        int j = noteSum-1, sumBeats = 0;
        for (int i=rhythmSequence.size()-1; i>=0; i--){
            if (rhythmSequence.get(i) == 1){
                rhythmSeqSec[j--] = sumBeats * 60 / tempo;
                sumBeats = 1;
            }
            else
                sumBeats++;
            //endif
        }   //for
        return rhythmSeqSec;
    }
}
