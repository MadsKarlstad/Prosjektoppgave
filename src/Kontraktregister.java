/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.util.Map;
import java.util.TreeMap;

/*
 * Register for alle kontrakter som registreres i systemet.
 * Skrevet av Mads Karlstad og Christoffer Jønsberg. Sist oppdatert 28.04.14
 */

public class Kontraktregister extends TreeMap<String, Kontrakt> implements Register{

    //Metode for å legge en kontrakt inn i registeret. Sjekker om det finnes en kontrakt allerede med samme Key-verdi
    @Override
    public boolean leggTil(Object object) {
        if(object instanceof Kontrakt){
            if(!finnes(((Kontrakt) object).getKontraktnr())){

                put(((Kontrakt) object).getKontraktnr(),(Kontrakt) object);
                return true;

            }
        }
        return false;
    }

    //Metode for å sjekke om det allerede finnes en kontrakt med gitt Key-verdi(String nr)
    @Override
    public boolean finnes(String nr) {
        return containsKey(nr);
    }

    //Metode for å slette en kontrakt fra registeret. String nr er kontraktnummeret(Key-verdien)
    @Override
    public boolean fjern(String nr) {
        if(finnes(nr)){
            remove(nr);
            return true;
        }
        return false;
    }

    //Returnerer objekt med parameter nr(Kontraktnummeret)
    @Override
    public Object getObject(String nr) {
        if(finnes(nr)){
            return get(nr);
        }
        return null;
    }

    //Returnerer registeret
    @Override
    public Map getMap() {
        return this;
    }
}
