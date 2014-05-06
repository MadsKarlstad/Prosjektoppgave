/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class Personregister extends TreeMap<String, Utleier> implements Register{
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


    @Override
    public boolean finnes(String nr) {
        return containsKey(nr);
    }

    @Override
    public boolean fjern(String nr) {
        if(finnes(nr)){
            remove(nr);
            return true;
        }
        return false;
    }

    @Override
    public Object getObject(String nr) {
        if(finnes(nr)){
            return get(nr);
        }
        return null;
    }

    @Override
    public Map getMap() {
        return this;
    }

}
