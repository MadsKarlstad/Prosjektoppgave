import java.util.Date;

/**
 SLANGEDREAM COPYRIGHT
 */
public class Leilighet extends Bolig {
    private int antallBoder;
    private int etasje;
    private boolean heis;

    public Leilighet(String adresse, int boareal, int antallRom, int byggeår, String beskrivelse, int pris, String ledigFra, String bolignummer, Utleier eier, boolean røyke, boolean husdyr, boolean balkong, boolean terasse, boolean tvInkludert, boolean internettInkludert, boolean strømInkludert, boolean parkering, int antallBoder, int etasje, boolean heis) {
        super(adresse, boareal, antallRom, byggeår, beskrivelse, pris, ledigFra, bolignummer, eier, røyke, husdyr, balkong, terasse, tvInkludert, internettInkludert, strømInkludert, parkering);
        this.antallBoder = antallBoder;
        this.etasje = etasje;
        this.heis = heis;
    }

    public int getAntallBoder() {
        return antallBoder;
    }

    public int getEtasje() {
        return etasje;
    }

    public boolean isHeis() {
        return heis;
    }
}
