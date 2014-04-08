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

    public void vinduEn(){

    JFrame ramme = new JFrame("Grid layout start");
    ramme.setVisible(true);
    ramme.setSize(300,400);
    ramme.setLayout(new GridLayout(3,3,15,15));
    ramme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ramme.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");

    ramme.add(button1);
    ramme.add(button2);
    ramme.add(button3);
    ramme.add(button4);
    ramme.add(button5);
    ramme.add(button6);
    ramme.add(button7);
    ramme.add(button8);
    ramme.add(button9);






    }










}
