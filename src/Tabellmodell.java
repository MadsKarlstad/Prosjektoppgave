/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
/**
 * Tabellmodell, super, definerer metoder som er felles for undermodellene for utleiere,søkere,eneboliger,leiligheter,kontrakter
 * Skrevet av Mads Karlstad. Sist oppdatert 02.05.14
 */
public abstract class Tabellmodell<T> extends AbstractTableModel {

    private String[] kolonnenavn;
    private LinkedList<T> data;

    public Tabellmodell(String[] kolonnenavn, LinkedList<T> data) {
        this.kolonnenavn = kolonnenavn;
        this.data = data;
    }
    //Set- og Get-metoder for tabellene
    public String getColumnName(int kol) {
        return kolonnenavn[kol];
    }

    public int getRowCount() {
        return data.size();
    }

    public T getRow(int rad) {
        return data.get(rad);
    }

    public int getColumnCount() {
        return kolonnenavn.length;
    }

    public LinkedList<T> getData() {
        return data;
    }

    @Override
    public abstract Object getValueAt(int rad, int kol);

    /**
     * Setter inn et objekt i en rad i tabellen og oppdaterer modellen.
     *
     * @param obj Objektetsom skal settes inn.
     */
    public void addRow(T obj) {
        data.add(obj);
        this.fireTableDataChanged();
    }

    /**
     * Fjerner en rad fra tabellen og oppdaterer modellen.
     *
     * @param rad Indeksen til den raden som skal fjernes.
     */
    public void delRow(int rad) {
        data.remove(rad);
        this.fireTableDataChanged();
    }

    /**
     * Sletter all data og oppdaterer modellen.
     */
    public void delTabledata() {
        data.clear();
        this.fireTableDataChanged();
    }

    /**
     * Sletter all data, setter inn ny og oppdaterer modellen.
     *
     * @param d Den nye listen med data.
     */
    public void setTabledata(LinkedList<T> d) {
        data.clear();
        data.addAll(d);
        this.fireTableDataChanged();
    }

}//tabellModell klasse feridg

//Modell for utleiere. Arver fra Tabellmodell
class Utleiermodell extends Tabellmodell<Utleier> {

    private final int FØDSELSNUMMER = 0;
    private final int FORNAVN = 1;
    private final int ETTERNAVN = 2;
    private final int ADRESSE = 3;
    private final int MAIL = 4;
    private final int TELEFONUMMER = 5;
    private final int FIRMA = 6;

    public Utleiermodell(String[] kolonnenavn, LinkedList<Utleier> data) {
        super(kolonnenavn, data);
    }
    //Setter inn informasjon om den gitte utleieren til sin respektive kollonne
    public Object getValueAt(int rad, int kol) {
        Utleier utleier = (Utleier) super.getData().get(rad);

        switch (kol) {
            case FØDSELSNUMMER:
                return utleier.getFødselsnummer();
            case FORNAVN:
                return utleier.getFornavn();
            case ETTERNAVN:
                return utleier.getEtternavn();
            case ADRESSE:
                return utleier.getAdresse();
            case MAIL:
                return utleier.getMail();
            case TELEFONUMMER:
                return utleier.getTelefonnummer();
            case FIRMA:
                return utleier.getFirma();
            default:
                return null;
        }

    }
    //Metode som returnerer valgt utleier i tabellen
    public Utleier getValueAt(int rad) {
        return (Utleier) super.getData().get(rad);

    }
}
class Sokermodell extends Tabellmodell<Soker> { //Tabellmodell for søker

    private final int FØDSELSNUMMER = 0;
    private final int FORNAVN = 1;
    private final int ETTERNAVN = 2;
    private final int ADRESSE = 3;
    private final int MAIL = 4;
    private final int TELEFONUMMER = 5;
    private final int RØYKER = 6;
    private final int HUSDYR = 7;
    private final int MIN_PRIS = 8;
    private final int MAKS_PRIS = 9;
    //private final int FIRMA =   sa

    public Sokermodell(String[] kolonnenavn, LinkedList<Soker> data) {
        super(kolonnenavn, data);
    }
    //Setter inn informasjon om den gitte søkeren til sin respektive kollonne
    public Object getValueAt(int rad, int kol) {
        Soker søker = (Soker) super.getData().get(rad);

        switch (kol) {
            case FØDSELSNUMMER:
                return søker.getFødselsnummer();
            case FORNAVN:
                return søker.getFornavn();
            case ETTERNAVN:
                return søker.getEtternavn();
            case ADRESSE:
                return søker.getAdresse();
            case MAIL:
                return søker.getMail();
            case TELEFONUMMER:
                return søker.getTelefonnummer();
            case RØYKER:
                String s = "";
                if(søker.isRøyk()==true){
                    s = "Ja";
                    return s;
                }
                else{
                    s="Nei";
                    return s;
                }

            case HUSDYR:
                String h = "";
                if(søker.isHusdyr()==true){
                    h = "Ja";
                    return h;
                }
                else{
                    h="Nei";
                    return h;
                }
            case MIN_PRIS:
                return søker.getPris();
            default:
                return null;
        }

    }
    //Returnerer et søkerobjekt fra valgt rad i tabellen
    public Soker getValueAt(int rad) {
        return (Soker) super.getData().get(rad);
    }
}
class Eneboligmodell extends Tabellmodell<Enebolig> { //Tabellmodell for enebolig

    private final int ADRESSE = 0;
    private final int BOAREAL = 1;
    private final int ANTROM = 2;
    private final int BYGGÅR = 3;
    private final int BESKRIVELSE = 4;
    private final int PRIS = 5;
    private final int LEDIGFRA = 6;
    private final int BOLIGNR = 7;
    private final int RØYKER = 8;
    private final int EIER = 9;
    private final int LEDIG = 10;
    private final int BYDEL = 11;
    private final int HUSDYR = 12;
    private final int TV = 13;
    private final int STRØM = 14;

    public Eneboligmodell(String[] kolonnenavn, LinkedList<Enebolig> data) {
        super(kolonnenavn, data);
    }
    //Setter inn informasjon om den gitte eneboligen til sin respektive kollonne
    public Object getValueAt(int rad, int kol) {
        Enebolig enebolig = (Enebolig) super.getData().get(rad);

        switch (kol) {
            case ADRESSE:
                return enebolig.getAdresse();
            case BOLIGNR:
                return enebolig.getBolignr();
            case EIER:
                return enebolig.getEiersNavn();
            case BOAREAL:
                return enebolig.getBoareal();
            case ANTROM:
                return enebolig.getAntallRom();
            case BYGGÅR:
                return enebolig.getByggår();
            case BESKRIVELSE:
                return enebolig.getBeskrivelse();
            case PRIS:
                return enebolig.getPris();
            case LEDIGFRA:
                return enebolig.getLedigDato();
            case RØYKER:
                return enebolig.røyketekst();
            case LEDIG:
                return enebolig.utleidTekst();
            case BYDEL:
                return enebolig.getBydel();
            case HUSDYR:
                return enebolig.husdyrTekst();
            case TV:
                return enebolig.tvTekst();
            case STRØM:
                return enebolig.strømTekst();
            default:
                return null;
        }

    }
    //returnerer en enebolig fra tabellen
    public Enebolig getValueAt(int rad) {
        return (Enebolig) super.getData().get(rad);
    }
}

class Leilighetmodell extends Tabellmodell<Leilighet> { //Tabellmodell for enebolig

    private final int ADRESSE = 0;
    private final int BOAREAL = 1;
    private final int ANTROM = 2;
    private final int BYGGÅR = 3;
    private final int BESKRIVELSE = 4;
    private final int PRIS = 5;
    private final int LEDIGFRA = 6;
    private final int BOLIGNR = 7;
    private final int EIER = 8;
    private final int UTLEID = 9;
    private final int BYDEL = 10;

    public Leilighetmodell(String[] kolonnenavn, LinkedList<Leilighet> data) {
        super(kolonnenavn, data);
    }
    //Setter inn informasjon om den gitte leiligheten til sin respektive kollonne
    public Object getValueAt(int rad, int kol) {
        Leilighet leilighet = (Leilighet) super.getData().get(rad);

        switch (kol) {
            case ADRESSE:
                return leilighet.getAdresse();
            case BOLIGNR:
                return leilighet.getBolignr();
            case EIER:
                return leilighet.getEiersNavn();
            case BOAREAL:
                return leilighet.getBoareal();
            case ANTROM:
                return leilighet.getAntallRom();
            case BYGGÅR:
                return leilighet.getByggår();
            case BESKRIVELSE:
                return leilighet.getBeskrivelse();
            case PRIS:
                return leilighet.getPris();
            case LEDIGFRA:
                return leilighet.getLedigDato();
            case UTLEID:
                return leilighet.getLedigTekst();
            case BYDEL:
                return leilighet.getBydel();
            default:
                return null;
        }
    }
    //returnerer en valgt leilighet fra tabellen
    public Leilighet getValueAt(int rad) {
        return (Leilighet) super.getData().get(rad);
    }
}

class Kontraktmodell extends Tabellmodell<Kontrakt> {

    private final int KONTRAKTNR = 0;
    private final int EIER = 1;
    private final int LEIER = 2;
    private final int BOLIG = 3;
    private final int PRIS = 4;
    private final int FRA = 5;
    private final int TIL = 6;
    private final int AKTIV = 7;

    public Kontraktmodell(String[] kolonnenavn, LinkedList<Kontrakt> data) {
        super(kolonnenavn, data);
    }
    //Setter inn informasjon om den gitte kontrakten til sin respektive kollonne
    public Object getValueAt(int rad, int kol) {
        Kontrakt kontrakt = (Kontrakt) super.getData().get(rad);

        switch (kol) {
            case BOLIG:
                return kontrakt.getBolignr();
            case EIER:
                return kontrakt.getUtleiernavn();
            case LEIER:
                return kontrakt.getLeietagernavn();
            case KONTRAKTNR:
                return kontrakt.getKontraktnr();
            case PRIS:
                return kontrakt.getPris();
            case FRA:
                return kontrakt.getFra();
            case TIL:
                return kontrakt.getTil();
            case AKTIV:
                return kontrakt.getAktivTekst();
            default:
                return null;
        }

    }
    //returnerer en kontrakt fra valgt objekt i tabellem
    public Kontrakt getValueAt(int rad) {
        return (Kontrakt) super.getData().get(rad);

    }
}