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
    }

    public void replace(int noteID, int duration, int velocity){
        this.duration=duration;
        this.noteID=noteID;
        this.velocity=velocity;
    }

    public void addNote(Track track, Sequencer sequencer) throws InvalidMidiDataException{
        position = sequencer.getTickPosition();
        msg.setMessage(ShortMessage.NOTE_ON, 0, noteID, velocity);
        event = new MidiEvent(msg,position);
        track.add(event);
        position = position + duration;
        msg.setMessage(ShortMessage.NOTE_OFF, 0, noteID, velocity);
        event = new MidiEvent(msg,position);
        track.add(event);
        sequencer.setTickPosition(position);
    }
}
