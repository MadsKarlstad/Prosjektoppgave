/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Date;

public abstract class Bolig implements Hjem,Serializable {
    private String adresse;
    private int boareal;
    private int antallRom;
    private int byggeår;
    private String beskrivelse;
    private int pris;
    private String ledigFra;
    private String bolignummer;
    private Utleier eier;
    private Soker soker;

    private boolean røyke;
    private boolean husdyr;
    private boolean balkong;
    private boolean terasse;

    private boolean tvInkludert;
    private boolean internettInkludert;
    private boolean strømInkludert;
    private boolean parkering;

    private boolean erØnsket;
    private boolean erUtleid;

    private String bildesti;

    private ImageIcon bilde;


    private Personregister register;


    public Bolig(String bildesti, String adresse, int boareal, int antallRom, int byggeår, String beskrivelse, int pris, String ledigFra, String bolignummer, Utleier eier, boolean røyke, boolean husdyr, boolean balkong, boolean terasse, boolean tvInkludert, boolean internettInkludert, boolean strømInkludert, boolean parkering, boolean erØnsket, boolean erUtleid) {
        this.bildesti = bildesti;
        this.adresse = adresse;
        this.boareal = boareal;
        this.antallRom = antallRom;
        this.byggeår = byggeår;
        this.beskrivelse = beskrivelse;
        this.pris = pris;
        this.ledigFra = ledigFra;
        this.bolignummer = bolignummer;
        this.eier = eier;
        this.røyke = røyke;
        this.husdyr = husdyr;
        this.balkong = balkong;
        this.terasse = terasse;
        this.tvInkludert = tvInkludert;
        this.internettInkludert = internettInkludert;
        this.strømInkludert = strømInkludert;
        this.parkering = parkering;
        this.erUtleid = erUtleid;
        this.erØnsket = erØnsket;
    }
    public void setBilde(ImageIcon bilde){
        this.bilde = bilde;
    }
    public ImageIcon getBilde(){
        return bilde;
    }

    @Override
    public String getAdresse() {
        return adresse;
    }

    @Override
    public String getBeskrivelse() {
        return beskrivelse;
    }

    @Override
    public int getBoareal() {
        return boareal;
    }

    @Override
    public int getAntallRom() {
        return antallRom;
    }

    @Override
    public int getByggår() {

        return byggeår;
    }

    @Override
    public String getBolignr() {
        return bolignummer;
    }

    @Override
    public int getPris() {
        return pris;
    }

    @Override
    public String getLedigDato() {
        return ledigFra;
    }
    @Override
    public Utleier getEier(){
        return eier;
    }

    public String getBildesti(){return bildesti;}

    public String getEiersNavn(){
        return eier.getNavn();
    }
    public void setEier(Utleier eier){
        this.eier = eier;
    }

    @Override
    public boolean røyke() {
        return røyke;
    }
    public String røyketekst(){
        String s = "";
        if(røyke==true){
            s = "Ja";
        }
        else
            s="Nei";
        return s;
    }

    @Override
    public boolean husdyr() {
        return husdyr;
    }

    @Override
    public boolean balkong() {
        return balkong;
    }

    @Override
    public boolean terasse() {
        return terasse;
    }

    @Override
    public boolean tvInkludert() {
        return tvInkludert;
    }

    @Override
    public boolean internettInkludert() {
        return internettInkludert;
    }

    @Override
    public boolean strømInkludert() {
        return strømInkludert;
    }
    @Override
    public boolean parkering(){
        return parkering;
    }

    public  boolean erUtleid(){return erUtleid;}

    public boolean erØnsker(){return erØnsket;}



}
