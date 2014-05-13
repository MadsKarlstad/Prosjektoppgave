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
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class UtleierOversiktPANEL extends JPanel implements ActionListener, DocumentListener {
    private JPanel overskriftpanel;
    private JPanel tabellpanel;
    private JPanel søkpanel;
    private JPanel knapppanel;

    private JLabel overskrift;
    private JTextField søkfelt;
    private JTable tabell;
    private JScrollPane scroll;
    private Utleiermodell modell;
    private final String[] kolonner = {"Fødselsnummer", "Fornavn", "Etternavn", "Adresse", "Mail", "Telefonnummer", "Firma"};

    private JButton visInfo;
    private JButton endre;
    private JButton fjern;
    private JButton tilbake;

    private JButton oppdater;

    private LinkedList<Bolig> eiersboliger;
    private LinkedList<Utleier> utleierliste;
    private LinkedList<Utleier> temp;//listen som omfatter søket vårt


    private Personregister register;
    private Boligregister bregister;
    private Leilighetregister legister;
    private MainFrame parent;
    private RegistrerUtleierPANEL sibling;

    public UtleierOversiktPANEL(Personregister register,Boligregister bregister,Leilighetregister legister, MainFrame parent) {

        super(new BorderLayout());
        this.register = register;
        this.bregister = bregister;
        this.legister = legister;
        this.parent = parent;

        initialiser();
        lagGUI();

        parent.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    public void initialiser() {

        setLayout(new BorderLayout());

        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        søkpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tabellpanel = new JPanel(new BorderLayout());
        knapppanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        overskrift = new JLabel("Antall utleiere: " + register.size());
        søkfelt = new JTextField(10);
        tabell = new JTable();

        søkfelt.getDocument().addDocumentListener(this);

        Iterator it = register.entrySet().iterator();


        utleierliste = new LinkedList<Utleier>();



        for(Map.Entry<String,Utleier> entry : register.entrySet()) {
            utleierliste.add((Utleier) entry.getValue());

        }







        modell = new Utleiermodell(kolonner, utleierliste);

        tabell.setModel(modell);

        scroll = new JScrollPane(tabell);

        visInfo = new JButton("Vis info");
        endre = new JButton("Endre");
        fjern = new JButton("Slett");
        tilbake = new JButton("Tilbake");
        oppdater = new JButton("Oppdater");

        visInfo.addActionListener(this);
        endre.addActionListener(this);
        fjern.addActionListener(this);
        tilbake.addActionListener(this);
        oppdater.addActionListener(this);

        sibling = new RegistrerUtleierPANEL(register,parent);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));




    }

    public void visAlle(){



        overskriftpanel.remove(overskrift);
        tabellpanel.remove(scroll);
        scroll.remove(tabell);
        revalidate();

        modell = new Utleiermodell(kolonner, utleierliste);

        tabell = new JTable(modell);

        scroll = new JScrollPane(tabell);
        tabellpanel.add(scroll, BorderLayout.CENTER);

        overskrift = new JLabel("Antall utleire: " + utleierliste.size());
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

        temp = new LinkedList<Utleier>();


        søkfelt.setBackground(Color.WHITE);


        String søk = søkfelt.getText().toUpperCase();

       try{

           if (søk.substring(0,søk.length() - 1).matches("[0-9]+") && søk.substring(søk.length() - 1).equals(".")){

               String fødselsnummer = søk.substring(0,søk.length() - 1);

               Utleier utleier = (Utleier) register.getObject(fødselsnummer);

               if(utleier != null){

                   temp.add(utleier);
                   overskriftpanel.remove(overskrift);
                   tabellpanel.remove(scroll);
                   scroll.remove(tabell);
                   revalidate();

                   modell = new Utleiermodell(kolonner, temp);

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



        Iterator it = utleierliste.iterator();

        while(it.hasNext()){

            Utleier utleier = (Utleier) it.next();

            String fødselsnummer = utleier.getFødselsnummer().toUpperCase();
            String fornavn = utleier.getFornavn().toUpperCase();
            String etternavn = utleier.getEtternavn().toUpperCase();
            String adresse = utleier.getAdresse().toUpperCase();
            String mail = utleier.getMail().toUpperCase();
            String telfonnummer = utleier.getTelefonnummer().toUpperCase();
            String firma = utleier.getFirma().toUpperCase();
            String navn = utleier.getNavn().toUpperCase();

            if(fødselsnummer.startsWith(søk) || fornavn.startsWith(søk) || etternavn.startsWith(søk) || adresse.startsWith(søk) || mail.startsWith(søk) ||
                    telfonnummer.startsWith(søk) ||
                    firma.startsWith(søk) || navn.startsWith(søk)){

                temp.add(utleier);

            }


        }

        overskriftpanel.remove(overskrift);
        tabellpanel.remove(scroll);
        scroll.remove(tabell);
        revalidate();

        modell = new Utleiermodell(kolonner, temp);

        tabell = new JTable(modell);

        scroll = new JScrollPane(tabell);
        tabellpanel.add(scroll, BorderLayout.CENTER);

        overskrift = new JLabel("Søk etter: " + søkfelt.getText() + ", ga " + temp.size() + " resultater");
        overskriftpanel.add(overskrift);

        revalidate();
        repaint();


    }

    public void slettUtleier(int rad){
        int svar = JOptionPane.showOptionDialog(null,"Vil du slette utleieren?","Bekreft sletting",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(svar==JOptionPane.YES_OPTION){
            rad = tabell.getSelectedRow();
            try{
                Utleier utleier = modell.getValueAt(rad);
                utleier.oppdaterBoliger(bregister,legister);
                eiersboliger = utleier.getEideBoliger();
                if(eiersboliger.size()!=0){
                    JOptionPane.showMessageDialog(null,"Utleieren har boliger registrert og kan ikke slettes");
                }
                else if(eiersboliger.size()==0) {
                    String persnr = utleier.getFødselsnummer();
                    modell.delRow(rad);
                    register.fjern(persnr);
                }
            }
            catch(IndexOutOfBoundsException ioobe){
                JOptionPane.showMessageDialog(null,"Ingen utleier markert/registrert");
            }
        }
        if(svar==JOptionPane.NO_OPTION){
            JOptionPane.showMessageDialog(null,"Sletting avbrutt");
        }
    }

    public void endreUtleier(int rad){

        int svar = JOptionPane.showOptionDialog(null,"Vil du endre denne utleier?","Bekreft endring",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);

        if(svar==JOptionPane.YES_OPTION){
            try{

                Utleier utleier = modell.getValueAt(rad);

                sibling.endreUtleier(utleier);

                removeAll();
                revalidate();
                repaint();

                add(sibling, BorderLayout.CENTER);
                add(oppdater,BorderLayout.PAGE_END);
            }
            catch(IndexOutOfBoundsException ioobe){
                JOptionPane.showMessageDialog(null,"Ingen utleier markert/registrert");
            }
        }
        if(svar==JOptionPane.NO_OPTION){
            JOptionPane.showMessageDialog(null,"endring avbrutt");
        }}

    public void oppdater(){

        Utleier utleier = register.get(sibling.getFødselnummer());

        for(int i = 0; i<utleier.getEideBoliger().size(); i++){

            utleier.getEideBoliger().get(i).setEier(utleier);
        }



        utleier.setFornavn(sibling.getFornavn());
        utleier.setEtternavn(sibling.getEtternavn());
        utleier.setAdresse(sibling.getAdresse());
        utleier.setMail(sibling.getMail());
        utleier.setTelefonnummer(sibling.getTelefonnummer());

        removeAll();
        revalidate();
        repaint();

        initialiser();
        lagGUI();
    }

    public void visInfo(int rad){
        try{
            Utleier utleier = modell.getValueAt(rad);
            utleier.oppdaterBoliger(bregister,legister);
            utleier.oppdaterLister();
            JOptionPane.showMessageDialog(null, "Ønskede boliger: "+utleier.getØnskedeEneboliger() + "\n" + utleier.getØnskedeLeiligheter()
                    +"\nEide boliger: " + utleier.getEideBoliger());
        }
        catch(IndexOutOfBoundsException ioobe){
            JOptionPane.showMessageDialog(null,"Ingen utleier markert/registrert");
        }

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
            slettUtleier(rad);
        }
        else if(e.getSource() == endre){
            int rad = tabell.getSelectedRow();
            endreUtleier(rad);
        }
        else if(e.getSource() == visInfo){
            int rad = tabell.getSelectedRow();
            visInfo(rad);
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