/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class Soker extends Person{
    private String antallPersoner;
    private String sivilstatus;
    private String yrke;
    private String arbeidsfohold_studiested;

    private DecimalFormat df;

    private int match = 1;

    private int minAreal;
    private int maksAreal;

    private int pris;

    private boolean røyk;
    private boolean husdyr;
    private boolean balkong;
    private boolean terasse;
    private boolean TVinkludert;
    private boolean Internetinkludert;
    private boolean strøminkludert;
    private boolean parkering;
    private boolean kjeller;
    private boolean heis;

    private LinkedList<Enebolig> eneboligliste;
    private LinkedList<Leilighet> leilighetliste;
    private LinkedList<Bolig> ønskedeboliger;

    public Soker(String FØDSELSNUMMER, String fornavn, String etternavn, String adresse, String mail, String telefonnummer,
                 String antallPersoner, String sivilstatus, String yrke, String arbeidsfohold_studiested, int minAreal,
                 int maksAreal, int pris,boolean røyk,boolean husdyr,boolean balkong,boolean terasse,
                 boolean TVinkludert,boolean Internetinkludert,boolean strøminkludert,boolean parkering,boolean kjeller,boolean heis) {
        super(FØDSELSNUMMER, fornavn, etternavn, adresse, mail, telefonnummer);
        this.antallPersoner = antallPersoner;
        this.sivilstatus = sivilstatus;
        this.yrke = yrke;
        this.arbeidsfohold_studiested = arbeidsfohold_studiested;
        this.minAreal = minAreal;
        this.maksAreal = maksAreal;
        this.pris = pris;

        this.røyk = røyk;
        this.husdyr = husdyr;
        this.balkong = balkong;
        this.terasse= terasse;
        this.TVinkludert = TVinkludert;
        this.Internetinkludert = Internetinkludert;
        this.strøminkludert= strøminkludert;
        this.parkering = parkering;
        this.kjeller = kjeller;
        this.heis = heis;

        initialiserLister();

        df = new DecimalFormat("#.##");
    }

    public void setAntallPersoner(String s){
        antallPersoner = s;
    }
    public void setYrke(String s){
        yrke = s;
    }
    public void setSivilstatus(String s){sivilstatus = s;}
    public void setArbeidsfohold_studiested(String s){arbeidsfohold_studiested = s;}
    public void setPris(int p){pris = p;}
    public void setMinAreal(int p){minAreal = p;}
    public void setMaksAreal(int p){maksAreal = p;}

    public void initialiserLister(){
        eneboligliste = new LinkedList<Enebolig>();
        leilighetliste = new LinkedList<Leilighet>();
        ønskedeboliger = new LinkedList<Bolig>();
    }

    public String getAntallPersoner() {
        return antallPersoner;
    }

    public String getSivilstatus() {
        return sivilstatus;
    }

    public String getYrke() {
        return yrke;
    }

    public String getArbeidsfohold_studiested() {
        return arbeidsfohold_studiested;
    }

    public int getMinAreal() {
        return minAreal;
    }

    public int getMaksAreal() {
        return maksAreal;
    }

    public int getPris() {
        return pris;
    }

    public boolean isRøyk() {
        return røyk;
    }

    public boolean isHusdyr() {
        return husdyr;
    }

    public boolean isBalkong() {
        return balkong;
    }

    public boolean isTerasse() {
        return terasse;
    }

    public boolean isTVinkludert() {
        return TVinkludert;
    }

    public boolean isInternetinkludert() {
        return Internetinkludert;
    }

    public boolean isStrøminkludert() {
        return strøminkludert;
    }

    public boolean isParkering() {
        return parkering;
    }

    public boolean isKjeller() {
        return kjeller;
    }

    public boolean isHeis() {
        return heis;
    }




    public void matcherEnebolig(Boligregister eneboligregister ){
        for (Map.Entry<String, Enebolig> entry : eneboligregister.entrySet()) {
            double prosent = 0;
            double sum = 0;
            if (isRøyk() == entry.getValue().røyke()) {
                sum += 1;
            }
            if (isBalkong() == entry.getValue().balkong()) {
                sum += match;

            }
            if (isHusdyr() == entry.getValue().husdyr()) {
                sum += match;

            }
            if (isInternetinkludert() == entry.getValue().internettInkludert()) {
                sum += match;

            }
            if (isTVinkludert() == entry.getValue().tvInkludert()) {
                sum += match;

            }
            if (isKjeller() == entry.getValue().isKjeller()) {
                sum += match;

            }
            if (isStrøminkludert() == entry.getValue().strømInkludert()) {
                sum += match;

            }
            if (isParkering() == entry.getValue().parkering()) {
                sum += match;

            }
            if (isTerasse() == entry.getValue().terasse()) {
                sum += match;
            }

            prosent = ((sum / 9) * 100);

            if (prosent > 50) {
                //System.out.println("Prosentmatch: "+ df.format(prosent) +"% for bolignummer " + entry.getValue().getBolignr());
                eneboligliste.add((Enebolig) entry.getValue());

                entry.getValue().setProsent(prosent);

            }
        }
    }

    public void matcherLeilighet(Leilighetregister leilighetregister){
        for (Map.Entry<String, Leilighet> entry : leilighetregister.entrySet()){
            double prosent = 0;
            double sum = 0;
            if(isRøyk() == entry.getValue().røyke()){
                sum+=1;
            }
            if(isBalkong() == entry.getValue().balkong()){
                sum+=match;

            }
            if(isHusdyr() == entry.getValue().husdyr()){
                sum+=match;

            }
            if(isInternetinkludert() == entry.getValue().internettInkludert()){
                sum+=match;

            }
            if(isTVinkludert() == entry.getValue().tvInkludert()){
                sum+=match;

            }
            if(isHeis() == entry.getValue().isHeis()){
                sum+=match;

            }
            if(isStrøminkludert() == entry.getValue().strømInkludert()){
                sum+=match;

            }
            if(isParkering() == entry.getValue().parkering()){
                sum+=match;

            }
            if(isTerasse() == entry.getValue().terasse()){
                sum+=match;
            }

            prosent = ((sum/9)*100);

            if(prosent > 50){
                //System.out.println("Prosentmatch: "+ df.format(prosent) +"% for bolignummer " + entry.getValue().getBolignr());
                leilighetliste.add((Leilighet) entry.getValue());
                entry.getValue().setProsent(prosent);
            }
        }
    }

    public Soker getSoker(){
        return this;
    }

    public void addØnskedBolig(Bolig bolig){
        ønskedeboliger.add(bolig);
    }

    public String getNavn(){
        String navn = getFornavn() + " " + getEtternavn();
        return navn;
    }


    public LinkedList getØnskedeBolgier(){
        return ønskedeboliger;
    }

    public LinkedList<Enebolig> getEneboligliste(){

        return eneboligliste;

    }

    public LinkedList<Leilighet> getLeilighetliste(){

        return leilighetliste;
    }

    public void setRøyk(boolean røyk) {
        this.røyk = røyk;
    }

    public void setHusdyr(boolean husdyr) {
        this.husdyr = husdyr;
    }

    public void setBalkong(boolean balkong) {
        this.balkong = balkong;
    }

    public void setTerasse(boolean terasse) {
        this.terasse = terasse;
    }

    public void setTVinkludert(boolean TVinkludert) {
        this.TVinkludert = TVinkludert;
    }

    public void setInternetinkludert(boolean internetinkludert) {
        Internetinkludert = internetinkludert;
    }

    public void setStrøminkludert(boolean strøminkludert) {
        this.strøminkludert = strøminkludert;
    }

    public void setParkering(boolean parkering) {
        this.parkering = parkering;
    }

    public void setKjeller(boolean kjeller) {
        this.kjeller = kjeller;
    }

    public void setHeis(boolean heis) {
        this.heis = heis;
    }



}