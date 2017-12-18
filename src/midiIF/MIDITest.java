package midiIF;

import javax.sound.midi.*;
import java.io.File;

public class MIDITest {

    private Synthesizer synth;
    private Sequencer sequencer;
    private Sequence midiSequence;
    private Track track;
    private ShortMessage shortMsg;
    private MidiEvent event;
    private int noteNo;

    public MIDITest(){
        try {
            synth = MidiSystem.getSynthesizer();
            sequencer = MidiSystem.getSequencer();
            midiSequence = new Sequence(Sequence.PPQ, 4);
            track = midiSequence.createTrack();
            shortMsg = new ShortMessage();
        }
        catch (MidiUnavailableException mue)
        { System.out.println("MIDI device unavailable");}
        catch (InvalidMidiDataException imde)
        { System.out.println("Invalid MIDI data");}

    }

    public void playSequence() {

        try {
            sequencer.open();
            //if (!(sequencer instanceof Synthesizer)){
                synth = MidiSystem.getSynthesizer();
                Receiver synthRec = synth.getReceiver();
                Transmitter seqTrans = sequencer.getTransmitter();
                seqTrans.setReceiver(synthRec);
            //}
            synth.open();
            shortMsg.setMessage(ShortMessage.PROGRAM_CHANGE, 2, 0);
            event = new MidiEvent(shortMsg,(long)0);
            track.add(event);
            synthRec.send(shortMsg,(long)0);
            sequencer.setTempoInBPM(80);
            noteNo=60;

            for (long tick=0; tick<24; tick+=4) {
                shortMsg.setMessage(ShortMessage.NOTE_ON, 2, noteNo, 127);
                event = new MidiEvent(shortMsg, tick);
                track.add(event);
                synthRec.send(shortMsg,tick);
//                System.out.println(bytes1 + "\t" + event.getTick());
//                System.out.print(noteNo + "\t");
                shortMsg.setMessage(ShortMessage.NOTE_OFF, 2, noteNo, 127);
                event = new MidiEvent(shortMsg, tick+4);
                track.add(event);
                synthRec.send(shortMsg,tick);
                noteNo++;
            }
//            System.out.println();
//            for (int i=0; i<track.size(); i++) {
//                System.out.println(track.get(i).getMessage().getStatus());
//            }
            sequencer.setSequence(midiSequence);
            sequencer.setTickPosition((long)0);
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
            synth.close();
        }
        catch (MidiUnavailableException mue)
        { System.out.println("MIDI device unavailable");}
        catch (InvalidMidiDataException imde)
        { System.out.println("Invalid MIDI data");}
    }

    public void playSequenceSynth() {
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();

            MidiChannel channel[] = synth.getChannels();

            if (channel[0] != null) {
                channel[0].noteOn(60,127);
               Thread.sleep(2000);
                channel[0].noteOn(60,0);
            }
            synth.close();
        }
        catch (MidiUnavailableException mue)
        { mue.printStackTrace(); }
        catch (InterruptedException ie)
        { ie.printStackTrace(); }
    }

    public void playFile()
    {
        File midiFile = new File("/home/teri/testFile.mid");

        if(!midiFile.exists() || midiFile.isDirectory() || !midiFile.canRead()) {
            System.out.println("Error while trying to read MIDI file!");
        }
        MIDISequence sequence = new MIDISequence(midiFile);
        sequence.getSequencer().setTempoInBPM(80);
        sequence.play();
    }
}
