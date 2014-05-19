/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;
/*
 *Driverklassen for programmet. Skrevet av Mads Karlstad,Christoffer Jønsberg og Erlend Westbye. Sist oppdatert 14.05.14
 */
public class BoligBrowse {

    private static Personregister register;
    private static Sokerregister sregister;
    private static Boligregister bregister;
    private static Leilighetregister legister;
    private static Kontraktregister kregister;

    public static void main(String[] args) {

        String erwindows = System.getProperty("os.name");

       if(!erwindows.equals("Mac OS X")){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
               
            }
        }}

        lesAlle();

        final MainFrame frame;
        frame = new MainFrame(register, bregister, sregister, legister, kregister);


        
        final int UTLEIER = 0;
        final int SOKER = 1;
        final int ENEBOLIG = 2;
        final int LEILIGHET = 3;
        final int KONTRAKT = 4;

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;

       
        frame.setVisible(true);

        frame.setSize(bredde / 2, høyde - 100);

        frame.setLocation(skjerm.width / 2 - frame.getSize().width / 2, skjerm.height / 2 - frame.getSize().height / 2);

        frame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        skrivAlle();
                        System.exit(0);
                    }
                }
        );
    }

    public static void lesPersonregister() {
        try (ObjectInputStream innfil = new ObjectInputStream(new FileInputStream("utleierliste.data"))) {
            register = (Personregister) innfil.readObject();
            System.out.println("Personregister ble lastet!");
        } catch (ClassNotFoundException ce) {

            register = new Personregister();
            System.out.println("Nytt personregister ble opprettet!");
        } catch (IOException ioe) {
            register = new Personregister();
            System.out.println("Nytt personregister ble opprettet!");
        }
    }

    public static void lesSokerregister() {
        try (ObjectInputStream innfil = new ObjectInputStream(new FileInputStream("sokerliste.data"))) {
            sregister = (Sokerregister) innfil.readObject();
            System.out.println("Søkerregister ble lastet!");
        } catch (ClassNotFoundException ce) {
            System.out.println("Søkerregister ble ikke funnet, opppretter nytt register!");
            sregister = new Sokerregister();
        } catch (IOException ioe) {

            sregister = new Sokerregister();
            System.out.println("Fant ikke filen sokerliste.data, oppretter nytt register!");
        }
    }

    public static void lesBoligregistrer() {
        try (ObjectInputStream innfil = new ObjectInputStream(new FileInputStream("eneboligliste.data"))) {
            bregister = (Boligregister) innfil.readObject();
            System.out.println("Boligregister ble lastet!");
        } catch (ClassNotFoundException ce) {
            System.out.println("Boligregister ble ikke funnet! Oppretter nytt register!");
            bregister = new Boligregister();
        } catch (IOException ioe) {
            System.out.println("Fant ikke filen eneboligliste.data, oppretter nytt register!");
            bregister = new Boligregister();
        }
    }

    public static void lesLeilighhetsregister() {
        try (ObjectInputStream innfil = new ObjectInputStream(new FileInputStream("leilighetliste.data"))) {
            legister = (Leilighetregister) innfil.readObject();
            System.out.println("Leilighetsliste ble lastet!");
        } catch (ClassNotFoundException ce) {
            legister = new Leilighetregister();
        } catch (IOException ioe) {
            legister = new Leilighetregister();
        }
    }

    public static void lesKontraktregister() {
        try (ObjectInputStream innfil = new ObjectInputStream(new FileInputStream("kontraktliste.data"))) {
            kregister = (Kontraktregister) innfil.readObject();
            System.out.println("Kontraktregister ble lastet!");
        } catch (ClassNotFoundException ce) {
            kregister = new Kontraktregister();
        } catch (IOException ioe) {
            kregister = new Kontraktregister();
        }
    }

    /**
     * Skriver personregister til fil
     */
    public static void skrivPersonregister() {
        try (ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream("utleierliste.data"))) {
            utfil.writeObject(register);
            System.out.println("Personregister ble lagret!");
        } catch (IOException ioe) {
            System.out.println("Personregister ble ikke lagret!");
        }
    }

    public static void skrivSokerregister() {
        try (ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream("sokerliste.data"))) {
            utfil.writeObject(sregister);
            System.out.println("Søkerliste ble lagret!");
        } catch (IOException ioe) {

        }
    }

    public static void skrivBoligregister() {
        try (ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream("eneboligliste.data"))) {
            utfil.writeObject(bregister);
            System.out.println("Boligregisre ble lagret!");
        } catch (IOException ioe) {
        }
    }

    public static void skrivLeilighetregister() {
        try (ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream("leilighetliste.data"))) {
            utfil.writeObject(legister);
            System.out.println("Leilighetsregister ble lagret!!!");
        } catch (IOException ioe) {
        }
    }

    public static void skrivKontraktregister() {
        try (ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream("kontraktliste.data"))) {
            utfil.writeObject(kregister);
            System.out.println("Kontraktregister ble lagret!");
        } catch (IOException ioe) {

        }
    }
    /**
     * laster alle register fra fil
     */

    public static void lesAlle() {
        lesPersonregister();
        lesSokerregister();
        lesBoligregistrer();
        lesLeilighhetsregister();
        lesKontraktregister();
    }

    /**
     * skriver alle register til fil
     */
    public static void skrivAlle() {
        skrivPersonregister();
        skrivSokerregister();
        skrivBoligregister();
        skrivLeilighetregister();
        skrivKontraktregister();
    }
}
