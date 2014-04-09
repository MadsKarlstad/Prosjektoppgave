package prosjektoppgave;
/**
 *
 * @author madsmkarlstad (grid layout, grid bag layout, boxlayout - lokerheims forslag)
 */
import java.awt.event.*;

import java.awt.EventQueue;
import javax.swing.JFrame;

//Testing GridLayoutFrame.
public class BoligformidlingDriver
{
    public static void main( String args[] )
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                BoligGUI2 gridLayoutFrame = new BoligGUI2();
                gridLayoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gridLayoutFrame.setSize( 400, 700 );
                gridLayoutFrame.setVisible( true );
            }
        });
    }
}
