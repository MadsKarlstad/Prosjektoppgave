/**
 SLANGEDREAM COPYRIGHT
 */
public class Utleier extends Person {
    private String firma;
    private Boligregister boligregister;

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

    public Boligregister getBoligregister() {
        return boligregister;
    }

}
