package prosjektoppgave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 *
 * @author madsmkarlstad
 */
public class BoligGUI extends JFrame
{
    private JTextField personNummer, navn, adresse, email, telefonNummer, firma,
                    sivilstatus, yrke, bAdresse, boAreal, antallRom, byggeÅr,
                    utleiePris, boligNr,etg,leiedato,beskrivelse,kjeller,tm2;
    private JButton regUtleier,regBoligsøker, regEnebolig,regLeil, visBolig, 
                    visPerson, finnBolig, finnPerson,
                    slettBolig, slettPerson;
    private JTextArea utskriftsområde;
    private Boligregister bregister = new Boligregister();
    private Personregister pregister = new Personregister();
    
    public BoligGUI()
    {
        super("BoligGUI");
     
        personNummer = new JTextField(18);
        navn = new JTextField(18);
        adresse = new JTextField(18);
        email = new JTextField(18);
        telefonNummer = new JTextField(18);
        firma = new JTextField(18);
        sivilstatus = new JTextField(18);
        yrke = new JTextField(18);
        bAdresse = new JTextField(18);
        boAreal = new JTextField(18);
        antallRom = new JTextField(18);
        byggeÅr = new JTextField(18);
        beskrivelse = new JTextField(18);
        leiedato = new JTextField(18);
        kjeller = new JTextField(18);
        tm2 = new JTextField(18);
        utleiePris = new JTextField(18);
        boligNr = new JTextField(18);
        etg = new JTextField(18);
        regUtleier = new JButton("Registrer utleier");
        regBoligsøker = new JButton("Registrer boligsøker");
        regEnebolig = new JButton("Registrer enebolig");
        regLeil = new JButton("Registrer leilighet");
        visBolig = new JButton("Vis boliger");
        visPerson = new JButton("Vis personer");
        finnBolig = new JButton("Finn bolig");
        finnPerson = new JButton("Finn person");
        slettBolig = new JButton("Slett bolig");
        slettPerson = new JButton("Slett person");
        utskriftsområde = new JTextArea(15,45);
        utskriftsområde.setEditable(false);
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        c.add(new JLabel("Personnummer: "));
        c.add(personNummer);
        c.add(new JLabel("Navn: "));
        c.add(navn);
        c.add(new JLabel("Adresse:"));
        c.add(adresse);
        c.add(new JLabel("Email: "));
        c.add(email);
        c.add(new JLabel("Telefonnummer: "));
        c.add(telefonNummer);
        c.add(new JLabel("Firma: "));
        c.add(firma);
        c.add(new JLabel("Sivilstatus: "));
        c.add(sivilstatus);
        c.add(new JLabel("Yrke:"));
        c.add(yrke);
        c.add(new JLabel("Boligadresse: "));
        c.add(bAdresse);
        c.add(new JLabel("Boareal:"));
        c.add(boAreal);
        c.add(new JLabel("Antall rom: "));
        c.add(antallRom);
        c.add(new JLabel("Bygget år: "));
        c.add(byggeÅr);
        c.add(new JLabel("Utleiepris: "));
        c.add(utleiePris);
        c.add(new JLabel("Utleiedato: "));
        c.add(leiedato);
        c.add(new JLabel("Etasje: "));
        c.add(etg);
        c.add(new JLabel("Bolignummer: "));
        c.add(boligNr);
        c.add(new JLabel("Kort beskrivelse: "));
        c.add(beskrivelse);
        c.add(regUtleier);
        c.add(regBoligsøker);
        c.add(regEnebolig);
        c.add(regLeil);
        c.add(visBolig);
        c.add(visPerson);
        c.add(finnBolig);
        c.add(finnPerson);
        c.add(slettBolig);
        c.add(slettPerson);
        c.add(new JScrollPane(utskriftsområde));
        
        Lytter lytter = new Lytter();
        
        regUtleier.addActionListener(lytter);
        regBoligsøker.addActionListener(lytter);
        regEnebolig.addActionListener(lytter);
        regLeil.addActionListener(lytter);
        visBolig.addActionListener(lytter);
        visPerson.addActionListener(lytter);
        finnBolig.addActionListener(lytter);
        finnPerson.addActionListener(lytter);
        slettBolig.addActionListener(lytter);
        slettPerson.addActionListener(lytter);
        setSize(700,600);
        setVisible(true);
    }
    public void nyUtleier()
    {
        String pnr = personNummer.getText();
        String n = navn.getText();
        String a = adresse.getText();
        String mail = email.getText();
        String tlf = telefonNummer.getText();
        String f = firma.getText();
        
        if(pnr.length() == 0 || n.length() == 0
           || a.length() == 0 || mail.length() == 0
           || tlf.length() == 0)
        {
            visMelding("Fyll ut all info pliz");
        }
        if(pnr.length() != 0 || n.length() != 0
           || a.length() != 0 || mail.length() != 0
           || tlf.length() != 0)
        {
            pregister.settInn(
                new Utleier(pnr,n,a,mail,tlf,f));
            Person utleier = pregister.getPerson(pnr);
            //JOptionPane.showMessageDialog(null,"Utleier registrert");
            slettFelter();
        }
    }
    public void nyBoligsøker()
    {
        String pnr = personNummer.getText();
        String n = navn.getText();
        String a = adresse.getText();
        String mail = email.getText();
        String tlf = telefonNummer.getText();
        
        
    }
    public void finnPerson()
    {
        String pnr = personNummer.getText();
        if(pnr.length()!=0)
        {
            Person person = pregister.getPerson(pnr);
            utskriftsområde.setText(person.toString());
            //JOptionPane.showMessageDialog(null, person);
        }
    }
    public void fjernPerson()
    {
        String pnr = personNummer.getText();
        if(pnr.length()!=0)
        {
            pregister.fjernPerson(pnr);
        }
    }

    public void nyEnebolig()
    {
        String pnr = personNummer.getText();
        String bnr = boligNr.getText();
        String adr = bAdresse.getText();
        String m2 = boAreal.getText();
        String antrom = antallRom.getText();
        String bår = byggeÅr.getText();
        String pris = utleiePris.getText();
        String e = etg.getText();
        String k = kjeller.getText();
        String b = beskrivelse.getText();
        String d = leiedato.getText();
        String tomt = tm2.getText();
        
        if(pnr.length()!=0 || bnr.length() != 0 || adr.length() != 0
           || m2.length() != 0 || bår.length() != 0
           || pris.length() != 0 || e.length() != 0
           || antrom.length() != 0 || k.length()!=0
           || b.length()!=0 || d.length()!=0 || tomt.length()!=0)
        try{
            Person person = pregister.getPerson(pnr);
            if(bregister.getBolig(bnr)==null);
            {
                person.settInnBolig(
                    new Enebolig(bnr,adr,m2,antrom,bår,b,pris,d,e,k,tomt));
                Bolig enebolig = bregister.getBolig(bnr);
                //JOptionPane.showMessageDialog(null,"Bolig registrert");
                finnBolig();
                slettFelter();
                
            }
        }
        catch(NullPointerException NPE)
        {
            visMelding("NullPointer!");
        } 
    }
    public void nyLeilighet()
    {
        visMelding("Not supported yet");
    }
    public void finnBolig()
    {
        String bnr = boligNr.getText();
        if(bnr.length()!=0)
        {
            Bolig bolig = bregister.getBolig(bnr);
            utskriftsområde.setText(bolig.toString());       
        }
    }
    public void visBoligRegister()
    {
        String boligliste = bregister.skrivListe();
        utskriftsområde.setText(boligliste);
    }
        public void visPersRegister()
    {
        String liste = pregister.skrivListe();
        utskriftsområde.setText(liste);
    }
    private void visMelding(String melding)
    {
        JOptionPane.showMessageDialog(this,melding);
    }
    private class Lytter implements ActionListener
    {
        public void actionPerformed( ActionEvent e )
        {
            if ( e.getSource() == regUtleier )
                nyUtleier();
            else if(e.getSource() == regBoligsøker)
                nyBoligsøker();
            else if(e.getSource() == regEnebolig)
                nyEnebolig();
            else if(e.getSource() == finnPerson)
                finnPerson();
            else if(e.getSource() == slettPerson)
                fjernPerson();
            else if(e.getSource() == finnBolig)
                finnBolig();
            else if(e.getSource() == visPerson)
                visPersRegister();
            else if(e.getSource() == visBolig)
                visBoligRegister();
        }
    }
    private void slettFelter()
    {
        personNummer.setText("");
        navn.setText("");
        adresse.setText("");
        email.setText("");
        telefonNummer.setText("");
        firma.setText("");
        sivilstatus.setText("");
        boligNr.setText("");
        bAdresse.setText("");
        boAreal.setText("");
        antallRom.setText("");
        byggeÅr.setText("");
        utleiePris.setText("");
        etg.setText("");
        kjeller.setText("");
        beskrivelse.setText("");
        leiedato.setText("");
        tm2.setText("");
    }
}
