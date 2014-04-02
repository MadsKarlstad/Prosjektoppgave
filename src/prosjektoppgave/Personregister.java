package prosjektoppgave;

import java.util.*;
import javax.swing.JOptionPane;
/**
 *
 * @author madsmkarlstad
 */
public class Personregister 
{
    private TreeMap personregister = new TreeMap();
    
    public boolean personFinnes(String nr)
    {
        return personregister.containsKey(nr);
    }


    public boolean settInn(Person ny)
    {
        if(!personFinnes(ny.getNr()))
        {
            personregister.put(ny.getNr(), ny);
            return true;
        }
        return false;
    }

    public Person getPerson(String nr)
    {
        if(personFinnes(nr))
        {
            return (Person) personregister.get(nr); 
        }
        return null;
    }

    public boolean fjernPerson(String nr)
    {
        if(personFinnes(nr))
        {
            personregister.remove(nr);
            return true;
        }
        return false;
    }
    public String skrivListe()
    {
        String s = ""+personregister;
        return s;
    }
}