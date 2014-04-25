import java.util.Iterator;
import java.util.LinkedList;

/**
 SLANGEDREAM COPYRIGHT
 */
public class Kontraktregister extends LinkedList {

    public void registrerKontrakt(Kontrakt kontrakt){
        add(kontrakt);
    }
    public LinkedList getKontrakter(){
        return this;
    }


}
