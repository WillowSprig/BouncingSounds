package bncngsnds;

public class CLI implements UI{


    public CLI (){
        setMelodyParams();
        setRhythmParams();
    }

    @Override
    public void setMelodyParams() {
        System.out.println("Podaj klucz");
    }

    @Override
    public void setRhythmParams() {
        System.out.println("Podaj metrum - glowna miare: ");
        //int ans

    }

    @Override
    public void getMelodyParams() {

    }

    @Override
    public void getRhythmParams() {

    }
}