package prosjektoppgave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by madsmkarlstad on 07/04/14.
 *
 Vinduer vi må prooge;

 Registrer utleier
 Registrer søker
 vis boligliste
 vis utleier
 vis Søker
 BoligBrowse
 vis Kontrakter
 Statistikk

 ref: http://i.imgur.com/uYbOZHV.png


 */

public class BoligGUI2{

    //vi trenger et vindu som har lik størrelse heile tiden!!!!!!!

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;




    public void vinduEn(){

    JFrame ramme = new JFrame("Grid layout start");
    ramme.setVisible(true);
    ramme.setSize(300,400);
    ramme.setLayout(new GridLayout(3,3,15,15));
    ramme.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        button1 = new JButton("1");
        button2 = new JButton("2");
        button3 = new JButton("3");
        button4 = new JButton("4");
        button5 = new JButton("5");
        button6 = new JButton("6");
        button7 = new JButton("7");
        button8 = new JButton("8");
        button9 = new JButton("9");






    }










}
