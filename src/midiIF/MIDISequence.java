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
    private File midiFile;
    private MidiChannel channel;
    private MIDINote currNote;

    public MIDISequence(File midiFile){
        this.midiFile = midiFile;
        try {
            synthesizer = MidiSystem.getSynthesizer();
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(MidiSystem.getSequence(midiFile));
        }
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

    public void prepareSynth(){
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            MidiChannel chans[] = synthesizer.getChannels();
            channel = chans[0];
        }
        catch (MidiUnavailableException mue){
            System.out.println("MIDI device is unavailable!");
        }
    }

    public void playSynth(){
        try {
            int noteID=60;
            int velocity=127;
            long[] durations = rhythm.getRhythmSequenceMSec();
            if (channel != null) {
                for (int i=0; i<rhythm.getNoteSum(); i++) {
                    System.out.print(noteID + "\t");
                    channel.noteOn(noteID, velocity);
                    Thread.sleep(durations[i]);
                    channel.noteOff(noteID);
                }
            }
            System.out.println();
            synthesizer.close();
        }
        catch (InterruptedException ie)
        { ie.printStackTrace(); }
    }


    public void prepare(){
        try {
            synthesizer = MidiSystem.getSynthesizer();
            sequencer = MidiSystem.getSequencer();
            synthesizer.open();
            sequencer.open();
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
                currNote.addNoteOn(track);
                currNote.addNoteOff(track);
            }

//            midiFile = new File("myFile.mid");
//            try
//            {
//                MidiSystem.write(midiSequence, 0, midiFile);
//            }
//            catch (IOException e)
//            { System.out.println("I/O exception occured"); }

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
            sequencer.setTickPosition((long)0);
            sequencer.start();
            while(true) {
                if(sequencer.isRunning()) {
                    try {
                        Thread.sleep(100);
                    }
                    catch(InterruptedException iu) {
                        System.out.println("Interrupted Exception");
                        break;
                    }
                } else {
                    break;
                }
            } //while
            sequencer.stop();
            sequencer.close();
            synthesizer.close();
    }

    public Sequencer getSequencer() {
        return sequencer;
    }

    public Sequence getMidiSequence() {
        return midiSequence;
    }
}