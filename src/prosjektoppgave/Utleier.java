
package prosjektoppgave;

/**
 *
 * @author madsmkarlstad
 */
public class Utleier extends Person
{
    private String firma;
    private Boligregister boligliste;
    
    public Utleier(String pnr, String n, String adr, String mail, String tlf, String firm)
    {
        super(pnr,n,adr,mail,tlf);
        firma = firm;
    }

}