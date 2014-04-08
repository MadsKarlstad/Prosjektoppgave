package prosjektoppgave;

import prosjektoppgave.RegistrerUtleier;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.ByteOrder;
import javax.swing.*;
import javax.xml.crypto.dsig.keyinfo.KeyName;

public class BoligGUI2 extends JFrame implements ActionListener{

    //datafelt for layoutshit
    private JButton knappene[];
    private boolean toggle = true;
    private String navnarray[]= {"reg utleier","reg søker","vis boliger",
            "vis utleiere","vis søkere","BoligBrowse","vis kontrakt","stats","reg enebolig"};

    private static final int REG_UTLEIER = 0;
    private static final int REG_SOKER = 1;
    private static final int VIS_BOLIGLISTE = 2;
    private static final int VIS_UTLEIERE = 3;
    private static final int VIS_SOKER = 4;
    private static final int BOLIGBROWSE = 5;
    private static final int VIS_KONTRAKT = 6;
    private static final int STATS = 7;
    private static final int REGISTRER_BOLIG = 8;



    public static final String REGISTRER_ENEBOLIG = "registrer enebolig";
    public static final String HOVEDPANEL = "hovedpanel";


    private JPanel hovedpanel;
    private JPanel c;


    private Container container;
    private GridLayout layout; //her åner vi for å legge inn de 9 layoutene ve trenger til vinduene våre
    private FlowLayout layout2;

    private JButton tilbake;

    public BoligGUI2(){

        super("BoligBrowse(tm)");

        try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){

        }

        c = new JPanel();

        hovedpanel = new JPanel(new GridLayout(3,3,10,10));

        tilbake = new JButton("TILBAKE");
        tilbake.addActionListener(this);


        container = getContentPane();
        c.setLayout(new CardLayout());
        c.add(HOVEDPANEL,hovedpanel);
        knappene = new JButton[navnarray.length];


        for(int i = 0; i < navnarray.length; i++){

            knappene[i] = new JButton(navnarray[i]);
            knappene[i].addActionListener(this);
            hovedpanel.add(knappene[i]);
        }
        container.setLayout(new BorderLayout());
        container.add(c, BorderLayout.CENTER);

    }

    public void visPanel(String n){

        CardLayout cl = (CardLayout)c.getLayout();
        cl.show(c,n);

    }
    public void registrerUtleier(){
        container.add(tilbake, BorderLayout.SOUTH);
        c.add("Vindu", new RegistrerUtleier(this));
        visPanel("Vindu");

    }

    public void regSoker(){
        container.add(tilbake, BorderLayout.SOUTH);
        c.add("Vindu", new RegistrerSoker(this));
        visPanel("Vindu");

    }

    public void tilbake(){
        container.remove(tilbake);
        revalidate();
        repaint();
        visPanel(HOVEDPANEL);
    }


        public void actionPerformed( ActionEvent e )
        {
            /*
            if( toggle )
                c.setLayout(layout2);
            else
                c.setLayout(layout);

            toggle = !toggle;
            c.validate();*/

            if(e.getSource() == knappene[REG_UTLEIER]){
                registrerUtleier();
            }

            else if(e.getSource() == knappene[REG_SOKER]){

                regSoker();

            }

            else if(e.getSource() == tilbake){
                tilbake();
            }

        }
    }