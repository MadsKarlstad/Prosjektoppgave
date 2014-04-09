package prosjektoppgave;

/**
 * 
 * @author madsmkarlstad
 */
public class Boligsøker extends Person 
{
    private boolean røyker;
    private boolean husdyr;
    private int antallPersoner;
    private String sivilstatus;
    private String yrke;
    private String arbeidsforhold;
    private String boligtype; //Chris skal mekke dropdown på dette datafeltet
    private String areal;
    private String antallRom;
    private boolean balkong;
    private boolean hage;
    private String beliggenhet; //Chris skal mekke dropdown på dette datafeltet
    private boolean heis;
    private boolean parkering;
    private int utleiepris;
    
    public Boligsøker(String pnr,String n, String adr, String mail, String tlf, boolean røyk,
                      boolean dyr, int antPers, String status, String y, String a, String bType,
                      String m2, String rom, boolean balk, boolean h, String location,
                      boolean elevator, boolean p, int pris)
    {
        super(pnr,n,adr,mail,tlf);
        røyker = røyk;
        husdyr = dyr;
        antallPersoner = antPers;
        sivilstatus = status;
        yrke = y;
        arbeidsforhold = a;
        boligtype = bType;
        areal = m2;
        antallRom = rom;
        balkong = balk;
        hage = h;
        beliggenhet = location;
        heis = elevator;
        parkering = p;
        utleiepris = pris;
    }
}