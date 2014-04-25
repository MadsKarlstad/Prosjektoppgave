import java.io.Serializable;
import java.util.Date;

/**
 SLANGEDREAM COPYRIGHT
 */
public interface Hjem extends Serializable {


    public String getAdresse();

    public String getBeskrivelse();

    public int getBoareal();

    public int getAntallRom();

    public int getByggår();

    public String getBolignr();

    public int getPris();


    public String getLedigDato();

    public Person getEier();

    public void setEier(Utleier eier);

    public boolean røyke();
    public boolean husdyr();
    public boolean balkong();
    public boolean terasse();
    public boolean tvInkludert();
    public boolean internettInkludert();
    public boolean strømInkludert();

    public boolean parkering();


}
