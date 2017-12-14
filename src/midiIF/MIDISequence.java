package midiIF;

import musicPars.Rhythm;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class MIDISequence {

    private Sequencer sequencer;
    private Synthesizer synthesizer;
    private Rhythm rhythm;
    private Sequence midiSequence;
    private Track track;
    private MidiEvent event;
    private ShortMessage shortMsg;

    public MIDISequence(Rhythm rhythm){

        try {
            this.rhythm = rhythm;
            midiSequence = new Sequence(Sequence.PPQ, rhythm.getBaseUnit()/4);
            track = midiSequence.createTrack();
            shortMsg = new ShortMessage();
        }
        catch (InvalidMidiDataException imde){
            System.out.println("MIDI data is invalid!");
        }
    }

    public void prepare(){
        try {
            //synthesizer = MidiSystem.getSynthesizer();
            sequencer = MidiSystem.getSequencer();
            int[] noteSequence = rhythm.getRhythmSequenceTicks();
            shortMsg.setMessage(ShortMessage.PROGRAM_CHANGE, 0, 0);
            event = new MidiEvent(shortMsg,(long)0);
            track.add(event);
            sequencer.setTempoInBPM(rhythm.getTempo());

            int noteID=60;
            int velocity=127;
            MIDINote currNote=new MIDINote();

            for (int i=0; i<rhythm.getNoteSum(); i++){
                currNote.replace(noteID,noteSequence[i],velocity);
                currNote.addNote(track);
            }
            sequencer.setSequence(midiSequence);

        } //try
        catch (MidiUnavailableException mue){
            System.out.println("MIDI device is unavailable!");
        }
        catch (InvalidMidiDataException imde){
            System.out.println("MIDI data is invalid!");
        }
    }

    public void play(){
        try {
            prepare();
            sequencer.open();
            sequencer.start();
            while(true) {
                if(sequencer.isRunning()) {
                    try {
                        Thread.sleep(100);
                    }
                    catch(InterruptedException iu) {
                        break;
                    }
                } else {
                    break;
                }
            } //while
            sequencer.stop();
            sequencer.close();
        } //try
        catch (MidiUnavailableException mue){
            System.out.println("MIDI device is unavailable!");
        }
    }

}