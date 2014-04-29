import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Erlend on 22/04/14.
 */
public class BoligBrowseSokerPANEL extends JPanel implements ActionListener{

    private double sum;

    private JTextField fødselsnummer;
    private JButton finn;

    private JButton neste;
    private JButton tilbake;
    private JButton avbryt;

    private JPanel knappepanel;
    private JPanel søkepanel;

    private JPanel infopanel_tomt;
    private JPanel infopanel_søker;
    private JPanel infopanel_utleier;

    private Sokerregister sregister;
    private Personregister pregister;
    private Boligregister bregister;
    private Leilighetregister legister;

    private Soker soker;

    private MainFrame parent;

    public BoligBrowseSokerPANEL(Sokerregister sregister, Boligregister bregister, Leilighetregister legister,Personregister pregister, MainFrame parent) {
        this.sregister = sregister;
        this.bregister = bregister;
        this.legister = legister;
        this.parent = parent;
        this.pregister = pregister;


        initialiser();
        lagGui();
    }

    public void initialiser(){
        setLayout(new BorderLayout());

        knappepanel = new JPanel(new GridLayout(1,3,1,1));
        søkepanel = new JPanel(new GridLayout(1,2,1,1));

        infopanel_tomt = new JPanel();
        infopanel_søker = new JPanel();
        infopanel_utleier = new JPanel();

        fødselsnummer = new JTextField(10);
        finn = new JButton("Finn");

        neste = new JButton("neste");
        tilbake = new JButton("tilbake");
        avbryt = new JButton("forrige");




        matchProsent();
        lagGui();

        neste.addActionListener(this);
        tilbake.addActionListener(this);
        avbryt.addActionListener(this);
        finn.addActionListener(this);


    }

    public void lagGui(){

        knappepanel.add(avbryt);
        knappepanel.add(neste);
        knappepanel.add(tilbake);

        søkepanel.add(fødselsnummer);
        søkepanel.add(finn);

        add(knappepanel, BorderLayout.PAGE_END);
        add(søkepanel, BorderLayout.PAGE_START);

    }

    public void matchProsent(){

    }

    public void toggle(String pnr){

        if(sregister.finnes(pnr)){

            Soker søker = sregister.get(pnr);
            System.out.println("hei " + soker.getNavn());
        }

        else if(pregister.finnes(pnr)){

            Utleier utleier = pregister.get(pnr);
            System.out.println("hei " + utleier.getNavn());
        }

        else{

            System.out.println("ingen person med dette fødselsnummer registrert");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tilbake){

            parent.visPanel("VIS BOLIGBROWSEPROMPT");
        }

        if(e.getSource() == finn){

            toggle(fødselsnummer.getText());





        }
    }
}
