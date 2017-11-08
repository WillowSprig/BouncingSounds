package musicPars;

import java.util.ArrayList;
import java.util.List;

public class Rhythm {

    private List<Bar> bars;
    private int numOfBars;

    private TimeSign timeSign;
    private int rhythmSeqLength;
    private List<Integer> rhythmSequence;
    private int baseUnit;
    private int noteCntr;

    public Rhythm(int inBaseUnit, int inTimeSignT, int inTimeSignB){

        bars = new ArrayList<>(numOfBars);
        this.baseUnit = inBaseUnit;
        this.timeSign = new TimeSign(inTimeSignT, inTimeSignB);
        this.rhythmSeqLength = timeSign.getTimeSignT()*baseUnit/timeSign.getTimeSignB();
    }

    public List<Integer> getRhythmSequence(){
        return this.rhythmSequence;
    }

    public void setRhythmSequence(int[] inRhythmSequence) {

    }

    public double[] getRhythmSequenceSec(int tempo) {
        double[] rhythmSeqSec = new double[noteCntr];
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

    public void randomiseRhythmSequence(){
        this.rhythmSequence = new ArrayList<>(rhythmSeqLength);
        noteCntr = 0;
        rhythmSequence.set(0, 1);
        for (int i=1; i<rhythmSeqLength; i++) {
            if (i % baseUnit/timeSign.getTimeSignB() == 0)
                rhythmSequence.set(i, (int) Math.round(Math.random()+0.3));
            else
                rhythmSequence.set(i, (int) Math.round(Math.random()));
            //endif
            if (rhythmSequence.get(i) == 1)
                noteCntr++;
            //endif
        }   //for
    }

}
