/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class BoligOversiktPANEL extends JPanel implements ActionListener, DocumentListener {
    private JPanel overskriftpanel;
    private JPanel tabellpanel;
    private JPanel søkpanel;
    private JPanel knapppanel;

    private JLabel overskrift;
    private JTextField søkfelt;
    private JTable tabell;
    private JScrollPane scroll;
    private Eneboligmodell modell;
    private final String[] kolonner = {"Adresse", "Boareal", "Antall rom", "Byggeår", "Beskrivelse", "Pris", "Ledig fra","Bolignr","Røyker","Eier","Ledig","Bydel","Husdyr","TV inkludert","Strøm inkludert"};

    private JButton visInfo;
    private JButton endre;
    private JButton fjern;
    private JButton tilbake;

    private LinkedList<Enebolig> eneboligliste;
    private LinkedList<Enebolig> temp;//listen som omfatter søket vårt

    private Utleier utleier;
    private Boligregister register;
    private Personregister pregister;
    private MainFrame parent;

    public BoligOversiktPANEL(Boligregister register, Personregister pregister, MainFrame parent) {

        super(new BorderLayout());
        this.register = register;
        this.pregister = pregister;
        this.parent = parent;

        initialiser();
        lagGUI();
    }

    public void initialiser() {
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        søkpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tabellpanel = new JPanel(new BorderLayout());
        knapppanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        overskrift = new JLabel("Antall eneboliger: " + register.size());
        søkfelt = new JTextField(10);
        tabell = new JTable();

        søkfelt.getDocument().addDocumentListener(this);

        Iterator it = register.entrySet().iterator();

        eneboligliste = new LinkedList<Enebolig>();

        for(Map.Entry<String,Enebolig> entry : register.entrySet()) {
            eneboligliste.add((Enebolig) entry.getValue());
        }

        modell = new Eneboligmodell(kolonner, eneboligliste);

        tabell.setModel(modell);

        scroll = new JScrollPane(tabell);

        visInfo = new JButton("Vis info");
        endre = new JButton("Endre");
        fjern = new JButton("Slett");
        tilbake = new JButton("Tilbake");

        visInfo.addActionListener(this);
        endre.addActionListener(this);
        fjern.addActionListener(this);
        tilbake.addActionListener(this);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void visAlle(){
        overskriftpanel.remove(overskrift);
        tabellpanel.remove(scroll);
        scroll.remove(tabell);
        revalidate();

        modell = new Eneboligmodell(kolonner, eneboligliste);

        tabell = new JTable(modell);

        scroll = new JScrollPane(tabell);
        tabellpanel.add(scroll, BorderLayout.CENTER);

        overskrift = new JLabel("Antall eneboliger: " + eneboligliste.size());
        overskriftpanel.add(overskrift);

        revalidate();
        repaint();
    }


    public void lagGUI(){
        overskriftpanel.add(overskrift);

        søkpanel.add(søkfelt);

        tabellpanel.add(søkpanel, BorderLayout.PAGE_START);
        tabellpanel.add(scroll, BorderLayout.CENTER);

        knapppanel.add(visInfo);
        knapppanel.add(fjern);
        knapppanel.add(tilbake);
        knapppanel.add(endre);

        add(overskriftpanel, BorderLayout.PAGE_START);
        add(tabellpanel, BorderLayout.CENTER);
        add(knapppanel, BorderLayout.PAGE_END);

        knapppanel.setBackground(Color.decode("#DAEDF5"));
        overskriftpanel.setBackground(Color.decode("#B3D5E3"));
        //tabellpanel.setBackground(Color.decode("#DAEDF5"));
        søkpanel.setBackground(Color.decode("#DAEDF5"));
        setBackground(Color.decode("#B3D5E3"));
    }

    public void søk(){

        temp = new LinkedList<Enebolig>();

        søkfelt.setBackground(Color.WHITE);

        String søk = søkfelt.getText().toUpperCase();

        try{

            if (søk.substring(0,søk.length() - 1).matches("[0-9]+") && søk.substring(søk.length() - 1).equals(".")){

                String bolignr = søk.substring(0,søk.length() - 1);

                Enebolig enebolig = (Enebolig) register.getObject(bolignr);

                if(enebolig != null){

                    temp.add(enebolig);
                    overskriftpanel.remove(overskrift);
                    tabellpanel.remove(scroll);
                    scroll.remove(tabell);
                    revalidate();

                    modell = new Eneboligmodell(kolonner, temp);

                    tabell = new JTable(modell);

                    scroll = new JScrollPane(tabell);
                    tabellpanel.add(scroll, BorderLayout.CENTER);

                    overskrift = new JLabel("Søk etter: " + søkfelt.getText() + ", ga " + temp.size() + " resultater");
                    overskriftpanel.add(overskrift);

                    revalidate();
                    repaint();

                    return;
                }
            }
        }

        catch (IndexOutOfBoundsException x){

        }

        if(søk.length()== 0){
            visAlle();
            return;
        }

        Iterator it = eneboligliste.iterator();

        while(it.hasNext()){

            Enebolig enebolig = (Enebolig) it.next();

            String adresse = enebolig.getAdresse().toUpperCase();
            boolean røyke = enebolig.røyke();
            String ledigfra = enebolig.getLedigDato();
            String bolignr = enebolig.getBolignr().toUpperCase();
            utleier = enebolig.getEier();
            String utleierfornavn = utleier.getFornavn().toUpperCase();
            String utleieretternavn = utleier.getEtternavn().toUpperCase();
            String bydel = enebolig.getBydel().toUpperCase();

            if(adresse.startsWith(søk) || bolignr.startsWith(søk) || utleierfornavn.startsWith(søk) || utleieretternavn.startsWith(søk)
                    ||bydel.startsWith(søk)){

                temp.add(enebolig);
            }
        }

        if(temp.isEmpty()){

            søkfelt.setBackground(Color.decode("#fd6d6d"));
        }

        overskriftpanel.remove(overskrift);
        tabellpanel.remove(scroll);
        scroll.remove(tabell);
        revalidate();

        modell = new Eneboligmodell(kolonner, temp);

        tabell = new JTable(modell);

        scroll = new JScrollPane(tabell);
        tabellpanel.add(scroll, BorderLayout.CENTER);

        overskrift = new JLabel("Søk etter: " + søkfelt.getText() + ", ga " + temp.size() + " resultater");
        overskriftpanel.add(overskrift);

        revalidate();
        repaint();
    }

    public void slettEnebolig(int rad){
        int svar = JOptionPane.showOptionDialog(null,"Vil du slette eneboligen?","Bekreft sletting",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(svar==JOptionPane.YES_OPTION){

            rad = tabell.getSelectedRow();
            try{
                Enebolig enebolig = modell.getValueAt(rad);
                String bolignr = enebolig.getBolignr();
                    if(enebolig.erUtleid() == true){
                        JOptionPane.showMessageDialog(null,"Boligen er utleid og kan ikke slettes.");
                    }
                    else {
                        modell.delRow(rad);
                        register.fjern(bolignr);
                        parent.skrivTilFil(enebolig);
                    }
                }
            catch (IndexOutOfBoundsException ioobe){
                JOptionPane.showMessageDialog(null,"Ingen boliger markert/registrert");
            }

        }


        if(svar==JOptionPane.NO_OPTION){
            JOptionPane.showMessageDialog(null,"Sletting avbrutt");
        }
    }

    public void endreBolig(){
        JOptionPane.showMessageDialog(null,"Not yet supported");
    }

    public void visInfo(int rad){
        try{
            Enebolig enebolig = modell.getValueAt(rad);
            String s = "";
            s+="Dette er en " + enebolig.getBeskrivelse()+" enebolig på "+enebolig.getBoareal() + " kvadratmeter" + ", som ligger i " + enebolig.getAdresse()+ ", " + enebolig.getBydel();
            s+="\nEneboligen ble bygget i "+enebolig.getByggår();
            s+="\n\nEneboligen har følgene fasiliteter:";
            s+="\nAntall rom: "+enebolig.getAntallRom();
            s+="\nAntall bad: "+enebolig.getAntallBad();
            s+="\nAntall etasjer: "+enebolig.getAntallEtasjer();
            s+="\nTV inkludert: " + enebolig.tvTekst();
            s+="\nStrøm inkludert: " + enebolig.strømTekst();
            s+="\nInternett inkludert: " + enebolig.internettInkludert();
            s+="\nParkering: " + enebolig.parkering();
            s+="\nTillater husdyr: " + enebolig.husdyrTekst();
            s+="\nTillater røyking: " + enebolig.røyketekst();
            s+="\n\nEneboligen er ledig fra og med " + enebolig.getLedigDato();
            s+="\nKontakt eier, " + enebolig.getEiersNavn() + ", gjennom BoligBrowse for å registrere ønske om å leie denne eneboligen";
            JOptionPane.showMessageDialog(null,s);
        }
        catch(IndexOutOfBoundsException ioobe){
            JOptionPane.showMessageDialog(null,"Ingen bolig markert/registrert");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tilbake){
            parent.visPanel("VIS PROMPT");
            
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension skjerm = kit.getScreenSize();
            int bredde = skjerm.width;
            int høyde = skjerm.height;
        
            parent.setSize(bredde/2, høyde-100);
            parent.setLocation(skjerm.width / 2 - parent.getSize().width / 2, skjerm.height / 2 - parent.getSize().height / 2);
        }
        else if(e.getSource() == fjern) {
            int rad = tabell.getSelectedRow();
            slettEnebolig(rad);
        }
        else if(e.getSource() == endre){
            endreBolig();
        }
        else if(e.getSource() == visInfo){
            int rad = tabell.getSelectedRow();
            visInfo(rad);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        søk();
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        søk();
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        søk();
    }
}
