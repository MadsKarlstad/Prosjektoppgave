import java.io.Serializable;
import java.util.Map;

/**
 SLANGEDREAM COPYRIGHT
 */
public interface Register extends Serializable{

    public boolean leggTil(Object object);
    public boolean finnes(String nr);
    public boolean fjern(String nr);

    public Object getObject(String nr);

    public Map getMap();




}
