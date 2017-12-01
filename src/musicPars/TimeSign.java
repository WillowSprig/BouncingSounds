package musicPars;

import java.util.HashSet;
import java.util.Set;

public class TimeSign {

    private int timeSignT;
    private int timeSignB;
    public static Set<Integer> rhythmVals = new HashSet<>(6);

    public TimeSign(int timeSignT, int timeSignB) throws IllegalArgumentException{
        for (int i=0; i<5; i++) rhythmVals.add((int)Math.pow(2,i));
        if (timeSignT <= 0 || timeSignT > 30 || !this.rhythmVals.contains(timeSignB))
            throw new IllegalArgumentException("time signature must be greater than 0 and from the set {1,2,4,8,16}");
        else {
            this.timeSignT = timeSignT;
            this.timeSignB = timeSignB;
        }
    }

    public void setTimeSign(int timeSignT, int timeSignB) throws IllegalArgumentException{
        if (timeSignT <= 0 || timeSignT > 30 || !this.rhythmVals.contains(timeSignB))
            throw new IllegalArgumentException("time signature must be greater than 0 and from the set {1,2,4,8,16}");
        else {
            this.timeSignT = timeSignT;
            this.timeSignB = timeSignB;
        }
    }

    public int getTimeSignT() { return timeSignT; }

    public int getTimeSignB() {
        return timeSignB;
    }
}
