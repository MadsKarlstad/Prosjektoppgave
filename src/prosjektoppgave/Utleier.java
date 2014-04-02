
package prosjektoppgave;

/**
 *
 * @author madsmkarlstad
 */
public class Utleier extends Person
{
    private String firma;
    private Boligregister bregister;
    
    public Utleier(String pnr, String n, String adr, String mail, String tlf, String firm)
    {
        super(pnr,n,adr,mail,tlf);
        firma = firm;
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

}