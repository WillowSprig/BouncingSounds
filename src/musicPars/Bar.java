package musicPars;

import java.util.ArrayList;
import java.util.List;

public class Bar {

    private TimeSign timeSign;
    private int barNo;
    private int noteCntr;

    public Bar(int inTST, int inTSB, int inBarNo){
        this.timeSign = new TimeSign(inTST, inTSB);
        this.barNo = inBarNo;

    }

    public void incBarNo(){
        this.barNo++;
    }

    int getBarNo(){
        return this.barNo;
    }

    public void setTimeSign(int newTST, int newTSB){ this.timeSign.setTimeSign(newTST, newTSB); }

    public List<Integer> randomiseRhythmSequence(int baseUnit){
        try{
            int rhythmSeqLength = timeSign.getTimeSignT()*baseUnit/timeSign.getTimeSignB();

            List<Integer> rhythmSequence = new ArrayList<>(rhythmSeqLength);
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

            return rhythmSequence;
        }   //try
        catch (ClassCastException cce){
            System.out.println("Wrong time signature or base rhythmic unit - rhythm sequence length must be an integer value");
            return null;
        }
    }

    public int getNoteCntr() { return this.noteCntr; }

    public TimeSign getTimeSign(){
        return this.timeSign;
    }

}
