/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.util.LinkedList;
import java.util.Map;

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

    public String getFirma() {
        return firma;
    }
    public String getNavn(){
        String navn = getFornavn() + " " + getEtternavn();
        return navn;
    }



    public void oppdaterLister(Boligregister boligregister){

        eideBoliger.clear();

        Enebolig enebolig;

        for(int i = 0; i < ønskedeeneboligliste.size(); i++){


            if(ønskedeeneboligliste.get(i).erUtleid()){
                ønskedeeneboligliste.remove(i);
            }


        }


        for(int i = 0; i < boligregister.size(); i++){
            String key = (String) boligregister.keySet().toArray()[i];
            Utleier eier = this;
            enebolig = boligregister.get(key);

            System.out.println(key);

            if (boligregister.get(key).getEier().getNavn().equals(eier.getNavn())){

                eideBoliger.add(enebolig);
                enebolig.setEier(eier);

            }



        }

    }

    public void addLeilighet(Leilighet leilighet){
        ønskedeleilighetListe.add(leilighet);
    }

    public void addEnebolig(Enebolig enebolig){
        ønskedeeneboligliste.add(enebolig);
    }

    public void removeLeilighet(Leilighet leilighet){ ønskedeleilighetListe.remove(leilighet);}

    public void removeEnebolig(Enebolig enebolig){ ønskedeeneboligliste.remove(enebolig);}

    public LinkedList<Enebolig> getØnskedeEneboliger(){

        return ønskedeeneboligliste;
    }

    public LinkedList<Leilighet> getØnskedeLeiligheter(){

        return ønskedeleilighetListe;
    }


    public void addBolig(Bolig bolig){
        eideBoliger.add(bolig);
    }

    public void removeBolig(Bolig bolig){eideBoliger.remove(bolig);}

    public LinkedList<Bolig> getEideBoliger(){
        return eideBoliger;
    }



}
