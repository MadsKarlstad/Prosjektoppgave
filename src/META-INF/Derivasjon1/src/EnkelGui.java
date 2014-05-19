//Leser en fil tegn for tegn og skriver ut tegn for tegn
//til output-vinduet.
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class EnkelGui extends JFrame
{
    private JTextField input;
    private JTextArea output;

    public EnkelGui()
    {
        super( "Visning av tekstfil" );
        Container c = getContentPane();
        c.setLayout( new FlowLayout() );
        c.add( new JLabel( "Viser innhold av valgt tekstfil." ) );
        c.add( new JLabel( "Velg fil: " ) );
        input = new JTextField( 30 );
        input.addActionListener( new Inputlytter() );
        c.add( input );
        c.add( new JLabel( "Filinnhold" ) );
        output = new JTextArea( 20, 50 );
        output.setEditable( false );
        c.add( new JScrollPane( output ) );
        setSize( 600, 500 );
        setVisible( true );
    }



    public void idPattern(String s){

        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(s);

        while (m.find()) {

            output.append(m.group());

            int v = Integer.parseInt(m.group());

            Regner(v);





        }

    }

    private class Inputlytter implements ActionListener
    {
        public void actionPerformed( ActionEvent e )
        {

                String fil = input.getText();


                idPattern( fil );





    }
}


    public void Regner(int i){


         int x = i;

         int hjelp = x;


        System.out.println(x);







    }

}