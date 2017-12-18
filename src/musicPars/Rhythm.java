package musicPars;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Rhythm {

    private List<Bar> bars;
    private int numOfBars;
    private TimeSign currTimeSign;
    private int tempo;
    private Vector<Integer> rhythmSequence;
    private int baseUnit;
    private int noteSum;
    private double noteProb;

    public Rhythm(int baseUnit, int timeSignT, int timeSignB, int tempo, double noteProb,int numOfBars){
        this.baseUnit = baseUnit;
        noteSum = 0;
        this.tempo = tempo;
        this.noteProb = noteProb; //note probability
        this.numOfBars = numOfBars;
        currTimeSign = new TimeSign(timeSignT, timeSignB);
        bars = new ArrayList<>(numOfBars);
        Bar bar = new Bar(currTimeSign, 1);
        for (int currBarNo=1; currBarNo<=numOfBars; currBarNo++) {
            bars.add(bar);
            bar.incBarNo();
        }
        rhythmSequence = new Vector<>(1);
    }

    public Vector<Integer> getRhythmSequence(){ return this.rhythmSequence; }
    public int getRhythmSequence(int i) {return this.rhythmSequence.get(i);}

    public void setRhythmSequence() {
        for (Bar currBar : bars) {
            rhythmSequence.addAll(currBar.setRhythmSequence());
            noteSum += currBar.getNoteCntr();
        } //for
    }

    public void randomiseRhythmSequence() {
        for (Bar currBar : bars) {
            try {
            rhythmSequence.addAll(currBar.randomiseRhythmSequence(baseUnit, noteProb));
            noteSum += currBar.getNoteCntr();
            } //try
            catch (IllegalArgumentException iae) {
                System.out.println(iae);
            }
        } //for
    }

    public long[] getRhythmSequenceMSec() {
        long[] rhythmSeqSec = new long[noteSum];
        int j = noteSum-1, sumBeats = 0;
        for (int i=rhythmSequence.size()-1; i>=0; i--){
            if (rhythmSequence.get(i) == 1){
                rhythmSeqSec[j--] = Math.round(sumBeats * 6000 / tempo);
                sumBeats = 1;
            }
            else
                sumBeats++;
            //endif
        }   //for
        return rhythmSeqSec;
    }

    public int[] getRhythmSequenceTicks() {
        int[] rhythmSeqTicks = new int[noteSum];
        int j = noteSum-1, sumBeats = 0;
        for (int i=rhythmSequence.size()-1; i>=0; i--){
            if (rhythmSequence.get(i) == 1){
                rhythmSeqTicks[j--] = sumBeats;
                sumBeats = 1;
            }
            else
                sumBeats++;
            //endif
        }   //for
        return rhythmSeqTicks;
    }

    public int getNumOfBars() { return numOfBars; }

    public TimeSign getCurrTimeSign() { return currTimeSign; }

    public int getTempo() { return tempo; }

    public int getBaseUnit() { return baseUnit; }

    public int getNoteSum() { return noteSum; }

    //public double getNoteProb() { return noteProb; }
}
