import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Erlend on 22/04/14.
 */
public class BoligBrowseSokerPANEL extends JPanel implements ActionListener{

    private double sum;

    private JButton neste;
    private JButton tilbake;
    private JButton avbryt;

    private JPanel knappepanel;

    private Sokerregister sregister;
    private Boligregister bregister;
    private Leilighetregister legister;

    private String pnr;

    private BoligBrowsePromptPANEL parent;
    private MainFrame grandfather;

    public BoligBrowseSokerPANEL(Sokerregister sregister, Boligregister bregister, Leilighetregister legister, BoligBrowsePromptPANEL parent, MainFrame grandfather) {
        this.sregister = sregister;
        this.bregister = bregister;
        this.legister = legister;
        this.parent = parent;
        this.grandfather = grandfather;

        initialiser();
        lagGui();
    }

    public void initialiser(){
        setLayout(new BorderLayout());

        knappepanel = new JPanel(new GridLayout(1,3,1,1));

        neste = new JButton("neste");
        tilbake = new JButton("tilbake");
        avbryt = new JButton("forrige");

        //pnr = parent.getFø
        matchProsent();
        lagGui();

        neste.addActionListener(this);
        tilbake.addActionListener(this);
        avbryt.addActionListener(this);
    }

    public void lagGui(){

        knappepanel.add(avbryt);
        knappepanel.add(neste);
        knappepanel.add(tilbake);

        add(knappepanel, BorderLayout.CENTER);
    }

    public void matchProsent(){

        System.out.println("matchprosent kalt på");
        /*if(sregister.finnes(pnr)){
            System.out.println("funnet");
        }*/
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tilbake){

            grandfather.visPanel("VIS BOLIGBROWSEPROMPT");
        }
    }
}
