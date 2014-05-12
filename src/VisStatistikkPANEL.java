/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisStatistikkPANEL extends JPanel implements ActionListener {

    private JPanel tilbakepanel;
    private JPanel overskriftpanel;
    private JPanel statsUtleierpanel;


    private JButton tilbake;

    private JLabel overskrift;


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
    }


    public void initialiser() {

        setLayout(new BorderLayout());


        tilbakepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        tilbake = new JButton("Tilbake");

        overskrift = new JLabel("Statistikk");

        utleierutskriftsområde = new JTextArea();
        utleierutskriftsområde.setEditable(false);

        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        statsUtleierpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


        String stats = "Antall utleiere BoligBrowse™ har registrert i sine systemer: " + register.size()
                        +"\n\nAntall boligsøkere BoligBrowse™ har registrert i sine systemer: " + sregister.size()
                        +"\n\nAntall leiligheter BoligBrowse™ har registrert for leie i sine systemer: " + legister.size()
                        +"\n\nAntall eneboliger BoligBrowse™ har registrert for leie i sine systemer: " + bregister.size()
                        +"\n\nAntall kontrakter BoligBrowse™ har registrert i sine systemer: " + kregister.size();

        utleierutskriftsområde.setText(stats);


        tilbake.addActionListener(this);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void lagGUI(){

        tilbakepanel.add(tilbake);
        overskriftpanel.add(overskrift);
        statsUtleierpanel.add(utleierutskriftsområde);

        add(statsUtleierpanel,BorderLayout.CENTER);
        add(overskriftpanel, BorderLayout.PAGE_START);
        add(tilbakepanel,BorderLayout.PAGE_END);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tilbake){
            parent.visPanel(MainFrame.MAIN_BOARD);
        }
    }
}
