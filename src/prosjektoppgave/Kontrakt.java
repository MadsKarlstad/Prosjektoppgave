package prosjektoppgave;

/**
 * Created by Erlend on 31/03/14.
 *
 *
 * Kontraktklassen kommer til å generere et kontraktobjekt som henter mesteparten av infoen sin fra personobjektet
 * Dette skal være et objekt som kan generere en god string for å lage en leie/utleiekontrakt for brukerene våre
 * på lik linje med sio sin ordning ref:  http://i.imgur.com/97ekn5B.jpg
 *
 * All info vi trenger for lontrakten kan
 */
public class Kontrakt{


    //datashit for dette objektet førsøk på å printe kontarktstring

    private String navn;
    private String pnr;
    private String kontraktNr;

    public Kontrakt(String navn, String pnr, String kontraktNr){

        this.navn = navn;
        this.pnr = pnr;
        this.kontraktNr = kontraktNr;
        }


    public String printKontrakt(){


        String kontrakt = "Dette er kontrakt for " + navn + " persnr er "  + pnr;

        return kontrakt;

    }

    public String getKontraktNr(){

        return kontraktNr;

    }
}
