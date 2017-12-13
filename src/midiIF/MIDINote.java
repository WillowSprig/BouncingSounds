package midiIF;

import java.util.Vector;

public class MIDINote{

    private int noteID;
    private int velocity;
    private int duration;
    private int msg;

    public MIDINote(int noteID, int duration, int velocity){
        this.duration=duration;
        this.noteID=noteID;
        this.velocity=velocity;
    }

    public void addNote(Vector<Integer> noteList){
        //noteOn
        msg=0x90;
        noteList.add(0);
        noteList.add(msg);
        noteList.add(noteID);
        noteList.add(velocity);
        //noteOff
        msg=0x80;
        noteList.add(duration);
        noteList.add(msg);
        noteList.add(noteID);
        noteList.add(0);
    }
}
