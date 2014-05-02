/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.util.LinkedList;

public class Utleier extends Person {
    private String firma;
    private Boligregister boligregister;

    private LinkedList<Enebolig> ønskedeeneboligliste;
    private LinkedList<Leilighet> ønskedeleilighetListe;

    public Utleier(String FØDSELSNUMMER, String fornavn, String etternavn, String adresse, String mail, String telefonnummer, String firma) {
        super(FØDSELSNUMMER, fornavn, etternavn, adresse, mail, telefonnummer);
        this.firma = firma;
        boligregister = new Boligregister();

        ønskedeeneboligliste = new LinkedList<Enebolig>();
        ønskedeleilighetListe = new LinkedList<Leilighet>();

    }

    public String getFirma() {
        return firma;
    }
    public String getNavn(){
        String navn = getFornavn() + " " + getEtternavn();
        return navn;
    }


    /*public void leggInnØnsketEnebolig(Enebolig enebolig){

        eneboligliste.add(enebolig);
        }

    public void leggInnØnsketLeilighet(Leilighet leilighet){

        leilighetliste.add(leilighet);
    }*/

    public LinkedList<Enebolig> getØnskedeEneboliger(){

        return ønskedeeneboligliste;
    }

    public LinkedList<Leilighet> getØnskedeLeiligheter(){

        return ønskedeleilighetListe;
    }

    public void addLeilighet(Leilighet leilighet){
        ønskedeleilighetListe.add(leilighet);
    }

    public void addEnebolig(Enebolig enebolig){
        ønskedeeneboligliste.add(enebolig);
    }

    public Boligregister getBoligregister() {
        return boligregister;
    }

}
