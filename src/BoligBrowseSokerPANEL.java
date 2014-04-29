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

    private JTextArea boligArea;

    private Sokerregister sregister;
    private Personregister pregister;
    private Boligregister bregister;
    private Leilighetregister legister;

    private LinkedList<Enebolig> eneboligliste;

    private Soker soker;
    private Enebolig første;




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
        infopanel_søker = new JPanel(new BorderLayout());
        infopanel_utleier = new JPanel();

        boligArea = new JTextArea();

        fødselsnummer = new JTextField(10);
        finn = new JButton("Finn");

        neste = new JButton("neste");
        tilbake = new JButton("tilbake");
        avbryt = new JButton("forrige");


        neste.addActionListener(this);
        tilbake.addActionListener(this);
        avbryt.addActionListener(this);
        finn.addActionListener(this);

        Iterator it = bregister.entrySet().iterator();
        eneboligliste = new LinkedList<Enebolig>();

    }

    public void lagGui(){

        knappepanel.add(avbryt);
        knappepanel.add(neste);
        knappepanel.add(tilbake);

        infopanel_søker.add(boligArea, BorderLayout.CENTER);

        søkepanel.add(fødselsnummer);
        søkepanel.add(finn);

        add(knappepanel, BorderLayout.PAGE_END);
        add(søkepanel, BorderLayout.PAGE_START);
        add(infopanel_søker,BorderLayout.CENTER);
    }

    public void visEneboliger(Soker soker){
        eneboligliste = soker.matcherEnebolig();
        boligArea.setText(eneboligliste.get(0).getBolignr());
    }

    public void visNesteEnebolig(Soker soker,int i){
        int j = i;
        boligArea.setText(eneboligliste.get(j).getBolignr());
    }

    public void toggle(String pnr){

        if(sregister.finnes(pnr)){

            Soker soker = sregister.get(pnr);
            System.out.println("hei " + soker.getNavn());
            visEneboliger(soker);
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
        else if(e.getSource() == finn){
            toggle(fødselsnummer.getText());
        }
        else if(e.getSource() == neste){
            int i = 0;
            visNesteEnebolig(soker,i+1);
        }
    }
}
