/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoligBrowsePromptPANEL extends JPanel implements ActionListener {

    public JPanel overskriftpanel;
    public JPanel feltpanel;
    public JPanel knappepanelpanel;

    private JTextField fødselsnummer;

    private JLabel overskrift;

    private JButton finn;
    private JButton tilbake;

    private MainFrame parent;

    private Personregister pregister;
    private Sokerregister sregister;

    private String fødselsnr;

    public BoligBrowsePromptPANEL(Personregister pregister,Sokerregister sregister,MainFrame parent){

        setLayout(new BorderLayout());
        this.pregister = pregister;
        this.sregister = sregister;
        this.parent = parent;

        initialiser();
        lagGui();

    }


    public void initialiser(){

        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        feltpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        knappepanelpanel = new JPanel(new GridLayout(1,2,5,5));

        fødselsnummer = new JTextField(10);

        overskrift = new JLabel("Skriv inn fødselsnummer");

        finn = new JButton("finn person");
        tilbake = new JButton("tilbake");

        finn.addActionListener(this);
        tilbake.addActionListener(this);

    }
    public void lagGui(){

        overskriftpanel.add(overskrift);
        feltpanel.add(fødselsnummer);
        knappepanelpanel.add(finn);
        knappepanelpanel.add(tilbake);

        add(overskriftpanel,BorderLayout.PAGE_START);
        add(feltpanel, BorderLayout.CENTER);
        add(knappepanelpanel, BorderLayout.PAGE_END);

    }

    public void finnPerson(String pnr){

        if(pregister.finnes(pnr)){
            parent.doClick(3);

        }

        else{
            parent.doClick(4);
        }
    }


    public String getPnr(){
        return fødselsnummer.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == finn){

            finnPerson(fødselsnummer.getText());
            System.out.println("i prompt : " + getPnr());
            System.out.println("metode i prompt : " + getPnr());

        }

        else if(e.getSource() == tilbake){

            parent.visPanel(MainFrame.MAIN_BOARD);

        }

    }
}
