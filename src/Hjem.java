/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.io.Serializable;
import java.util.Date;

/*
 * Interface Hjem, brukes av Bolig, som igjen arves av Enebolig og Leilighet
 * Skrevet av Mads Karlstad og Erlend Westbye. Sist oppdatert 10.04.14
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
