import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Erlend on 22/04/14.
 */
public class BoligBrowseSokerPANEL extends JPanel implements ActionListener{


    private int flytt;
    private int frem;
    private int tilbake;

    private JTextField fødselsnummer;
    private JButton finn;

    private JButton neste;
    private JButton tilbakeknapp;
    private JButton forrige;

    private JPanel knappepanel;
    private JPanel søkepanel;

    private JPanel infopanel_tomt;
    private JPanel infopanel_søker;
    private JPanel infopanel_utleier;

    private JPanel midtpanel;

    private JPanel eneboligFelter;

    private JTextField bolignummer;
    private JTextField eier;
    private JTextField pris;
    private JTextField areal;

    private JLabel bolignummeLabel;
    private JLabel eierLabel;
    private JLabel prisLabel;
    private JLabel arealLabel;


    private JTextArea boligArea;

    private Sokerregister sregister;
    private Personregister pregister;
    private Boligregister bregister;
    private Leilighetregister legister;

    private LinkedList<Enebolig> eneboligliste;

    private Soker soker;

    private DecimalFormat df;




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

        midtpanel = new JPanel(new BorderLayout());
        eneboligFelter = new JPanel(new GridLayout(4,2,1,1));

        infopanel_tomt = new JPanel();
        infopanel_søker = new JPanel(new BorderLayout());
        infopanel_utleier = new JPanel();

        boligArea = new JTextArea();

        fødselsnummer = new JTextField(10);
        finn = new JButton("Finn");

        neste = new JButton("neste");
        tilbakeknapp = new JButton("tilbake");
        forrige = new JButton("forrige");


        neste.addActionListener(this);
        tilbakeknapp.addActionListener(this);
        forrige.addActionListener(this);
        finn.addActionListener(this);

        //Iterator it = bregister.entrySet().iterator();
        eneboligliste = new LinkedList<Enebolig>();

        flytt = 0;
        frem = 1;
        tilbake = 1;

        bolignummer = new JTextField(10);
        eier = new JTextField(10);
        areal = new JTextField(10);
        pris = new JTextField(10);

        bolignummer.setEditable(false);
        eier.setEditable(false);
        areal.setEditable(false);
        pris.setEditable(false);

        bolignummeLabel = new JLabel("Bolignummer");
        eierLabel = new JLabel("Boligeier");
        prisLabel = new JLabel("Pris pr mnd");
        arealLabel = new JLabel("Areal");

        boligArea.setLineWrap(true);
        boligArea.setWrapStyleWord(true);

        df = new DecimalFormat("#.");




    }

    public void lagGui(){
        knappepanel.add(forrige);
        knappepanel.add(neste);
        knappepanel.add(tilbakeknapp);

        eneboligFelter.add(bolignummeLabel);
        eneboligFelter.add(bolignummer);
        eneboligFelter.add(eierLabel);
        eneboligFelter.add(eier);
        eneboligFelter.add(prisLabel);
        eneboligFelter.add(pris);
        eneboligFelter.add(arealLabel);
        eneboligFelter.add(areal);

        infopanel_søker.add(boligArea, BorderLayout.CENTER);

        søkepanel.add(fødselsnummer);
        søkepanel.add(finn);

        add(knappepanel, BorderLayout.PAGE_END);
        add(søkepanel, BorderLayout.PAGE_START);


        add(midtpanel, BorderLayout.CENTER);
    }

    public void visEneboliger(Soker soker,int i){
        eneboligliste = soker.matcherEnebolig();
        bolignummer.setText(eneboligliste.get(flytt).getBolignr());
        eier.setText(eneboligliste.get(flytt).getEiersNavn());
        pris.setText(String.valueOf(eneboligliste.get(flytt).getPris()));
        areal.setText(String.valueOf(eneboligliste.get(flytt).getBoareal()));

        boligArea.setText(eneboligliste.get(flytt).toString() + " Dette er en " + String.valueOf(df.format((eneboligliste.get(flytt).getProsent()))) + " % match etter " + soker.getNavn()
        + " sine ønsker");


    }

    public void visEneboligPANEL(){

        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();

        infopanel_søker.add(eneboligFelter, BorderLayout.LINE_START);
        infopanel_søker.add(boligArea, BorderLayout.CENTER);

        midtpanel.add(infopanel_søker);

    }

    public void nextVasClicked(String pnr){

        flytt+=frem;
        Soker soker = sregister.get(pnr);
        visEneboliger(soker,flytt);

    }

    public void previousVasClicked(String pnr){

        flytt-=tilbake;
        Soker soker = sregister.get(pnr);
        visEneboliger(soker,flytt);
    }

    public void toggle(String pnr){

        if(sregister.finnes(pnr)){
            Soker soker = sregister.get(pnr);
            System.out.println("hei " + soker.getNavn());
            visEneboliger(soker, 0);
            visEneboligPANEL();
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

        if (e.getSource() == tilbakeknapp){
            parent.visPanel(MainFrame.MAIN_BOARD);
        }
        else if(e.getSource() == finn){
            toggle(fødselsnummer.getText());
        }
        else if(e.getSource() == neste){
            nextVasClicked(fødselsnummer.getText());
        }
        else if(e.getSource() == forrige){
            previousVasClicked(fødselsnummer.getText());
        }
    }
}
