import java.util.LinkedList;

/**
 SLANGEDREAM COPYRIGHT
 */
public class Utleier extends Person {
    private String firma;
    private Boligregister boligregister;

    private LinkedList<Enebolig> eneboligliste;
    private LinkedList<Leilighet> leilighetliste;

    public Utleier(String FØDSELSNUMMER, String fornavn, String etternavn, String adresse, String mail, String telefonnummer, String firma) {
        super(FØDSELSNUMMER, fornavn, etternavn, adresse, mail, telefonnummer);
        this.firma = firma;
        boligregister = new Boligregister();
    }

    public String getFirma() {
        return firma;
    }
    public String getNavn(){
        String navn = getFornavn() + " " + getEtternavn();
        return navn;
    }

    public void leggInnBolig(Enebolig enebolig){
        boligregister.put(enebolig.getBolignr(),enebolig);
    }

    public Boligregister getBoligregister() {
        return boligregister;
    }

}
