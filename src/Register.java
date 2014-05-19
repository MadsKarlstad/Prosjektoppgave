/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.io.Serializable;
import java.util.Map;
/*
 * Interface Register. Brukes av Utleierregister,Søkerregister,Eneboligregister,Leilighetregister,Kontraktregister
 * Skrevet av Erlend Westbye. Sist oppdatert 02.05.14
 */
public interface Register extends Serializable{
    //Metoder for å legge til, fjerne, returnere og sjekke om et objekt finnes
    public boolean leggTil(Object object);
    public boolean finnes(String nr);
    public boolean fjern(String nr);

    public Object getObject(String nr);

    public Map getMap();




}
