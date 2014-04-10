package prosjektoppgave;
import org.omg.CORBA.MARSHAL;


import prosjektoppgave.KontraktRegister;
import prosjektoppgave.Personregister;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoligGUI2 {

    //Frame+kontinuelig panel SKAL IKKE RØRES
    private JFrame frame = new JFrame("Bolig Browse™");
    private JPanel panelKontinuelig = new JPanel();

    private Personregister pregister = new Personregister();
    private KontraktRegister kregister = new KontraktRegister();

    //Paneler for alle klasser, gies navn etterhvert
    private JPanel første = new JPanel();
    private JPanel andre = new JPanel();
    private JPanel tredje = new JPanel();
    private JPanel fjerde = new JPanel();
    private JPanel femte = new JPanel();
    private JPanel sjette = new JPanel();
    private JPanel syvende = new JPanel();
    private JPanel åttende = new JPanel();
    private JPanel niende = new JPanel();
    private JPanel tiende = new JPanel();

    //JTextAreas
    JTextArea utskriftsområde = new JTextArea(15,15);
    JTextArea utskriftsområde2 = new JTextArea(15,15);

    //JTextFields
    JTextField Pnr = new JTextField(18);
    JTextField Navn = new JTextField(18);
    JTextField Adr = new JTextField(18);
    JTextField Mail = new JTextField(18);
    JTextField Tlf = new JTextField(18);
    JTextField Firma = new JTextField(18);

    //Faste arrayer for å legge til komponenter via array.length
    private JButton knapperpanel1[];
    private String[] knappenavnpanel1 = { "en","Registrer utleier","Vis utleiere",
            "fire","fem", "seks",
            "syv","åtte", "ni"};
    private JTextField[] feltene;
    private String[] feltnavn = {"PNR","Navn","Adr", "mail", "Tlf", "firma", "reg"};

    //Lager et instance av cardlayout og gridlayout
    CardLayout cl = new CardLayout();
    GridLayout gl = new GridLayout(3,3,5,5);//kan gjøres lokalt

    public BoligGUI2(){

        panelKontinuelig.setLayout(cl);//passer cardlayoutet vårt inn i det kontunielige panelet



        //komponenter for hovedpanelet legges til her
        panelKontinuelig.add(første, "1");
        panelKontinuelig.add(andre, "2");
        panelKontinuelig.add(tredje, "3");
        panelKontinuelig.add(fjerde, "4");
        panelKontinuelig.add(femte, "5");
        panelKontinuelig.add(sjette, "6");
        panelKontinuelig.add(syvende, "7");
        panelKontinuelig.add(åttende, "8");
        panelKontinuelig.add(niende, "9");
        panelKontinuelig.add(tiende,"10");

        //komponenter for underpanelen kan legges inn her
        frame.add(new JScrollPane(utskriftsområde));


        //panel 1
        knapperpanel1 = new JButton[knappenavnpanel1.length];
        første.setLayout(gl);


        for (int i = 0; i < knappenavnpanel1.length; i++) {
            knapperpanel1[i] = new JButton(knappenavnpanel1[i]);
            første.add(knapperpanel1[i]);
            final String nr = String.valueOf(i + 1);
            knapperpanel1[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    cl.show(panelKontinuelig, nr);

                }
            });
        }

        //panel 2(Registrer utleier)



        andre.setLayout(new BorderLayout());
        andre.add(utskriftsområde, BorderLayout.PAGE_START);
        utskriftsområde.setVisible(true);
        utskriftsområde.setEditable(false);
        JButton reg = new JButton("Registrer utleier");
        JButton tilbake = new JButton("Tilbake");

        JPanel tekstinput = new JPanel();
        JPanel knapperneders = new JPanel();
        tekstinput.setLayout(new GridLayout(3,2));
        knapperneders.setLayout(new GridLayout(2,1));

        Pnr.setText("Personnummer");
        Navn.setText("Navn");
        Adr.setText("Adresse");
        Mail.setText("Mail");
        Tlf.setText("Tlf");
        Firma.setText("Firma");

        tekstinput.add(Pnr);
        tekstinput.add(Navn);
        tekstinput.add(Adr);
        tekstinput.add(Mail);
        tekstinput.add(Tlf);
        tekstinput.add(Firma);

        knapperneders.add(reg);
        knapperneders.add(tilbake);


        reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nyUtleier();
            }
        });

        tilbake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //panelKontinuelig.add(første,"1");
                cl.show(panelKontinuelig,"1");
            }
        });

        Pnr.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                Pnr.setText("");
            }
        });
        Navn.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                Navn.setText("");
            }
        });
        Adr.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                Adr.setText("");
            }
        });
        Mail.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                Mail.setText("");
            }
        });
        Tlf.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                Tlf.setText("");
            }
        });
        Firma.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                Firma.setText("");
            }
        });


        andre.add(tekstinput,BorderLayout.CENTER);
        andre.add(knapperneders,BorderLayout.PAGE_END);



        //panel 3 (vis Utleiere)
        tredje.setLayout(new BorderLayout());
        tredje.add(utskriftsområde2, BorderLayout.PAGE_START);
        knapperneders.setLayout(new GridLayout(2,1));
        knapperneders.setLayout(new GridLayout(2,1));
        JButton tilbake2 = new JButton("Tilbake");
        JButton vis = new JButton("Vis utleiere");
        tredje.add(tilbake2, BorderLayout.BEFORE_LINE_BEGINS);
        tredje.add(vis);

        vis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visPersRegister();
            }
        });

        tilbake2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //panelKontinuelig.add(første,"1");
                cl.show(panelKontinuelig,"1");
            }
        });
        /*tredje.setBackground(Color.yellow);
        for(int i = 0; i < knappenavnpanel1.length; i++){
            knapperpanel1[i] = new JButton(knappenavnpanel1[i]);
            tredje.add(knapperpanel1[i]);
            final String nr = String.valueOf(i+1);
            knapperpanel1[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    cl.show(panelKontinuelig,nr);

                }
            });
        }*/

        //panel4
        fjerde.setLayout(gl);
        fjerde.setBackground(Color.cyan);
        for(int i = 0; i < knappenavnpanel1.length; i++){
            knapperpanel1[i] = new JButton(knappenavnpanel1[i]);
            fjerde.add(knapperpanel1[i]);
            final String nr = String.valueOf(i+1);
            knapperpanel1[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    cl.show(panelKontinuelig,nr);

                }
            });
        }

        //panel5
        femte.setLayout(gl);
        femte.setBackground(Color.BLUE);
        for(int i = 0; i < knappenavnpanel1.length; i++){
            knapperpanel1[i] = new JButton(knappenavnpanel1[i]);
            femte.add(knapperpanel1[i]);
            final String nr = String.valueOf(i+1);
            knapperpanel1[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    cl.show(panelKontinuelig,nr);

                }
            });
        }
        //panel6
        sjette.setLayout(gl);
        sjette.setBackground(Color.DARK_GRAY);
        for(int i = 0; i < knappenavnpanel1.length; i++){
            knapperpanel1[i] = new JButton(knappenavnpanel1[i]);
            sjette.add(knapperpanel1[i]);
            final String nr = String.valueOf(i+1);
            knapperpanel1[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    cl.show(panelKontinuelig,nr);

                }
            });
        }
        //panel7
        syvende.setLayout(gl);
        syvende.setBackground(Color.green);
        for(int i = 0; i < knappenavnpanel1.length; i++){
            knapperpanel1[i] = new JButton(knappenavnpanel1[i]);
            syvende.add(knapperpanel1[i]);
            final String nr = String.valueOf(i+1);
            knapperpanel1[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    cl.show(panelKontinuelig,nr);

                }
            });
        }
        //panel8
        åttende.setLayout(gl);
        åttende.setBackground(Color.pink);
        for(int i = 0; i < knappenavnpanel1.length; i++){
            knapperpanel1[i] = new JButton(knappenavnpanel1[i]);
            åttende.add(knapperpanel1[i]);
            final String nr = String.valueOf(i+1);
            knapperpanel1[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    cl.show(panelKontinuelig,nr);

                }
            });
        }
        //panel9
        niende.setLayout(gl);
        niende.setBackground(Color.gray);
        for(int i = 0; i < knappenavnpanel1.length; i++){
            knapperpanel1[i] = new JButton(knappenavnpanel1[i]);
            niende.add(knapperpanel1[i]);
            final String nr = String.valueOf(i+1);
            knapperpanel1[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    cl.show(panelKontinuelig,nr);

                }
            });
        }
        //panel10
        tiende.setLayout(gl);
        tiende.setBackground(Color.red);
        for(int i = 0; i < knappenavnpanel1.length; i++){
            knapperpanel1[i] = new JButton(knappenavnpanel1[i]);
            tiende.add(knapperpanel1[i]);
            final String nr = String.valueOf(i+1);
            knapperpanel1[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    cl.show(panelKontinuelig,nr);

                }
            });
        }





        //Sender ved defaultpanelt identifisrt av stringen
        cl.show(panelKontinuelig,"1");


        //regler for den kontinuelige rammen kan skiftes her!
        frame.add(panelKontinuelig);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600,600);


    }

    public void nyUtleier()
    {
        String pnr = Pnr.getText();
        String n = Navn.getText();
        String a = Adr.getText();
        String mail = Mail.getText();
        String tlf = Tlf.getText();
        String f = Firma.getText();

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
            visMelding("Utleier registrert\n" + utleier);
            slettFelter();
        }
    }

    public void visPersRegister()
    {
        String liste = pregister.skrivListe();
        utskriftsområde2.setText(liste);
    }

    public void visMelding(String melding){
        utskriftsområde.setText(melding);
    }

    public void slettFelter(){
        Pnr.setText("Personnummer");
        Navn.setText("Navn");
        Adr.setText("Adresse");
        Mail.setText("Email");
        Tlf.setText("Telefonnummer");
        Firma.setText("Firma");
    }








}