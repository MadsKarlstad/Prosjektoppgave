package prosjektoppgave;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Erlend on 08/04/14.
 */
public class RegistrerSoker extends JPanel {

    private BoligGUI2 parent;
    private JButton tilbake;

    public RegistrerSoker(BoligGUI2 parent){
        super(new BorderLayout());

        this.parent = parent;

        tilbake = new JButton("TILBAKE");
        tilbake.addActionListener(new Knapplytter());

        // add(tilbake, BorderLayout.SOUTH);

    }
    public void tilbake(){
        parent.visPanel(BoligGUI2.HOVEDPANEL);
    }
    private class Knapplytter implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == tilbake){
                tilbake();
            }
        }
    }

}

