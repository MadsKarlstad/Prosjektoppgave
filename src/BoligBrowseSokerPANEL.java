import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Erlend on 22/04/14.
 */
public class BoligBrowseSokerPANEL extends JPanel implements ActionListener{

    private double sum;

    private Sokerregister sregister;
    private Boligregister bregister;
    private Leilighetregister legister;
    private LinkedList<Bolig> boligliste;
    private Bolig løper;

    private String pnr;

    private BoligBrowsePromptPANEL parent;

    public BoligBrowseSokerPANEL(Sokerregister sregister, Boligregister bregister, Leilighetregister legister, BoligBrowsePromptPANEL parent) {
        this.sregister = sregister;
        this.bregister = bregister;
        this.legister = legister;
        this.parent = parent;

        initialiser();
    }

    public void initialiser(){



        matchProsent();
        System.out.println("Du er nå most def in søkerbrowse");

    }

    public double matchProsent(){
        sum = 0;
        /*Soker soker = sregister.getSoker(parent.getFødselsnummer());
        String navn = soker.getNavn();
        System.out.println(navn);
        if(sregister.finnes(parent.getFødselsnummer())){
            for(Map.Entry<String,Enebolig> entry:bregister.entrySet()){
                løper = entry.getValue();
                if(soker.isRøyk() == løper.røyke()){
                    sum = 1;
                }
            }
            System.out.println(sum);
        }*/
        return sum;

    }






    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
