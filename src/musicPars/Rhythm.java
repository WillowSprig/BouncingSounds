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
    private double noteProb;

    public Rhythm(int baseUnit, int timeSignT, int timeSignB, int tempo){
        this.baseUnit = baseUnit;
        noteSum = 0;
        this.tempo = tempo;
        //this.noteProb = noteProb; //note probability
        currTimeSign = new TimeSign(timeSignT, timeSignB);
        //rhythmSeqLength = currTimeSign.getTimeSignT()*baseUnit/currTimeSign.getTimeSignB();
        bars = new ArrayList<>(numOfBars);
        int currBarNo = 1;
        for (Bar bar : bars) {
            bar = new Bar(currTimeSign, currBarNo++);
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

    public int getNumOfBars() { return numOfBars; }

    public TimeSign getCurrTimeSign() { return currTimeSign; }

    public int getTempo() { return tempo; }

    public int getBaseUnit() { return baseUnit; }

    public int getNoteSum() { return noteSum; }

    //public double getNoteProb() { return noteProb; }
}
