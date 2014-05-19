/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
/*
 * Objekt av typen Enebolig, arver datafelter fra superklassen Bolig og definerer informasjon som er unikt for enebolig
 * Skrevet av Mads Karlstad. Sist oppdatert 10.04.14
 */
public class Enebolig extends Bolig {
    private int antallEtasjer;
    private boolean kjeller;
    private double tomtareal;
    private int antallBad;
    private Utleier eier;

    private LinkedList<Soker> sokerliste;

    String bildesti;

    private double prosent;

    private boolean ønsket;
    private boolean utleid;

    private Sokerregister sokerregister;


    public Enebolig(String bildesti, String adresse, int boareal, int antallRom, int byggeår, String beskrivelse, int pris, String ledigFra, String bolignummer, Utleier eier, boolean røyke, boolean husdyr, boolean balkong, boolean terasse, boolean tvInkludert, boolean internettInkludert, boolean strømInkludert, boolean parkering, int antallEtasjer, boolean kjeller, double tomtareal, int antallBad, boolean ønsket, boolean utleid,String bydel) {
        super(bildesti,adresse, boareal, antallRom, byggeår, beskrivelse, pris, ledigFra, bolignummer, eier, røyke, husdyr, balkong, terasse, tvInkludert, internettInkludert, strømInkludert, parkering, ønsket, utleid,bydel);
        this.antallEtasjer = antallEtasjer;
        this.kjeller = kjeller;
        this.tomtareal = tomtareal;
        this.antallBad = antallBad;

        sokerliste = new LinkedList<Soker>();
        sokerregister= new Sokerregister();

        setBlidesti(super.getBildesti());
        setEier(eier);
    }
    //Set- og Get-metoder
    public void setEier(Utleier utleier){
        eier = utleier;
    }

    public Utleier getEier(){
        return eier;
    }

    public int getAntallEtasjer() {
        return antallEtasjer;
    }

    public boolean isKjeller() {
        return kjeller;
    }

    public double getTomtareal() {
        return tomtareal;
    }

    public int getAntallBad() {
        return antallBad;
    }

    public void setBlidesti(String s){

        bildesti = s;

    }


    public String getBildesti(){

        return bildesti;

    }

    @Override
    public String toString() {

        return "Dette er en " + getBeskrivelse() + " på " + getBoareal() + " kvadratmeter, den ligger på adressen " + getAdresse()+ ", " + getBydel() + ", med bolignummer: " + getBolignr()+"\n";
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

    public String utleidTekst(){
        if(erUtleid()==true){
            return "Nei";
        }
        else
            return "Ja";
    }

    public String husdyrTekst(){
        String s = "";
        if(husdyr()==true){
            s="Ja";
        }
        else
            s="Nei";
        return s;
    }

    public String tvTekst(){
        String s = "";
        if(tvInkludert()==true){
            s="Ja";
        }
        else
            s="Nei";
        return s;
    }

    public String strømTekst(){
        String s = "";
        if(strømInkludert()==true){
            s="Ja";
        }
        else
            s="Nei";
        return s;
    }

    public Enebolig getEnebolig(){

        return this;
    }

    public void addSoker(Soker s){

        sokerliste.add(s);

    }

    public LinkedList getSokere(){
        return sokerliste;
    }

}
