package prosjektoppgave;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Erlend on 08/04/14.
 */
public class RegistrerUtleier extends JPanel {


    private BoligGUI2 parent;
    private JButton reg;
    private JPanel utleiepanel;
    private JTextField[] field;
    private String[] fieldnavn = {"pnr","navn","adr","mail","tlf","firma"};
    private Personregister pregister = new Personregister();
    private JTextArea utskriftsområde;

    private final int HENT_PNR = 0;
    private final int HENT_NAVN = 1;
    private final int HENT_ADR = 2;
    private final int HENT_MAIL = 3;
    private final int HENT_TLF = 4;
    private final int HENT_FIRMA = 5;

    public RegistrerUtleier(BoligGUI2 parent){

        super(new BorderLayout());
        this.parent = parent;

        for(int i = 0; i < fieldnavn.length; i++){

            field[i] = new JTextField(fieldnavn[i]);
            utleiepanel.add(field[i]);
        }



    }

    private void slettFelter()
    {
        field[HENT_PNR].setText("");
        field[HENT_NAVN].setText("");
        field[HENT_ADR].setText("");
        field[HENT_MAIL].setText("");
        field[HENT_TLF].setText("");
        field[HENT_FIRMA].setText("");

    }

    public void visMelding(String melding){

        utskriftsområde.setText(melding);

    }

    public void nyUtleier()
    {
        String pnr = field[HENT_PNR].getText();
        String n = field[HENT_NAVN].getText();
        String a = field[HENT_ADR].getText();
        String mail = field[HENT_MAIL].getText();
        String tlf = field[HENT_TLF].getText();
        String f = field[HENT_FIRMA].getText();

        if(pnr.length() == 0 || n.length() == 0
                || a.length() == 0 || mail.length() == 0
                || tlf.length() == 0)
        {
            visMelding("Fyll ut all info pliz");
        }
        if(pnr.length() != 0 || n.length() != 0
                || a.length() != 0 || mail.length() != 0
                || tlf.length() != 0)
        {
            pregister.settInn(
                    new Utleier(pnr,n,a,mail,tlf,f));
            Person utleier = pregister.getPerson(pnr);
            visMelding("Utleier registrert");
            slettFelter();
        }
    }

}


/*    private class Knapplytter implements ActionListener{
        public void actionPerformed(ActionEvent e){

            }
        }
    }*/



