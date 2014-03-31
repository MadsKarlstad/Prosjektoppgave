package prosjektoppgave;
/**
 *
 * @author madsmkarlstad (grid layout, grid bag layout, boxlayout - lokerheims forslag)
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

}
