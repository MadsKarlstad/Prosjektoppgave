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

    private int index,frem,tilbake;
    private boolean aLeilighet,aEnebolig;

    private Sokerregister sregister;
    private Personregister pregister;
    private Boligregister bregister;
    private Leilighetregister legister;

    private JTextField fødselsnummer,bolignummer,eier,pris,areal;
    private JButton neste,ønsketleilighet,ønsketenebolig,leilighet,enebolig,forrige,tilbakeknapp,finn;
    private JLabel  bolignummeLabel,eierLabel,prisLabel,arealLabel,bildeLabel;
    private JPanel knappepanel,knappepanel_søker,søkepanel,infopanel_start_søker,midtpanel,boligFelter,bilde_info,infopanel_søker,infopanel_utleier;
    private JTextArea boligArea;

    private Border border;

    private String bildenavn;
    private ImageIcon bildeikon;

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
        boligFelter = new JPanel(new GridLayout(4,2,1,1));

        infopanel_start_søker = new JPanel(new GridLayout(1,2,1,1));
        infopanel_søker = new JPanel(new BorderLayout());
        infopanel_utleier = new JPanel();

        knappepanel_søker = new JPanel(new FlowLayout(FlowLayout.CENTER));

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
        boligArea.setEditable(false);

        df = new DecimalFormat("#");

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

        aEnebolig = true;
        aLeilighet = false;

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

        aLeilighet = true;
        aEnebolig = false;


        bildenavn = leilighetliste.get(index).getBildesti();


        bildeikon = new ImageIcon(getClass().getResource(bildenavn));
        bildeikon.getImage().flush();
        bildeLabel.setIcon( bildeikon );
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

        knappepanel_søker.removeAll();
        knappepanel_søker.revalidate();
        knappepanel_søker.repaint();



        knappepanel_søker.add(ønsketenebolig);

        bilde_info.add(boligArea, BorderLayout.PAGE_START);
        bilde_info.add(bildeLabel, BorderLayout.CENTER);


        infopanel_søker.add(boligFelter, BorderLayout.LINE_START);
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
        bilde_info.add(bildeLabel, BorderLayout.CENTER);


        infopanel_søker.add(boligFelter, BorderLayout.LINE_START);
        infopanel_søker.add(bilde_info, BorderLayout.CENTER);
        infopanel_søker.add(knappepanel_søker,BorderLayout.PAGE_END);

        midtpanel.add(infopanel_søker);

    }

    public void nextVasClicked(String pnr) throws IOException {

        if(index>=0){
            index+=frem;
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
            index -= tilbake;
            Soker soker = sregister.get(pnr);

            if(aEnebolig){
                visEneboliger(soker);
            }

            if(aLeilighet){
                visLeilighet(soker);
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
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension skjerm = kit.getScreenSize();
            int bredde = skjerm.width;
            int høyde = skjerm.height;

            parent.setSize(bredde/2, høyde-100);
            parent.setLocation(skjerm.width/2-parent.getSize().width/2, skjerm.height/2-parent.getSize().height/2);
        }

        else if (e.getSource() == ønsketenebolig){
            if(bregister.finnes(bolignummer.getText())){
                Utleier utleier = eneboligliste.get(index).getEier();
                Soker soker = sregister.get(fødselsnummer.getText());
            System.out.println("beskjedmetode legges her enebolig");
                eneboligliste.get(index).setØnsket(true);
                eneboligliste.get(index).setSoker(soker);

                utleier.leggInnØnsketEnebolig(eneboligliste.get(index).getEnebolig());

                System.out.println(eneboligliste.get(index).getSoker().getNavn());


            }
        }

        else if(e.getSource() == ønsketleilighet){

                if(bregister.finnes(bolignummer.getText())){
                Utleier utleier = leilighetliste.get(index).getEier();
                Soker soker = sregister.get(fødselsnummer.getText());
                System.out.println("beskjedmetode legges her leilighet");
                leilighetliste.get(index).setØnsket(true);
                leilighetliste.get(index).setSoker(soker);


                utleier.leggInnØnsketLeilighet(leilighetliste.get(index).getLeilighet());
                System.out.println(leilighetliste.get(index).getSoker().getNavn());

            }


        }

        else if (e.getSource() == enebolig){

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

        else if (e.getSource() == leilighet){

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
