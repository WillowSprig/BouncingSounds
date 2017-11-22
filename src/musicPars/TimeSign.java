package musicPars;

public class TimeSign {

    private int timeSignT;
    private int timeSignB;

    public TimeSign(int timeSignT, int timeSignB){
        if (timeSignT <= 0 || timeSignT > 30 || !Bar.rhythmVals.contains(timeSignB))
            throw new IllegalArgumentException("time signature must be greater than 0 and from the set {1,2,4,8,16}");

        this.timeSignT = timeSignT;
        this.timeSignB = timeSignB;
    }

    public void setTimeSign(int timeSignT, int timeSignB){
        if (timeSignT <= 0 || timeSignT > 30 || !Bar.rhythmVals.contains(timeSignB))
            throw new IllegalArgumentException("time signature must be greater than 0 and from the set {1,2,4,8,16}");

        this.timeSignT = timeSignT;
        this.timeSignB = timeSignB;
    }

    public int getTimeSignT(){ return timeSignT; }

    public int getTimeSignB(){
        return timeSignB;
    }
}