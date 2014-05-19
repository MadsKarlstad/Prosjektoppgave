/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

/**
 * Panel for å registrere boliger. Med valgmulighet mellom de to boligtypene
 * Skrevet av Erlend Westbye, Christoffer Jønsberg og Mads Karlstad. Sist oppdatert 10.05.14
 */
public class RegistrerBoligPANEL extends JPanel implements ActionListener {


    private int bildesti;
    private boolean bildeOk;


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
    private final String[]boksnavn = {"Røyke","Husdyr","Balkong","Terasse","Tv","Internett","Strøm","Parkering","Kjeller","Heis"};
    private final int minusHeis = 1;

    //combobokser
    private JComboBox<String> beliggenhet;
    private JComboBox<String> boligtype;

    private JLabel[] boxlabels;

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

    private final int VELG = 0;
    private final int ALNA = 1;
    private final int BJERKE = 2;
    private final int FROGNER = 3;
    private final int GAMLE_OSLO = 4;
    private final int GRORUD = 5;
    private final int LØKKA = 6;
    private final int NORDRE_AKER = 7;
    private final int NORDSTRAND = 8;
    private final int SAGENE = 9;
    private final int STHANSHAUGEN = 10;
    private final int STOVNER = 11;
    private final int SØNDRE_NORDSTRAND = 12;
    private final int ULLERN = 13;
    private final int VESTRE_AKER = 14;
    private final int ØSTENSJØ = 15;

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

    private String sti;
    
    private  String path;

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

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    }
    //Initialiserer utskriftsområdet,feltene,boksene,labels,paneler,tekstfylleren osv
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

        boxlabels = new JLabel[boksnavn.length];

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
        bokspanel = new JPanel(new GridLayout(3,3));

        //leilighetunikt
        leilighetpanel = new JPanel(new BorderLayout());
        leilighetpanel.setLayout(new BorderLayout());
        feltpanelLeilighet = new JPanel(new GridLayout(1,2,5,5));
        bokspanelLeilighet = new JPanel(new GridLayout(5,2,5,5));

        TekstFyller tp [] = new TekstFyller[standardfelter.length];
        TekstFyller tp1 [] = new TekstFyller[eneboligfelt.length];
        TekstFyller tp2 [] = new TekstFyller[leilighetfelt.length];

        bildeOk = false;




        for (int i = 0; i < feltnavn.length; i++) {
            standardfelter[i] = new JTextField(10);
            tp[i] = new TekstFyller(feltnavn[i], standardfelter[i]);
            tp[i].changeAlpha(0.7f);
        }
        for (int i = 0; i < eneboligfeltnavn.length; i++) {
            eneboligfelt[i] = new JTextField(10);
            tp1[i] = new TekstFyller(eneboligfeltnavn[i],eneboligfelt[i]);
            tp1[i].changeAlpha(0.7f);
            feltpanelEnebolig.add(eneboligfelt[i]);
        }

        for (int i = 0; i < leilighetfeltnavn.length; i++) {
            leilighetfelt[i] = new JTextField(10);
            tp2[i] = new TekstFyller(leilighetfeltnavn[i],leilighetfelt[i]);
            tp2[i].changeAlpha(0.7f);
            feltpanelLeilighet.add(leilighetfelt[i]);

        }

        for (int i = 0; i < boksnavn.length-minusHeis; i++) {
            eneboligbokser[i] = new JCheckBox();
            boxlabels[i] = new JLabel(boksnavn[i]);

        }
        for (int i = 0; i < boksnavn.length; i++) {
            leilighetbokser[i] = new JCheckBox();
            boxlabels[i] = new JLabel((boksnavn[i]));

        }
        
        try{
            initialiserCurrentDirectory();}
        catch(Exception x){
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

        bildesti = 0;


    }
    //Oppretter brukergrensesnittet
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

        knappepanel.setBackground(Color.decode("#B3D5E3"));
        overskriftpanel.setBackground(Color.decode("#B3D5E3"));
        //tabellpanel.setBackground(Color.decode("#DAEDF5"));
        boligtypepanel.setBackground(Color.decode("#B3D5E3"));
        bokspanelLeilighet.setBackground(Color.decode("#B3D5E3"));
        feltpanel.setBackground(Color.decode("#B3D5E3"));
        feltpanelEnebolig.setBackground(Color.decode("#B3D5E3"));
        feltpanelLeilighet.setBackground(Color.decode("#B3D5E3"));
        bokspanel.setBackground(Color.decode("#B3D5E3"));
        bydelpanel.setBackground(Color.decode("#B3D5E3"));
        bunnpanel.setBackground(Color.decode("#B3D5E3"));
        midtpanel.setBackground(Color.decode("#B3D5E3"));

        setBackground(Color.decode("#B3D5E3"));


    }
        //Metode som viser panel for når brukeren ikke har spesifisert hvilken boligtype som skal registreres
    public void visTomtpanel(){
        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();
        tomtpanel.add(utskriftsområde);
        isTom=true;
        isEnebolig=false;
        isLeilighet=false;

        midtpanel.setBackground(Color.decode("#B3D5E3"));
    }
        //Metode som viser panel for når brukeren har valgt å registrere en enebolig
    public void visEneboligpanel(){


        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();
        midtpanel.add(enebolig);
        midtpanel.add(bydelpanel, BorderLayout.PAGE_END);

        for (int i = 0; i < boksnavn.length-minusHeis; i++) {
            bokspanel.add(eneboligbokser[i]);
            bokspanel.add(boxlabels[i]);
        }

        eneboligpanel.add(bokspanel, BorderLayout.CENTER);
        eneboligpanel.add(feltpanelEnebolig,BorderLayout.PAGE_END);
        midtpanel.add(eneboligpanel, BorderLayout.CENTER);
        isEnebolig=true;
        isTom=false;
        isLeilighet=false;



    }
        //Metode som viser panel for når brukeren har spesifisert at det er leilighet som skal registreres
    public void visLeilighetpanel(){
        remove(midtpanel);
        revalidate();
        repaint();
        midtpanel = new JPanel(new BorderLayout());
        midtpanel.add(leilighet);
        midtpanel.add(bydelpanel, BorderLayout.PAGE_END);

        for (int i = 0; i < boksnavn.length; i++) {
            bokspanelLeilighet.add(leilighetbokser[i]);
            bokspanelLeilighet.add(boxlabels[i]);
        }
        leilighetpanel.add(bokspanelLeilighet,BorderLayout.CENTER);
        leilighetpanel.add(feltpanelLeilighet,BorderLayout.PAGE_END);
        midtpanel.add(leilighetpanel,BorderLayout.CENTER);
        isLeilighet=true;
        isTom=false;
        isEnebolig=false;
        add(midtpanel, BorderLayout.CENTER);

    }
        //Metode for å registrere en enebolig, henter informasjon fra de utfylte feltene og boksene
    public void registrerEnebolig() throws IOException{

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

        String bydel = beliggenhet.getItemAt(beliggenhet.getSelectedIndex());

        if(!bildeOk){
            bildesti = 0;
            
        }

        if(bolignr.length()!=0||pnr.length()!=0||adresse.length()!=0||beskrivelse.length()!=0||ledig.length()!=0){
            if(pregister.finnes(pnr)){
                if(!bregister.finnes(bolignr)){
                    Enebolig enebolig = new Enebolig(path + String.valueOf(bildesti) + ".jpg",adresse,boareal,antrom,byggår,beskrivelse,pris,ledig,bolignr,utleier,
                            røyker,husdyr,balkong,terasse,tv,internet,strøm,parkering,antetg,kjeller,tomta,antbad,false,false,bydel);

                    bregister.put(bolignr, enebolig);

                    utleier.addBolig(enebolig);
                    

                   try{
                        kopierbilde();}
                    catch (Exception e){

                    }

                    parent.visPanel(MainFrame.MAIN_BOARD);
                    parent.Size();
                }
                else if(bregister.finnes(bolignr)){
                    visMelding("En bolig med dette bolignummeret er allerede registrert i våre systemer");
                }
            }
            else if(!pregister.finnes(pnr)){
                visMelding("Ingen utleier funnet med dette personnummeret er allerede registrert i våre systemer");
            }

        }
        else{
            visMelding("Vennligst fyll inn all informasjon");
        }

    }
        //Metode for å registrere leilighet, henter informasjon fra feltene og boksene
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

        String bydel = beliggenhet.getItemAt(beliggenhet.getSelectedIndex());

        if(!bildeOk){
            bildesti = 0;
        }

        if(bolignr.length()!=0||pnr.length()!=0||adresse.length()!=0||beskrivelse.length()!=0||ledig.length()!=0){
            if(pregister.finnes(pnr)){
                if(!bregister.finnes(bolignr)){
                    Leilighet leilighet = new Leilighet(path + String.valueOf(bildesti) + ".jpg",adresse,boareal,antrom,byggår,beskrivelse,pris,ledig,bolignr,utleier,
                            røyker,husdyr,balkong,terasse,tv,internet,strøm,parkering,antboder,etg,heis,false,false,bydel);

                    legister.put(bolignr, leilighet);
                    utleier.addBolig(leilighet);

                   try{
                        kopierbilde();}
                    catch (Exception e){
                    }

                    parent.visPanel(MainFrame.MAIN_BOARD);
                    parent.Size();
                }
                else if(bregister.finnes(bolignr)){
                    visMelding("En bolig med dette bolignummeret er allerede registrert i våre systemer");
                }
            }
            else if(!pregister.finnes(pnr)){
                visMelding("Ingen utleier funnet med dette personnummeret er allerede registrert i våre systemer");
            }

        }
        else{
            visMelding("Venligst fyll inn all informasjon");
        }
    }
        //Metode som kopierer valgt boligbilde til brukerens mappestruktur
        public void initialiserCurrentDirectory() throws Exception{
    
            File f = new File(RegistrerBoligPANEL.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        
            path = URLDecoder.decode(f.getParent()+"/boligbilder/", "UTF-8");
    
    }
        //Metode som lar brukeren laste opp et boligbilde for boligen
        public void lastOppBilde() throws IOException{

        JFileChooser filvelger = new JFileChooser("app");
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
            
            JOptionPane.showMessageDialog(null,path);

        

        bildesti = new File(path).listFiles().length-1;


        filvelger.setCurrentDirectory(new File(System.getProperty("user.home")));

        int resultat = filvelger.showOpenDialog( this );

        sti = filvelger.getSelectedFile().getCanonicalPath();

        if(resultat == JFileChooser.APPROVE_OPTION){

            bildeOk = true;

        }
    }

    public void kopierbilde() throws Exception{

        FileInputStream source = new FileInputStream(sti);
        FileOutputStream destination =
                new FileOutputStream(path + String.valueOf(bildesti) + ".jpg");

        FileChannel sourceFileChannel = source.getChannel();
        FileChannel destinationFileChannel = destination.getChannel();

        long size = sourceFileChannel.size();
        sourceFileChannel.transferTo(0, size, destinationFileChannel);
        
        destinationFileChannel.close();
        sourceFileChannel.close();
        
        source.close();
        destination.close();

    }
    //Metode for å vise pop.up-meldinger i programmet
    public void visMelding(String melding){
        JOptionPane.showMessageDialog(null,melding);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == registrer){
            if(boligtype.getSelectedIndex() == TOMT_PANEL){
                JOptionPane.showMessageDialog(null,"Venligst velg boligtype");
                return;
            }else if(boligtype.getSelectedIndex() == ENEBOLIG){
                try{
                    registrerEnebolig();
                }catch (NullPointerException npe){

                }
                catch(IOException ioe){

                }
                catch (NumberFormatException nfe){
                    JOptionPane.showMessageDialog(null,"Venligst fyll inn tall for feltene som krever det");
                }


            }else if(boligtype.getSelectedIndex() == LEILIGHET){
                try{
                    registrerLeilighet();
                }catch (NullPointerException npe){

                }
                catch(IOException ioe){

                }
                catch (NumberFormatException nfe){
                    JOptionPane.showMessageDialog(null,"Venligst fyll inn tall for feltene som krever det");
                }


            }
        }else if(e.getSource() == avbryt){
            parent.visPanel(MainFrame.MAIN_BOARD);
            parent.Size();
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

            }
        }
    }
}