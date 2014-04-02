package prosjektoppgave;

import java.util.*;

/**
 * Created by Erlend on 31/03/14.
 *
 * Et treemap på lik linje med personregister osv, til å lagre de div kontraktene knyttet til leietakere/utleire.
 * Vi stiller krav om mulighetene for å slette/legge til kontrakter. Feks kan ikke en kontrakt slettes uten
 * at leieavtale er opphørt, man kan ikke slette en bruker før kontrakter er oppløst osv. Alle kontrakter må være
 * knyttet til en utleier/leietaker
 */
public class KontraktRegister {

    private TreeMap kontraktReg = new TreeMap();

    public boolean kontrakFinnes(String nr){

        return kontraktReg.containsKey(nr);


    }

    public boolean settInn(Kontrakt ny){

        if(!kontrakFinnes(ny.getKontraktNr())){

            kontraktReg.put(ny.getKontraktNr()/*KEY*/, ny);

            return true;


        }

        else return false;
    }


    public Kontrakt getKontrakt(String nr){

        if(kontrakFinnes(nr)){

            return (Kontrakt) kontraktReg.get(nr);


        }

        return null;
    }


    //key er kontraktnummer

    //Første klasse bør undersøke om personen finnes så vi kjører samme test som i PersReg







}
