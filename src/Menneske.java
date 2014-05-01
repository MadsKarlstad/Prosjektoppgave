/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.TreeMap;

public interface Menneske extends Serializable {
    public String getFødselsnummer();
    public String getNavn();
    public String getFornavn();
    public String getEtternavn();
    public String getAdresse();
    public String getMail();
    public String getTelefonnummer();

    public void setFornavn(String fornavn);
    public void setEtternavn(String etternavn);
    public void setAdresse(String adresse);
    public void setMail(String mail);
    public void setTelefonnummer(String telefonnummer);






}
