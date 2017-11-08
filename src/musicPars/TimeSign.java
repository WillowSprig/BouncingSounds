package musicPars;

public class TimeSign {

    private int timeSignT;
    private int timeSignB;

    public TimeSign(int inTST, int inTSB){
        this.timeSignT = inTST;
        this.timeSignB = inTSB;
    }

    public void setTimeSign(int newTST, int newTSB){
        this.timeSignT = newTST;
        this.timeSignB = newTSB;
    }

    public int getTimeSignT(){
        return timeSignT;
    }

    public int getTimeSignB(){
        return timeSignB;
    }
}