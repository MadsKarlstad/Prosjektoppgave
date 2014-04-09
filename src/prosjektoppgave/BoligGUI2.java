package prosjektoppgave;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class BoligGUI2 extends JFrame implements ActionListener{

    //datafelt for layoutshit
    private JButton knappene[];
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
        
        Toolkit verktøykasse = Toolkit.getDefaultToolkit();
        Dimension skjermdimensjon = verktøykasse.getScreenSize();
        int bredde = skjermdimensjon.width;
        int høyde = skjermdimensjon.height;
        
        setSize( bredde / 4, høyde / 4 );
        setLocationByPlatform(true);
        String bildefil = "logoliten2.png";
        URL kilde = BoligGUI.class.getResource(bildefil);
        if (kilde != null)
        {
            ImageIcon bilde = new ImageIcon(kilde);
            Image ikon = bilde.getImage();    
            setIconImage(ikon);
        }

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

    public void regBolig(){
        container.add(tilbake, BorderLayout.SOUTH);
        c.add("Vindu", new RegistrerSoker(this));
        visPanel("Vindu");

    }

    public void visBoligliste(){
        container.add(tilbake, BorderLayout.SOUTH);
        c.add("Vindu", new RegistrerSoker(this));
        visPanel("Vindu");

    }

    public void visUtleire(){
        container.add(tilbake, BorderLayout.SOUTH);
        c.add("Vindu", new RegistrerSoker(this));
        visPanel("Vindu");

    }

    public void visSoker(){
        container.add(tilbake, BorderLayout.SOUTH);
        c.add("Vindu", new RegistrerSoker(this));
        visPanel("Vindu");

    }

    public void BoligBrowse(){
        container.add(tilbake, BorderLayout.SOUTH);
        c.add("Vindu", new RegistrerSoker(this));
        visPanel("Vindu");

    }

    public void visKontrakt(){
        container.add(tilbake, BorderLayout.SOUTH);
        c.add("Vindu", new RegistrerSoker(this));
        visPanel("Vindu");

    }

    public void visStats(){
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


            if(e.getSource() == knappene[REG_UTLEIER]){
                registrerUtleier();
            }

            else if(e.getSource() == knappene[REG_SOKER]){

                regSoker();

            }

            else if(e.getSource() == knappene[VIS_BOLIGLISTE]){

                visBoligliste();


            }

            else if(e.getSource() == knappene[VIS_UTLEIERE]){

                visUtleire();

            }

            else if(e.getSource() == knappene[VIS_SOKER]){

                visSoker();

            }

            else if(e.getSource()== knappene[BOLIGBROWSE]){

                BoligBrowse();
            }

            else if(e.getSource() == knappene[STATS]){

                visStats();
            }

            else if(e.getSource() == knappene[REGISTRER_BOLIG]){

                regBolig();

            }

            else if(e.getSource() == knappene[VIS_KONTRAKT]){

                visKontrakt();

            }

            else if(e.getSource() == tilbake){
                tilbake();
            }

        }
    }
