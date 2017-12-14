package bncngsnds;

import midiIF.*;
import musicPars.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        Rhythm rhythm = new Rhythm(16,3,4,60,0.3,5);
        rhythm.setRhythmSequence();
        MIDISequence midiSequence = new MIDISequence(rhythm);
        midiSequence.play();

    }
}
