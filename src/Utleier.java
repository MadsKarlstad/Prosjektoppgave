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

        eneboligliste = new LinkedList<Enebolig>();
        leilighetliste = new LinkedList<Leilighet>();

    }

    public String getFirma() {
        return firma;
    }
    public String getNavn(){
        String navn = getFornavn() + " " + getEtternavn();
        return navn;
    }


    public void leggInnØnsketEnebolig(Enebolig enebolig){


        if(enebolig.erØnsket()){

            eneboligliste.add(enebolig);


        }

    }

    public void leggInnØnsketLeilighet(Leilighet leilighet){

        if(leilighet.erØnsket()){

            leilighetliste.add(leilighet);

        }

    }

    public LinkedList getØnskedeEneboliger(){

        return eneboligliste;
    }

    public LinkedList getØnskedeLeiligheter(){

        return leilighetliste;
    }

    public Boligregister getBoligregister() {
        return boligregister;
    }

}
