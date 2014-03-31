package prosjektoppgave;
/**
 *yoyo
 * @author madsmkarlstad hei
 */
public class Bolig 
{
    private String adresse;
    //private String boligtype;
    private String boareal;
    private String antallRom;
    private String byggeår;
    private String beskrivelse;
    private String utleiepris;
    private String dato;
    private String bolignr;
    Bolig neste;
    
    public Bolig(String nr, String adr, String m2, String rom,
                String år, String beskriv, String pris, String d)
    {
        bolignr = nr;
        adresse = adr;
        //boligtype = bt;
        boareal = m2;
        antallRom = rom;
        byggeår = år;
        beskrivelse = beskriv;
        utleiepris = pris;
        dato = d;
    }
    public String getAdr()
    {
        return adresse;
    } 
    public String getNr()
    {
        return bolignr;
    }
    public String toString()
    {
        String s = "Bolignummer " + bolignr;
        s+="\nAdresse: " + adresse;
        s+="\nBoareal: " + boareal;
        s+="\nAntall rom: " + antallRom;
        s+="\nByggeår: " + byggeår;
        s+="\nUtleiepris: " + utleiepris;
        s+="\nUtleiedato: " + dato;
        s+="\nBeskrivelse: " + beskrivelse;
        return s;
    }
}
