package musicPars;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class Bar {

    private TimeSign timeSign;
    private int barNo;
    private int noteCntr;
    public static Set<Integer> rhythmVals = new HashSet<>(6);

    public Bar(TimeSign timeSign, int barNo){
        if (barNo <= 0) throw new IllegalArgumentException("barNo must be greater than 0 integer value");
        else this.barNo = barNo;
        //endif
        this.timeSign = timeSign;
        for (int i=0; i<5; i++) rhythmVals.add(2^i);
    }

    public void incBarNo(){
        barNo++;
    }

    int getBarNo(){ return barNo; }

    public int getNoteCntr() { return noteCntr; }

    public void setTimeSign(int newTST, int newTSB){ timeSign.setTimeSign(newTST, newTSB); }

    public TimeSign getTimeSign(){
        return timeSign;
    }

    public Vector<Integer> randomiseRhythmSequence(int baseUnit, double noteProb) throws IllegalArgumentException{
        if (noteProb < 0 || noteProb > 1)
            throw new IllegalArgumentException("note probability must be from <0-1> range");

        if (!rhythmVals.contains(baseUnit))
            throw new IllegalArgumentException("declared base unit not in the set {1,2,4,8,16}");

        int rhythmSeqLength = timeSign.getTimeSignT()*baseUnit/timeSign.getTimeSignB();
        Vector<Integer> rhythmSequence = new Vector<>(rhythmSeqLength);
        noteCntr = 0;
        //rhythmSequence.add(1);
        for (int i=0; i<rhythmSeqLength; i++) {
            if (i % baseUnit/timeSign.getTimeSignB() == 0)
                //rhythmSequence.set(i, (int) Math.round(Math.random()+0.3));
                rhythmSequence.add((int) Math.round(Math.random()+noteProb));
            else
                //rhythmSequence.set(i, (int) Math.round(Math.random()));
                rhythmSequence.add((int) Math.round(Math.random()));
            //endif
            if (rhythmSequence.get(i) == 1)
                noteCntr++;
            //endif
        }   //for

        return rhythmSequence;
    }

    public Vector<Integer> setRhythmSequence() {
        int baseUnit = 16;
        int rhythmSeqLength = timeSign.getTimeSignT() * baseUnit / timeSign.getTimeSignB();
        Vector<Integer> rhythmSequence = new Vector<>(rhythmSeqLength);
        noteCntr = 0;
        for (int i = 0; i < rhythmSeqLength; i++) {
            // sekwencja: cwiercnuta + 2 osemki
            if (i % 8 == 0 || i % 8 == 4 || i % 8 == 6) {
                rhythmSequence.add(1);
                noteCntr++;
            } else
                rhythmSequence.add(0);
            //endif
        } //for
        return rhythmSequence;
    }
}
