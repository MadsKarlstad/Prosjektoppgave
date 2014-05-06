/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import javax.swing.*;
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
            "Arbeidsforhold/Studiested","MinAreal","MaxAreal","MinPris","MaxPris"};
    private JCheckBox boligtype;
    private JComboBox<String> beliggenhet;

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
    private final int MINAREAL = 10;
    private final int MAXAREAL = 11;
    private final int MINPRIS = 12;
    private final int MAXPRIS = 13;

    private JPanel feltpanel;
    private JPanel bokspanel;
    private JPanel knapppanel;

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
         
        parent.setSize(bredde/2, høyde/2);
        parent.setLocation(skjerm.width/2-parent.getSize().width/2, skjerm.height/2-parent.getSize().height/2);
    }

    public void initialiser() {

        setLayout(new BorderLayout());

        feltpanel = new JPanel(new GridLayout(7, 2, 5, 5));
        knapppanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bokspanel = new JPanel(new GridLayout(2,5));

        felt = new JTextField[feltnavn.length];
        bokser = new JCheckBox[boksnavn.length];


        TextPrompt tp [] = new TextPrompt[felt.length];




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

        add(feltpanel, BorderLayout.PAGE_START);
        add(bokspanel,BorderLayout.CENTER);
        add(knapppanel, BorderLayout.PAGE_END);
    }

    public void registrer(){
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

        int minareal = Integer.parseInt(felt[MINAREAL].getText());
        int maxareal = Integer.parseInt(felt[MAXAREAL].getText());
        int minpris = Integer.parseInt(felt[MINPRIS].getText());
        int maxpris = Integer.parseInt(felt[MAXPRIS].getText());

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

        Person søker = new Soker(fødselsnummer,fornavn,etternavn,adresse, mail, telefonnummer, antpers, sivilstatus,yrke,
                arbeidsforhold,minareal,maxareal,minpris,maxpris,røyke,dyr,balk,ter,tv,nett,strøm,parkering,kjeller,heis,eneboligregister,leilighetregister);

        if(register.leggTil(søker)){
            parent.skrivTilFil(søker);
            //gå tilbake til mainframe
            return;
        }
    }

    public static void copyFile( File from, File to ) throws IOException {
        Files.copy( from.toPath(), to.toPath() );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == registrer){

            registrer();
            parent.visPanel(MainFrame.MAIN_BOARD);
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
}
