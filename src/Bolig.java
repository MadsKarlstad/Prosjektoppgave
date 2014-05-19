/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import javax.swing.*;
/*Superklasse for boliger, som implementerer interfacet Hjem. Inneholder alle felter som er felles for Enebolig og Leilighet
 *Skrevet av Mads Karlstad. Sist oppdatert 20.04.14
 */
public abstract class Bolig implements Hjem {
    private String adresse;
    private String bydel;
    private int boareal;
    private int antallRom;
    private int byggeår;
    private String beskrivelse;
    private int pris;
    private String ledigFra;
    private String bolignummer;
    private Utleier eier;


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

    String [] bydeler = { "Velg bydel", "Alna", "Bjerke", "Frogner", "Gamle Oslo", "Grorud",
            "Grünerløkka", "Nordre Aker", "Nordstrand", "Sagene", "St. Hanshaugen",
            "Stovner", "Søndre Nordstrand", "Ullern", "Vestre Aker", "Østensjø"};


    public Bolig(String bildesti, String adresse, int boareal, int antallRom, int byggeår, String beskrivelse, int pris, String ledigFra, String bolignummer, Utleier eier, boolean røyke, boolean husdyr, boolean balkong, boolean terasse, boolean tvInkludert, boolean internettInkludert, boolean strømInkludert, boolean parkering, boolean erØnsket, boolean erUtleid,String bydel) {
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
        this.bydel=bydel;

        final int VELG = 0;
        final int ALNA = 1;
        final int BJERKE = 2;
        final int FROGNER = 3;
        final int GAMLE_OSLO = 4;
        final int GRORUD = 5;
        final int LØKKA = 6;
        final int NORDRE_AKER = 7;
        final int NORDSTRAND = 8;
        final int SAGENE = 9;
        final int STHANSHAUGEN = 10;
        final int STOVNER = 11;
        final int SØNDRE_NORDSTRAND = 12;
        final int ULLERN = 13;
        final int VESTRE_AKER = 14;
        final int ØSTENSJØ = 15;

    }
    //Set- og Get-metoder
    public void setBilde(ImageIcon bilde){
        this.bilde = bilde;
    }
    public ImageIcon getBilde(){
        return bilde;
    }

    public String getBydel(){
        return bydel;
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

    public void setUtleid(boolean b){
        erUtleid = b;
    }

    public boolean erØnsker(){return erØnsket;}



}
