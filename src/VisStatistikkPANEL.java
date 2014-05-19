/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Panel som viser statistikk 
 * Skrevet av Mads Karlstad. Sist oppdatert 01.05.14
 */
public class VisStatistikkPANEL extends JPanel implements ActionListener {

    private JPanel tilbakepanel;
    private JPanel overskriftpanel;
    private JPanel statspanel;

    private JButton tilbake;

    private JLabel overskrift;
    private JLabel utleierstats;
    private JLabel søkerstats;
    private JLabel eneboligstats;
    private JLabel leilighetstats;
    private JLabel kontraktstats;

    private JTextArea utleierutskriftsområde;

    private Boligregister bregister;
    private Leilighetregister legister;
    private Personregister register;
    private Sokerregister sregister;
    private Kontraktregister kregister;

    private MainFrame parent;

    public VisStatistikkPANEL(Boligregister bregister,Leilighetregister legister,Personregister register,Sokerregister sregister,Kontraktregister kregister, MainFrame parent){
        super(new BorderLayout());
        this.bregister = bregister;
        this.legister = legister;
        this.register = register;
        this.sregister = sregister;
        this.kregister = kregister;
        this.parent = parent;
        initialiser();
        lagGUI();
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;
         
        parent.setSize(bredde/2, høyde/3);
        parent.setLocation(skjerm.width/2-parent.getSize().width/2, skjerm.height/2-parent.getSize().height/2);
    
    }

    //Initialiserer komponentene
    public void initialiser() {

        setLayout(new BorderLayout());

        statspanel = new JPanel(new GridLayout(5,1));
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tilbakepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        tilbake = new JButton("Tilbake");

        overskrift = new JLabel("Statistikk");
        utleierstats = new JLabel("Antall utleiere BoligBrowse™ har registrert i sine systemer: " + register.size());
        søkerstats = new JLabel("Antall boligsøkere BoligBrowse™ har registrert i sine systemer: " + sregister.size());
        leilighetstats = new JLabel("Antall leiligheter BoligBrowse™ har registrert for leie i sine systemer: " + legister.size());
        eneboligstats = new JLabel("Antall eneboliger BoligBrowse™ har registrert for leie i sine systemer: " + bregister.size());
        kontraktstats = new JLabel("Antall kontrakter BoligBrowse™ har registrert i sine systemer: " + kregister.size());



        tilbake.addActionListener(this);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
            overskrift.setHorizontalAlignment(JLabel.CENTER);
            utleierstats.setHorizontalAlignment(JLabel.CENTER);
            søkerstats.setHorizontalAlignment(JLabel.CENTER);
            eneboligstats.setHorizontalAlignment(JLabel.CENTER);
            leilighetstats.setHorizontalAlignment(JLabel.CENTER);
            kontraktstats.setHorizontalAlignment(JLabel.CENTER);
    }
    //Oppretter brukergrensesnittet
    public void lagGUI(){

        tilbakepanel.add(tilbake);
        overskriftpanel.add(overskrift);
        statspanel.add(utleierstats);
        statspanel.add(søkerstats);
        statspanel.add(eneboligstats);
        statspanel.add(leilighetstats);
        statspanel.add(kontraktstats);

        add(overskriftpanel, BorderLayout.PAGE_START);
        add(tilbakepanel,BorderLayout.PAGE_END);
        add(statspanel,BorderLayout.CENTER);

        overskriftpanel.setBackground(Color.decode("#B3D5E3"));
        tilbakepanel.setBackground(Color.decode("#B3D5E3"));
        statspanel.setBackground(Color.decode("#B3D5E3"));
        setBackground(Color.decode("#B3D5E3"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tilbake){
            parent.visPanel(MainFrame.MAIN_BOARD);
            parent.Size();
        }
    }
}