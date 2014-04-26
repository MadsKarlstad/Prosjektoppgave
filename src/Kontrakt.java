import java.io.Serializable;
import java.util.Date;

/**
 SLANGEDREAM COPYRIGHT
 */
public class Kontrakt implements Serializable {
    /*utleieboligen
utleier
leietager
leiepris pr måned
leieavtalens varighet (tidrom)*/
    private String kontraktnr;
    private Bolig bolig;
    private Utleier utleier;
    private Soker leietager;
    private int pris;
    private String fra;
    private String til;


    public Kontrakt(String kontraktnr,Bolig bolig, Utleier utleier, Soker leietager, int pris, String fra, String til) {
        this.kontraktnr = kontraktnr;
        this.bolig = bolig;
        this.utleier = utleier;
        this.leietager = leietager;
        this.pris = pris;
        this.fra = fra;
        this.til = til;

    }

    public String getKontraktnr(){
        return kontraktnr;
    }

    public Bolig getBolig() {
        return bolig;
    }

    public String getBolignr(){
        return bolig.getBolignr();
    }

    public Utleier getUtleier() {
        return utleier;
    }

    public String getUtleiernavn(){
        return utleier.getNavn();
    }

    public Soker getLeietager() {
        return leietager;
    }

    public String getLeietagernavn(){
        return leietager.getNavn();
    }

    public int getPris() {
        return pris;
    }

    public String getFra() {
        return fra;
    }

    public String getTil() {
        return til;
    }

    @Override
    public String toString() {
        return "Kontrakt for leie av bolig med bolignummer: " +
                bolig.getBolignr() +
                "\n utleier: " + utleier.getNavn() +
                "\n leietager:" + leietager.getNavn() +
                "\n pris:" + pris +
                "\n fra:" + fra +
                "\n til" + til;
    }
}
