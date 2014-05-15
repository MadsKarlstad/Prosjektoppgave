/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Panel for å registrere en søker
 * Skrevet av Erlend Westbye. Sist oppdatert 06.05.14
 */
public class RegistrerSokerPANEL extends JPanel implements ActionListener, DocumentListener {
    //Definerer felter,komboboks,sliders,labels og bydeler
    private JTextField[] felt;
    private final String[] feltnavn = {"Fødselsnummer", "Fornavn", "Etternavn", "Adresse","Mail","Telefonnr", "AntallPers", "Sivilstatus", "Yrke",
            "Arbeidsforhold/Studiested"};
    private JCheckBox boligtype;
    private JComboBox<String> beliggenhet;

    private JLabel[] boxlabels;

    private JSlider pris;
    private JSlider minsteareal;
    private JSlider størsteareal;

    private JLabel prislabel;
    private JLabel minareal;
    private JLabel maksareal;

    private JLabel sliderstate_pris;
    private JLabel sliderstate_minareal;
    private JLabel sliderstate_maksareal;

    private JLabel overskrift;
    private JLabel endrelabel;



    String [] bydeler = { "Velg bydel", "Alna", "Bjerke", "Frogner", "Gamle Oslo", "Grorud",
            "Grünerløkka", "Nordre Aker", "Nordstrand", "Sagene", "St. Hanshaugen",
            "Stovner", "Søndre Nordstrand", "Ullern", "Vestre Aker", "Østensjø"};

    private final int FØDSELSNUMMER = 0;
    private final int FORNAVN = 1;
    private final int ETTERNAVN = 2;
    private final int ADRESSE = 3;
    private final int MAIL = 4;
    private final int TELEFONNUMMER = 5;
    private final int ANTPERS = 6;
    private final int SIVILSTATUS = 7;
    private final int YRKE = 8;
    private final int ARBFORHOLD = 9;

    private JPanel feltpanel;
    private JPanel bokspanel;
    private JPanel knapppanel;
    private JPanel toppanel;
    private JPanel sliderpanel;

    private JPanel overskriftpanel;

    private JCheckBox[] bokser;
    private final String[]boksnavn = {"Røyke","Husdyr","Balkong","Terasse","Tv","Internett","Strøm","Parkering","Kjeller","Heis"};

    private final int RØYKER = 0;
    private final int DYR = 1;
    private final int BALKONG = 2;
    private final int TERASSE = 3;
    private final int TV = 4;
    private final int NETT = 5;
    private final int STRØM = 6;
    private final int PARKERING = 7;
    private final int KJELLER = 8;
    private final int HEIS = 9;

    private JButton registrer;
    private JButton avbryt;

    private Sokerregister register;
    private Boligregister eneboligregister;
    private Leilighetregister leilighetregister;
    private MainFrame parent;

    private String fødselnummer;
    private String fornavn;
    private String etternavn;
    private String adresse;
    private String mail;
    private String telefonnummer ;
    private String antpersoner; ;
    private String sivistatus ;
    private String arbeidforhold ;
    private String yrke;

    private int ønsket_pris;
    private int ønsket_areal_min;
    private int ønsket_areal_maks;


    public RegistrerSokerPANEL(Sokerregister register,Boligregister eneboligregister,Leilighetregister leilighetregister, MainFrame parent) {
        super(new BorderLayout());
        this.register = register;
        this.eneboligregister = eneboligregister;
        this.leilighetregister = leilighetregister;
        this.parent = parent;
        initialiser();
        lagGUI();
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;
         
        parent.setSize(bredde/2, høyde-175);
        parent.setLocation(skjerm.width/2-parent.getSize().width/2, skjerm.height/2-parent.getSize().height/2);
    }
    
    //Initialiserer feltene,panelene,bokser,labels osv
    public void initialiser() {

        setLayout(new BorderLayout());

        feltpanel = new JPanel(new GridLayout(7, 2, 5, 5));
        knapppanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bokspanel = new JPanel(new GridLayout(2,10));
        toppanel = new JPanel(new BorderLayout());
        sliderpanel = new JPanel(new GridLayout(4,3,5,5));
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        felt = new JTextField[feltnavn.length];
        bokser = new JCheckBox[boksnavn.length];

        boxlabels = new JLabel[boksnavn.length];


        TekstFyller tp [] = new TekstFyller[felt.length];

        pris = new JSlider(JSlider.HORIZONTAL,0,20000,0);
        prislabel = new JLabel("Pris");
        minsteareal = new JSlider(JSlider.HORIZONTAL,0,600,0);
        minareal = new JLabel("Minste areal");
        størsteareal = new JSlider(JSlider.HORIZONTAL,0,600,0);
        maksareal = new JLabel("Maks areal");


        overskrift = new JLabel("Registrer søker");
        endrelabel = new JLabel("Endring av søker");

        pris.setMajorTickSpacing(pris.getMaximum()/40);
        minsteareal.setMajorTickSpacing((minsteareal.getMaximum()/100)-1);
        størsteareal.setMajorTickSpacing((størsteareal.getMaximum()/100)-1);

        pris.setSnapToTicks(true);
        minsteareal.setSnapToTicks(true);
        størsteareal.setSnapToTicks(true);

        sliderstate_pris = new JLabel("ønsket:         0");
        sliderstate_minareal = new JLabel("ønsket:         0");
        sliderstate_maksareal = new JLabel("ønsket:         0");





        for (int i = 0; i < feltnavn.length; i++) {
            felt[i] = new JTextField(10);
            tp[i] = new TekstFyller(feltnavn[i], felt[i]);
            tp[i].changeAlpha(0.7f);

            //felt[i].setHorizontalAlignment(JTextField.CENTER);
        }

        for (int i = 0; i < boksnavn.length; i++) {
            bokser[i] = new JCheckBox();
            boxlabels[i] = new JLabel(boksnavn[i]);
            bokser[i].setHorizontalAlignment(JCheckBox.CENTER);
        }

        beliggenhet = new JComboBox<String>(bydeler);
        registrer = new JButton("Registrer");
        avbryt = new JButton("Avbryt");

        registrer.addActionListener(this);
        avbryt.addActionListener(this);

        event e = new event();
        pris.addChangeListener(e);
        minsteareal.addChangeListener(e);
        størsteareal.addChangeListener(e);

        setBorder(BorderFactory.createEmptyBorder(10/*top*/, 10/*left*/, 10/*bottom*/, 10/*right*/));
        overskriftpanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));


    }

    //Oppretter brukergrensesnittet
    public void lagGUI() {

        for (int i = 0; i < felt.length; i++) {
            feltpanel.add(felt[i]);
        }
        for (int i = 0; i < boksnavn.length; i++) {
            bokspanel.add(boxlabels[i]);
            bokspanel.add(bokser[i]);
        }

        knapppanel.add(registrer);
        knapppanel.add(avbryt);

        sliderpanel.add(prislabel);
        sliderpanel.add(pris);
        sliderpanel.add(sliderstate_pris);
        sliderpanel.add(minareal);
        sliderpanel.add(minsteareal);
        sliderpanel.add(sliderstate_minareal);
        sliderpanel.add(maksareal);
        sliderpanel.add(størsteareal);
        sliderpanel.add(sliderstate_maksareal);

        overskriftpanel.add(overskrift);


        toppanel.add(overskriftpanel, BorderLayout.PAGE_START);
        toppanel.add(feltpanel, BorderLayout.CENTER);
        toppanel.add(sliderpanel,BorderLayout.PAGE_END);

        add(toppanel, BorderLayout.PAGE_START);
        add(bokspanel,BorderLayout.CENTER);
        add(knapppanel, BorderLayout.PAGE_END);

        feltpanel.setBackground(Color.decode("#B3D5E3"));
        toppanel.setBackground(Color.decode("#B3D5E3"));
        knapppanel.setBackground(Color.decode("#B3D5E3"));
        overskriftpanel.setBackground(Color.decode("#B3D5E3"));
        bokspanel.setBackground(Color.decode("#B3D5E3"));
        sliderpanel.setBackground(Color.decode("#B3D5E3"));

        setBackground(Color.decode("#B3D5E3"));
        setBorder(BorderFactory.createEmptyBorder(10/*top*/, 10/*left*/, 10/*bottom*/, 10/*right*/));

        overskriftpanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        feltpanel.setBorder(BorderFactory.createEmptyBorder(20/*top*/, 0/*left*/, 0/*bottom*/, 0/*right*/));
    }


    //Metode for å registrere en søker. Henter informasjon fra hva brukeren har skrvet inn i feltene og check-boksene
    public void registrer () {
        String fødselsnummer = felt[FØDSELSNUMMER].getText();
        String fornavn = felt[FORNAVN].getText();
        String etternavn = felt[ETTERNAVN].getText();
        String adresse = felt[ADRESSE].getText();
        String mail = felt[MAIL].getText();
        String telefonnummer = felt[TELEFONNUMMER].getText();
        String antpers = felt[ANTPERS].getText();
        String sivilstatus = felt[SIVILSTATUS].getText();
        String yrke = felt[YRKE].getText();
        String arbeidsforhold = felt[ARBFORHOLD].getText();

        int minareal = minsteareal.getValue();
        int maxareal = størsteareal.getValue();
        int ønsketpris = pris.getValue();


            boolean røyke = bokser[RØYKER].isSelected();
            boolean dyr = bokser[DYR].isSelected();
            boolean balk = bokser[BALKONG].isSelected();
            boolean ter = bokser[TERASSE].isSelected();
            boolean tv = bokser[TV].isSelected();
            boolean nett = bokser[NETT].isSelected();
            boolean strøm = bokser[STRØM].isSelected();
            boolean parkering = bokser[PARKERING].isSelected();
            boolean kjeller = bokser[KJELLER].isSelected();
            boolean heis = bokser[HEIS].isSelected();


        if (fødselsnummer.length() != 0 || fornavn.length() != 0 || etternavn.length() != 0 || adresse.length() != 0 || mail.length() != 0 || telefonnummer.length() != 0) {
            Person søker = new Soker(fødselsnummer, fornavn, etternavn, adresse, mail, telefonnummer, antpers, sivilstatus, yrke,
                    arbeidsforhold, minareal, maxareal, ønsketpris, røyke, dyr, balk, ter, tv, nett, strøm, parkering, kjeller, heis);

            if (register.leggTil(søker)) {
                //gå tilbake til mainframe
                parent.visPanel(MainFrame.MAIN_BOARD);

            } /*else if (!register.leggTil(søker)) {
                visMelding("Feil informasjon ble utfylt, venligst prøv igjen");
            }*/
        } else {
            visMelding("Vennligst fyll ut all informasjon");
        }
    }

    //Metode for å vise pop-up-meldingene i programmet
    public void visMelding(String melding){
        JOptionPane.showMessageDialog(null,melding);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == registrer){

            try{
                registrer();

            }
            catch (NumberFormatException nfe){
                visMelding("Vennligst fyll inn tall ved feltene for areal og pris");
            }
            parent.Size();
        }
        else if(e.getSource() == avbryt){
            parent.visPanel(MainFrame.MAIN_BOARD);
            parent.Size();
        }
    }

    //Metode for å endre informasjon hos et søkerobjekt. man sendes hit via SøkerOversiktPANEL
    public void endreSoker(Soker soker){

        feltpanel.removeAll();
        feltpanel.revalidate();
        feltpanel.repaint();

        knapppanel.removeAll();

        overskriftpanel.removeAll();

        overskriftpanel.add(endrelabel);

        for (int i = 0; i < feltnavn.length; i++) {
            felt[i] = new JTextField(10);
            feltpanel.add(felt[i]);
            felt[i].getDocument().addDocumentListener(this);

        }



        felt[FØDSELSNUMMER].setEditable(false);

        felt[FØDSELSNUMMER].setText(soker.getFødselsnummer());
        felt[FORNAVN].setText(soker.getFornavn());
        felt[ETTERNAVN].setText(soker.getEtternavn());
        felt[ADRESSE].setText(soker.getAdresse());
        felt[MAIL].setText(soker.getMail());
        felt[TELEFONNUMMER].setText(soker.getTelefonnummer());
        felt[SIVILSTATUS].setText(soker.getSivilstatus());
        felt[YRKE].setText(soker.getYrke());
        felt[ARBFORHOLD].setText(soker.getArbeidsfohold_studiested());
        felt[ANTPERS].setText(soker.getAntallPersoner());

        bokser[RØYKER].setSelected(soker.isRøyk());
        bokser[DYR].setSelected(soker.isHusdyr());
        bokser[BALKONG].setSelected(soker.isBalkong());
        bokser[TERASSE].setSelected(soker.isTerasse());
        bokser[TV].setSelected(soker.isTVinkludert());
        bokser[NETT].setSelected(soker.isInternetinkludert());
        bokser[STRØM].setSelected(soker.isStrøminkludert());
        bokser[PARKERING].setSelected(soker.isParkering());
        bokser[HEIS].setSelected(soker.isHeis());
        bokser[KJELLER].setSelected(soker.isKjeller());




        toppanel.add(overskriftpanel, BorderLayout.PAGE_START);
        toppanel.add(feltpanel, BorderLayout.CENTER);
        toppanel.add(sliderpanel, BorderLayout.PAGE_END);

        pris.setValue(soker.getPris());
        minsteareal.setValue(soker.getMinAreal());
        størsteareal.setValue(soker.getMaksAreal());



    }

    public void setFødselsnummer(String s){fødselnummer = s;}
    public void setFornavn(String s){fornavn = s;}
    public void setETTERNAVN(String s){etternavn = s;}
    public void setADRESSE(String s){adresse = s;}
    public void setMAIL(String s){mail = s;}
    public void setTELEFONUMMER(String s){telefonnummer = s;}
    public void setANTPERS(String s){antpersoner = s;}
    public void setSIVILSTATUS(String s){sivistatus = s;}
    public void setYRKE(String s){yrke = s;}
    public void setARBFORHOLD(String s){arbeidforhold = s;}

    public void setPris(int p ){ønsket_pris = p;}
    public void setØnsket_areal_min(int p ){ønsket_areal_min = p;}
    public void setØnsket_areal_maks(int p ){ønsket_areal_maks = p;}

    public boolean getRøyke(){return bokser[RØYKER].isSelected();}
    public boolean getHusdyr(){return bokser[DYR].isSelected();}
    public boolean getBalkong(){return bokser[BALKONG].isSelected();}
    public boolean getTerasse(){return bokser[TERASSE].isSelected();}
    public boolean getTv(){return bokser[TV].isSelected();}
    public boolean getInternett(){return bokser[NETT].isSelected();}
    public boolean getStrøm(){return bokser[STRØM].isSelected();}
    public boolean getParkering(){return bokser[PARKERING].isSelected();}
    public boolean getKjeller(){return bokser[KJELLER].isSelected();}
    public boolean getHeis(){return bokser[HEIS].isSelected();}

    public String getFødselnummer(){return fødselnummer;}
    public String getFornavn(){return fornavn;}
    public String getEtternavn(){return etternavn;}
    public String getAdresse(){return adresse;}
    public String getMail(){return mail;}
    public String getTelefonnummer(){return telefonnummer;}
    public String getANTPERS(){return antpersoner;}
    public String getSIVILSTATUS(){return sivistatus;}
    public String getYRKE(){return yrke;}
    public String getARBFORHOLD(){return arbeidforhold;}
    public int getPris(){return ønsket_pris;}
    public int getØnsket_areal_min(){return ønsket_areal_min;}
    public int getØnsket_areal_maks(){return ønsket_areal_maks;}

    //Oppdaterer informasjonen til det som nå er utfylt av brukeren
    public void oppdaterinfo(){

        setFødselsnummer(felt[FØDSELSNUMMER].getText());
        setFornavn(felt[FORNAVN].getText());
        setETTERNAVN(felt[ETTERNAVN].getText());
        setADRESSE(felt[ADRESSE].getText());
        setMAIL(felt[MAIL].getText());
        setTELEFONUMMER(felt[TELEFONNUMMER].getText());
        setANTPERS(felt[ANTPERS].getText());
        setSIVILSTATUS(felt[SIVILSTATUS].getText());
        setYRKE(felt[YRKE].getText());
        setARBFORHOLD(felt[ARBFORHOLD].getText());

        setØnsket_areal_min(minsteareal.getValue());
        setØnsket_areal_maks(størsteareal.getValue());
        setPris(pris.getValue());



    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        oppdaterinfo();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        oppdaterinfo();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        oppdaterinfo();
    }


    public class event implements ChangeListener{


        @Override
        public void stateChanged(ChangeEvent e) {
            if(e.getSource() == pris){sliderstate_pris.setText("ønsket:         " + String.valueOf(pris.getValue())); setPris(pris.getValue());}
            else if(e.getSource() == minsteareal){ sliderstate_minareal.setText("ønsket:         " + String.valueOf(minsteareal.getValue()));setØnsket_areal_min(minsteareal.getValue());}
            else if(e.getSource() == størsteareal){ sliderstate_maksareal.setText("ønsket:         " + String.valueOf(størsteareal.getValue()));setØnsket_areal_maks(størsteareal.getValue());}

        }

    }
}
