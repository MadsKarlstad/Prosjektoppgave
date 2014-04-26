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
    private JPanel boligvinduer;

    public static final String MAIN_BOARD = "-1";

    private JButton regUt, visUt, regSøk, visSøk, regBo, visBo, visKon, stat, boligBrowse;
    private Icon regUtIkon, visUtIkon, regSøkIkon, visSøkIkon, regBoIkon,
            visBoIkon, visKonIkon, statIkon, boligBrowseIkon;


    private Personregister register;
    private Boligregister bregister;
    private Sokerregister sregister;
    private Leilighetregister legister;
    private Kontraktregister kregister;


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
        regUtIkon = new ImageIcon(getClass().getResource("Bilder/Registrerutleier.png"));
        visUtIkon = new ImageIcon(getClass().getResource("Bilder/Visutleier.png"));
        regSøkIkon = new ImageIcon(getClass().getResource("Bilder/Registrersoker.png"));
        visSøkIkon = new ImageIcon(getClass().getResource("Bilder/Vissoker.png"));
        visKonIkon = new ImageIcon(getClass().getResource("Bilder/Viskontrakt.png"));
        statIkon = new ImageIcon(getClass().getResource("Bilder/Statistikk.png"));
        regBoIkon = new ImageIcon(getClass().getResource("Bilder/Registrerbolig.png"));
        visBoIkon = new ImageIcon(getClass().getResource("Bilder/Visbolig.png"));
        boligBrowseIkon = new ImageIcon(getClass().getResource("Bilder/Boligbrowse.png"));

        visLeilighet = new JButton();
        visEnebolig = new JButton();
        visUtleierBrowse = new JButton();

        regUt = new JButton (regUtIkon);
        visUt = new JButton (visUtIkon);
        regSøk = new JButton (regSøkIkon);
        visSøk = new JButton (visSøkIkon);
        regBo = new JButton (regBoIkon);
        visBo = new JButton (visBoIkon);
        visKon = new JButton (visKonIkon);
        stat = new JButton (statIkon);
        boligBrowse = new JButton (boligBrowseIkon);

        mainboard.add(regUt);
        mainboard.add(visUt);
        mainboard.add(regSøk);
        mainboard.add(visSøk);
        mainboard.add(boligBrowse);
        mainboard.add(regBo);
        mainboard.add(visBo);
        mainboard.add(visKon);
        mainboard.add(stat);

        regUt.addActionListener(this);
        visUt.addActionListener(this);
        regSøk.addActionListener(this);
        visSøk.addActionListener(this);
        regBo.addActionListener(this);
        visBo.addActionListener(this);
        visKon.addActionListener(this);
        stat.addActionListener(this);
        boligBrowse.addActionListener(this);

        visLeilighet.addActionListener(this);
        visEnebolig.addActionListener(this);

        visUtleierBrowse.addActionListener(this);
        visUtleierBrowse.addActionListener(this);


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

        if(e.getSource() == regUt){
            vinduer.add(new RegistrerUtleierPANEL(register, this), "REG UTLEIER");
            visPanel("REG UTLEIER");
        }else if(e.getSource()  == visUt){
            vinduer.add(new UtleierOversiktPANEL(register, this), "Oversikt");
            visPanel("Oversikt");
        }
        else if(e.getSource()  == regSøk){
            vinduer.add(new RegistrerSokerPANEL(sregister, this), "REG SØKER");
            visPanel("REG SØKER");
        }
        else if(e.getSource()  == visSøk){
            vinduer.add(new SokerOversiktPANEL(sregister, this), "OversiktSøker");
            visPanel("OversiktSøker");
        }
        else if(e.getSource()  == regBo){
            vinduer.add(new RegistrerBoligPANEL(register,bregister,legister, this), "REG BOLIG");
            visPanel("REG BOLIG");
        }
        else if(e.getSource()  == visBo){
            vinduer.add(new BoligPromptPANEL(bregister,legister,register,this), "VIS PROMPT");
            visPanel("VIS PROMPT");
        }
        else if(e.getSource()  == visKon){
            vinduer.add(new KontraktOversiktPANEL(bregister,legister,sregister,register,kregister, this), "VIS KONTRAKTER");
            visPanel("VIS KONTRAKTER");
        }
        else if(e.getSource()  == stat){
            vinduer.add(new VisStatistikkPANEL(bregister,legister,register,sregister, this), "STATS");
            visPanel("STATS");
        }
        else if(e.getSource()  == boligBrowse){
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



    }
}
