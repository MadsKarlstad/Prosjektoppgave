import java.util.Date;

/**
SLANGEDREAM COPYRIGHT
 */

public abstract class Bolig implements Hjem {
    private String adresse;
    private int boareal;
    private int antallRom;
    private int byggeår;
    private String beskrivelse;
    private int pris;
    private String ledigFra;
    private String bolignummer;
    private Utleier eier;

    private boolean røyke;
    private boolean husdyr;
    private boolean balkong;
    private boolean terasse;

    private boolean tvInkludert;
    private boolean internettInkludert;
    private boolean strømInkludert;
    private boolean parkering;

    private boolean erØnsket;
    private boolean erUtleid;

    private Personregister register;


    public Bolig(String adresse, int boareal, int antallRom, int byggeår, String beskrivelse, int pris, String ledigFra, String bolignummer, Utleier eier, boolean røyke, boolean husdyr, boolean balkong, boolean terasse, boolean tvInkludert, boolean internettInkludert, boolean strømInkludert, boolean parkering) {
        this.adresse = adresse;
        this.boareal = boareal;
        this.antallRom = antallRom;
        this.byggeår = byggeår;
        this.beskrivelse = beskrivelse;
        this.pris = pris;
        this.ledigFra = ledigFra;
        this.bolignummer = bolignummer;
        this.eier = eier;
        this.røyke = røyke;
        this.husdyr = husdyr;
        this.balkong = balkong;
        this.terasse = terasse;
        this.tvInkludert = tvInkludert;
        this.internettInkludert = internettInkludert;
        this.strømInkludert = strømInkludert;
        this.parkering = parkering;
    }

    @Override
    public String getAdresse() {
        return adresse;
    }

    @Override
    public String getBeskrivelse() {
        return beskrivelse;
    }

    @Override
    public int getBoareal() {
        return boareal;
    }

    @Override
    public int getAntallRom() {
        return antallRom;
    }

    @Override
    public int getByggår() {

        return byggeår;
    }

    @Override
    public String getBolignr() {
        return bolignummer;
    }

    @Override
    public int getPris() {
        return pris;
    }

    @Override
    public String getLedigDato() {
        return ledigFra;
    }
    @Override
    public Utleier getEier(){
        return eier;
    }

    public String getEiersNavn(){
        return eier.getNavn();
    }
    public void setEier(Utleier eier){
        this.eier = eier;
    }

    @Override
    public boolean røyke() {
        return røyke;
    }
    public String røyketekst(){
        String s = "";
        if(røyke==true){
            s = "Ja";
        }
        else
            s="Nei";
        return s;
    }

    @Override
    public boolean husdyr() {
        return husdyr;
    }

    @Override
    public boolean balkong() {
        return balkong;
    }

    @Override
    public boolean terasse() {
        return terasse;
    }

    @Override
    public boolean tvInkludert() {
        return tvInkludert;
    }

    @Override
    public boolean internettInkludert() {
        return internettInkludert;
    }

    @Override
    public boolean strømInkludert() {
        return strømInkludert;
    }
    @Override
    public boolean parkering(){
        return parkering;
    }

    public void setErØnsket(boolean b){

        erØnsket = new Boolean(b);

    }

    public void setErUtleid(boolean b){

        erUtleid = new Boolean(b);
    }
    public boolean isErØnsket(){return erØnsket;}

    public boolean isErUtleid(){return erUtleid;}

}
