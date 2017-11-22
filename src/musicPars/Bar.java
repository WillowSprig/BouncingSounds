package musicPars;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Bar {

    private TimeSign timeSign;
    private int barNo;
    private int noteCntr;

    public Bar(TimeSign timeSign, int barNo){
        this.timeSign = timeSign;
        this.barNo = barNo;
    }

    public void incBarNo(){
        this.barNo++;
    }

    int getBarNo(){
        return this.barNo;
    }

    public int getNoteCntr() { return this.noteCntr; }

    public void setTimeSign(int newTST, int newTSB){ timeSign.setTimeSign(newTST, newTSB); }

    public TimeSign getTimeSign(){
        return this.timeSign;
    }

    public Vector<Integer> randomiseRhythmSequence(int baseUnit, double noteProb){
        if (baseUnit/timeSign.getTimeSignB() < 1) {
            baseUnit = timeSign.getTimeSignB();
            System.out.println("Warning: baseUnit changed to " + baseUnit);
        } //endif
        try{
            int rhythmSeqLength = timeSign.getTimeSignT()*baseUnit/timeSign.getTimeSignB();
            Vector<Integer> rhythmSequence = new Vector<>(rhythmSeqLength);
            noteCntr = 0;
            //rhythmSequence.set(0, 1);
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
        }   //try
        catch (ClassCastException cce){
            System.out.println("Wrong time signature or base rhythmic unit - rhythm sequence length must be an integer value");
            return null;
        } //catch
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
    }
}
