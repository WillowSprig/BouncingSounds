package midiIF;

import javax.sound.midi.*;

public class MIDITest {

    Synthesizer synth;
    Sequencer sequencer;
    Sequence midiSequence;
    Track track;
    ShortMessage shortMsg;
    MidiEvent event;
    int noteNo;

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

    public void main() {

        try {
            shortMsg.setMessage(ShortMessage.PROGRAM_CHANGE, 0, 0);
            event = new MidiEvent(shortMsg,(long)0);
            track.add(event);
            sequencer.setTempoInBPM(60);
            noteNo=60;

            for (long tick=0; tick<24; tick+=4) {
                shortMsg.setMessage(ShortMessage.NOTE_ON, 0, noteNo, 127);
                event = new MidiEvent(shortMsg, tick);
                track.add(event);
                System.out.print(noteNo + "\t");
                shortMsg.setMessage(ShortMessage.NOTE_OFF, 0, noteNo++, 127);
                event = new MidiEvent(shortMsg, tick+4);
                track.add(event);
            }
            System.out.println();
            for (int i=0; i<track.size(); i++) {
                System.out.println(track.get(i).getMessage().getStatus());
            }
            sequencer.setSequence(midiSequence);

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

        }
        catch (MidiUnavailableException mue)
        { System.out.println("MIDI device unavailable");}
        catch (InvalidMidiDataException imde)
        { System.out.println("Invalid MIDI data");}
    }
}
