package musicPars;

import java.util.Set;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class TimeSign {

    private int timeSignT;
    private int timeSignB;
    public boolean errorOcc = FALSE;
    Set<Integer> rhythmVals;

    public TimeSign(int timeSignT, int timeSignB){

        if (timeSignT<=0)// || rhythmVals.contains(timeSignB))
            errorOcc = TRUE;
        else{
            errorOcc = FALSE;
            this.timeSignT = timeSignT;
            this.timeSignB = timeSignB;
        }
    }

    public void setTimeSign(int timeSignT, int timeSignB){
        if (timeSignT<=0)// || rhythmVals.contains(timeSignB))
            errorOcc = TRUE;
        else{
            errorOcc = FALSE;
            this.timeSignT = timeSignT;
            this.timeSignB = timeSignB;
        }
    }

    public int getTimeSignT(){ return timeSignT; }

    public int getTimeSignB(){
        return timeSignB;
    }
}