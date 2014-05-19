/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.util.Map;
import java.util.TreeMap;
/*
 * Register over leiligheter som er registrert i systemet
 * Skrevet av Mads Karlstad. sist oppdatert 29.04.14
 */
public class Leilighetregister extends TreeMap<String, Leilighet> implements Register{

    //Metode som legger inn en leilighet i registeret. Sjekker om det finnes en leilighet med gitt Key-verdi fra før i registeret
    @Override
    public boolean leggTil(Object object) {
        if(object instanceof Leilighet){
            if(!finnes(((Leilighet) object).getBolignr())){

                put(((Leilighet) object).getBolignr(),(Leilighet) object);
                return true;

            }
        }
        return false;
    }

    //Metode som sjekker om det finnes et objekt med samme Key-verdi i registeret fra før
    @Override
    public boolean finnes(String nr) {
        return containsKey(nr);
    }
    //Fjerner en leilighet med innkommen parameter(Key-verdi)
    @Override
    public boolean fjern(String nr) {
        if(finnes(nr)){
            remove(nr);
            return true;
        }
        return false;
    }
    //Returnerer en leilighet med innkommen parameter(Key-verdi)
    @Override
    public Object getObject(String nr) {
        if(finnes(nr)){
            return get(nr);
        }
        return null;
    }
    //returnerer registeret
    @Override
    public Map getMap() {
        return this;
    }
}
