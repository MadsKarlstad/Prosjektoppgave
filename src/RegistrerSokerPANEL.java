/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Erlend on 22/04/14.
 */
public class RegistrerSokerPANEL extends JPanel implements ActionListener {
    private JTextField[] felt;
    private final String[] feltnavn = {"Fødselsnummer", "Fornavn", "Etternavn", "Adresse","Mail","Telefonnr", "AntallPers", "Sivilstatus", "Yrke",
            "Arbeidsforhold/Studiested"};
    private JCheckBox boligtype;
    private JComboBox<String> beliggenhet;

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
    private final String[]boksnavn = {"røyker","husdyr","balkong","terasse","TVinkludert","internetInkludert","strømInkludert","parkering","kjeller","heis"};

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

    public void initialiser() {

        setLayout(new BorderLayout());

        feltpanel = new JPanel(new GridLayout(7, 2, 5, 5));
        knapppanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bokspanel = new JPanel(new GridLayout(2,5));
        toppanel = new JPanel(new BorderLayout());
        sliderpanel = new JPanel(new GridLayout(4,3,5,5));
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        felt = new JTextField[feltnavn.length];
        bokser = new JCheckBox[boksnavn.length];


        TextPrompt tp [] = new TextPrompt[felt.length];

        pris = new JSlider(JSlider.HORIZONTAL,0,20000,0);
        prislabel = new JLabel("pris");
        minsteareal = new JSlider(JSlider.HORIZONTAL,0,600,0);
        minareal = new JLabel("Minste areal");
        størsteareal = new JSlider(JSlider.HORIZONTAL,0,600,0);
        maksareal = new JLabel("Maks areal");


        overskrift = new JLabel("Registrer søker");

        pris.setMajorTickSpacing(pris.getMaximum()/40);
        minsteareal.setMajorTickSpacing((minsteareal.getMaximum()/100)-1);
        størsteareal.setMajorTickSpacing((størsteareal.getMaximum()/100)-1);

        pris.setSnapToTicks(true);
        minsteareal.setSnapToTicks(true);
        størsteareal.setSnapToTicks(true);

        sliderstate_pris = new JLabel(": 0");
        sliderstate_minareal = new JLabel(": 0");
        sliderstate_maksareal = new JLabel(": 0");





        for (int i = 0; i < feltnavn.length; i++) {
            felt[i] = new JTextField(10);
            tp[i] = new TextPrompt(feltnavn[i], felt[i]);
            tp[i].changeAlpha(0.7f);

            //felt[i].setHorizontalAlignment(JTextField.CENTER);
        }

        for (int i = 0; i < boksnavn.length; i++) {
            bokser[i] = new JCheckBox();
            bokser[i].setText(boksnavn[i]);
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

    public void lagGUI() {

        for (int i = 0; i < felt.length; i++) {
            feltpanel.add(felt[i]);
        }
        for (int i = 0; i < boksnavn.length; i++) {
            bokspanel.add(bokser[i]);
        }

        knapppanel.add(registrer);
        knapppanel.add(avbryt);

        sliderpanel.add(prislabel);
        sliderpanel.add(sliderstate_pris);
        sliderpanel.add(pris);
        sliderpanel.add(minareal);
        sliderpanel.add(sliderstate_minareal);
        sliderpanel.add(minsteareal);
        sliderpanel.add(maksareal);
        sliderpanel.add(sliderstate_maksareal);
        sliderpanel.add(størsteareal);

        overskriftpanel.add(overskrift);


        toppanel.add(overskriftpanel, BorderLayout.PAGE_START);
        toppanel.add(feltpanel, BorderLayout.CENTER);
        toppanel.add(sliderpanel,BorderLayout.PAGE_END);

        add(toppanel, BorderLayout.PAGE_START);
        add(bokspanel,BorderLayout.CENTER);
        add(knapppanel, BorderLayout.PAGE_END);

        feltpanel.setBackground(Color.decode("#B3D5E3"));
        toppanel.setBackground(Color.decode("#B3D5E3"));
        knapppanel.setBackground(Color.decode("#DAEDF5"));
        overskriftpanel.setBackground(Color.decode("#DAEDF5"));
        bokspanel.setBackground(Color.decode("#B3D5E3"));
        sliderpanel.setBackground(Color.decode("#B3D5E3"));

        setBackground(Color.decode("#B3D5E3"));
        setBorder(BorderFactory.createEmptyBorder(10/*top*/, 10/*left*/, 10/*bottom*/, 10/*right*/));

        overskriftpanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        feltpanel.setBorder(BorderFactory.createEmptyBorder(20/*top*/, 0/*left*/, 0/*bottom*/, 0/*right*/));
    }

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

        int minareal = Integer.parseInt(sliderstate_pris.getText());
        int maxareal = Integer.parseInt(sliderstate_minareal.getText());
        int ønsketpris = Integer.parseInt(sliderstate_maksareal.getText());


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
                    arbeidsforhold, minareal, maxareal, ønsketpris, røyke, dyr, balk, ter, tv, nett, strøm, parkering, kjeller, heis, eneboligregister, leilighetregister);

            if (register.leggTil(søker)) {
                //gå tilbake til mainframe
                parent.visPanel(MainFrame.MAIN_BOARD);

            } else if (!register.leggTil(søker)) {
                visMelding("Feil informasjon ble utfylt, venligst prøv igjen");
            }
        } else {
            visMelding("Vennligst fyll ut all informasjon");
        }
    }


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
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension skjerm = kit.getScreenSize();
            int bredde = skjerm.width;
            int høyde = skjerm.height;

            parent.setSize(bredde/2, høyde-100);
            parent.setLocation(skjerm.width / 2 - parent.getSize().width / 2, skjerm.height / 2 - parent.getSize().height / 2);
        }
        else if(e.getSource() == avbryt){
            parent.visPanel(MainFrame.MAIN_BOARD);
            
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension skjerm = kit.getScreenSize();
            int bredde = skjerm.width;
            int høyde = skjerm.height;
        
            parent.setSize(bredde/2, høyde-100);
            parent.setLocation(skjerm.width/2-parent.getSize().width/2, skjerm.height/2-parent.getSize().height/2);
        }
    }


    public class event implements ChangeListener{


        @Override
        public void stateChanged(ChangeEvent e) {
            if(e.getSource() == pris){sliderstate_pris.setText(": " + String.valueOf(pris.getValue()));}
            else if(e.getSource() == minsteareal){ sliderstate_minareal.setText(": " + String.valueOf(minsteareal.getValue()));}
            else if(e.getSource() == størsteareal){ sliderstate_maksareal.setText(": " + String.valueOf(størsteareal.getValue()));}

        }

    }
}
