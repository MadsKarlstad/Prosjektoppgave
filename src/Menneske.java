import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.TreeMap;

/**
 SLANGEDREAM COPYRIGHT
 */
public interface Menneske extends Serializable {
    public String getFødselsnummer();
    public String getNavn();
    public String getFornavn();
    public String getEtternavn();
    public String getAdresse();
    public String getMail();
    public String getTelefonnummer();

    public void setFornavn(String fornavn);
    public void setEtternavn(String etternavn);
    public void setAdresse(String adresse);
    public void setMail(String mail);
    public void setTelefonnummer(String telefonnummer);






}
