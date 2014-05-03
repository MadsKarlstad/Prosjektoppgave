/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.util.Date;
import java.util.LinkedList;

public class Leilighet extends Bolig {
    private int antallBoder;
    private int etasje;
    private boolean heis;
    private boolean ønsket;
    private boolean utleid;

    private LinkedList<Soker> sokerliste;

    private double prosent;

    public Leilighet(String bildesti, String adresse, int boareal, int antallRom, int byggeår, String beskrivelse, int pris, String ledigFra, String bolignummer, Utleier eier, boolean røyke, boolean husdyr, boolean balkong, boolean terasse, boolean tvInkludert, boolean internettInkludert, boolean strømInkludert, boolean parkering, int antallBoder, int etasje, boolean heis, boolean ønsket, boolean utleid) {
        super(bildesti, adresse, boareal, antallRom, byggeår, beskrivelse, pris, ledigFra, bolignummer, eier, røyke, husdyr, balkong, terasse, tvInkludert, internettInkludert, strømInkludert, parkering, utleid, ønsket);
        this.antallBoder = antallBoder;
        this.etasje = etasje;
        this.heis = heis;

        sokerliste = new LinkedList<Soker>();

    }

    public int getAntallBoder() {
        return antallBoder;
    }

    public int getEtasje() {
        return etasje;
    }

    public boolean isHeis() {
        return heis;
    }

    public String getBildesti(){
        return super.getBildesti();
    }

    public void setProsent(double p){

        prosent = p;


    }

    public double getProsent(){

        return  prosent;
    }

    public void setØnsket(boolean b){

        ønsket = b;

    }

    public boolean erØnsket(){

        return  ønsket;
    }

    public void setUtleid(boolean b){

        utleid = b;
    }
    public boolean erUtleid(){

        return  utleid;
    }

    public Leilighet getLeilighet(){

        return this;
    }

    @Override
    public String toString() {

        return "Dette er en (leilighet) " + getBeskrivelse() + " på " + getBoareal() + " kvadratmeter, den ligger på adressen " + getAdresse() + " med bolignummer: " + getBolignr()+"\n";
    }

    public void addSoker(Soker s){

        sokerliste.add(s);

    }

    public LinkedList getSokere(){
        return sokerliste;
    }

}
