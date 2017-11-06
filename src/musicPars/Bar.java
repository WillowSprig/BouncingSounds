package musicPars;

public class Bar {

    private TimeSign timeSign;
    private int barNo;
    private int rhythmSeqLength;
    private int[] rhythmSequence;
    private int baseUnit;
    private int noteCntr;

    public Bar(int inTST, int inTSB, int inBaseUnit, int inBarNo){
        this.timeSign = new TimeSign(inTST, inTSB);
        this.barNo = inBarNo;
        this.baseUnit = inBaseUnit;
        this.rhythmSeqLength = timeSign.getTimeSignT()*baseUnit/timeSign.getTimeSignB();
        this.rhythmSequence = new int[rhythmSeqLength];
    }

    public void incBarNo(){
        this.barNo++;
    }

    int getBarNo(){
        return this.barNo;
    }

    public int[] getRhythmSequence(){
        return this.rhythmSequence;
    }

    public double[] getRhythmSequenceSec(int tempo) {
        double[] rhythmSeqSec = new double[noteCntr];
        int j = 0, sumBeats = 1;
        for (int i = 1; i < rhythmSeqLength; i++){
            if (rhythmSequence[i] == 1)
                rhythmSeqSec[j++] = sumBeats * 60 / tempo;
            else
                sumBeats++;
            //endif
        }   //for
        return rhythmSeqSec;
    }

    public void randomiseRhythmSequence(){
        noteCntr = 0;
        rhythmSequence[0] = 1;
        for (int i=1; i<rhythmSeqLength; i++) {
            if (i % baseUnit/timeSign.getTimeSignB() == 0)
                rhythmSequence[i] = (int) Math.round(Math.random()+0.3);
            else
                rhythmSequence[i] = (int) Math.round(Math.random());
            //endif
            if (rhythmSequence[i] == 1)
                noteCntr++;
            //endif
        }   //for
    }

    public void changeTimeSign(int newTST, int newTSB){
        this.timeSign.changeTimeSign(newTST, newTSB);
        this.rhythmSeqLength = newTST*baseUnit/newTSB;
        this.rhythmSequence = new int[rhythmSeqLength];
    }

    public TimeSign getTimeSign(){
        return this.timeSign;
    }

    public class TimeSign {

        private int timeSignT;
        private int timeSignB;

        TimeSign(int inTST, int inTSB){
            this.timeSignT = inTST;
            this.timeSignB = inTSB;
        }

        void changeTimeSign(int newTST, int newTSB){
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
}
