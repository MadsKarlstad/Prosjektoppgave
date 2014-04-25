/**
 SLANGEDREAM COPYRIGHT
 */
public class Soker extends Person{
    private String antallPersoner;
    private String sivilstatus;
    private String yrke;
    private String arbeidsfohold_studiested;

    private int minAreal;
    private int maksAreal;

    private int minPris;
    private int maksPris;

    private boolean røyk;
    private boolean husdyr;
    private boolean balkong;
    private boolean terasse;
    private boolean TVinkludert;
    private boolean Internetinkludert;
    private boolean strøminkludert;
    private boolean parkering;
    private boolean kjeller;
    private boolean heis;

    public Soker(String FØDSELSNUMMER, String fornavn, String etternavn, String adresse, String mail, String telefonnummer,
                 String antallPersoner, String sivilstatus, String yrke, String arbeidsfohold_studiested, int minAreal,
                 int maksAreal, int minPris, int maksPris,boolean røyk,boolean husdyr,boolean balkong,boolean terasse,
                 boolean TVinkludert,boolean Internetinkludert,boolean strøminkludert,boolean parkering,boolean kjeller,boolean heis) {
        super(FØDSELSNUMMER, fornavn, etternavn, adresse, mail, telefonnummer);
        this.antallPersoner = antallPersoner;
        this.sivilstatus = sivilstatus;
        this.yrke = yrke;
        this.arbeidsfohold_studiested = arbeidsfohold_studiested;
        this.minAreal = minAreal;
        this.maksAreal = maksAreal;
        this.minPris = minPris;
        this.maksPris = maksPris;
        this.røyk = røyk;
        this.husdyr = husdyr;
        this.balkong = balkong;
        this.terasse= terasse;
        this.TVinkludert = TVinkludert;
        this.Internetinkludert = Internetinkludert;
        this.strøminkludert= strøminkludert;
        this.parkering = parkering;
        this.kjeller = kjeller;
        this.heis = heis;
    }

    public String getAntallPersoner() {
        return antallPersoner;
    }

    public String getSivilstatus() {
        return sivilstatus;
    }

    public String getYrke() {
        return yrke;
    }

    public String getArbeidsfohold_studiested() {
        return arbeidsfohold_studiested;
    }

    /*public String getBeliggenhetsønske1() {
        return beliggenhetsønske1;
    }

    public String getBeliggenhetsønske2() {
        return beliggenhetsønske2;
    }

    public String getBeliggenhetsønske3() {
        return beliggenhetsønske3;
    }

    public String getBeliggenhetsønske4() {
        return beliggenhetsønske4;
    }

    public String getBeliggenhetsønske5() {
        return beliggenhetsønske5;
    }

    public String getBeliggenhetsønske6() {
        return beliggenhetsønske6;
    }

    public String getBeliggenhetsønske7() {
        return beliggenhetsønske7;
    }

    public String getBeliggenhetsønske8() {
        return beliggenhetsønske8;
    }

    public String getBeliggenhetsønske9() {
        return beliggenhetsønske9;
    }

    public String getBeliggenhetsønske10() {
        return beliggenhetsønske10;
    }

    public String getBeliggenhetsønske11() {
        return beliggenhetsønske11;
    }

    public String getBeliggenhetsønske12() {
        return beliggenhetsønske12;
    }

    public String getBolygtypeønske1() {
        return bolygtypeønske1;
    }

    public String getBolygtypeønske2() {
        return bolygtypeønske2;
    }*/

    public int getMinAreal() {
        return minAreal;
    }

    public int getMaksAreal() {
        return maksAreal;
    }

    public int getMinPris() {
        return minPris;
    }

    public int getMaksPris() {
        return maksPris;
    }

    public boolean isRøyk() {
        return røyk;
    }

    public boolean isHusdyr() {
        return husdyr;
    }

    public boolean isBalkong() {
        return balkong;
    }

    public boolean isTerasse() {
        return terasse;
    }

    public boolean isTVinkludert() {
        return TVinkludert;
    }

    public boolean isInternetinkludert() {
        return Internetinkludert;
    }

    public boolean isStrøminkludert() {
        return strøminkludert;
    }

    public boolean isParkering() {
        return parkering;
    }

    public boolean isKjeller() {
        return kjeller;
    }

    public boolean isHeis() {
        return heis;
    }

}
