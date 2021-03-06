/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Vindu som fungerer som hovedvindu for brukergrensesnittet
 * Her vil man kunne velge de resterende ni underpanelene som til sammen utgjør programmet BoligBrowse
 * Skrevet av Erlend Westbye og Christoffer Jønsberg. Sist oppdatert 10.05.14
 */
public class MainFrame extends JFrame implements ActionListener {

    private JPanel mainboard;

    private JPanel vinduer;

    private JButton visLeilighet;
    private JButton visEnebolig;
    private JButton visUtleierBrowse;
    private JButton visLeietakerBrowse;

    private final int UTLEIER = 0;
    private final int SOKER = 1;
    private final int ENEBOLIG = 2;
    private final int LEILIGHET = 3;
    private final int KONTRAKT = 4;

    public static final String MAIN_BOARD = "-1";

    private JButton regUt, visUt, regSøk, visSøk, regBo, visBo, visKon, stat, boligBrowse;
    private Icon regUtIkon, visUtIkon, regSøkIkon, visSøkIkon, regBoIkon,
            visBoIkon, visKonIkon, statIkon, boligBrowseIkon;

    private Personregister register;
    private Boligregister bregister;
    private Sokerregister sregister;
    private Leilighetregister legister;
    private Kontraktregister kregister;

    public MainFrame(Personregister register, Boligregister bregister, Sokerregister sregister, Leilighetregister legister, Kontraktregister kregister) {
        super("Bolig Browse™");
        setLayout(new BorderLayout());

        this.register = register;
        this.sregister = sregister;
        this.bregister = bregister;
        this.legister = legister;
        this.kregister = kregister;

        initialiser();

        vinduer = new JPanel(new CardLayout());
        vinduer.add(mainboard, MAIN_BOARD);
        mainboard.setBackground(Color.decode("#B3D5E3"));

        add(vinduer, BorderLayout.CENTER);

        setVisible(true);
        pack();
        

    }
    //Initialiserer panelene,knappene,ImageIcons,ActionListeners og legger alt inn i sine respektive paneler.
    public void initialiser() {

        mainboard = new JPanel(new GridLayout(3, 3, 5, 5));

        /*visLeil = new JButton("Vis leilighet");
         visEne = new JButton("Vis enebolig");*/
        visLeilighet = new JButton();
        visEnebolig = new JButton();
        visUtleierBrowse = new JButton();
        visLeietakerBrowse = new JButton();

        try {
            regUtIkon = new ImageIcon(getClass().getResource("Bilder/Registrerutleier.png"));
            visUtIkon = new ImageIcon(getClass().getResource("Bilder/Visutleier.png"));
            regSøkIkon = new ImageIcon(getClass().getResource("Bilder/Registrersoker.png"));
            visSøkIkon = new ImageIcon(getClass().getResource("Bilder/Vissoker.png"));
            visKonIkon = new ImageIcon(getClass().getResource("Bilder/Viskontrakt.png"));
            statIkon = new ImageIcon(getClass().getResource("Bilder/Statistikk.png"));
            regBoIkon = new ImageIcon(getClass().getResource("Bilder/Registrerbolig.png"));
            visBoIkon = new ImageIcon(getClass().getResource("Bilder/Visbolig.png"));
            boligBrowseIkon = new ImageIcon(getClass().getResource("Bilder/Boligbrowse.png"));

            regUt = new JButton(regUtIkon);
            visUt = new JButton(visUtIkon);
            regSøk = new JButton(regSøkIkon);
            visSøk = new JButton(visSøkIkon);
            regBo = new JButton(regBoIkon);
            visBo = new JButton(visBoIkon);
            visKon = new JButton(visKonIkon);
            stat = new JButton(statIkon);
            boligBrowse = new JButton(boligBrowseIkon);
        } catch (NullPointerException npe) {
            regUt = new JButton("Registrer utleier");
            visUt = new JButton("Vis utleiere");
            regSøk = new JButton("Registrer søker");
            visSøk = new JButton("Vis søkere");
            regBo = new JButton("Registrer bolig");
            visBo = new JButton("Vis boliger");
            visKon = new JButton("Vis kontrakter");
            stat = new JButton("Statistikk");
            boligBrowse = new JButton("Bolig Browse");
        }

        mainboard.add(regUt);
        mainboard.add(visUt);
        mainboard.add(regSøk);
        mainboard.add(visSøk);
        mainboard.add(boligBrowse);
        mainboard.add(regBo);
        mainboard.add(visBo);
        mainboard.add(visKon);
        mainboard.add(stat);

        regUt.addActionListener(this);
        visUt.addActionListener(this);
        regSøk.addActionListener(this);
        visSøk.addActionListener(this);
        regBo.addActionListener(this);
        visBo.addActionListener(this);
        visKon.addActionListener(this);
        stat.addActionListener(this);
        boligBrowse.addActionListener(this);

        visLeilighet.addActionListener(this);
        visEnebolig.addActionListener(this);

        visUtleierBrowse.addActionListener(this);
        visLeietakerBrowse.addActionListener(this);
        
        try{
            ImageIcon bilde = new ImageIcon(getClass().getResource("Bilder/Logoliten2.png"));
            Image icon = bilde.getImage();
            setIconImage(icon);
        }
        catch(NullPointerException npe){}
        
         mainboard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    }
    //Metode for å vise panel med innkommen parameter
    public void visPanel(String st) {
        CardLayout cl = (CardLayout) vinduer.getLayout();
        cl.show(vinduer, st);
    }
    //Metode for å vise pop-up-meldinger i programmet
    public void visMelding(String melding) {
        JOptionPane.showMessageDialog(null, melding);
    }
    //Metode som switcher mellom hvilke paneler som skal vises. Brukes kun i "under-paneler".
    public void doClick(int i) {//generer actionevent fra ved kall

        switch (i) {
            case 1:
                visLeilighet.doClick();
                break;
            case 2:
                visEnebolig.doClick();
                break;
            case 3:
                visUtleierBrowse.doClick();
                break;
            case 4:
                visLeietakerBrowse.doClick();

            case 5:
                regUt.doClick();
            case 6: 
                visKon.doClick();
                break;
        }

    }

      //Metode som finner skjermstørrelse og setter størrelsen på programmets vindu relativt til skjermstørrelsen
    public void Size() {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;

        setSize(bredde / 2, høyde - 100);
        setLocation(skjerm.width / 2 - getSize().width / 2, skjerm.height / 2 - getSize().height / 2);
    }

     //ActionPerformed-metode for å bytte mellom de ni underpanelene
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == regUt) {
            vinduer.add(new RegistrerUtleierPANEL(register, this), "REG UTLEIER");
            visPanel("REG UTLEIER");
        } else if (e.getSource() == visUt) {
            vinduer.add(new UtleierOversiktPANEL(register, bregister, legister, this), "Oversikt");
            visPanel("Oversikt");
        } else if (e.getSource() == regSøk) {
            vinduer.add(new RegistrerSokerPANEL(sregister, bregister, legister, this), "REG SØKER");
            visPanel("REG SØKER");
        } else if (e.getSource() == visSøk) {
            vinduer.add(new SokerOversiktPANEL(sregister, this), "OversiktSøker");
            visPanel("OversiktSøker");
        } else if (e.getSource() == regBo) {
            vinduer.add(new RegistrerBoligPANEL(register, bregister, legister, this), "REG BOLIG");
            visPanel("REG BOLIG");
        } else if (e.getSource() == visBo) {
            vinduer.add(new BoligPromptPANEL(bregister, legister, register, this), "VIS PROMPT");
            visPanel("VIS PROMPT");
        } else if (e.getSource() == visKon) {
            vinduer.add(new KontraktOversiktPANEL(kregister, legister, register, this), "VIS KONTRAKTER");
            visPanel("VIS KONTRAKTER");
        } else if (e.getSource() == stat) {
            vinduer.add(new VisStatistikkPANEL(bregister, legister, register, sregister, kregister, this), "STATS");
            visPanel("STATS");
        } else if (e.getSource() == visEnebolig) {
            vinduer.add(new BoligOversiktPANEL(bregister, register, this), "VIS BOLIG");
            visPanel("VIS BOLIG");
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else if (e.getSource() == visLeilighet) {

            vinduer.add(new LeilighetOversiktPANEL(legister, register, this), "VIS LEILIGHET");
            visPanel("VIS LEILIGHET");
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else if (e.getSource() == boligBrowse) {

            vinduer.add(new BoligBrowsePANEL(sregister, bregister, legister, register, kregister, this), "VIS SØKERBROWSE");
            visPanel("VIS SØKERBROWSE");
        } else if (e.getSource() == visUtleierBrowse) {

            System.out.println("utleier not supported");
        }

    }
}
