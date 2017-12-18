package bncngsnds;

import musicPars.Rhythm;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class CLI implements UI{

    private Scanner scanner = new Scanner(System.in);
    private int rhythm[] = new int[6];
    private static Set<Integer> rhythmVals = new HashSet<>(6);
    private double ans = -1;

    public CLI (){
        for (int i=0; i<5; i++) rhythmVals.add((int)Math.pow(2,i));
    }

    @Override
    public Rhythm setRhythmParams() {
        while (rhythm[1]<=0 || rhythm[1]>10) {
            System.out.println("Podaj metrum - liczbe miar w takcie: ");
            rhythm[1] = scanner.nextInt();
        }

        while (!rhythmVals.contains(rhythm[2])) {
            System.out.println("Podaj metrum - glowna miare: ");
            rhythm[2] = scanner.nextInt();
        }

        while (!this.rhythmVals.contains(rhythm[0])) {
            System.out.println("Podaj najmniejsza jednostke rytmiczna (1 - 16): ");
            rhythm[0] = scanner.nextInt();
        }

        while (rhythm[3]<=0 || rhythm[3]>200) {
            System.out.println("Podaj tempo [BPM]: ");
            rhythm[3] = scanner.nextInt();
        }

        while (rhythm[4]<=0 || rhythm[4]>100) {
            System.out.println("Podaj liczbe taktow: ");
            rhythm[4] = scanner.nextInt();
        }

        while (ans < 0 || ans > 1) {
            try {
                System.out.println("Podaj prawdopodobienstwo : ");
                ans = scanner.nextDouble();
            } catch (InputMismatchException ime) {
                System.out.println("Uzyj przecinka jako separatora dziesietnego");
                ans = -1;
                scanner = new Scanner(System.in);
            }
        }

        System.out.println();
        return new Rhythm(rhythm[0], rhythm[1], rhythm[2], rhythm[3], ans, rhythm[4]);
    }
}
