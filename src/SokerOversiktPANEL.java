/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Erlend on 22/04/14.
 */
public class SokerOversiktPANEL extends JPanel implements ActionListener, DocumentListener {
    private JPanel overskriftpanel;
    private JPanel tabellpanel;
    private JPanel søkpanel;
    private JPanel knapppanel;

    private JLabel overskrift;
    private JTextField søkfelt;
    private JTable tabell;
    private JScrollPane scroll;
    private Sokermodell modell;
    private final String[] kolonner = {"Fødselsnummer", "Fornavn", "Etternavn", "Adresse", "Mail", "Telefonnummer","Røyker","Husdyr","Min. pris","Maks. pris"};

    private JButton visInfo;
    private JButton endre;
    private JButton fjern;
    private JButton tilbake;

    private LinkedList<Soker> søkerliste;
    private LinkedList<Soker> temp;//listen som omfatter søket vårt


    private Sokerregister pregister;
    private MainFrame parent;

    public SokerOversiktPANEL(Sokerregister register, MainFrame parent) {

        super(new BorderLayout());
        this.pregister = register;
        this.parent = parent;

        initialiser();
        lagGUI();
        
        parent.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void initialiser() {
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        søkpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tabellpanel = new JPanel(new BorderLayout());
        knapppanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        overskrift = new JLabel("Antall søkere: " + pregister.size());
        søkfelt = new JTextField(10);
        tabell = new JTable();

        søkfelt.getDocument().addDocumentListener(this);

        Iterator it = pregister.entrySet().iterator();

        søkerliste = new LinkedList<Soker>();

        for(Map.Entry<String,Soker> entry : pregister.entrySet()) {
            søkerliste.add((Soker) entry.getValue());
        }

        modell = new Sokermodell(kolonner, søkerliste);

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
    }

    public void visAlle(){
        overskriftpanel.remove(overskrift);
        tabellpanel.remove(scroll);
        scroll.remove(tabell);
        revalidate();

        modell = new Sokermodell(kolonner, søkerliste);

        tabell = new JTable(modell);

        scroll = new JScrollPane(tabell);
        tabellpanel.add(scroll, BorderLayout.CENTER);

        overskrift = new JLabel("Antall søkere: " + søkerliste.size());
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
    }

    public void søk(){

        temp = new LinkedList<Soker>();

        søkfelt.setBackground(Color.WHITE);

        String søk = søkfelt.getText().toUpperCase();

        try{

            if (søk.substring(0,søk.length() - 1).matches("[0-9]+") && søk.substring(søk.length() - 1).equals(".")){

                String fødselsnummer = søk.substring(0,søk.length() - 1);

                Soker søker = (Soker) pregister.getObject(fødselsnummer);

                if(søker != null){

                    temp.add(søker);
                    overskriftpanel.remove(overskrift);
                    tabellpanel.remove(scroll);
                    scroll.remove(tabell);
                    revalidate();

                    modell = new Sokermodell(kolonner, temp);

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

        Iterator it = søkerliste.iterator();

        while(it.hasNext()){

            Soker søker = (Soker) it.next();

            String fødselsnummer = søker.getFødselsnummer().toUpperCase();
            String fornavn = søker.getFornavn().toUpperCase();
            String etternavn = søker.getEtternavn().toUpperCase();
            String adresse = søker.getAdresse().toUpperCase();
            String mail = søker.getMail().toUpperCase();
            String telfonnummer = søker.getTelefonnummer().toUpperCase();
            String navn = søker.getNavn().toUpperCase();

            if(fødselsnummer.startsWith(søk) || fornavn.startsWith(søk) || etternavn.startsWith(søk) || adresse.startsWith(søk) || mail.startsWith(søk) ||
                    telfonnummer.startsWith(søk) ||
                     navn.startsWith(søk)){

                temp.add(søker);
            }
        }

        if(temp.isEmpty()){

            søkfelt.setBackground(Color.decode("#fd6d6d"));
        }

        overskriftpanel.remove(overskrift);
        tabellpanel.remove(scroll);
        scroll.remove(tabell);
        revalidate();

        modell = new Sokermodell(kolonner, temp);

        tabell = new JTable(modell);

        scroll = new JScrollPane(tabell);
        tabellpanel.add(scroll, BorderLayout.CENTER);

        overskrift = new JLabel("Søk etter: " + søkfelt.getText() + ", ga " + temp.size() + " resultater");
        overskriftpanel.add(overskrift);

        revalidate();
        repaint();
    }

    public void slettSoker(int rad) {
        modell.delRow(rad);
    }

    public void endreUtleier(){
        JOptionPane.showMessageDialog(null,"Not yet supported");
    }

    public void visInfo(Soker soker){
        JOptionPane.showMessageDialog(null,soker.getØnskedeBolgier());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tilbake){
            parent.visPanel(MainFrame.MAIN_BOARD);
            
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension skjerm = kit.getScreenSize();
            int bredde = skjerm.width;
            int høyde = skjerm.height;
        
            parent.setSize(bredde/2, høyde-100);
            parent.setLocation(skjerm.width / 2 - parent.getSize().width / 2, skjerm.height / 2 - parent.getSize().height / 2);
        }
        else if(e.getSource() == fjern){
            int rad = tabell.getSelectedRow();
            Soker soker = modell.getValueAt(rad);
            String persnr = soker.getFødselsnummer();
            pregister.fjern(persnr);
            slettSoker(rad);
        }
        else if(e.getSource() == endre){
            endreUtleier();
        }
        else if(e.getSource() == visInfo){
            int rad = tabell.getSelectedRow();
            Soker soker = modell.getValueAt(rad);
            visInfo(soker);
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
