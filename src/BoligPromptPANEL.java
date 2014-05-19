/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * Panel for som viser de forskjellige typene boliger man kan velge å vise i BoligBrowsePANEL.
 *  Skrevet av Erlend Westbye. Sist oppdatert 01.05.14
 */
public class BoligPromptPANEL extends JPanel implements ActionListener {

    private JPanel knappanel;
    private JPanel tilbakepanel;
    private JPanel overskriftpanel;
    private JButton visLeil;
    private JButton visEnebolig;
    private JButton tilbake;
    private JLabel overskrift;
    private Boligregister bregister;
    private Leilighetregister legister;
    private Personregister register;
    private MainFrame parent;
    private Icon leilighetIkon, eneboligIkon;

    private static final int VIS_ENEBOLIG = 0;
    private static final int VIS_LEIL = 1;

    public BoligPromptPANEL(Boligregister bregister,Leilighetregister legister,Personregister register, MainFrame parent){
        super(new BorderLayout());
        this.bregister = bregister;
        this.legister = legister;
        this.register = register;
        this.parent = parent;
        initialiser();
        lagGUI();
    }

    //Initialiserer paneler,knapper,felter,ImageIcon,ActionListener, osv.
    public void initialiser() {

        setLayout(new BorderLayout());

        knappanel = new JPanel(new GridLayout(1,2,5,5));
        tilbakepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        try{
        leilighetIkon = new ImageIcon(getClass().getResource("Bilder/Leilighet.png"));
        eneboligIkon = new ImageIcon(getClass().getResource("Bilder/Enebolig.png"));
        visLeil = new JButton(leilighetIkon);
        visEnebolig = new JButton(eneboligIkon);
        }
        
        catch (NullPointerException npe){
            visLeil = new JButton ("Vis leiligheter");
            visEnebolig = new JButton ("Vis eneboliger");
        }
        
        tilbake = new JButton("Tilbake");

        overskrift = new JLabel("Velg boligtype");

        tilbake.addActionListener(this);
        visLeil.addActionListener(this);
        visEnebolig.addActionListener(this);
        

    }
    //Lager brukergrensesnittet
    public void lagGUI(){

        knappanel.add(visEnebolig);
        knappanel.add(visLeil);
        tilbakepanel.add(tilbake);
        overskriftpanel.add(overskrift);
        
        
        add(overskriftpanel,BorderLayout.PAGE_START);
        add(knappanel, BorderLayout.CENTER);
        add(tilbakepanel,BorderLayout.PAGE_END);
        
        knappanel.setBackground(Color.decode("#B3D5E3"));
        tilbakepanel.setBackground(Color.decode("#B3D5E3"));
        overskriftpanel.setBackground(Color.decode("#B3D5E3"));
        
        setBackground(Color.decode("#B3D5E3"));
        
        setBorder(BorderFactory.createEmptyBorder(0/*top*/, 20/*left*/, 0/*bottom*/, 20/*right*/));

                Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;
        

       parent.setSize(bredde/2, høyde/2);
       //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
       //this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    //actionPerformed-metode. Inneholder kall på MainFrame sin doClick-metode som aktiverer paneler i MainFrame
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tilbake){
            parent.visPanel(MainFrame.MAIN_BOARD);
            parent.Size();
        }
        else if(e.getSource() == visEnebolig){
            parent.doClick(2);
        }
        else if(e.getSource() == visLeil){
            parent.doClick(1);
        }
    }
}
