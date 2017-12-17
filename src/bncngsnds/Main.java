package bncngsnds;

import midiIF.*;
import musicPars.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wybierz opcję:\n\t0 - generowanie sekwencji\n\t1 - odtwarzanie z pliku\n");
        int ans = scanner.nextInt();
        if (ans==1) {
            MIDITest test = new MIDITest();
            test.playFile();
        } else if (ans == 0) {
            Rhythm rhythm = new Rhythm(16, 3, 4, 60, 0.3, 5);
            rhythm.setRhythmSequence();
            MIDISequence midiSequence = new MIDISequence(rhythm);
            midiSequence.prepare();
            midiSequence.play();
        }
        else {
            System.out.println("Incorrect input");
        }
    }
}
