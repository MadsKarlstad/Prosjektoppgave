import javax.swing.*;

/**
 * Created by Erlend on 01/04/14.
 */
import java.awt.event.*;

public class Main
{
    public static void main(String[]args)
    {
        final EnkelGui vindu = new EnkelGui();
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