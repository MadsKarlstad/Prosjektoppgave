import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 SLANGEDREAM COPYRIGHT
 */
public class BoligPromptPANEL extends JPanel implements ActionListener {

    private JPanel knappanel;
    private JPanel tilbakepanel;
    private JPanel overskriftpanel;
    private JButton visLeil;
    private JButton visEnebolig;
    private JButton tilbake;
    private JLabel overskrift;
    private MainFrame parent;

    private static final int VIS_ENEBOLIG = 2;
    private static final int VIS_LEILIGHET = 1;

    public BoligPromptPANEL(MainFrame parent){
        super(new BorderLayout());
        this.parent = parent;
        initialiser();
        lagGUI();
    }


    public void initialiser() {

        setLayout(new BorderLayout());

        knappanel = new JPanel(new GridLayout(1,2,5,5));
        tilbakepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        visLeil = new JButton("Vis leiligheter");
        visEnebolig = new JButton("Vis eneboliger");
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

        overskriftpanel.add(overskrift);

        add(overskriftpanel,BorderLayout.PAGE_START);
        add(knappanel, BorderLayout.CENTER);
        add(tilbakepanel,BorderLayout.PAGE_END);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tilbake){
            parent.visPanel(MainFrame.MAIN_BOARD);
        }
        else if(e.getSource() == visEnebolig){
            parent.doClick(VIS_ENEBOLIG);
        }
        else if(e.getSource() == visLeil){
            parent.doClick(VIS_LEILIGHET);
        }
    }
}
