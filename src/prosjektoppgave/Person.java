package prosjektoppgave;
/**
 *
 * @author madsmkarlstad
 */
public class Person 
{
    private String navn;
    private String adresse;
    private String email;
    private String tlfnummer;
    private String personnummer;
    private Personregister pregister;
    private Boligregister bregister;
    Person neste;

    /* For å slippe to lister for leietaker og utleier kan vi vurdere en boolean variable for å teste
    * utleier true/false*/
    
    public Person(String pnr,String n,String adr,String mail,String tlf)
    {
        personnummer = pnr;
        navn = n;
        adresse = adr;
        email = mail;
        tlfnummer = tlf;
    }
    
    public String getNavn()
    {
        return navn;
    }
    
    public String getNr()
    {
        return personnummer;
    }

    public String getAdresse(){
        return adresse;
    }

    public String getEmail(){
        return email;
    }

    public String getTlfnummer(){
        return tlfnummer;
    }
    
    public void settInnBolig(Bolig ny)
    {
        try
        {
            bregister.settInn(ny);
        }
        catch(NullPointerException NPE)
        {
            bregister = new Boligregister();
            bregister.settInn(ny);
        }
    }
    
    public Bolig finnBolig(String bolignr)
    {
        if(bregister==null)
            return null;
        else
            return bregister.getBolig(bolignr);
    }
    public String toString()
    {
        String s = "Personnummer: " + personnummer;
        s+= "\nNavn: " + navn;
        s+="\nAdresse: " + adresse;
        s+="\nEmail: " + email;
        s+="\nTelefonnummer: " + tlfnummer;
        s+="\n";
        if(bregister==null)
        {
            s += "\n\nPersonen har ingen boliger registrert.";
            return s;
        }
        else{
            
            s += "Boliger registrert: " + bregister.skrivListe();
        }
        return s;     
    }
}