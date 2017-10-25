package bncngsnds;

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
        this.timeSign.changeTimeSign(newTST, newTSB);
    }

    public TimeSign getTimeSign(){
        return this.timeSign;
    }

    public class TimeSign {

        private int timeSignT;
        private int timeSignB;

        public TimeSign(int inTST, int inTSB){
            this.timeSignT = inTST;
            this.timeSignB = inTSB;
        }

        public void changeTimeSign(int newTST, int newTSB){
            this.timeSignT = newTST;
            this.timeSignB = newTSB;
        }
    }
}
