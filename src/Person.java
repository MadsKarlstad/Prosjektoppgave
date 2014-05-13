/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.io.Serializable;
import java.util.TreeMap;

public abstract class Person implements Menneske {
    private final String FØDSELSNUMMER;
    private String fornavn;
    private String etternavn;
    private String adresse;
    private String mail;
    private String telefonnummer;


    /**
     * konstruktør for abstract superklasse Person. Konstruktøren initialiserer datafeltene i personklassen
     * @param FØDSELSNUMMER final String for fødselsnummer, brukes til å sette fødselsnummer til person, settes kun en gang
     * @param fornavn personens fornavn
     * @param etternavn personens etternavn
     * @param adresse personens adresse
     * @param mail personens email
     * @param telefonnummer personens telefonnummer
     */

    protected Person(String FØDSELSNUMMER, String fornavn, String etternavn, String adresse, String mail, String telefonnummer) {
        this.FØDSELSNUMMER = FØDSELSNUMMER;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.adresse = adresse;
        this.mail = mail;
        this.telefonnummer = telefonnummer;

    }



    @Override
    public String getFødselsnummer() {
        return FØDSELSNUMMER;
    }

    @Override
    public String getNavn() {
        return fornavn + " " + etternavn;
    }

    @Override
    public String getFornavn() {
        return fornavn;
    }

    @Override
    public String getEtternavn() {
        return etternavn;
    }

    @Override
    public String getAdresse() {
        return adresse;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public String getTelefonnummer() {
        return telefonnummer;
    }

    @Override
    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    @Override
    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    @Override
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }


}
