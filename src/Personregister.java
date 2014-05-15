/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/*
 * Register for alle registrerte utleiere i systemet. Implementerer interfacet Register
 * Skrevet av Mads Karlstad og Christoffer Jønsberg. Sist oppdatert 10.04.14
 */
public class Personregister extends TreeMap<String, Utleier> implements Register{
    
    //Metode som legger inn objektet av innkommen parameter. Sjekker om det finnes et objekt med samme Key-verdi i systemet fra før
    @Override
    public boolean leggTil(Object object) {
        if(object instanceof Utleier){
            if(!finnes(((Utleier) object).getFødselsnummer())){


                put(((Utleier) object).getFødselsnummer(),(Utleier) object);
                return true;

            }
        }
        return false;
    }

    //Metode som sjekker om det finnes et objekt med innkommen parameter(Key-verdi) i registeret fra før
    @Override
    public boolean finnes(String nr) {
        return containsKey(nr);
    }

    //Metode som sletter en Utleier fra registeret
    @Override
    public boolean fjern(String nr) {
        if(finnes(nr)){
            remove(nr);
            return true;
        }
        return false;
    }

    //Metode som returnerer et objekt med innkommen Key-verdi
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
