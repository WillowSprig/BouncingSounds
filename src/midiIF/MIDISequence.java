package midiIF;

import musicPars.Rhythm;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class MIDISequence {

    private Sequencer sequencer;
    private Synthesizer synthesizer;
    private Rhythm rhythm;
    private Sequence midiSequence;

    public MIDISequence(Rhythm rhythm){

        try {
            this.rhythm = rhythm;
            midiSequence = new Sequence(Sequence.PPQ, rhythm.getBaseUnit()/4);
        }
        catch (InvalidMidiDataException imde){
            System.out.println("MIDI data is invalid!");
        }
    }

    public void prepare(){
        try {
            synthesizer = MidiSystem.getSynthesizer();
            sequencer = MidiSystem.getSequencer();

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
