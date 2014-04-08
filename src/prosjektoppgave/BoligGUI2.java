package prosjektoppgave;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BoligGUI2 extends JFrame implements ActionListener{

    //datafelt for layoutshit
    private JButton knappene[];
    private boolean toggle = true;
    private String navnarray[]= {"reg utleier","reg søker","vis boliger",
            "vis utleiere","vis søkere","BoligBrowse","vis kontrakt","stats","reg enebolig"};
    private Container c;
    private GridLayout layout; //her åner vi for å legge inn de 9 layoutene ve trenger til vinduene våre
    private BoligGUI boliggui;

    public BoligGUI2(){

        super("BoligBrowse(tm)");
        layout = new GridLayout();
        boliggui = new BoligGUI();
        c = getContentPane();
        c.setLayout(layout);
        layout = new GridLayout(3,3,10,10);
        knappene = new JButton[navnarray.length];

        for(int i = 0; i < navnarray.length; i++){

            knappene[i] = new JButton(navnarray[i]);
            knappene[i].addActionListener(this);
            c.add(knappene[i]);
        }
    }


        public void actionPerformed( ActionEvent e )
        {
            if( toggle )
                c.setLayout(boliggui);
            else
                c.setLayout(layout);

            toggle = !toggle;
            c.validate();
        }
    }