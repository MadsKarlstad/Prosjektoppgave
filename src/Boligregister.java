import java.util.Map;
import java.util.TreeMap;

/**
 SLANGEDREAM COPYRIGHT
 */
public class Boligregister extends TreeMap<String, Enebolig> implements Register{


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


    @Override
    public boolean finnes(String nr) {
        return containsKey(nr);
    }

    @Override
    public boolean fjern(String nr) {
        if(finnes(nr)){
            remove(nr);
            return true;
        }
        return false;
    }

    @Override
    public Object getObject(String nr) {
        if(finnes(nr)){
            return get(nr);
        }
        return null;
    }

    @Override
    public Map getMap() {
        return this;
    }
}
