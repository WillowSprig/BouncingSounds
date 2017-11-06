package bncngsnds;

import midiIF.*;
import musicPars.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        File midiFile = new File("/home/teri/IdeaProjects/BouncingSounds/Pseudolosowosc.midi");

        if(!midiFile.exists() || midiFile.isDirectory() || !midiFile.canRead()) {
            System.out.println("Error while trying to read MIDI file!");
        }



        MIDISequence sequence = new MIDISequence(midiFile);
        sequence.play();

    }
}
