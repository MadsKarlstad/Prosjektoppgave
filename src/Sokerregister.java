/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.util.Map;
import java.util.TreeMap;

/**
 * Register over alle søkere i systemet
 * Skrevet av Mads Karlstad. Sist oppdatert 28.04.14
 */

public class Sokerregister extends TreeMap<String, Soker> implements Register{
    
    //Metode som legger inn en søker i registeret. Sjekker om det finnes et objekt i registeret med samme Key-verdi fra før
    @Override
    public boolean leggTil(Object object) {
        if(object instanceof Soker){
            if(!finnes(((Soker) object).getFødselsnummer())){

                put(((Soker) object).getFødselsnummer(),(Soker) object);
                return true;

            }
        }
        return false;
    }

    //Metode som sjekker om det finnes et objekt i registeret med samme Key-verdi(parameteret) fra før
    @Override
    public boolean finnes(String nr) {
        return containsKey(nr);
    }
    
    //Metode som sletter en søker fra registeret. Tar fødselsnummer(Key-verdien) som parameter
    @Override
    public boolean fjern(String nr) {
        if(finnes(nr)){
            remove(nr);
            return true;
        }
        return false;
    }

    //metode som returnerer en søker med innkommen parameter nr
    @Override
    public Object getObject(String nr) {
        if(finnes(nr)){
            return get(nr);
        }
        return null;
    }

    public Soker getSoker(String nr){
        if(finnes(nr)){
            return get(nr);
        }
        return null;
    }
    
    //metode som returnerer registeret
    @Override
    public Map getMap() {
        return this;
    }

}
