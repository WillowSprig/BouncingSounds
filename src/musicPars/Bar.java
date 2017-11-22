package musicPars;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Bar {

    private TimeSign timeSign;
    private int barNo;
    private int noteCntr;

    public Bar(TimeSign inTS, int inBarNo){
        this.timeSign = inTS;
        this.barNo = inBarNo;

    }

    public void incBarNo(){
        this.barNo++;
    }

    int getBarNo(){
        return this.barNo;
    }

    public void setTimeSign(int newTST, int newTSB){ timeSign.setTimeSign(newTST, newTSB); }

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
            double startProb = 0.3;
            for (int i=0; i<rhythmSeqLength; i++) {
                if (i % baseUnit/timeSign.getTimeSignB() == 0)
                    //rhythmSequence.set(i, (int) Math.round(Math.random()+0.3));
                    rhythmSequence.add((int) Math.round(Math.random()+noteProb+startProb));
                else
                    //rhythmSequence.set(i, (int) Math.round(Math.random()));
                    rhythmSequence.add((int) Math.round(Math.random()+noteProb));
                //endif
                if (rhythmSequence.get(i) == 2)
                    noteCntr++;
                //endif
            }   //for
            return rhythmSequence;
        }   //try
        catch (ClassCastException cce){
            System.out.println("Wrong time signature or base rhythmic unit - rhythm sequence length must be an integer value");
            return null;
        }
    }

    public void setRhythmSequence(){
        int baseUnit = 16;
        int rhythmSeqLength = timeSign.getTimeSignT()*baseUnit/timeSign.getTimeSignB();
        Vector<Integer> rhythmSequence = new Vector<>(rhythmSeqLength);
        noteCntr = 0;
        for (int i=0; i<rhythmSeqLength ;i++) {
            if (i % 4 == 0 || i % 4 == 2) {
                rhythmSequence.add(2);
                noteCntr++;
            }
            else if (i % 4 == 1)
                rhythmSequence.add(1);
            else
                rhythmSequence.add(0);
            //endif
        }
    }

    public int getNoteCntr() { return this.noteCntr; }

    public TimeSign getTimeSign(){
        return this.timeSign;
    }

}
