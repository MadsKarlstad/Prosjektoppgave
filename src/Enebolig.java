import java.util.Date;

/**
 SLANGEDREAM COPYRIGHT
 */
public class Enebolig extends Bolig {
    private int antallEtasjer;
    private boolean kjeller;
    private double tomtareal;
    private int antallBad;

    String bildesti;

    private double prosent;


    public Enebolig(String bildesti, String adresse, int boareal, int antallRom, int byggeår, String beskrivelse, int pris, String ledigFra, String bolignummer, Utleier eier, boolean røyke, boolean husdyr, boolean balkong, boolean terasse, boolean tvInkludert, boolean internettInkludert, boolean strømInkludert, boolean parkering, int antallEtasjer, boolean kjeller, double tomtareal, int antallBad) {
        super(bildesti,adresse, boareal, antallRom, byggeår, beskrivelse, pris, ledigFra, bolignummer, eier, røyke, husdyr, balkong, terasse, tvInkludert, internettInkludert, strømInkludert, parkering);
        this.antallEtasjer = antallEtasjer;
        this.kjeller = kjeller;
        this.tomtareal = tomtareal;
        this.antallBad = antallBad;

        setBlidesti(super.getBildesti());
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
}
