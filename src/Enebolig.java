import java.util.Date;
import java.util.LinkedList;

/**
 SLANGEDREAM COPYRIGHT
 */
public class Enebolig extends Bolig {
    private int antallEtasjer;
    private boolean kjeller;
    private double tomtareal;
    private int antallBad;
    private Soker soker;
    private Utleier eier;

    private LinkedList<Soker> sokerliste;

    String bildesti;

    private double prosent;

    private boolean ønsket;
    private boolean utleid;


    public Enebolig(String bildesti, String adresse, int boareal, int antallRom, int byggeår, String beskrivelse, int pris, String ledigFra, String bolignummer, Utleier eier, boolean røyke, boolean husdyr, boolean balkong, boolean terasse, boolean tvInkludert, boolean internettInkludert, boolean strømInkludert, boolean parkering, int antallEtasjer, boolean kjeller, double tomtareal, int antallBad, boolean ønsket, boolean utleid) {
        super(bildesti,adresse, boareal, antallRom, byggeår, beskrivelse, pris, ledigFra, bolignummer, eier, røyke, husdyr, balkong, terasse, tvInkludert, internettInkludert, strømInkludert, parkering, ønsket, utleid);
        this.antallEtasjer = antallEtasjer;
        this.kjeller = kjeller;
        this.tomtareal = tomtareal;
        this.antallBad = antallBad;

        sokerliste = new LinkedList<Soker>();

        setBlidesti(super.getBildesti());
        setEier(eier);
    }

    public void setEier(Utleier utleier){
        eier = utleier;
    }

    public Utleier getEier(){
        return eier;
    }

    public int getAntallEtasjer() {
        return antallEtasjer;
    }

    public boolean isKjeller() {
        return kjeller;
    }

    public double getTomtareal() {
        return tomtareal;
    }

    public int getAntallBad() {
        return antallBad;
    }

    public void setBlidesti(String s){

        bildesti = s;

    }


    public String getBildesti(){

        return bildesti;

    }

    @Override
    public String toString() {

        return "Dette er en " + getBeskrivelse() + " på " + getBoareal() + " kvadratmeter, den ligger på adressen " + getAdresse();
    }

    public void setProsent(double p){

        prosent = p;


    }

    public double getProsent(){

        return  prosent;
    }

    public void setØnsket(boolean b){

        ønsket = b;

    }

    public boolean erØnsket(){

        return  ønsket;
    }

    public void setUtleid(boolean b){

        utleid = b;

    }

    public boolean erUtleid(){

        return  utleid;
    }

    public Enebolig getEnebolig(){

        return this;
    }

    public void addSoker(Soker s){

        sokerliste.add(s);

    }

    public LinkedList getSokere(){
        return sokerliste;
    }



}
