import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Erlend on 22/04/14.
 */
public class BoligBrowseSokerPANEL extends JPanel implements ActionListener{

    private double sum;

    private Sokerregister sregister;
    private Boligregister bregister;
    private Leilighetregister legister;

    private String pnr;

    private BoligBrowsePromptPANEL parent;

    public BoligBrowseSokerPANEL(Sokerregister sregister, Boligregister bregister, Leilighetregister legister, BoligBrowsePromptPANEL parent) {
        this.sregister = sregister;
        this.bregister = bregister;
        this.legister = legister;
        this.parent = parent;
    }

    public void initialiser(){

    }

    public double matchProsent(){
        sum = 0;
        if(sregister.finnes(parent.getFødselsnummer())){
            Soker soker = sregister.getSoker(parent.getFødselsnummer());
            
        }



        return sum;
    }






    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
