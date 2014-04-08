package prosjektoppgave;

/**
 *
 * @author madsmkarlstad
 */
public class Enebolig extends Bolig
{
    private String antallEtasjer;
    private boolean kjeller;
    private String tomt;
    
    public Enebolig(String nr, String adr, String m2, String rom,
            String år, String beskriv, String pris, String d, 
            String etg, boolean k, String tm2)
    {
        super(nr,adr,m2,rom,år,beskriv,pris,d);
        antallEtasjer = etg;
        kjeller = k;
        tomt = tm2;
    }

}