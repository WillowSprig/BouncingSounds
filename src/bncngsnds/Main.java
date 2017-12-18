package bncngsnds;

import midiIF.*;
import musicPars.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wybierz opcję:" + "\n\t0 - generowanie sekwencji" + "\n\t1 - odtwarzanie z pliku" + "\n\t2 - odtwarzanie gotowej sekwencji\n");
        int ans = scanner.nextInt();
        if (ans==1) {
            MIDITest test = new MIDITest();
            test.playFile();
        } else if (ans==2) {
            MIDITest test = new MIDITest();
            test.playSequenceSynth();
        }
        else if (ans == 0) {
            CLI cli = new CLI();
            Rhythm rhythm = cli.setRhythmParams();
            rhythm.randomiseRhythmSequence();
            MIDISequence midiSequence = new MIDISequence(rhythm);
            midiSequence.prepareSynth();
            midiSequence.playSynth();
        }
        else {
            System.out.println("Incorrect input");
        }
    }
}
