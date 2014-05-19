import java.util.Map;
import java.util.TreeMap;

/*
 * Register over registrerte Eneboliger i systemet.
 * Skrevet av Mads Karlstad. Sist oppdatert 15.04.14
 */
public class Boligregister extends TreeMap<String, Enebolig> implements Register{

    //Metode for å legge inn en enebolig. Sjekker om det allerede finnes en enebolig med denne Key-verdien
    @Override
    public boolean leggTil(Object object) {
        if(object instanceof Enebolig){
            if(!finnes(((Enebolig) object).getBolignr())){

                put(((Enebolig) object).getBolignr(),(Enebolig) object);
                return true;

            }
        }
        return false;
    }

    //Metode for å sjekke om en enebolig allerede er registrert med denne Key-verdien
    @Override
    public boolean finnes(String nr) {
        return containsKey(nr);
    }
    //Metode for å slette en enebolig fra registeret
    @Override
    public boolean fjern(String nr) {
        if(finnes(nr)){
            remove(nr);
            return true;
        }
        return false;
    }
    //Metode som returnerer objektet
    @Override
    public Object getObject(String nr) {
        if(finnes(nr)){
            return get(nr);
        }
        return null;
    }
    //Metode som returnerer dette registeret
    @Override
    public Map getMap() {
        return this;
    }
}
