import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 SLANGEDREAM COPYRIGHT
 */
public class BoligPromptPANEL extends JPanel implements ActionListener {

    private JPanel knappanel;
    private JPanel tilbakepanel;
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


    public void initialiser() {

        setLayout(new BorderLayout());

        knappanel = new JPanel(new GridLayout(1,2,5,5));
        tilbakepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

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

    public void lagGUI(){

        knappanel.add(visEnebolig);
        knappanel.add(visLeil);
        tilbakepanel.add(tilbake);
        add(overskrift,BorderLayout.PAGE_START);
        add(knappanel, BorderLayout.CENTER);
        add(tilbakepanel,BorderLayout.PAGE_END);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tilbake){
            parent.visPanel(MainFrame.MAIN_BOARD);
        }
        else if(e.getSource() == visEnebolig){
            parent.doClick(2);
        }
        else if(e.getSource() == visLeil){
            parent.doClick(1);
        }
    }
}
