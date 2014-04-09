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

    private JTextField persnr,navn,adresse,email,tlfnr,firma;
    private Personregister pregister = new Personregister();
    private JTextArea utskriftsområde;
    public static final String HOVEDPANEL = "hovedpanel";

    private JPanel utleiepanel;
    private JPanel c;

    private Container container;
    private BoxLayout layout;
    private FlowLayout layout2;

    public RegistrerUtleier(BoligGUI2 parent){

        super(new BorderLayout());
        this.parent = parent;
        utleiepanel = new JPanel();
        c = new JPanel();
        //container = getContentPane();
        c.setLayout(new CardLayout());
        c.add(HOVEDPANEL,utleiepanel);
        persnr = new JTextField(18);
        navn = new JTextField(18);
        adresse = new JTextField(18);
        email = new JTextField(18);
        tlfnr = new JTextField(18);
        firma = new JTextField(18);

        utleiepanel.setLayout(new BoxLayout(utleiepanel, BoxLayout.X_AXIS));
        utleiepanel.add(persnr);
        utleiepanel.add(navn);
        utleiepanel.add(adresse);
        utleiepanel.add(email);
        utleiepanel.add(tlfnr);
        utleiepanel.add(firma);
        utleiepanel.add(reg);
    }

    private void slettFelter()
    {
        persnr.setText("");
        navn.setText("");
        adresse.setText("");
        email.setText("");
        tlfnr.setText("");
        firma.setText("");

    }

    public void visMelding(String melding){

        utskriftsområde.setText(melding);

    }

    public void nyUtleier()
    {
        String pnr = persnr.getText();
        String n = navn.getText();
        String a = adresse.getText();
        String mail = email.getText();
        String tlf = tlfnr.getText();
        String f = firma.getText();


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

    private class Knapplytter implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if ( e.getSource() == reg )
                nyUtleier();

            }
        }
    }



