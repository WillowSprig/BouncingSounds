package bncngsnds;

public interface Playable {

    //void prepare(MIDINote[] notes);
    void prepare();
    void play();
    void changeParams();
}
