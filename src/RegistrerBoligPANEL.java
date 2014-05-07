/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by Erlend on 22/04/14.
 * http://stackoverflow.com/questions/5388146/copy-and-rename-file-on-different-location til image uploader
 */
public class RegistrerBoligPANEL extends JPanel implements ActionListener {

    String sti;
    int bildesti;

    //felter som gjelder for både enebolig og leilighet
    private JTextField[] standardfelter;
    private final String[] feltnavn = {"eiersFødselsnummer","adresse","beskrivelse","bolignummer","boareal","antallRom","byggeår","pris","ledigFra"};

    //Felter og bokser som bare gjelder enebolig
    private JTextField[] eneboligfelt;
    private final String[] eneboligfeltnavn = {"TomtAreal","antallBad","antallEtasjer"};

    //felter og bokser som bare gjelder leilighet
    private JTextField[] leilighetfelt;
    private final String[] leilighetfeltnavn = {"Antall boder","Etasje"};

    //checkbokser + string
    private JCheckBox[] eneboligbokser;
    private JCheckBox[] leilighetbokser;
    private final String[]boksnavn = {"røyker","husdyr","balkong","terasse","TVinkludert","internetInkludert","strømInkludert","parkering","kjeller","heis"};
    private final int minusHeis = 1;

    //combobokser
    private JComboBox<String> beliggenhet;
    private JComboBox<String> boligtype;

    String[] boligtypevalg = {"Velg boligtype","Enebolig","Leilighet"};
    String [] bydeler = { "Velg bydel", "Alna", "Bjerke", "Frogner", "Gamle Oslo", "Grorud",
            "Grünerløkka", "Nordre Aker", "Nordstrand", "Sagene", "St. Hanshaugen",
            "Stovner", "Søndre Nordstrand", "Ullern", "Vestre Aker", "Østensjø"};

    private Person eier;
    private Utleier utleier;

    //registere det er behov for
    private Boligregister bregister;
    private Personregister pregister;
    private Leilighetregister legister;

    boolean isTom;
    boolean isEnebolig;
    boolean isLeilighet;


    private final int EIER = 0;
    private final int ADRESSE = 1;
    private final int BESKRIVELSE = 2;
    private final int BOLIGNR = 3;
    private final int BOAREAL = 4;
    private final int ANTALLROM = 5;
    private final int BYGGÅR = 6;
    private final int PRIS = 7;
    private final int LEDIGFRA = 8;

    private final int RØYKER = 0;
    private final int HUSDYR = 1;
    private final int BALKONG = 2;
    private final int TERASSE = 3;
    private final int TV = 4;
    private final int INTERNET = 5;
    private final int STRØM = 6;
    private final int PARKERING = 7;
    private final int KJELLER = 8;
    private final int HEIS = 9;

    private final int TOMTA = 0;
    private final int ANTBAD = 1;
    private final int ANTETG = 2;

    private final int ANTBODER = 0;
    private final int ETG = 1;

    private final int TOMT_PANEL = 0;
    private final int ENEBOLIG = 1;
    private final int LEILIGHET = 2;

    private JTextArea utskriftsområde;

    private JLabel overskrift;

    private JPanel feltpanel;
    private JPanel boligtypepanel;
    private JPanel overskriftpanel;
    private JPanel toppanel;

    private JPanel tomtpanel;
    private JPanel enebolig;
    private JPanel leilighet;
    private JPanel bydelpanel;
    private JPanel midtpanel;

    private JPanel bokspanel;
    private JPanel feltpanelEnebolig;
    private JPanel eneboligpanel;
    private JPanel leilighetpanel;
    private JPanel feltpanelLeilighet;
    private JPanel bokspanelLeilighet;

    private JPanel knappepanel;
    private JPanel bunnpanel;

    private JButton registrer;
    private JButton avbryt;
    private JButton bilde;

    private MainFrame parent;

    private ImageIcon image;

    public RegistrerBoligPANEL(Personregister pregister,Boligregister bregister,Leilighetregister legister, MainFrame parent) {
        super(new BorderLayout());
        this.pregister = pregister;
        this.bregister = bregister;
        this.legister = legister;
        this.parent = parent;
        initialiser();
        lagGUI();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;

        parent.setSize(bredde / 2, høyde / 2);
        parent.setLocation(skjerm.width / 2 - parent.getSize().width / 2, skjerm.height / 2 - parent.getSize().height / 2);

    }

    public void initialiser() {

        setLayout(new BorderLayout());

        utskriftsområde = new JTextArea();

        standardfelter = new JTextField[feltnavn.length];
        eneboligbokser = new JCheckBox[boksnavn.length-minusHeis];
        eneboligfelt = new JTextField[eneboligfeltnavn.length];

        leilighetbokser = new JCheckBox[boksnavn.length];
        leilighetfelt = new JTextField[leilighetfeltnavn.length];

        overskrift = new JLabel("Registrer ny Bolig");
        overskrift.setVisible(true);

        //toppanel
        feltpanel = new JPanel(new GridLayout(3, 4, 5, 5));
        boligtypepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        overskriftpanel= new JPanel(new FlowLayout(FlowLayout.CENTER));
        toppanel = new JPanel(new BorderLayout());

        //midtpanel
        bydelpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tomtpanel = new JPanel();

        enebolig = new JPanel();
        leilighet = new JPanel();
        leilighet.setBackground(Color.blue);
        midtpanel = new JPanel(new BorderLayout());

        //bunnpanel
        knappepanel = new JPanel(new GridLayout(1,3,5,5));
        bunnpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //eneboligunikt
        eneboligpanel = new JPanel(new BorderLayout());
        eneboligpanel.setLayout(new BorderLayout());
        feltpanelEnebolig = new JPanel(new GridLayout(1,3,5,5));
        bokspanel = new JPanel(new GridLayout(3,3,5,5));

        //leilighetunikt
        leilighetpanel = new JPanel(new BorderLayout());
        leilighetpanel.setLayout(new BorderLayout());
        feltpanelLeilighet = new JPanel(new GridLayout(1,2,5,5));
        bokspanelLeilighet = new JPanel(new GridLayout(5,2,5,5));

        TextPrompt tp [] = new TextPrompt[standardfelter.length];
        TextPrompt tp1 [] = new TextPrompt[eneboligfelt.length];
        TextPrompt tp2 [] = new TextPrompt[leilighetfelt.length];




        for (int i = 0; i < feltnavn.length; i++) {
            standardfelter[i] = new JTextField(10);
            tp[i] = new TextPrompt(feltnavn[i], standardfelter[i]);
            tp[i].changeAlpha(0.7f);
        }
        for (int i = 0; i < eneboligfeltnavn.length; i++) {
            eneboligfelt[i] = new JTextField(10);
            tp1[i] = new TextPrompt(eneboligfeltnavn[i],eneboligfelt[i]);
            tp1[i].changeAlpha(0.7f);
            feltpanelEnebolig.add(eneboligfelt[i]);
        }

        for (int i = 0; i < leilighetfeltnavn.length; i++) {
            leilighetfelt[i] = new JTextField(10);
            tp2[i] = new TextPrompt(leilighetfeltnavn[i],leilighetfelt[i]);
            tp2[i].changeAlpha(0.7f);
            feltpanelLeilighet.add(leilighetfelt[i]);

        }

        for (int i = 0; i < boksnavn.length-minusHeis; i++) {
            eneboligbokser[i] = new JCheckBox();
            eneboligbokser[i].setText(boksnavn[i]);
            eneboligbokser[i].setHorizontalAlignment(JCheckBox.CENTER);
        }
        for (int i = 0; i < boksnavn.length; i++) {
            leilighetbokser[i] = new JCheckBox();
            leilighetbokser[i].setText(boksnavn[i]);
            leilighetbokser[i].setHorizontalAlignment(JCheckBox.CENTER);
        }





        beliggenhet = new JComboBox<String>(bydeler);
        boligtype = new JComboBox<String>(boligtypevalg);

        boligtype.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> boligtype = (JComboBox) e.getSource();
                int n = boligtype.getSelectedIndex();
                String boligtypevalg = boligtype.getItemAt(n);
                if(n == 1){
                    visEneboligpanel();

                }
                else if(n == 2){

                    visLeilighetpanel();
                }
                else if(n==0){
                    visTomtpanel();
                }
            }
        });
        registrer = new JButton("Registrer");
        avbryt = new JButton("Avbryt");
        bilde = new JButton("Last opp bilde");


        registrer.addActionListener(this);
        avbryt.addActionListener(this);
        bilde.addActionListener(this);


    }

    public void lagGUI() {
        for (int i = 0; i < standardfelter.length; i++) {
            feltpanel.add(standardfelter[i]);
        }
        //legger til kombobokser
        boligtypepanel.add(boligtype);
        bydelpanel.add(beliggenhet);

        knappepanel.add(registrer);
        knappepanel.add(avbryt);
        knappepanel.add(bilde);

        overskriftpanel.add(overskrift);

        toppanel.add(feltpanel,BorderLayout.CENTER);
        toppanel.add(boligtypepanel,BorderLayout.PAGE_END);
        toppanel.add(overskriftpanel,BorderLayout.PAGE_START);

        //midtpanel.add(tomtpanel, BorderLayout.CENTER);
        midtpanel.add(bydelpanel, BorderLayout.PAGE_END);


        bunnpanel.add(knappepanel);




        add(toppanel, BorderLayout.PAGE_START);
        add(midtpanel,BorderLayout.CENTER);
        add(bunnpanel,BorderLayout.PAGE_END);


    }
    public void visTomtpanel(){
        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();
        tomtpanel.add(utskriftsområde);
        isTom=true;
        isEnebolig=false;
        isLeilighet=false;

    }
    public void visEneboligpanel(){


        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();
        midtpanel.add(enebolig);
        midtpanel.add(bydelpanel, BorderLayout.PAGE_END);

        for (int i = 0; i < boksnavn.length-minusHeis; i++) {
            bokspanel.add(eneboligbokser[i]);
        }

        eneboligpanel.add(bokspanel, BorderLayout.CENTER);
        eneboligpanel.add(feltpanelEnebolig,BorderLayout.PAGE_END);
        midtpanel.add(eneboligpanel, BorderLayout.CENTER);
        isEnebolig=true;
        isTom=false;
        isLeilighet=false;



    }
    public void visLeilighetpanel(){
        remove(midtpanel);
        revalidate();
        repaint();
        midtpanel = new JPanel(new BorderLayout());
        midtpanel.add(leilighet);
        midtpanel.add(bydelpanel, BorderLayout.PAGE_END);

        for (int i = 0; i < boksnavn.length; i++) {
            bokspanelLeilighet.add(leilighetbokser[i]);
        }
        leilighetpanel.add(bokspanelLeilighet,BorderLayout.CENTER);
        leilighetpanel.add(feltpanelLeilighet,BorderLayout.PAGE_END);
        midtpanel.add(leilighetpanel,BorderLayout.CENTER);
        isLeilighet=true;
        isTom=false;
        isEnebolig=false;
        add(midtpanel, BorderLayout.CENTER);

    }
    public void registrerEnebolig(){

        int boareal = Integer.parseInt(standardfelter[BOAREAL].getText());
        int antrom = Integer.parseInt(standardfelter[ANTALLROM].getText());
        int byggår = Integer.parseInt(standardfelter[BYGGÅR].getText());
        int antbad = Integer.parseInt(eneboligfelt[ANTBAD].getText());
        int antetg = Integer.parseInt(eneboligfelt[ANTETG].getText());
        int pris = Integer.parseInt(standardfelter[PRIS].getText());

        String beskrivelse = standardfelter[BESKRIVELSE].getText();
        String ledig = standardfelter[LEDIGFRA].getText();
        String bolignr = standardfelter[BOLIGNR].getText();
        String pnr = standardfelter[EIER].getText();
        String adresse = standardfelter[ADRESSE].getText();

        double tomta = Double.parseDouble(eneboligfelt[TOMTA].getText());

        utleier = pregister.get(pnr);

        boolean røyker = eneboligbokser[RØYKER].isSelected();
        boolean husdyr = eneboligbokser[HUSDYR].isSelected();
        boolean balkong = eneboligbokser[BALKONG].isSelected();
        boolean terasse = eneboligbokser[TERASSE].isSelected();
        boolean tv = eneboligbokser[TV].isSelected();
        boolean internet = eneboligbokser[INTERNET].isSelected();
        boolean strøm = eneboligbokser[STRØM].isSelected();
        boolean parkering = eneboligbokser[PARKERING].isSelected();
        boolean kjeller = eneboligbokser[KJELLER].isSelected();

        if(sti == null){
            bildesti = 0;
        }

        Enebolig enebolig = new Enebolig(String.valueOf(bildesti),adresse,boareal,antrom,byggår,beskrivelse,pris,ledig,bolignr,utleier,
                røyker,husdyr,balkong,terasse,tv,internet,strøm,parkering,antetg,kjeller,tomta,antbad,false,false);
        bregister.put(bolignr,enebolig);
        utleier.addBolig(enebolig);

        System.out.println("Registrert");
    }
    public void registrerLeilighet() throws IOException {

        int boareal = Integer.parseInt(standardfelter[BOAREAL].getText());
        int antrom = Integer.parseInt(standardfelter[ANTALLROM].getText());
        int byggår = Integer.parseInt(standardfelter[BYGGÅR].getText());
        int antboder = Integer.parseInt(leilighetfelt[ANTBODER].getText());
        int pris = Integer.parseInt(standardfelter[PRIS].getText());
        int etg = Integer.parseInt(leilighetfelt[ETG].getText());

        String beskrivelse = standardfelter[BESKRIVELSE].getText();
        String ledig = standardfelter[LEDIGFRA].getText();
        String bolignr = standardfelter[BOLIGNR].getText();
        String pnr = standardfelter[EIER].getText();
        String adresse = standardfelter[ADRESSE].getText();


        utleier = pregister.get(pnr);

        boolean røyker = eneboligbokser[RØYKER].isSelected();
        boolean husdyr = eneboligbokser[HUSDYR].isSelected();
        boolean balkong = eneboligbokser[BALKONG].isSelected();
        boolean terasse = eneboligbokser[TERASSE].isSelected();
        boolean tv = eneboligbokser[TV].isSelected();
        boolean internet = eneboligbokser[INTERNET].isSelected();
        boolean strøm = eneboligbokser[STRØM].isSelected();
        boolean parkering = eneboligbokser[PARKERING].isSelected();
        boolean heis = leilighetbokser[HEIS].isSelected();

        if(sti == null){
            bildesti = 0;
        }



        Leilighet leilighet = new Leilighet(String.valueOf(bildesti),adresse,boareal,antrom,byggår,beskrivelse,pris,ledig,bolignr,utleier,
                røyker,husdyr,balkong,terasse,tv,internet,strøm,parkering,antboder,etg,heis,false,false);

        legister.put(bolignr,leilighet);
        utleier.addBolig(leilighet);




    }

    public void lastOppBilde() throws IOException{

        JFileChooser filvelger = new JFileChooser();
        filvelger.setCurrentDirectory( new File( "." ) );
        filvelger.setAcceptAllFileFilterUsed(false);
        filvelger.addChoosableFileFilter(new FileFilter() {

            public String getDescription() {
                return "JPEG";
            }

            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".jpg");
                }
            }
        });

        bildesti = new File("/Users/Erlend/IdeaProjects/Prosjektoppgave/out/production/Prosjektoppgave/Bilder/boligbilder/").listFiles().length;

        filvelger.setCurrentDirectory( new File( "." ) );

        int resultat = filvelger.showOpenDialog( this );

        sti = filvelger.getSelectedFile().getCanonicalPath();

        if(resultat == JFileChooser.APPROVE_OPTION){

            FileInputStream source = new FileInputStream(sti);
            FileOutputStream destination =
                    new FileOutputStream("/Users/Erlend/IdeaProjects/Prosjektoppgave/out/production/Prosjektoppgave/Bilder/boligbilder/" + String.valueOf(bildesti) + ".jpg");

            FileChannel sourceFileChannel = source.getChannel();
            FileChannel destinationFileChannel = destination.getChannel();

            long size = sourceFileChannel.size();
            sourceFileChannel.transferTo(0, size, destinationFileChannel);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == registrer){
            if(boligtype.getSelectedIndex() == TOMT_PANEL){
                JOptionPane.showMessageDialog(null,"Venligst velg boligtype");
                return;
            }else if(boligtype.getSelectedIndex() == ENEBOLIG){
                registrerEnebolig();
                parent.visPanel(MainFrame.MAIN_BOARD);
            }else if(boligtype.getSelectedIndex() == LEILIGHET){
                try{
                    registrerLeilighet();
                }catch (Exception ex){

                }

                parent.visPanel(MainFrame.MAIN_BOARD);
            }
        }else if(e.getSource() == avbryt){
            parent.visPanel(MainFrame.MAIN_BOARD);
        }

        else if(e.getSource() == bilde){

            try
            {
                lastOppBilde();
                knappepanel.removeAll();
                knappepanel.revalidate();
                knappepanel.repaint();
                knappepanel.add(registrer);
                knappepanel.add(avbryt);
            }
            catch (IOException io){

            }
            catch(NullPointerException ne){
                System.out.println("feil i filsti");
            }
        }
    }
}