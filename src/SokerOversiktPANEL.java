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
    private final String[] kolonner = {"Fødselsnummer", "Fornavn", "Etternavn", "Adresse", "Mail", "Telefonnummer","Røyker","Husdyr","Ønsket pris"};

    private JButton visInfo;
    private JButton endre;
    private JButton fjern;
    private JButton tilbake;
    private JButton oppdater;


    private LinkedList<Soker> søkerliste;
    private LinkedList<Soker> temp;//listen som omfatter søket vårt


    private Sokerregister sregister;
    private Boligregister bregister;
    private Leilighetregister legister;
    private MainFrame parent;

    private RegistrerSokerPANEL sibling;

    public SokerOversiktPANEL(Sokerregister sregister, MainFrame parent) {

        super(new BorderLayout());
        this.sregister = sregister;
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

        overskrift = new JLabel("Antall søkere: " + sregister.size());
        søkfelt = new JTextField(10);
        tabell = new JTable();

        søkfelt.getDocument().addDocumentListener(this);

        Iterator it = sregister.entrySet().iterator();

        søkerliste = new LinkedList<Soker>();

        for(Map.Entry<String,Soker> entry : sregister.entrySet()) {
            søkerliste.add((Soker) entry.getValue());
        }

        modell = new Sokermodell(kolonner, søkerliste);

        tabell.setModel(modell);

        scroll = new JScrollPane(tabell);

        visInfo = new JButton("Vis ønskede boliger");
        endre = new JButton("Endre");
        fjern = new JButton("Slett");
        tilbake = new JButton("Tilbake");
        oppdater = new JButton("Oppdater");

        visInfo.addActionListener(this);
        endre.addActionListener(this);
        fjern.addActionListener(this);
        tilbake.addActionListener(this);
        oppdater.addActionListener(this);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        legister = new Leilighetregister();
        bregister = new Boligregister();

        sibling = new RegistrerSokerPANEL(sregister,bregister,legister,parent);
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


        knapppanel.setBackground(Color.decode("#B3D5E3"));
        overskriftpanel.setBackground(Color.decode("#B3D5E3"));
        //tabellpanel.setBackground(Color.decode("#DAEDF5"));
        søkpanel.setBackground(Color.decode("#B3D5E3"));
        setBackground(Color.decode("#B3D5E3"));
    }

    public void søk(){

        temp = new LinkedList<Soker>();

        søkfelt.setBackground(Color.WHITE);

        String søk = søkfelt.getText().toUpperCase();

        try{

            if (søk.substring(0,søk.length() - 1).matches("[0-9]+") && søk.substring(søk.length() - 1).equals(".")){

                String fødselsnummer = søk.substring(0,søk.length() - 1);

                Soker søker = (Soker) sregister.getObject(fødselsnummer);

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
        int svar = JOptionPane.showOptionDialog(null,"Vil du slette søkeren?","Bekreft sletting",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(svar==JOptionPane.YES_OPTION){
            rad = tabell.getSelectedRow();
            try{
                Soker soker = modell.getValueAt(rad);
                String persnr = soker.getFødselsnummer();
                modell.delRow(rad);
                sregister.fjern(persnr);
            }
            catch(IndexOutOfBoundsException ioobe){
                JOptionPane.showMessageDialog(null,"Ingen søker markert/registrert");
            }

        }
        if(svar==JOptionPane.NO_OPTION){
            JOptionPane.showMessageDialog(null,"Sletting avbrutt");
        }

    }

    public void visInfo(Soker soker){
        System.out.println(soker.getEneboligliste());
        if(soker.getØnskedeBolgier().size()>0) {
            JOptionPane.showMessageDialog(null, soker.getØnskedeBolgier());
        }
        else if(soker.getØnskedeBolgier().size()==0){
            JOptionPane.showMessageDialog(null,"Søkeren har ingen ønskede boliger for øyeblikket");
        }
    }



    public void endreSoker(int rad){

        int svar = JOptionPane.showOptionDialog(null,"Vil du endre denne søkeren?","Bekreft endring",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);

        if(svar==JOptionPane.YES_OPTION){

            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension skjerm = kit.getScreenSize();
            int bredde = skjerm.width;
            int høyde = skjerm.height;

            parent.setSize(bredde/2, høyde-200);
            parent.setLocation(skjerm.width/2-parent.getSize().width/2, skjerm.height/2-parent.getSize().height/2);

            Soker soker = modell.getValueAt(rad);

            sibling.endreSoker(soker);

            removeAll();
            revalidate();
            repaint();

            add(sibling, BorderLayout.CENTER);
            add(oppdater,BorderLayout.PAGE_END);


        }
        if(svar==JOptionPane.NO_OPTION){
            JOptionPane.showMessageDialog(null,"endring avbrutt");
        }}

    public void oppdater(){

        Soker soker = sregister.get(sibling.getFødselnummer());


        soker.setFornavn(sibling.getFornavn());
        soker.setEtternavn(sibling.getEtternavn());
        soker.setAdresse(sibling.getAdresse());
        soker.setMail(sibling.getMail());
        soker.setTelefonnummer(sibling.getTelefonnummer());
        soker.setAntallPersoner(sibling.getANTPERS());
        soker.setYrke(sibling.getYRKE());
        soker.setArbeidsfohold_studiested(sibling.getARBFORHOLD());
        soker.setSivilstatus(sibling.getSIVILSTATUS());

        soker.setPris(sibling.getPris());
        soker.setMinAreal(sibling.getØnsket_areal_min());
        soker.setMaksAreal(sibling.getØnsket_areal_maks());

        soker.setRøyk(sibling.getRøyke());
        soker.setHusdyr(sibling.getHusdyr());
        soker.setBalkong(sibling.getBalkong());
        soker.setTerasse(sibling.getTerasse());
        soker.setTVinkludert(sibling.getTv());
        soker.setInternetinkludert(sibling.getInternett());
        soker.setKjeller(sibling.getKjeller());
        soker.setParkering(sibling.getParkering());
        soker.setStrøminkludert(sibling.getStrøm());
        soker.setHeis(sibling.getHeis());






        removeAll();
        revalidate();
        repaint();

        initialiser();
        lagGUI();
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

            slettSoker(rad);
        }
        else if(e.getSource() == endre){
            int rad = tabell.getSelectedRow();
            endreSoker(rad);

        }
        else if(e.getSource() == visInfo){
            int rad = tabell.getSelectedRow();
            Soker soker = modell.getValueAt(rad);
            visInfo(soker);
        }

        else if(e.getSource() == oppdater){

            oppdater();

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
