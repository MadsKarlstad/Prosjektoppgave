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
    public String finnBolig(String nr){
        if(boligFinnes(nr)){
            return (String) boligregister.get(nr).toString();
        }
        return "FINNES IKKE";
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
        
        String s = "" + boligregister;
        return s;
    }
}