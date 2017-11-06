package midiIF;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class MIDISequence implements Playable {

    private Sequencer sequencer;
    private Synthesizer synthesizer;

    private File midifile;

    public MIDISequence(MIDINote[] notes){
    }

    public MIDISequence(File inMidiFile){
        this.midifile = inMidiFile;
    }

    //public void prepare(MIDINote[] notes){ - wersja docelowa
        public void prepare(){
        try {
            synthesizer = MidiSystem.getSynthesizer();
            sequencer = MidiSystem.getSequencer();

            sequencer.setSequence(MidiSystem.getSequence(midifile));
        } //try
        catch (MidiUnavailableException mue){
            System.out.println("MIDI device is unavailable!");
        }
        catch (InvalidMidiDataException imde){
            System.out.println("MIDI data is invalid!");
        }
        catch (IOException ioe){
            System.out.println("I/O error occured!");
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

    public void changeParams(){

    }
}
