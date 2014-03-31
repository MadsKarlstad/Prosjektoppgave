package prosjektoppgave;
/**
 *
 * @author madsmkarlstad
 */
import java.awt.event.*;

public class BoligformidlingDriver 
{
    public static void main(String[]args)
    {
        final BoligGUI vindu = new BoligGUI();
        vindu.addWindowListener(
        new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        
    }

}//HEI ERLEND!!
