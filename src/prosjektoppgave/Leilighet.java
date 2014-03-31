package prosjektoppgave;

/**
 *
 * @author madsmkarlstad
 */
public class Leilighet extends Bolig
{
    private String etasje;
    private String heis;
    private String balkong;
    
    public Leilighet(String nr, String adr, String m2, String rom,
            String år, String beskriv, String pris, String d, 
            String etg, String elevator, String balk)
    {
        super(nr,adr,m2,rom,år,beskriv,pris,d);
        etasje = etg;
        heis = elevator;
        balkong = balk;
    }

}
