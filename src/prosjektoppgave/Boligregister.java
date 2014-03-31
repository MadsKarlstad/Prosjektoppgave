package prosjektoppgave;
import java.util.*;
/**
 *
 * @author madsmkarlstad
 */
public class Boligregister 
{
    private TreeMap boligregister = new TreeMap();
    
    public boolean boligFinnes(String nr)
    {
        return boligregister.containsKey(nr);
    }
    public boolean settInn(Bolig ny)
    {
        if(!boligFinnes(ny.getNr()))
        {
            boligregister.put(ny.getNr(), ny);
            return true;
        }
        return false;
    }
    public Bolig getBolig(String nr)
    {
        if(boligFinnes(nr))
        {
            return (Bolig) boligregister.get(nr);
        }
        return null;
    }
    public boolean fjernBolig(String nr)
    {
        if(boligFinnes(nr))
        {
            boligregister.remove(nr);
            return true;
        }
        return false;
    }
    public String skrivListe()
    {
        String s = "";
        for(Iterator it = boligregister.keySet().iterator(); it.hasNext();)
        {
            String key = (String) it.next();
            Person value = (Person) boligregister.get(key);
            s += value;
            return s;
        }
        s = "Kom du hit, fungerte nok ikke metoden din, Mads";
        return s;
    }
}