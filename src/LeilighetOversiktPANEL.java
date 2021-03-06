/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

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
/*
 * Panel som viser oversikt over alle Leiligheter registrert i systemet.
 * Skrevet av Mads Karlstad. Sist oppdatert 08.05.14
 */
public class LeilighetOversiktPANEL extends JPanel implements ActionListener, DocumentListener {
    private JPanel overskriftpanel;
    private JPanel tabellpanel;
    private JPanel søkpanel;
    private JPanel knapppanel;

    private JLabel overskrift;
    private JTextField søkfelt;
    private JTable tabell;
    private JScrollPane scroll;
    private Leilighetmodell modell;
    private final String[] kolonner = {"Adresse", "Boareal", "Antall rom", "Byggeår", "Beskrivelse", "Pris", "Ledig fra","Bolignr","Eier","Ledig","Bydel"};

    private JButton visInfo;
    private JButton fjern;
    private JButton tilbake;

    private LinkedList<Leilighet> leilighetliste;
    private LinkedList<Leilighet> temp;//listen som omfatter søket vårt

    private Utleier utleier;
    private Leilighetregister register;
    private Personregister pregister;
    private MainFrame parent;

    public LeilighetOversiktPANEL(Leilighetregister register, Personregister pregister, MainFrame parent) {

        super(new BorderLayout());
        this.register = register;
        this.parent = parent;

        initialiser();
        lagGUI();
    }
    //Initialiserer alle felter,knapper,iterator,paneler,osv
    public void initialiser() {
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        søkpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tabellpanel = new JPanel(new BorderLayout());
        knapppanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        overskrift = new JLabel("Antall leiligheter: " + register.size());
        søkfelt = new JTextField(10);
        tabell = new JTable();

        søkfelt.getDocument().addDocumentListener(this);

        Iterator it = register.entrySet().iterator();

        leilighetliste = new LinkedList<Leilighet>();

        for(Map.Entry<String,Leilighet> entry : register.entrySet()) {
            leilighetliste.add((Leilighet) entry.getValue());
        }

        modell = new Leilighetmodell(kolonner, leilighetliste);

        tabell.setModel(modell);

        scroll = new JScrollPane(tabell);

        visInfo = new JButton("Vis info");
        fjern = new JButton("Slett");
        tilbake = new JButton("Tilbake");

        visInfo.addActionListener(this);
        fjern.addActionListener(this);
        tilbake.addActionListener(this);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    //Viser alle leiligheter i registeret
    public void visAlle(){
        overskriftpanel.remove(overskrift);
        tabellpanel.remove(scroll);
        scroll.remove(tabell);
        revalidate();

        modell = new Leilighetmodell(kolonner, leilighetliste);

        tabell = new JTable(modell);

        scroll = new JScrollPane(tabell);
        tabellpanel.add(scroll, BorderLayout.CENTER);

        overskrift = new JLabel("Antall leiligheter: " + leilighetliste.size());
        overskriftpanel.add(overskrift);

        revalidate();
        repaint();
    }
    //Oppretter brukergrensesnittet
    public void lagGUI(){
        overskriftpanel.add(overskrift);

        søkpanel.add(søkfelt);

        tabellpanel.add(søkpanel, BorderLayout.PAGE_START);
        tabellpanel.add(scroll, BorderLayout.CENTER);

        knapppanel.add(visInfo);
        knapppanel.add(fjern);
        knapppanel.add(tilbake);

        add(overskriftpanel, BorderLayout.PAGE_START);
        add(tabellpanel, BorderLayout.CENTER);
        add(knapppanel, BorderLayout.PAGE_END);

        knapppanel.setBackground(Color.decode("#DAEDF5"));
        overskriftpanel.setBackground(Color.decode("#DAEDF5"));
        //tabellpanel.setBackground(Color.decode("#DAEDF5"));
        søkpanel.setBackground(Color.decode("#DAEDF5"));
        setBackground(Color.decode("#DAEDF5"));
    }
    //Metode som søker gjennom listen av leiligheter og viser leiligheter som matcher brukerens input
    public void søk(){

        temp = new LinkedList<Leilighet>();

        søkfelt.setBackground(Color.WHITE);

        String søk = søkfelt.getText().toUpperCase();

        try{

            if (søk.substring(0,søk.length() - 1).matches("[0-9]+") && søk.substring(søk.length() - 1).equals(".")){

                String bolignr = søk.substring(0,søk.length() - 1);

                Leilighet leilighet = (Leilighet) register.getObject(bolignr);

                if(leilighet != null){

                    temp.add(leilighet);
                    overskriftpanel.remove(overskrift);
                    tabellpanel.remove(scroll);
                    scroll.remove(tabell);
                    revalidate();

                    modell = new Leilighetmodell(kolonner, temp);

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

        Iterator it = leilighetliste.iterator();

        while(it.hasNext()){

            Leilighet leilighet = (Leilighet) it.next();

            String adresse = leilighet.getAdresse().toUpperCase();
            String ledigfra = leilighet.getLedigDato();
            String bolignr = leilighet.getBolignr().toUpperCase();
            utleier = leilighet.getEier();
            String utleierfornavn = utleier.getFornavn().toUpperCase();
            String utleieretternavn = utleier.getEtternavn().toUpperCase();
            String beskrivelse = leilighet.getBeskrivelse();
            String byggår = String.valueOf(leilighet.getByggår());
            String pris = String.valueOf(leilighet.getPris());
            String ledig = leilighet.getLedigTekst();
            String bydel = leilighet.getBydel().toUpperCase();


            if(adresse.startsWith(søk) || bolignr.startsWith(søk) || utleierfornavn.startsWith(søk) || utleieretternavn.startsWith(søk)
                    || ledigfra.startsWith(søk) || beskrivelse.startsWith(søk) || byggår.startsWith(søk) || pris.startsWith(søk)
                    || ledig.startsWith(søk) || bydel.startsWith(søk)
                    ){
                temp.add(leilighet);
            }
        }

        overskriftpanel.remove(overskrift);
        tabellpanel.remove(scroll);
        scroll.remove(tabell);
        revalidate();

        modell = new Leilighetmodell(kolonner, temp);

        tabell = new JTable(modell);

        scroll = new JScrollPane(tabell);
        tabellpanel.add(scroll, BorderLayout.CENTER);

        overskrift = new JLabel("Søk etter: " + søkfelt.getText() + ", ga " + temp.size() + " resultater");
        overskriftpanel.add(overskrift);

        revalidate();
        repaint();
    }
    //Metode for å slette en leilighet. Tar parameter int Rad, som er raden brukeren har markert, som igjen gir tilgang til riktig objekt
    public void slettLeilighet(int rad){
        int svar = JOptionPane.showOptionDialog(null,"Vil du slette leiligheten?","Bekreft sletting",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(svar==JOptionPane.YES_OPTION){
            rad = tabell.getSelectedRow();
            try{
                Leilighet leilighet = modell.getValueAt(rad);
                String bolignr = leilighet.getBolignr();
                if(leilighet.erUtleid() == true){
                    JOptionPane.showMessageDialog(null,"Leiligheten er utleid og kan ikke slettes");
                }
                else{
                    modell.delRow(rad);
                    register.fjern(bolignr);
                    Utleier eier = leilighet.getEier();
                    eier.removeBolig(leilighet);
                    modell.fireTableDataChanged();
                    
                }
            }
            catch(IndexOutOfBoundsException ioobe){
                JOptionPane.showMessageDialog(null,"Ingen bolig markert/registrert");
            }
            catch (NullPointerException npe){

            }
        }
        if(svar==JOptionPane.NO_OPTION){
            JOptionPane.showMessageDialog(null,"Sletting avbrutt");
        }
    }

    public void endreLeilighet(){
        JOptionPane.showMessageDialog(null,"Not yet supported");
    }
    //Metode som viser mer informasjon om leiligheten. Tar int rad som parameter, som gir oss tilgang til objektet ut ifra tabellen
    public void visInfo(int rad){
        try{
            Leilighet leilighet = modell.getValueAt(rad);
            String s = "";
            s+="Dette er en " + leilighet.getBeskrivelse()+" leilighet på "+leilighet.getBoareal() + " kvadratmeter" + ", som ligger i " + leilighet.getAdresse()+ ", " + leilighet.getBydel();
            s+="\nBolignummer: " + leilighet.getBolignr();
            s+="\nEneboligen ble bygget i "+leilighet.getByggår();
            s+="\n\nEneboligen har følgene fasiliteter:";
            s+="\nAntall rom: "+leilighet.getAntallRom();
            s+="\nAntall boder: " + leilighet.getAntallBoder();
            s+="\nEtasje: " + leilighet.getEtasje();
            s+="\nParkering: " + leilighet.parkering();
            s+="\nHeis: " + leilighet.isHeis();
            s+="\nTerasse: " + leilighet.terasse();
            s+="\nBalkong: " + leilighet.balkong();
            s+="\nTillater røyking: " + leilighet.røyketekst();
            s+="\nTV inkludert: " + leilighet.tvInkludert();
            s+="\nInternett inkludert: " + leilighet.internettInkludert();
            s+="\nStrøm inkludert: " + leilighet.strømInkludert();
            s+="\n\nEneboligen er ledig fra og med " + leilighet.getLedigDato();
            s+="\nKontakt eier, " + leilighet.getEiersNavn() + ", gjennom BoligBrowse for å registrere ønske om å leie denne eneboligen";
            JOptionPane.showMessageDialog(null,s);
        }
        catch(IndexOutOfBoundsException ioobe){
            JOptionPane.showMessageDialog(null,"Ingen bolig markert/registrert");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tilbake){
                        parent.visPanel(MainFrame.MAIN_BOARD);
            parent.Size();
        }
        else if(e.getSource() == fjern){
            int rad = tabell.getSelectedRow();
            slettLeilighet(rad);
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
