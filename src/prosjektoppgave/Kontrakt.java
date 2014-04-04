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


    String kontraktNr;



    public String getKontraktNr(){

        return kontraktNr;


    }

    public String pdfPrinter(Person inn){


        String midlertidigkontrakt = "Dette er kontrakt for" + inn.getNavn() + " personummer: " + inn.getNr() + " kontraktnummer: " + kontraktNr;





        return midlertidigkontrakt;

    }


}
