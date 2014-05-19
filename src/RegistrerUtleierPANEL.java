/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Panel for registrering av utleier.
 * Skrevet av Erlend Westbye. Sist oppdatert 10.05.14
 * 
 */
public class RegistrerUtleierPANEL extends JPanel implements ActionListener, DocumentListener{
    private JTextField[] felt;
    private final String[] feltnavn = {"Fødselsnummer", "Fornavn", "Etternavn", "Adresse", "Mail", "Telefonnummer", "Firma"};

    private final int FØDSELSNUMMER = 0;
    private final int FORNAVN = 1;
    private final int ETTERNAVN = 2;
    private final int ADRESSE = 3;
    private final int MAIL = 4;
    private final int TELEFONUMMER = 5;
    private final int FIRMA = 6;

    private String fødselnummer;
    private String fornavn;
    private String etternavn;
    private String adresse;
    private String mail;
    private String telefonnummer ;
    private String firma;

    private JPanel feltpanel;
    private JPanel knapppanel;

    private JButton registrer;
    private JButton avbryt;

    private Personregister register;
    private MainFrame parent;

    public RegistrerUtleierPANEL(Personregister register, MainFrame parent) {
        super(new BorderLayout());
        this.register = register;
        this.parent = parent;
        initialiser();
        lagGUI();
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;
         
        parent.setSize(bredde/2, høyde-200);
        parent.setLocation(skjerm.width/2-parent.getSize().width/2, skjerm.height/2-parent.getSize().height/2);
    

        //setLocation(skjerm.width / 2 - getSize().width / 2, skjerm.height / 2 - getSize().height / 2);
    
    }
    //Initialiserer alle felter,paneler,tekstfyller,knapper osv
    public void initialiser() {

        felt = new JTextField[feltnavn.length];
        TekstFyller tp [] = new TekstFyller[felt.length];

        feltpanel = new JPanel(new GridLayout(7, 1, 5, 5));
        knapppanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


        for (int i = 0; i < feltnavn.length; i++) {
            felt[i] = new JTextField(10);
            tp[i] = new TekstFyller(feltnavn[i], felt[i]);
            tp[i].changeAlpha(0.7f);

        }
        registrer = new JButton("Registrer");
        avbryt = new JButton("Avbryt");

        registrer.addActionListener(this);
        avbryt.addActionListener(this);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      
        
    }
    //Oppretter brukergrensesnittet
    public void lagGUI() {

        for (int i = 0; i < felt.length; i++) {
            feltpanel.add(felt[i]);
        }
        knapppanel.add(registrer);
        knapppanel.add(avbryt);

        add(feltpanel, BorderLayout.CENTER);
        add(knapppanel, BorderLayout.SOUTH);

        knapppanel.setBackground(Color.decode("#B3D5E3"));
        feltpanel.setBackground(Color.decode("#B3D5E3"));
        setBackground(Color.decode("#B3D5E3"));
    }
    //Metode for å registrere en utleier. tar informasjon fra hva brukeren har fylt inn i feltene
    public void registrer(){
        String fødselsnummer = felt[FØDSELSNUMMER].getText();//navsjekk skal egentlig legges inn her

         fornavn = Navnsjekker.getGyldigStringNavn(felt[FORNAVN].getText());
         etternavn = Navnsjekker.getGyldigStringNavn(felt[ETTERNAVN].getText());
         adresse = felt[ADRESSE].getText();
         mail = felt[MAIL].getText();
         telefonnummer = felt[TELEFONUMMER].getText();
        firma = felt[FIRMA].getText();
        
        if(fornavn == null || etternavn == null){
            visMelding("Skriv inn gydlige verdier!");
            return;
            
        }
        
        

        if(fødselsnummer.length()!=0||fornavn.length()!=0||etternavn.length()!=0||adresse.length()!=0||mail.length()!=0||telefonnummer.length()!=0){
                Person utleier = new Utleier(fødselsnummer,fornavn,etternavn,adresse, mail, telefonnummer, firma);
            if(register.leggTil(utleier)){
                //gå tilbake til mainframe
                parent.visPanel(MainFrame.MAIN_BOARD);
                parent.Size();
                
                   
                
                return;
            }
            else if(!register.leggTil(utleier)){
                visMelding("Feil informasjon ble utfylt, venligst prøv igjen");
            }
        }
        else{
            visMelding("Venligst fyll ut all informasjon");
        }
    }
    //Metode for å endre informasjon om en utleier. Hit sendes man via UtleierOversiktPANEL
    public void endreUtleier(Utleier utleier){

        feltpanel.removeAll();
        feltpanel.revalidate();
        feltpanel.repaint();

        knapppanel.removeAll();

        for (int i = 0; i < feltnavn.length; i++) {
            felt[i] = new JTextField(10);
            feltpanel.add(felt[i]);
            felt[i].getDocument().addDocumentListener(this);

        }

        felt[FØDSELSNUMMER].setEditable(false);

        felt[FØDSELSNUMMER].setText(utleier.getFødselsnummer());
        felt[FORNAVN].setText(utleier.getFornavn());
        felt[ETTERNAVN].setText(utleier.getEtternavn());
        felt[ADRESSE].setText(utleier.getAdresse());
        felt[MAIL].setText(utleier.getMail());
        felt[TELEFONUMMER].setText(utleier.getTelefonnummer());
        felt[FIRMA].setText(utleier.getFirma());

        add(feltpanel);

    }
    //Metode som oppdaterer informasjonen til det som nå er fyllt ut av brukeren
    public void oppdaterinfo(){

        setFødselsnummer(felt[FØDSELSNUMMER].getText());
        setFornavn(felt[FORNAVN].getText());
        setETTERNAVN(felt[ETTERNAVN].getText());
        setADRESSE(felt[ADRESSE].getText());
        setMAIL(felt[MAIL].getText());
        setTELEFONUMMER(felt[TELEFONUMMER].getText());
        setFirma(felt[FIRMA].getText());
    }

    public void setFødselsnummer(String s){fødselnummer = s;}

    public void setFornavn(String s){fornavn = s;}
    public void setETTERNAVN(String s){etternavn = s;}
    public void setADRESSE(String s){adresse = s;}
    public void setMAIL(String s){mail = s;}
    public void setTELEFONUMMER(String s){telefonnummer = s;}
    public void setFirma(String s){firma = s;}

    public String getFødselnummer(){return fødselnummer;}
    public String getFornavn(){return fornavn;}
    public String getEtternavn(){return etternavn;}
    public String getAdresse(){return adresse;}
    public String getMail(){return mail;}
    public String getTelefonnummer(){return telefonnummer;}
    public String getFirma(){return firma;}
    
    //metode som viser pop-meldingene i programmet
    public void visMelding(String melding){
        JOptionPane.showMessageDialog(null,melding);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == registrer){

            registrer();
           

        }else if(e.getSource() == avbryt){
            parent.visPanel(MainFrame.MAIN_BOARD);

            parent.Size();
        }
    }


    @Override
    public void insertUpdate(DocumentEvent e) {
        oppdaterinfo();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        oppdaterinfo();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        oppdaterinfo();
    }
}
