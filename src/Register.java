/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.io.Serializable;
import java.util.Map;

public interface Register extends Serializable{

    public boolean leggTil(Object object);
    public boolean finnes(String nr);
    public boolean fjern(String nr);

    public Object getObject(String nr);

    public Map getMap();




}
