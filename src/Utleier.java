/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.util.LinkedList;
import java.util.Map;
/**
 * Utleierklasse, arver datafelter fra superklassen Person, definerer unik data for sin egen klasse
 * Skrevet av Christoffer Jønsberg. Sist endret 10.04.14
 */
public class Utleier extends Person {
    private String firma;


    private LinkedList<Bolig> eideBoliger;
    private LinkedList<Enebolig> ønskedeeneboligliste;
    private LinkedList<Leilighet> ønskedeleilighetListe;

    public Utleier(String FØDSELSNUMMER, String fornavn, String etternavn, String adresse, String mail, String telefonnummer, String firma) {
        super(FØDSELSNUMMER, fornavn, etternavn, adresse, mail, telefonnummer);
        this.firma = firma;

        ønskedeeneboligliste = new LinkedList<Enebolig>();
        ønskedeleilighetListe = new LinkedList<Leilighet>();
        eideBoliger = new LinkedList<Bolig>();

    }
    //Set- og Get-metoder

    public String getFirma() {
        return firma;
    }
    public String getNavn(){
        String navn = getFornavn() + " " + getEtternavn();
        return navn;
    }


    //Metode som oppdaterer boliglistene
    public void oppdaterLister(Boligregister boligregister, Leilighetregister leilighetregister){

        eideBoliger.clear();
       
        

        Enebolig enebolig;
        Leilighet leilighet;


      


        if(boligregister!=null)for(int i = 0; i < boligregister.size(); i++){
            String key = (String) boligregister.keySet().toArray()[i];
            Utleier eier = this;
            enebolig = boligregister.get(key);

            if (boligregister.get(key).getEier().getNavn().equals(eier.getNavn())){

                eideBoliger.add(enebolig);
                enebolig.setEier(eier);


            }



        }
        
        if(leilighetregister!=null)for(int i = 0; i < leilighetregister.size(); i++){
            String key = (String) leilighetregister.keySet().toArray()[i];
            Utleier eier = this;
            leilighet = leilighetregister.get(key);


            

            if (leilighetregister.get(key).getEier().getNavn().equals(eier.getNavn())){

                eideBoliger.add(leilighet);
                leilighet.setEier(eier);

            }}



        for(int i = 0; i < ønskedeeneboligliste.size(); i++){

            

            if(ønskedeeneboligliste.get(i).erUtleid()){
                System.out.println("aktiveres");
                ønskedeeneboligliste.remove(i);
            }
            
     for(int j = 0; i < ønskedeleilighetListe.size(); i++){

            if(ønskedeleilighetListe.get(j).erUtleid()){
                ønskedeleilighetListe.remove(j);
            }


        }

    }
    }
    //Metode som legger inn leilighet i listen over leiligheter som er ønsket å leie
    public void addLeilighet(Leilighet leilighet){
        ønskedeleilighetListe.add(leilighet);
    }
    //Metode som legger inn enebolig i listen over eneboliger som er ønsket å leie
    public void addEnebolig(Enebolig enebolig){
        ønskedeeneboligliste.add(enebolig);
    }
    //Fjerner en leilighet fra ønskedeleiligheter
    public void removeLeilighet(Leilighet leilighet){ ønskedeleilighetListe.remove(leilighet);}
    //Fjerner en enebolig fra ønskedeeneboliger
    public void removeEnebolig(Enebolig enebolig){ ønskedeeneboligliste.remove(enebolig);}
    //Returnerer ønskedeeneboligliste
    public LinkedList<Enebolig> getØnskedeEneboliger(){

        return ønskedeeneboligliste;
    }
    //returnerer ønskedeleilighetliste
    public LinkedList<Leilighet> getØnskedeLeiligheter(){

        return ønskedeleilighetListe;
    }

    //Metode som legger inn en bolig i listen over boliger utleieren eier
    public void addBolig(Bolig bolig){
        eideBoliger.add(bolig);
    }
    //Metode som fjerner en bolig fra listen over boliger utleieren eier
    public void removeBolig(Bolig bolig){eideBoliger.remove(bolig);}
    //returnerer listen over eide boliger

    public LinkedList<Bolig> getEideBoliger(){
        return eideBoliger;
    }



}
