/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.TreeMap;

/*
 * Interface Menneske. Brukes av superklassen Person, som igjen arves av Utleier og Søker
 * Skrevet av Erlend Westbye, Mads Karlstad og Christoffer Jønsberg. Sist oppdatert 26.04.14
 */

public interface Menneske extends Serializable {
    /**
     * get-metode for fødselsnummer til personen
     * @return FØDSELSNUMMER - personens fødselsnummer
     */
    public String getFødselsnummer();
    public String getNavn();
    public String getFornavn();
    public String getEtternavn();
    public String getAdresse();
    public String getMail();
    public String getTelefonnummer();

    /**
     * Set metode for å endre fornavn
     * @param fornavn nytt fornavn til person
     */

    public void setFornavn(String fornavn);
    public void setEtternavn(String etternavn);
    public void setAdresse(String adresse);
    public void setMail(String mail);
    public void setTelefonnummer(String telefonnummer);






}
