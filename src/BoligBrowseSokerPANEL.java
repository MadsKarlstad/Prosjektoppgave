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

    private boolean aLeilighetSøker;
    private boolean aEneboligSøker;
    private boolean aLeilighetUtleier;
    private boolean aEneboligUtleier;



    private int index;
    private int frem;
    private int tilbake;

    private JTextField fødselsnummer;
    private JButton finn;

    private JButton neste;
    private JButton tilbakeknapp;
    private JButton forrige;
    private JButton eneboligSøker;
    private JButton leilighetSøker;
    private JButton eneboligUtleier;
    private JButton leilighetUtleier;
    private JButton ønsketenebolig;
    private JButton ønsketleilighet;

    private JPanel knappepanel;
    private JPanel knappepanel_søker;
    private JPanel søkepanel;
    private JPanel infopanel_start_søker;
    private JPanel infopanel_søker;
    private JPanel midtpanel;
    private JPanel feltpanel_søker;
    private JPanel bilde_info;


    //utleierpanelenee
    private JPanel infopanel_utleier;
    private JPanel feltpanel_utleier;
    private JPanel bilde_og_info_søker;
    private JPanel infopanel_start_utleier;

    private JTextField bolignummer;
    private JTextField eier;
    private JTextField pris;
    private JTextField areal;

    private JTextField søkeradresse;
    private JTextField søkernavn;
    private JTextField søkermail;
    private JTextField søkertelefon;

    private JLabel bolignummeLabel;
    private JLabel eierLabel;
    private JLabel prisLabel;
    private JLabel arealLabel;
    private JLabel søkerLabel;
    private JLabel søkerAdrLabel;
    private JLabel tlfLabel;
    private JLabel mailLabel;


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
        feltpanel_søker = new JPanel(new GridLayout(4,2,1,1));


        infopanel_start_søker = new JPanel(new GridLayout(1,2,1,1));
        infopanel_søker = new JPanel(new BorderLayout());
        knappepanel_søker = new JPanel(new FlowLayout(FlowLayout.CENTER));

        infopanel_utleier = new JPanel(new BorderLayout());
        infopanel_start_utleier = new JPanel(new GridLayout(1,2,1,1));
        feltpanel_utleier = new JPanel(new GridLayout(4,2,1,1));

        bilde_info = new JPanel(new BorderLayout());

        boligArea = new JTextArea();


        fødselsnummer = new JTextField(10);
        finn = new JButton("Finn");

        neste = new JButton("neste");
        tilbakeknapp = new JButton("tilbake");
        forrige = new JButton("forrige");

        ønsketenebolig = new JButton("Ønsker å leie denne");
        ønsketleilighet = new JButton("Ønsker å leie denne");

        try{
            leilighetIkon = new ImageIcon(getClass().getResource("Bilder/Leilighet.png"));
            eneboligIkon = new ImageIcon(getClass().getResource("Bilder/Enebolig.png"));
            leilighetSøker = new JButton(leilighetIkon);
            eneboligSøker = new JButton(eneboligIkon);
            leilighetUtleier = new JButton(leilighetIkon);
            eneboligUtleier = new JButton(eneboligIkon);
        }

        catch (NullPointerException npe){
            leilighetSøker = new JButton("vis leiligheter");
            eneboligSøker = new JButton("vis enebolig");
            leilighetUtleier = new JButton("vis leiligheter");
            eneboligUtleier = new JButton("vis enebolig");
        }

        /*enebolig = new JButton();
        leilighet = new JButton();*/


        neste.addActionListener(this);
        tilbakeknapp.addActionListener(this);
        forrige.addActionListener(this);
        finn.addActionListener(this);
        leilighetSøker.addActionListener(this);
        eneboligSøker.addActionListener(this);
        eneboligUtleier.addActionListener(this);
        leilighetUtleier.addActionListener(this);

        ønsketleilighet.addActionListener(this);
        ønsketenebolig.addActionListener(this);

        //Iterator it = bregister.entrySet().iterator();
        eneboligliste = new LinkedList<Enebolig>();
        leilighetliste = new LinkedList<Leilighet>();

        index = 0;
        frem = 1;
        tilbake = 1;

        bolignummer = new JTextField(10);
        eier = new JTextField(10);
        areal = new JTextField(10);
        pris = new JTextField(10);
        søkeradresse = new JTextField(10);
        søkermail = new JTextField(10);
        søkernavn = new JTextField(10);
        søkertelefon = new JTextField(10);


        bolignummer.setEditable(false);
        eier.setEditable(false);
        areal.setEditable(false);
        pris.setEditable(false);

        bolignummeLabel = new JLabel("Bolignummer");
        eierLabel = new JLabel("Biligeier");
        prisLabel = new JLabel("Pris pr mnd");
        arealLabel = new JLabel("Areal");
        søkerLabel = new JLabel("Søkers navn");
        søkerAdrLabel= new JLabel("Søkers adresse");
        tlfLabel = new JLabel("Søkers telfon");
        mailLabel = new JLabel("Søkers mail");

        boligArea.setLineWrap(true);
        boligArea.setWrapStyleWord(true);
        boligArea.setEditable(false);

        df = new DecimalFormat("#");

        border = BorderFactory.createLineBorder(Color.BLACK, 1);

        bildeLabel = new JLabel();

        aLeilighetSøker = false;
        aEneboligSøker = false;
        aLeilighetUtleier = false;
        aEneboligUtleier = false;

    }

    public void lagGui(){
        knappepanel.add(forrige);
        knappepanel.add(neste);
        knappepanel.add(tilbakeknapp);

        feltpanel_søker.add(bolignummeLabel);
        feltpanel_søker.add(bolignummer);
        feltpanel_søker.add(eierLabel);
        feltpanel_søker.add(eier);
        feltpanel_søker.add(prisLabel);
        feltpanel_søker.add(pris);
        feltpanel_søker.add(arealLabel);
        feltpanel_søker.add(areal);

        feltpanel_utleier.add(søkerLabel);
        feltpanel_utleier.add(søkernavn);
        feltpanel_utleier.add(søkerAdrLabel);
        feltpanel_utleier.add(søkeradresse);
        feltpanel_utleier.add(tlfLabel);
        feltpanel_utleier.add(søkertelefon);
        feltpanel_utleier.add(mailLabel);
        feltpanel_utleier.add(søkermail);

        infopanel_søker.add(boligArea, BorderLayout.CENTER);

        søkepanel.add(fødselsnummer);
        søkepanel.add(finn);//disse durde byttes rekkefølge på søkepanel og infopane_søker

        infopanel_start_søker.add(eneboligSøker);
        infopanel_start_søker.add(leilighetSøker);

        infopanel_start_utleier.add(eneboligUtleier);
        infopanel_start_utleier.add(leilighetUtleier);

        add(knappepanel, BorderLayout.PAGE_END);
        add(søkepanel, BorderLayout.PAGE_START);

        add(midtpanel, BorderLayout.CENTER);


        boligArea.setBorder(border);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;

        parent.setSize(bredde/2, høyde-400);
    }

    public void visEneboliger(Soker soker) throws IOException {

        eneboligliste = soker.matcherEnebolig();
        bolignummer.setText(eneboligliste.get(index).getBolignr());
        eier.setText(eneboligliste.get(index).getEiersNavn());
        pris.setText(String.valueOf(eneboligliste.get(index).getPris()));
        areal.setText(String.valueOf(eneboligliste.get(index).getBoareal()));

        boligArea.setText(eneboligliste.get(index).toString() + " Dette er en " + String.valueOf(df.format((eneboligliste.get(index).getProsent()))) + " % match etter " + soker.getNavn()
                + " sine ønsker");

        aLeilighetSøker = false;
        aEneboligSøker = true;
        aLeilighetUtleier = false;
        aEneboligUtleier = false;

        bildenavn = eneboligliste.get(index).getBildesti();


        bildeikon = new ImageIcon(getClass().getResource(bildenavn));
        bildeikon.getImage().flush();
        bildeLabel.setIcon( bildeikon );



    }

    public void visLeilighet(Soker soker) throws IOException {

        leilighetliste = soker.matcherLeilighet();
        bolignummer.setText(leilighetliste.get(index).getBolignr());
        eier.setText(leilighetliste.get(index).getEiersNavn());
        pris.setText(String.valueOf(leilighetliste.get(index).getPris()));
        areal.setText(String.valueOf(leilighetliste.get(index).getBoareal()));

        boligArea.setText(leilighetliste.get(index).toString() + " Dette er en " + String.valueOf(df.format((leilighetliste.get(index).getProsent()))) + " % match etter " + soker.getNavn()
                + " sine ønsker");

        aLeilighetSøker = true;
        aEneboligSøker = false;
        aLeilighetUtleier = false;
        aEneboligUtleier = false;


        bildenavn = leilighetliste.get(index).getBildesti();


        bildeikon = new ImageIcon(getClass().getResource(bildenavn));
        bildeikon.getImage().flush();
        bildeLabel.setIcon( bildeikon );
    }

    public void visEneboligUtleier(Enebolig enebolig){

        Soker soker = (Soker) enebolig.getSokere().get(index);

        søkernavn.setText(soker.getNavn());
        søkeradresse.setText(soker.getAdresse());
        søkertelefon.setText(soker.getTelefonnummer());
        søkermail.setText(soker.getMail());

        aLeilighetSøker = false;
        aEneboligSøker = false;
        aLeilighetUtleier = false;
        aEneboligUtleier = true;


    }

    public void visStartPANELsøker(){

        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();



        midtpanel.add(infopanel_start_søker, BorderLayout.CENTER);

    }

    public void visStartPANELutleier(){

        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();

        midtpanel.add(infopanel_start_utleier, BorderLayout.CENTER);
    }

    public void visEneboligPANEL() throws IOException {

        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();

        knappepanel_søker.removeAll();
        knappepanel_søker.revalidate();
        knappepanel_søker.repaint();



        knappepanel_søker.add(ønsketenebolig);

        bilde_info.add(boligArea, BorderLayout.PAGE_START);
        bilde_info.add(bildeLabel, BorderLayout.CENTER);


        infopanel_søker.add(feltpanel_søker, BorderLayout.LINE_START);
        infopanel_søker.add(bilde_info, BorderLayout.CENTER);
        infopanel_søker.add(knappepanel_søker,BorderLayout.PAGE_END);

        midtpanel.add(infopanel_søker);

    }

    public void visLeilighetPanel(){

        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();

        knappepanel_søker.removeAll();
        knappepanel_søker.revalidate();
        knappepanel_søker.repaint();

        knappepanel_søker.add(ønsketleilighet);

        bilde_info.add(boligArea, BorderLayout.PAGE_START);


        infopanel_søker.add(feltpanel_utleier, BorderLayout.LINE_START);
        infopanel_søker.add(bilde_info, BorderLayout.CENTER);
        infopanel_søker.add(knappepanel_søker,BorderLayout.PAGE_END);

        midtpanel.add(infopanel_søker);


    }

    public void visUtleierpanel(){

        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();

        infopanel_utleier.removeAll();
        infopanel_utleier.revalidate();
        infopanel_utleier.repaint();


        infopanel_utleier.add(feltpanel_utleier, BorderLayout.LINE_START);


        midtpanel.add(infopanel_utleier);


    }

    public void nextVasClicked(String pnr) throws IOException {

        if(index>=0){

            index+=frem;
            Soker soker = sregister.get(pnr);
            Utleier utleier = pregister.get(pnr);
            String bnr = bolignummer.getText();

            Enebolig enebolig = bregister.get(bnr);



            if(aEneboligSøker){
                visEneboliger(soker);
            }

            else if(aLeilighetSøker){
                visLeilighet(soker);
            }

            else if(aEneboligUtleier){
                visEneboligUtleier(enebolig);
            }
        }
        else{}

    }

    public void previousVasClicked(String pnr) throws IOException {

        try {
            index -= tilbake;
            Soker soker = sregister.get(pnr);
            Utleier utleier = pregister.get(pnr);

            String bnr = bolignummer.getText();

            Enebolig enebolig = bregister.get(bnr);

            if(aEneboligSøker){
                visEneboliger(soker);
            }

            else if(aLeilighetSøker){
                visLeilighet(soker);
            }

            else if(aEneboligUtleier){
                visEneboligUtleier(enebolig);
            }
        }

        catch (IndexOutOfBoundsException io){
            index = 0;

        }
    }

    public void toggle(String fnr){

        if(sregister.finnes(fnr)){

            visStartPANELsøker();


        }

        else if(pregister.finnes(fnr)){

            visStartPANELutleier();


        }

        else{
            System.out.println("ingen person med dette fødselsnummer registrert");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tilbakeknapp){
            parent.visPanel(MainFrame.MAIN_BOARD);
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension skjerm = kit.getScreenSize();
            int bredde = skjerm.width;
            int høyde = skjerm.height;

            parent.setSize(bredde/2, høyde-100);
            parent.setLocation(skjerm.width/2-parent.getSize().width/2, skjerm.height/2-parent.getSize().height/2);
        }

        else if (e.getSource() == ønsketenebolig){
            if(bregister.finnes(bolignummer.getText())){
                Enebolig enebolig = bregister.get(bolignummer.getText());
                Utleier utleier = eneboligliste.get(index).getEier();
                Soker soker = sregister.getSoker(fødselsnummer.getText());

                eneboligliste.get(index).setØnsket(true);
                eneboligliste.get(index).addSoker(soker);

                System.out.println(enebolig.getSokere());


            }
        }

        else if(e.getSource() == ønsketleilighet){

            if(bregister.finnes(bolignummer.getText())){
                Utleier utleier = leilighetliste.get(index).getEier();
                Soker soker = sregister.get(fødselsnummer.getText());
                System.out.println("beskjedmetode legges her leilighet");
                leilighetliste.get(index).setØnsket(true);
                leilighetliste.get(index).setSoker(soker);


                //utleier.leggInnØnsketLeilighet(leilighetliste.get(index).getLeilighet());
                System.out.println(leilighetliste);

            }


        }

        else if (e.getSource() == eneboligSøker){

            Soker soker = sregister.get(fødselsnummer.getText());
            try
            {
                visEneboliger(soker);
                visEneboligPANEL();
                index = 0;
            }
            catch (IOException io){

            }

        }

        else if (e.getSource() == leilighetSøker){

            Soker soker = sregister.get(fødselsnummer.getText());
            try
            {
                visLeilighet(soker);
                visLeilighetPanel();
                index = 0;

            }
            catch (IOException io){

            }

        }

        else if (e.getSource() == leilighetUtleier){

            Soker soker = sregister.get(fødselsnummer.getText());
            try
            {
                visLeilighet(soker);
                visLeilighetPanel();
                index = 0;

            }
            catch (IOException io){

            }

        }

        else if (e.getSource() == eneboligUtleier){

            Utleier utleier = pregister.get(fødselsnummer.getText());
            Enebolig enebolig = bregister.get(bolignummer.getText());

                visUtleierpanel();
                visEneboligUtleier(enebolig);
                index = 0;




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