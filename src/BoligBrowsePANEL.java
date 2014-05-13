/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;


/**
 * Created by Erlend on 22/04/14.test
 */
public class BoligBrowsePANEL extends JPanel implements ActionListener{

    private boolean aLeilighetSøker;
    private boolean aEneboligSøker;
    private boolean aLeilighetUtleier;
    private boolean aEneboligUtleier;

    private int index;
    private int frem;
    private int tilbake;

    private int boligindex;

    private JTextField fødselsnummer;
    private JButton finn;
    private JButton finnbolig;

    private JButton neste;
    private JButton tilbakeknapp;
    private JButton forrige;
    private JButton eneboligSøker;
    private JButton leilighetSøker;
    private JButton eneboligUtleier;
    private JButton leilighetUtleier;
    private JButton ønsketenebolig;
    private JButton ønsketleilighet;
    private JButton ønsketLeietakerEnebolig;
    private JButton ønsketLeietakerLeilighet;

    private JPanel knappepanel;
    private JPanel knappepanel_søker;
    private JPanel knappepanel_utleier;

    private JPanel søkepanel;
    private JPanel infopanel_start_søker;
    private JPanel infopanel_søker;
    private JPanel midtpanel;
    private JPanel feltpanel_søker;
    private JPanel bilde_info;


    private JPanel infopanel_utleier;
    private JPanel feltpanel_utleier;
    private JPanel infopanel_start_utleier;

    private JTextField finnboligfelt;
    private JTextField bolignummer;
    private JTextField eier;
    private JTextField pris;
    private JTextField areal;

    private JTextField søkeradresse;
    private JTextField søkernavn;
    private JTextField søkermail;
    private JTextField søkertelefon;
    private JTextField søkerbolignr;
    private JTextField søkerpersnr;


    private JLabel bolignummeLabel;
    private JLabel eierLabel;
    private JLabel prisLabel;
    private JLabel arealLabel;
    private JLabel søkerLabel;
    private JLabel søkerAdrLabel;
    private JLabel tlfLabel;
    private JLabel mailLabel;
    private JLabel søkerbolignrLabel;
    private JLabel søkerpersnrLabel;
    private JLabel utleidLabel;


    private JTextArea boligArea;

    private Border border;

    private String bildenavn;
    private JLabel bildeLabel;
    private ImageIcon bildeikon;

    private Sokerregister sregister;
    private Personregister pregister;
    private Boligregister bregister;
    private Leilighetregister legister;
    private Kontraktregister kontraktregister;

    private LinkedList<Enebolig> eneboligliste;
    private LinkedList<Leilighet> leilighetliste;

    private DecimalFormat df;

    private MainFrame parent;


    public BoligBrowsePANEL(Sokerregister sregister, Boligregister bregister, Leilighetregister legister,Personregister pregister, Kontraktregister kontraktregister, MainFrame parent) {
        this.sregister = sregister;
        this.bregister = bregister;
        this.legister = legister;
        this.parent = parent;
        this.pregister = pregister;
        this.kontraktregister = kontraktregister;

        initialiser();
        lagGui();
    }

    public void initialiser(){

        index = 0;


        setLayout(new BorderLayout());

        knappepanel = new JPanel(new GridLayout(1,3,1,1));
        søkepanel = new JPanel(new GridLayout(1,4,1,1));

        midtpanel = new JPanel(new BorderLayout());
        feltpanel_søker = new JPanel(new GridLayout(4,2,1,1));


        infopanel_start_søker = new JPanel(new GridLayout(1,2,1,1));
        infopanel_søker = new JPanel(new BorderLayout());
        knappepanel_søker = new JPanel(new FlowLayout(FlowLayout.CENTER));
        knappepanel_utleier = new JPanel(new FlowLayout(FlowLayout.CENTER));

        infopanel_utleier = new JPanel(new BorderLayout());
        infopanel_start_utleier = new JPanel(new GridLayout(1,2,1,1));
        feltpanel_utleier = new JPanel(new GridLayout(8,2,1,1));

        bilde_info = new JPanel(new BorderLayout());

        boligArea = new JTextArea();

        bilde_info.setBorder(BorderFactory.createEmptyBorder(0/*top*/, 20/*left*/, 0/*bottom*/, 0/*right*/));
        søkepanel.setBorder(BorderFactory.createEmptyBorder(10/*top*/, 10/*left*/, 20/*bottom*/, 10/*right*/));


        fødselsnummer = new JTextField(10);
        finn = new JButton("Finn person");
        finnbolig = new JButton("Finn bolig");

        neste = new JButton("neste");
        tilbakeknapp = new JButton("tilbake");
        forrige = new JButton("forrige");

        ønsketenebolig = new JButton("Ønsker å leie denne");
        ønsketleilighet = new JButton("Ønsker å leie denne");
        ønsketLeietakerEnebolig = new JButton("Godkjenn leietaker");
        ønsketLeietakerLeilighet = new JButton("Godkjenn leietaker");

        try{

            Icon leilighetIkon = new ImageIcon(getClass().getResource("Bilder/Leilighet.png"));
            Icon eneboligIkon = new ImageIcon(getClass().getResource("Bilder/Enebolig.png"));

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
        finnbolig.addActionListener(this);
        leilighetSøker.addActionListener(this);
        eneboligSøker.addActionListener(this);
        eneboligUtleier.addActionListener(this);
        leilighetUtleier.addActionListener(this);

        ønsketleilighet.addActionListener(this);
        ønsketenebolig.addActionListener(this);
        ønsketLeietakerEnebolig.addActionListener(this);
        ønsketLeietakerLeilighet.addActionListener(this);

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
        søkerbolignr = new JTextField(10);
        søkerpersnr = new JTextField(10);
        finnboligfelt = new JTextField();


        bolignummer.setEditable(false);
        eier.setEditable(false);
        areal.setEditable(false);
        pris.setEditable(false);

        søkeradresse.setEditable(false);
        søkermail.setEditable(false);
        søkernavn.setEditable(false);
        søkertelefon.setEditable(false);
        søkerbolignr.setEditable(false);
        søkerpersnr.setEditable(false);

        bolignummeLabel = new JLabel("Bolignummer");
        eierLabel = new JLabel("Boligeier");
        prisLabel = new JLabel("Pris pr mnd");
        arealLabel = new JLabel("Areal");
        søkerLabel = new JLabel("Søkers navn");
        søkerAdrLabel= new JLabel("Søkers adresse");
        tlfLabel = new JLabel("Søkers telfon");
        mailLabel = new JLabel("Søkers mail");
        søkerbolignrLabel = new JLabel("Boligens bolignummer");
        søkerpersnrLabel = new JLabel("Søkerens personnummer");
        utleidLabel = new JLabel("Du kan sjekke kontraktregisteret for når leieforholdet for denne boligen opphører");


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

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
        feltpanel_utleier.add(søkerbolignrLabel);
        feltpanel_utleier.add(søkerbolignr);
        feltpanel_utleier.add(søkerpersnrLabel);
        feltpanel_utleier.add(søkerpersnr);

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

        parent.setSize(bredde/2, høyde-300);


        knappepanel.setBackground(Color.decode("#B3D5E3"));
        knappepanel_søker.setBackground(Color.decode("#B3D5E3"));
        knappepanel_utleier.setBackground(Color.decode("#B3D5E3"));

        søkepanel.setBackground(Color.decode("#B3D5E3"));
        infopanel_start_søker.setBackground(Color.decode("#B3D5E3"));
        infopanel_søker.setBackground(Color.decode("#B3D5E3"));
        midtpanel.setBackground(Color.decode("#B3D5E3"));
        feltpanel_søker.setBackground(Color.decode("#B3D5E3"));
        bilde_info.setBackground(Color.decode("#B3D5E3"));


        infopanel_utleier.setBackground(Color.decode("#B3D5E3"));
        feltpanel_utleier.setBackground(Color.decode("#B3D5E3"));
        infopanel_start_utleier.setBackground(Color.decode("#B3D5E3"));

        setBackground(Color.decode("#B3D5E3"));


    }

    public void visEneboliger(Soker soker) throws IOException {

        soker.oppdaterØnskedeBoliger();

        eneboligliste = soker.getEneboligliste();

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

        if(!eneboligliste.get(index).erUtleid()) {
            knappepanel_søker.removeAll();
            knappepanel_søker.revalidate();
            knappepanel_søker.repaint();
            knappepanel_søker.add(ønsketenebolig);
        }
        else if(eneboligliste.get(index).erUtleid()){
            knappepanel_søker.removeAll();
            knappepanel_søker.revalidate();
            knappepanel_søker.repaint();
            knappepanel_søker.add(utleidLabel);
        }

        visSøkerPANEL();
    }

    public void visLeilighet(Soker soker) throws IOException {

        soker.oppdaterØnskedeBoliger();

        leilighetliste = soker.getLeilighetliste();
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
        bildeLabel.setIcon(bildeikon);

        if(!leilighetliste.get(index).erUtleid()) {
            knappepanel_søker.removeAll();
            knappepanel_søker.revalidate();
            knappepanel_søker.repaint();
            knappepanel_søker.add(ønsketleilighet);
        }
        else if(leilighetliste.get(index).erUtleid()){
            knappepanel_søker.removeAll();
            knappepanel_søker.revalidate();
            knappepanel_søker.repaint();
            knappepanel_søker.add(utleidLabel);
        }

        visSøkerPANEL();
    }

    public void visEneboligUtleier(Utleier utleier, int index, int boligindex) throws IndexOutOfBoundsException{


        eneboligliste = utleier.getØnskedeEneboliger();

        Soker soker = (Soker) eneboligliste.get(boligindex).getSokere().get(index);



        søkernavn.setText(soker.getNavn());
        søkeradresse.setText(soker.getAdresse());
        søkertelefon.setText(soker.getTelefonnummer());
        søkermail.setText(soker.getMail());
        søkerbolignr.setText(eneboligliste.get(boligindex).getBolignr());
        søkerpersnr.setText(soker.getFødselsnummer());

        aLeilighetSøker = false;
        aEneboligSøker = false;
        aLeilighetUtleier = false;
        aEneboligUtleier = true;

        knappepanel_utleier.add(ønsketLeietakerEnebolig);
    }

    public void visLeilighetUtleier(Utleier utleier,int index,int boligindex) throws IndexOutOfBoundsException{


        leilighetliste = utleier.getØnskedeLeiligheter();

        Soker soker = (Soker) leilighetliste.get(boligindex).getSokere().get(index);

        søkernavn.setText(soker.getNavn());
        søkeradresse.setText(soker.getAdresse());
        søkertelefon.setText(soker.getTelefonnummer());
        søkermail.setText(soker.getMail());
        søkerbolignr.setText(leilighetliste.get(boligindex).getBolignr());
        søkerpersnr.setText(soker.getFødselsnummer());

        aLeilighetSøker = false;
        aEneboligSøker = false;
        aLeilighetUtleier = true;
        aEneboligUtleier = false;

        knappepanel_utleier.add(ønsketLeietakerLeilighet);
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

    public void visSøkerPANEL() throws IOException {

        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();

        søkepanel.removeAll();
        søkepanel.revalidate();
        søkepanel.repaint();

        søkepanel.add(fødselsnummer);
        søkepanel.add(finn);
        søkepanel.add(finnboligfelt);
        søkepanel.add(finnbolig);

        bilde_info.add(boligArea, BorderLayout.PAGE_START);
        bilde_info.add(bildeLabel, BorderLayout.CENTER);

        infopanel_søker.add(feltpanel_søker, BorderLayout.LINE_START);
        infopanel_søker.add(bilde_info, BorderLayout.CENTER);
        infopanel_søker.add(knappepanel_søker,BorderLayout.PAGE_END);

        midtpanel.add(infopanel_søker);
    }

    public void visUtleierpanel(){

        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();

        knappepanel_utleier.removeAll();
        knappepanel_utleier.revalidate();
        knappepanel_utleier.repaint();

        infopanel_utleier.removeAll();
        infopanel_utleier.revalidate();
        infopanel_utleier.repaint();

        infopanel_utleier.add(feltpanel_utleier, BorderLayout.LINE_START);
        infopanel_utleier.add(knappepanel_utleier,BorderLayout.PAGE_END);

        midtpanel.add(infopanel_utleier);
    }

    public void nextVasClicked(String pnr) throws IOException {

        try{

            index+=frem;
            Soker soker = sregister.get(pnr);
            Utleier eier = pregister.get(fødselsnummer.getText());


            if(aEneboligSøker){
                visEneboliger(soker);
            }

            else if(aLeilighetSøker){
                visLeilighet(soker);
            }

            else if(aEneboligUtleier){
                try{visEneboligUtleier(eier,index,boligindex);}
                catch (IndexOutOfBoundsException ie){
                    boligindex++;
                    index = 0;
                    visEneboligUtleier(eier,index,boligindex);
                }
            }

            else if (aLeilighetUtleier){

                try{visLeilighetUtleier(eier,index,boligindex);}
                catch (IndexOutOfBoundsException ie){
                    boligindex++;
                    index = 0;
                    visLeilighetUtleier(eier,index,boligindex);
                }
            }
        }
        catch (IndexOutOfBoundsException io){



        }
    }

    public void previousVasClicked(String pnr) throws IOException {

        try {
            index -= tilbake;
            Soker soker = sregister.get(pnr);
            Utleier eier = pregister.get(fødselsnummer.getText());
            String bnr = bolignummer.getText();

            if (aEneboligSøker) {
                visEneboliger(soker);
            } else if (aLeilighetSøker) {
                visLeilighet(soker);
            } else if (aEneboligUtleier) {
                try {
                    visEneboligUtleier(eier, index, boligindex);
                } catch (IndexOutOfBoundsException ie) {
                    if (boligindex != 0) {
                        boligindex--;
                    } else {
                        boligindex = 0;
                    }
                    index = 0;
                    visEneboligUtleier(eier, index, boligindex);

                }
            } else if (aLeilighetUtleier) {

                try {
                    visLeilighetUtleier(eier, index, boligindex);
                } catch (IndexOutOfBoundsException ie) {
                    if (boligindex != 0) {
                        boligindex--;
                    } else {
                        boligindex = 0;
                    }
                    index = 0;
                    visLeilighetUtleier(eier, index, boligindex);
                }
            }
        }
        catch (IndexOutOfBoundsException io){

        }
    }

    public void toggle(String fnr){

        if(sregister.finnes(fnr)){

            visStartPANELsøker();
            Soker soker = sregister.get(fnr);
            soker.oppdaterBoliger(bregister,legister);
            soker.oppdaterØnskedeBoliger();

            søkepanel.removeAll();
            søkepanel.revalidate();
            søkepanel.repaint();

        }

        else if(pregister.finnes(fnr)){

            visStartPANELutleier();

            søkepanel.removeAll();
            søkepanel.revalidate();
            søkepanel.repaint();
        }

        else{

            visMelding("Ingen person med dette fødselsnummeret registrert");

        }
    }

    public void godkjennkontrakt(Bolig b){

        String eierspersnr = fødselsnummer.getText();
        String bolignr = søkerbolignr.getText();
        String leierspersnr = søkerpersnr.getText();
        Leilighet leilighet = legister.get(bolignr);
        Enebolig enebolig = bregister.get(bolignr);
        Utleier eier = pregister.get(eierspersnr);
        Soker soker = sregister.get(leierspersnr);

        int pris = b.getPris();
        String fra = b.getLedigDato();
        String til = "01.01.19";
        int siste = kontraktregister.size();
        int kontrakttall = siste +1;
        String kontraktnr = String.valueOf(kontrakttall);

        if(b instanceof Enebolig){


            Kontrakt kontrakt = new Kontrakt(kontraktnr,enebolig,eier,soker,pris,fra,til);
            if(!kontraktregister.finnes(kontraktnr)) {

                kontraktregister.put(kontrakt.getKontraktnr(), kontrakt);
                enebolig.setUtleid(true);
                kontrakt.setAktiv(true);


            }
            else if(kontraktregister.finnes(kontraktnr)) {
                visMelding("Kontrakt med kontraktnummer: " + kontraktnr +" finnes allerede");

            }
        }

        if(b instanceof Leilighet){


            Kontrakt kontrakt = new Kontrakt(kontraktnr,leilighet,eier,soker,pris,fra,til);
            if(!kontraktregister.finnes(kontraktnr)){
                kontraktregister.put(kontrakt.getKontraktnr(), kontrakt);
                leilighet.setUtleid(true);
                kontrakt.setAktiv(true);
            }
            else if(kontraktregister.finnes(kontraktnr)){
                visMelding("Kontrakt med kontraktnummer: " + kontraktnr +" finnes allerede");
            }
        }
    }

    public void finnBolig(String bnr)  throws Exception{

        finnboligfelt.setText("");

        if(bregister.finnes(bnr)){


            Enebolig enebolig = bregister.get(bnr);

            bolignummer.setText(enebolig.getBolignr());
            eier.setText(enebolig.getEiersNavn());
            pris.setText(String.valueOf(enebolig.getPris()));
            areal.setText(String.valueOf(enebolig.getBoareal()));

            boligArea.setText(enebolig.toString());

            aLeilighetSøker = false;
            aEneboligSøker = true;
            aLeilighetUtleier = false;
            aEneboligUtleier = false;

            bildenavn = enebolig.getBildesti();

            bildeikon = new ImageIcon(getClass().getResource(bildenavn));
            bildeikon.getImage().flush();
            bildeLabel.setIcon( bildeikon );

            if(!enebolig.erUtleid()) {
                knappepanel_søker.removeAll();
                knappepanel_søker.revalidate();
                knappepanel_søker.repaint();
                knappepanel_søker.add(ønsketenebolig);
            }
            else if(enebolig.erUtleid()){
                knappepanel_søker.removeAll();
                knappepanel_søker.revalidate();
                knappepanel_søker.repaint();
                knappepanel_søker.add(utleidLabel);
            }

            visSøkerPANEL();

        }

        else if(legister.finnes(bnr)){

            Leilighet leilighet = legister.get(bnr);

            bolignummer.setText(leilighet.getBolignr());
            eier.setText(leilighet.getEiersNavn());
            pris.setText(String.valueOf(leilighet.getPris()));
            areal.setText(String.valueOf(leilighet.getBoareal()));

            boligArea.setText(leilighet.toString());

            aLeilighetSøker = true;
            aEneboligSøker = false;
            aLeilighetUtleier = false;
            aEneboligUtleier = false;

            bildenavn = leilighet.getBildesti();

            bildeikon = new ImageIcon(getClass().getResource(bildenavn));
            bildeikon.getImage().flush();
            bildeLabel.setIcon(bildeikon);

            if(!leilighet.erUtleid()) {
                knappepanel_søker.removeAll();
                knappepanel_søker.revalidate();
                knappepanel_søker.repaint();
                knappepanel_søker.add(ønsketleilighet);
            }
            else if(leilighet.erUtleid()){
                knappepanel_søker.removeAll();
                knappepanel_søker.revalidate();
                knappepanel_søker.repaint();
                knappepanel_søker.add(utleidLabel);
            }

            visSøkerPANEL();

        }
    }

    public void visMelding(String melding){
        JOptionPane.showMessageDialog(null,melding);
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
                Soker soker = sregister.getSoker(fødselsnummer.getText());
                Utleier eier = enebolig.getEier();

                soker.addØnskedBolig(enebolig);
                eier.addEnebolig(enebolig);

                visMelding("Kunde har vist interesse\n venligst kontakt utleier");

                enebolig.setØnsket(true);
                enebolig.addSoker(soker);
            }
        }

        else if(e.getSource() == ønsketleilighet){

            if(legister.finnes(bolignummer.getText())){

                Leilighet leilighet = legister.get(bolignummer.getText());
                Soker soker = sregister.get(fødselsnummer.getText());
                Utleier eier = leilighet.getEier();

                soker.addØnskedBolig(leilighet);
                eier.addLeilighet(leilighet);

                visMelding("Kunde har vist interesse\n venligst kontakt utleier");

                leilighet.setØnsket(true);
                leilighet.addSoker(soker);
            }
        }

        else if (e.getSource() == eneboligSøker){

            Soker soker = sregister.get(fødselsnummer.getText());
            try
            {
                index = 0;
                soker.oppdaterØnskedeBoliger();
                visEneboliger(soker);
            }
            catch (IOException io){

            }
        }

        else if (e.getSource() == leilighetSøker){

            Soker soker = sregister.get(fødselsnummer.getText());
            try
            {
                index = 0;
                soker.oppdaterØnskedeBoliger();
                visLeilighet(soker);
            }
            catch (IOException io){

            }
        }

        else if (e.getSource() == leilighetUtleier){
            try{
                Utleier eier = pregister.get(fødselsnummer.getText());
                index = 0;
                boligindex = 0;
                visUtleierpanel();
                visLeilighetUtleier(eier,index,boligindex);
            }

            catch (NoSuchElementException ne){
                visStartPANELutleier();
                visMelding("Ingen ønsker registrert, venligst gå tilbake");
            }
            catch(IndexOutOfBoundsException ie){
                visStartPANELutleier();
                visMelding("Ingen ønsker registrert, venligst gå tilbake");
            }
        }

        else if (e.getSource() == eneboligUtleier){

            try{
                Utleier eier = pregister.get(fødselsnummer.getText());
                index = 0;
                boligindex = 0;
                visUtleierpanel();
                visEneboligUtleier(eier,index,boligindex);
            }
            catch(NoSuchElementException ne){
                visStartPANELutleier();
                visMelding("Ingen ønsker registrert, venligst gå tilbake");
            }
            catch(IndexOutOfBoundsException ie){
                visStartPANELutleier();
                visMelding("Ingen ønsker registrert, venligst gå tilbake");

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

        if(e.getSource() == ønsketLeietakerLeilighet){


            Leilighet leilighet = legister.get(søkerbolignr.getText());

            godkjennkontrakt(leilighet);

            visMelding("Kontrakt er generert, finnes i kontraktregisteret");
        }

        else if(e.getSource() == ønsketLeietakerEnebolig){

            Enebolig enebolig = bregister.get(søkerbolignr.getText());

            godkjennkontrakt(enebolig);

            visMelding("Kontrakt er generert, finnes i kontraktregisteret");
        }

        else if(e.getSource() == finnbolig){

            try{finnBolig(finnboligfelt.getText());}
            catch(Exception s){}

        }
    }
}