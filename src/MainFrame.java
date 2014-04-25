import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Erlend on 16/04/14.
 */
public class MainFrame extends JFrame implements ActionListener {


    private JPanel mainboard;

    private JPanel vinduer;

    private JButton visLeilighet;
    private JButton visEnebolig;

    private JButton visUtleierBrowse;
    private JButton visLeietakerBrowse;


    //paneler for visning av leiligheter og eneboliger
    //private JPanel boligvinduer;

    public static final String MAIN_BOARD = "-1";

    private JButton[] knapp;
    private String[] knappenavn = { "Registrer utleier","Vis Utleiere","Register Søker",
            "Vis Søkere","Registrer Bolig", "Vis Boliger",
            "vis kontrakter","Statistikk","BoligBrowse™"};

    private static final int REG_UTLEIER = 0;
    private static final int VIS_UTLEIER = 1;
    private static final int REG_SØKER = 2;
    private static final int VIS_SØKER = 3;
    private static final int REG_BOLIG = 4;
    private static final int VIS_BOLIG = 5;
    private static final int VIS_KONTRAKTER = 6;
    private static final int VIS_STATS = 7;
    private static final int VIS_BOLIGBROWSE = 8;


    private Personregister register;
    private Boligregister bregister;
    private Sokerregister sregister;
    private Leilighetregister legister;


    public MainFrame(Personregister register,Boligregister bregister,Sokerregister sregister,Leilighetregister legister){
        super("Bolig Browse™");
        setLayout(new BorderLayout());

        this.register = register;
        this.sregister = sregister;
        this.bregister = bregister;
        this.legister = legister;

        initialiser();

        vinduer = new JPanel(new CardLayout());
        vinduer.add(mainboard, MAIN_BOARD);

        add(vinduer, BorderLayout.CENTER);

        setVisible(true);
        pack();
    }

    public void initialiser(){

        mainboard = new JPanel(new GridLayout(3,3,5,5));

        /*visLeil = new JButton("Vis leilighet");
        visEne = new JButton("Vis enebolig");*/
        knapp = new JButton[knappenavn.length];

        visLeilighet = new JButton();
        visEnebolig = new JButton();

        visUtleierBrowse = new JButton();
        visLeietakerBrowse = new JButton();

        for(int i= 0 ; i < knappenavn.length;i++){

            knapp[i] = new JButton(knappenavn[i]);
            mainboard.add(knapp[i]);
            knapp[i].addActionListener(this);
        }

        visLeilighet.addActionListener(this);
        visEnebolig.addActionListener(this);

        visUtleierBrowse.addActionListener(this);
        visLeietakerBrowse.addActionListener(this);


    }

    public void visPanel(String st){
        CardLayout cl = (CardLayout) vinduer.getLayout();
        cl.show(vinduer,st);
    }

    public void doClick(int i){//generer actionevent fra ved kall

        switch (i) {
            case 1:  visLeilighet.doClick();
                break;
            case 2:  visEnebolig.doClick();
                break;
            case 3:  visUtleierBrowse.doClick();
                break;
            case 4:  visLeietakerBrowse.doClick();
                break;
    }

    }




    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == knapp[REG_UTLEIER]){
            vinduer.add(new RegistrerUtleierPANEL(register, this), "REG UTLEIER");
            visPanel("REG UTLEIER");
        }else if(e.getSource()  == knapp[VIS_UTLEIER]){
            vinduer.add(new UtleierOversiktPANEL(register, this), "Oversikt");
            visPanel("Oversikt");
        }
        else if(e.getSource()  == knapp[REG_SØKER]){
        vinduer.add(new RegistrerSokerPANEL(sregister, this), "REG SØKER");
        visPanel("REG SØKER");
        }
        else if(e.getSource()  == knapp[VIS_SØKER]){
        vinduer.add(new SokerOversiktPANEL(sregister, this), "OversiktSøker");
        visPanel("OversiktSøker");
        }
        else if(e.getSource()  == knapp[REG_BOLIG]){
        vinduer.add(new RegistrerBoligPANEL(register,bregister,legister, this), "REG BOLIG");
        visPanel("REG BOLIG");
        }
        else if(e.getSource()  == knapp[VIS_BOLIG]){
        vinduer.add(new BoligPromptPANEL(bregister,legister,register, this), "VIS PROMPT");
        visPanel("VIS PROMPT");
        }
        else if(e.getSource()  == knapp[VIS_KONTRAKTER]){
        vinduer.add(new UtleierOversiktPANEL(register, this), "Oversikt");
        visPanel("Oversikt");
        }
        else if(e.getSource()  == knapp[VIS_STATS]){
        vinduer.add(new UtleierOversiktPANEL(register, this), "Oversikt");
        visPanel("Oversikt");
        }
        else if(e.getSource()  == knapp[VIS_BOLIGBROWSE]){
        vinduer.add(new BoligBrowsePromptPANEL(register, this), "VIS BOLIGBROWSEPROMPT");
        visPanel("VIS BOLIGBROWSEPROMPT");
        }
        else if(e.getSource()  == visEnebolig){
            vinduer.add(new BoligOversiktPANEL(bregister,register, this), "VIS BOLIG");
            visPanel("VIS BOLIG");
        }

        else if(e.getSource()  == visLeilighet){

            vinduer.add(new LeilighetOversiktPANEL(legister,register,this), "VIS LEILIGHET");
            visPanel("VIS LEILIGHET");
        }

        else if(e.getSource()  == visLeietakerBrowse){

            System.out.println("leietaker not supported");
        }

        else if(e.getSource()  == visUtleierBrowse){

            System.out.println("utleier not supported");
        }



    }
}
