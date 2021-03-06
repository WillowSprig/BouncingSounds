package midiIF;

import javax.sound.midi.*;

public class MIDINote{

    private int noteID;
    private int velocity;
    private long duration;
    private ShortMessage msg;
    private MidiEvent event;
    private long position;

    public MIDINote(){
        msg = new ShortMessage();
        position=0;
    }

    public void replace(int noteID, int duration, int velocity){
        this.duration=duration;
        this.noteID=noteID;
        this.velocity=velocity;
    }

    public void addNoteOn(Track track) throws InvalidMidiDataException {
        msg.setMessage(ShortMessage.NOTE_ON, 0, noteID, velocity);
        event = new MidiEvent(msg, position);
        track.add(event);
    }
    public void addNoteOff(Track track) throws InvalidMidiDataException {
        position = position + duration;
        msg.setMessage(ShortMessage.NOTE_OFF, 0, noteID, velocity);
        event = new MidiEvent(msg,position);
        track.add(event);
    }
}
