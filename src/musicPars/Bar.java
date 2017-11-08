package musicPars;

import java.util.ArrayList;
import java.util.List;

public class Bar {

    private TimeSign timeSign;
    private int barNo;


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

    public void changeTimeSign(int newTST, int newTSB){
        this.timeSign.setTimeSign(newTST, newTSB);
        // notification for the Rhythm class needed
//        this.rhythmSeqLength = newTST*baseUnit/newTSB;
//        this.rhythmSequence = new int[rhythmSeqLength];
    }

    public TimeSign getTimeSign(){
        return this.timeSign;
    }

}
