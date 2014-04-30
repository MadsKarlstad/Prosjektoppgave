import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Erlend on 22/04/14.test
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
    private JButton enebolig;
    private JButton leilighet;

    private JPanel knappepanel;
    private JPanel søkepanel;
    private JPanel infopanel_start_søker;
    private JPanel infopanel_søker;
    private JPanel infopanel_utleier;
    private JPanel midtpanel;
    private JPanel boligFelter;
    private JPanel bilde_info;

    private JTextField bolignummer;
    private JTextField eier;
    private JTextField pris;
    private JTextField areal;

    private JLabel bolignummeLabel;
    private JLabel eierLabel;
    private JLabel prisLabel;
    private JLabel arealLabel;


    private JTextArea boligArea;

    private Border border;

    private String bildenavn;
    private JLabel bildeLabel;
    private ImageIcon bildeikon;

    private Sokerregister sregister;
    private Personregister pregister;
    private Boligregister bregister;
    private Leilighetregister legister;

    private LinkedList<Enebolig> eneboligliste;
    private LinkedList<Leilighet> leilighetliste;

    private DecimalFormat df;

    private Icon leilighetIkon, eneboligIkon;

    private MainFrame parent;

    private boolean aLeilighet;
    private boolean aEnebolig;

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
        boligFelter = new JPanel(new GridLayout(4,2,1,1));

        infopanel_start_søker = new JPanel(new GridLayout(1,2,1,1));
        infopanel_søker = new JPanel(new BorderLayout());
        infopanel_utleier = new JPanel();

        bilde_info = new JPanel(new BorderLayout());

        boligArea = new JTextArea();

        fødselsnummer = new JTextField(10);
        finn = new JButton("Finn");

        neste = new JButton("neste");
        tilbakeknapp = new JButton("tilbake");
        forrige = new JButton("forrige");

        try{
            leilighetIkon = new ImageIcon(getClass().getResource("Bilder/Leilighet.png"));
            eneboligIkon = new ImageIcon(getClass().getResource("Bilder/Enebolig.png"));
            leilighet = new JButton(leilighetIkon);
            enebolig = new JButton(eneboligIkon);
        }

        catch (NullPointerException npe){
            leilighet = new JButton ("Vis leiligheter");
            enebolig = new JButton ("Vis eneboliger");
        }

        /*enebolig = new JButton();
        leilighet = new JButton();*/


        neste.addActionListener(this);
        tilbakeknapp.addActionListener(this);
        forrige.addActionListener(this);
        finn.addActionListener(this);
        leilighet.addActionListener(this);
        enebolig.addActionListener(this);

        //Iterator it = bregister.entrySet().iterator();
        eneboligliste = new LinkedList<Enebolig>();
        leilighetliste = new LinkedList<Leilighet>();

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
        eierLabel = new JLabel("Biligeier");
        prisLabel = new JLabel("Pris pr mnd");
        arealLabel = new JLabel("Areal");

        boligArea.setLineWrap(true);
        boligArea.setWrapStyleWord(true);

        df = new DecimalFormat("#.");

        border = BorderFactory.createLineBorder(Color.BLACK, 1);

        bildeLabel = new JLabel();

        aLeilighet = false;
        aEnebolig = false;

    }

    public void lagGui(){
        knappepanel.add(forrige);
        knappepanel.add(neste);
        knappepanel.add(tilbakeknapp);

        boligFelter.add(bolignummeLabel);
        boligFelter.add(bolignummer);
        boligFelter.add(eierLabel);
        boligFelter.add(eier);
        boligFelter.add(prisLabel);
        boligFelter.add(pris);
        boligFelter.add(arealLabel);
        boligFelter.add(areal);

        infopanel_søker.add(boligArea, BorderLayout.CENTER);

        søkepanel.add(fødselsnummer);
        søkepanel.add(finn);//disse durde byttes rekkefølge på søkepanel og infopane_søker

        infopanel_start_søker.add(enebolig);
        infopanel_start_søker.add(leilighet);

        add(knappepanel, BorderLayout.PAGE_END);
        add(søkepanel, BorderLayout.PAGE_START);

        add(midtpanel, BorderLayout.CENTER);

        boligArea.setBorder(border);
    }

    public void visEneboliger(Soker soker) throws IOException {
        eneboligliste = soker.matcherEnebolig();
        bolignummer.setText(eneboligliste.get(flytt).getBolignr());
        eier.setText(eneboligliste.get(flytt).getEiersNavn());
        pris.setText(String.valueOf(eneboligliste.get(flytt).getPris()));
        areal.setText(String.valueOf(eneboligliste.get(flytt).getBoareal()));

        boligArea.setText(eneboligliste.get(flytt).toString() + " Dette er en " + String.valueOf(df.format((eneboligliste.get(flytt).getProsent()))) + " % match etter " + soker.getNavn()
                + " sine ønsker");

        aEnebolig = true;
        aLeilighet = false;

        bildenavn = eneboligliste.get(flytt).getBildesti();


        bildeikon = new ImageIcon(getClass().getResource(bildenavn));
        bildeikon.getImage().flush();
        bildeLabel.setIcon( bildeikon );



    }

    public void visLeilighet(Soker soker) throws IOException {

        leilighetliste = soker.matcherLeilighet();
        bolignummer.setText(leilighetliste.get(flytt).getBolignr());
        eier.setText(leilighetliste.get(flytt).getEiersNavn());
        pris.setText(String.valueOf(leilighetliste.get(flytt).getPris()));
        areal.setText(String.valueOf(leilighetliste.get(flytt).getBoareal()));

        boligArea.setText(leilighetliste.get(flytt).toString() + " Dette er en " + String.valueOf(df.format((leilighetliste.get(flytt).getProsent()))) + " % match etter " + soker.getNavn()
                + " sine ønsker");


        bildeikon = new ImageIcon(getClass().getResource(bildenavn));

        bildeikon = new ImageIcon(bildenavn);
        bildeikon.getImage().flush();
        bildeLabel.setIcon( bildeikon );



        aLeilighet = true;
        aEnebolig = false;

    }

    public void visStartPANELsøker(){

        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();

        midtpanel.add(infopanel_start_søker, BorderLayout.CENTER);

    }

    public void visEneboligPANEL() throws IOException {

        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();

        bilde_info.add(boligArea, BorderLayout.PAGE_START);
        bilde_info.add(bildeLabel, BorderLayout.CENTER);

        infopanel_søker.add(boligFelter, BorderLayout.LINE_START);
        infopanel_søker.add(bilde_info, BorderLayout.CENTER);

        midtpanel.add(infopanel_søker);

    }

    public void visLeilighetPanel(){

        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();

        bilde_info.add(boligArea, BorderLayout.PAGE_START);
        bilde_info.add(bildeLabel, BorderLayout.CENTER);

        infopanel_søker.add(boligFelter, BorderLayout.LINE_START);
        infopanel_søker.add(bilde_info, BorderLayout.CENTER);

        midtpanel.add(infopanel_søker);

    }

    public void nextVasClicked(String pnr) throws IOException {

        if(flytt>=0){
            flytt+=frem;
            Soker soker = sregister.get(pnr);

            if(aEnebolig){
            visEneboliger(soker);
            }

            if(aLeilighet){
                visLeilighet(soker);
            }
        }
        else{}

    }

    public void previousVasClicked(String pnr) throws IOException {

        try {
            flytt -= tilbake;
            Soker soker = sregister.get(pnr);

            if(aEnebolig){
                visEneboliger(soker);
            }

            if(aLeilighet){
                visLeilighet(soker);
            }
        }

        catch (IndexOutOfBoundsException io){
            flytt = 0;

        }
    }

     public void toggle(String fnr){

        if(sregister.finnes(fnr)){

            visStartPANELsøker();


        }

        else if(pregister.finnes(fnr)){
            Utleier utleier = pregister.get(fnr);
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

        else if (e.getSource() == enebolig){

            Soker soker = sregister.get(fødselsnummer.getText());
            try
            {
                visEneboliger(soker);
                visEneboligPANEL();
            }
            catch (IOException io){

            }

        }

        else if (e.getSource() == leilighet){

            Soker soker = sregister.get(fødselsnummer.getText());
            try
            {
                visLeilighet(soker);
                visLeilighetPanel();
            }
            catch (IOException io){

            }

        }

     try {
            if (e.getSource() == finn) {

                toggle(fødselsnummer.getText());

            } else if (e.getSource() == neste) {

                nextVasClicked(fødselsnummer.getText());

            } else if (e.getSource() == forrige) {
                previousVasClicked(fødselsnummer.getText());

            }
        }
        catch(IOException io){

        }
    }
}