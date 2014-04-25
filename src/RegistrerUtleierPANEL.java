import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
SLANGEDREAM COPYRIGHT
 */
public class RegistrerUtleierPANEL extends JPanel implements ActionListener {
    private JTextField[] felt;
    private final String[] feltnavn = {"Fødselsnummer", "Fornavn", "Etternavn", "Adresse", "Mail", "Telefonnummer", "Firma"};

    private final int FØDSELSNUMMER = 0;
    private final int FORNAVN = 1;
    private final int ETTERNAVN = 2;
    private final int ADRESSE = 3;
    private final int MAIL = 4;
    private final int TELEFONUMMER = 5;
    private final int FIRMA = 6;

    private JPanel feltpanel;
    private JPanel knapppanel;


    private JButton registrer;
    private JButton avbryt;


    private Personregister register;
    private MainFrame parent;

    public RegistrerUtleierPANEL(Personregister register, MainFrame parent) {
        super(new BorderLayout());
        this.register = register;
        this.parent = parent;
        initialiser();
        lagGUI();

        add(feltpanel, BorderLayout.CENTER);
        add(knapppanel, BorderLayout.SOUTH);


    }


    public void initialiser() {

        feltpanel = new JPanel(new GridLayout(7, 1, 5, 5));
        knapppanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        felt = new JTextField[feltnavn.length];

        for (int i = 0; i < feltnavn.length; i++) {
            felt[i] = new JTextField(10);
            felt[i].setText(feltnavn[i]);
            felt[i].setHorizontalAlignment(JTextField.CENTER);

        }
        registrer = new JButton("Registrer");
        avbryt = new JButton("Avbryt");

        registrer.addActionListener(this);
        avbryt.addActionListener(this);


    }

    public void lagGUI() {



        for (int i = 0; i < felt.length; i++) {
            feltpanel.add(felt[i]);
        }
        knapppanel.add(registrer);
        knapppanel.add(avbryt);


    }

    public void registrer(){
        String fødselsnummer = felt[FØDSELSNUMMER].getText();
        String fornavn = felt[FORNAVN].getText();
        String etternavn = felt[ETTERNAVN].getText();
        String adresse = felt[ADRESSE].getText();
        String mail = felt[MAIL].getText();
        String telefonnummer = felt[TELEFONUMMER].getText();
        String firma = felt[FIRMA].getText();

        Person utleier = new Utleier(fødselsnummer,fornavn,etternavn,adresse, mail, telefonnummer, firma);


        if(register.leggTil(utleier)){
            //gå tilbake til mainframe
            System.out.println("Lagt til!");
            return;
        }
        //vis feilmelding




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == registrer){

            registrer();
            parent.visPanel(MainFrame.MAIN_BOARD);

        }else if(e.getSource() == avbryt){
            parent.visPanel(MainFrame.MAIN_BOARD);
        }
    }


}
