/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.io.Serializable;
import java.util.Date;
/*
 *Kontrakt objekt. Opprettes kun ved inngåelse av et leieforhold mellom Utleier, Søker og en gitt Bolig.
 * Skrevet av Erlend Westbye. Sist oppdatert 10.04.14
 */
public class Kontrakt implements Serializable{
    /*utleieboligen
utleier
leietager
leiepris pr måned
leieavtalens varighet (tidrom)*/
    private String kontraktnr;
    private Bolig bolig;
    private Utleier utleier;
    private Soker leietager;
    private int pris;
    private String fra;
    private String til;
    private boolean aktiv;


    public Kontrakt(String kontraktnr,Bolig bolig, Utleier utleier, Soker leietager, int pris, String fra, String til) {
        this.kontraktnr = kontraktnr;
        this.bolig = bolig;
        this.utleier = utleier;
        this.leietager = leietager;
        this.pris = pris;
        this.fra = fra;
        this.til = til;

    }
    //Set- og Get-metoder
    public String getKontraktnr(){
        return kontraktnr;
    }

    public Bolig getBolig() {
        return bolig;
    }

    public String getBolignr(){
        return bolig.getBolignr();
    }

    public Utleier getUtleier() {
        return utleier;
    }

    public String getUtleiernavn(){
        return utleier.getNavn();
    }

    public Soker getLeietager() {
        return leietager;
    }

    public String getLeietagernavn(){
        return leietager.getNavn();
    }

    public int getPris() {
        return pris;
    }

    public String getFra() {
        return fra;
    }

    public String getTil() {
        return til;
    }

    public void setAktiv(boolean b){
        aktiv = b;
    }

    public boolean getAktiv(){
        return aktiv;
    }

    public String getAktivTekst(){
        String s = "";
        if(aktiv==true){
            s="Ja";
        }
        else if(aktiv==false){
            s="Nei";
        }
        return s;
    }

    @Override
    public String toString() {
        return "Kontrakt for leie av bolig med bolignummer: " +
                bolig.getBolignr() +
                "\n utleier: " + utleier.getNavn() +
                "\n leietager:" + leietager.getNavn() +
                "\n pris:" + pris +
                "\n fra:" + fra +
                "\n til" + til;
    }
}
