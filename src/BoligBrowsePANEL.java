/*
 * Copyright (c) 2014. Gruppeoppgave for Erlend Westbye s193377 Mads Karlstad s193949 Christoffer Jønsberg s193674
 */
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;


/**
 * Panel for visning av boliger som matcher leietagers ønsker, samt leieforespørsler for utleier.
 * Skrevet av Erlend Westbye og Christoffer Jønsberg. Sist oppdatert 10.05.14.
 */
public class BoligBrowsePANEL extends JPanel implements ActionListener{

    private boolean aLeilighetSøker;
    private boolean aEneboligSøker;
    private boolean aLeilighetUtleier;
    private boolean aEneboligUtleier;

    private int index;
    private int frem;
    private int tilbake;

    private int boligindex;
    private int sisteindex;

    private JTextField fødselsnummer;
    private JButton finn;
    private JButton finnbolig;

    private JButton neste;
    private JButton tilbakeknapp;
    private JButton forrige;
    private JButton eneboligSøker;
    private JButton leilighetSøker;
    private JButton eneboligUtleier;
    private JButton leilighetUtleier;
    private JButton ønsketenebolig;
    private JButton ønsketleilighet;
    private JButton ønsketLeietakerEnebolig;
    private JButton ønsketLeietakerLeilighet;

    private JPanel knappepanel;
    private JPanel knappepanel_søker;
    private JPanel knappepanel_utleier;

    private JPanel søkepanel;
    private JPanel infopanel_start_søker;
    private JPanel infopanel_søker;
    private JPanel midtpanel;
    private JPanel feltpanel_søker;
    private JPanel bilde_info;


    private JPanel infopanel_utleier;
    private JPanel feltpanel_utleier;
    private JPanel infopanel_start_utleier;

    private JPanel meldingpanel;

    private JTextField finnboligfelt;
    private JTextField bolignummer;
    private JTextField eier;
    private JTextField pris;
    private JTextField areal;

    private JTextField søkeradresse;
    private JTextField søkernavn;
    private JTextField søkermail;
    private JTextField søkertelefon;
    private JTextField søkerbolignr;
    private JTextField søkerpersnr;


    private JLabel bolignummeLabel;
    private JLabel eierLabel;
    private JLabel prisLabel;
    private JLabel arealLabel;
    private JLabel søkerLabel;
    private JLabel søkerAdrLabel;
    private JLabel tlfLabel;
    private JLabel mailLabel;
    private JLabel søkerbolignrLabel;
    private JLabel søkerpersnrLabel;
    private JLabel utleidLabel;

    private JLabel meldinglabel;


    private JTextArea boligArea;

    private Border border;

    private String bildenavn;
    private JLabel bildeLabel;
    private ImageIcon bildeikon;

    private Sokerregister sregister;
    private Personregister pregister;
    private Boligregister bregister;
    private Leilighetregister legister;
    private Kontraktregister kontraktregister;

    private LinkedList<Enebolig> eneboligliste;
    private LinkedList<Leilighet> leilighetliste;

    private DecimalFormat df;

    private MainFrame parent;


    public BoligBrowsePANEL(Sokerregister sregister, Boligregister bregister, Leilighetregister legister,Personregister pregister, Kontraktregister kontraktregister, MainFrame parent) {
        this.sregister = sregister;
        this.bregister = bregister;
        this.legister = legister;
        this.parent = parent;
        this.pregister = pregister;
        this.kontraktregister = kontraktregister;

        initialiser();
        lagGui();
    }
    //Intialiserer paneler,knapper, felter osv.
    public void initialiser(){

        index = 0;


        setLayout(new BorderLayout());

        knappepanel = new JPanel(new GridLayout(1,3,1,1));
        søkepanel = new JPanel(new GridLayout(1,4,1,1));

        midtpanel = new JPanel(new BorderLayout());
        feltpanel_søker = new JPanel(new GridLayout(4,2,1,1));


        infopanel_start_søker = new JPanel(new GridLayout(1,2,1,1));
        infopanel_søker = new JPanel(new BorderLayout());
        knappepanel_søker = new JPanel(new FlowLayout(FlowLayout.CENTER));
        knappepanel_utleier = new JPanel(new FlowLayout(FlowLayout.CENTER));

        infopanel_utleier = new JPanel(new BorderLayout());
        infopanel_start_utleier = new JPanel(new GridLayout(1,2,1,1));
        feltpanel_utleier = new JPanel(new GridLayout(8,2,1,1));

        bilde_info = new JPanel(new BorderLayout());

        boligArea = new JTextArea();

        meldingpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        bilde_info.setBorder(BorderFactory.createEmptyBorder(0/*top*/, 20/*left*/, 0/*bottom*/, 0/*right*/));
        søkepanel.setBorder(BorderFactory.createEmptyBorder(10/*top*/, 10/*left*/, 20/*bottom*/, 10/*right*/));


        fødselsnummer = new JTextField(10);
        finn = new JButton("Finn person");
        finnbolig = new JButton("Finn bolig");

        neste = new JButton("neste");
        tilbakeknapp = new JButton("tilbake");
        forrige = new JButton("forrige");

        ønsketenebolig = new JButton("Ønsker å leie denne");
        ønsketleilighet = new JButton("Ønsker å leie denne");
        ønsketLeietakerEnebolig = new JButton("Godkjenn leietaker");
        ønsketLeietakerLeilighet = new JButton("Godkjenn leietaker");

        try{

            Icon leilighetIkon = new ImageIcon(getClass().getResource("Bilder/Leilighet.png"));
            Icon eneboligIkon = new ImageIcon(getClass().getResource("Bilder/Enebolig.png"));

            leilighetSøker = new JButton(leilighetIkon);
            eneboligSøker = new JButton(eneboligIkon);
            leilighetUtleier = new JButton(leilighetIkon);
            eneboligUtleier = new JButton(eneboligIkon);
        }

        catch (NullPointerException npe){
            leilighetSøker = new JButton("vis leiligheter");
            eneboligSøker = new JButton("vis enebolig");
            leilighetUtleier = new JButton("vis leiligheter");
            eneboligUtleier = new JButton("vis enebolig");
        }

        neste.addActionListener(this);
        tilbakeknapp.addActionListener(this);
        forrige.addActionListener(this);
        finn.addActionListener(this);
        finnbolig.addActionListener(this);
        leilighetSøker.addActionListener(this);
        eneboligSøker.addActionListener(this);
        eneboligUtleier.addActionListener(this);
        leilighetUtleier.addActionListener(this);

        ønsketleilighet.addActionListener(this);
        ønsketenebolig.addActionListener(this);
        ønsketLeietakerEnebolig.addActionListener(this);
        ønsketLeietakerLeilighet.addActionListener(this);

        eneboligliste = new LinkedList<Enebolig>();
        leilighetliste = new LinkedList<Leilighet>();

        index = 0;
        frem = 1;
        tilbake = 1;

        bolignummer = new JTextField(10);
        eier = new JTextField(10);
        areal = new JTextField(10);
        pris = new JTextField(10);
        søkeradresse = new JTextField(10);
        søkermail = new JTextField(10);
        søkernavn = new JTextField(10);
        søkertelefon = new JTextField(10);
        søkerbolignr = new JTextField(10);
        søkerpersnr = new JTextField(10);
        finnboligfelt = new JTextField();


        bolignummer.setEditable(false);
        eier.setEditable(false);
        areal.setEditable(false);
        pris.setEditable(false);

        søkeradresse.setEditable(false);
        søkermail.setEditable(false);
        søkernavn.setEditable(false);
        søkertelefon.setEditable(false);
        søkerbolignr.setEditable(false);
        søkerpersnr.setEditable(false);

        bolignummeLabel = new JLabel("Bolignummer");
        eierLabel = new JLabel("Boligeier");
        prisLabel = new JLabel("Pris pr mnd");
        arealLabel = new JLabel("Areal");
        søkerLabel = new JLabel("Søkers navn");
        søkerAdrLabel= new JLabel("Søkers adresse");
        tlfLabel = new JLabel("Søkers telfon");
        mailLabel = new JLabel("Søkers mail");
        søkerbolignrLabel = new JLabel("Boligens bolignummer");
        søkerpersnrLabel = new JLabel("Søkerens personnummer");
        utleidLabel = new JLabel("Du kan sjekke kontraktregisteret for når leieforholdet for denne boligen opphører");
        meldinglabel = new JLabel();


        boligArea.setLineWrap(true);
        boligArea.setWrapStyleWord(true);
        boligArea.setEditable(false);

        df = new DecimalFormat("#");

        border = BorderFactory.createLineBorder(Color.BLACK, 1);

        bildeLabel = new JLabel();

        aLeilighetSøker = false;
        aEneboligSøker = false;
        aLeilighetUtleier = false;
        aEneboligUtleier = false;

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    //Oppretter brukergrensesnittet
    public void lagGui(){
        knappepanel.add(forrige);
        knappepanel.add(neste);
        knappepanel.add(tilbakeknapp);

        feltpanel_søker.add(bolignummeLabel);
        feltpanel_søker.add(bolignummer);
        feltpanel_søker.add(eierLabel);
        feltpanel_søker.add(eier);
        feltpanel_søker.add(prisLabel);
        feltpanel_søker.add(pris);
        feltpanel_søker.add(arealLabel);
        feltpanel_søker.add(areal);

        feltpanel_utleier.add(søkerLabel);
        feltpanel_utleier.add(søkernavn);
        feltpanel_utleier.add(søkerAdrLabel);
        feltpanel_utleier.add(søkeradresse);
        feltpanel_utleier.add(tlfLabel);
        feltpanel_utleier.add(søkertelefon);
        feltpanel_utleier.add(mailLabel);
        feltpanel_utleier.add(søkermail);
        feltpanel_utleier.add(søkerbolignrLabel);
        feltpanel_utleier.add(søkerbolignr);
        feltpanel_utleier.add(søkerpersnrLabel);
        feltpanel_utleier.add(søkerpersnr);

        infopanel_søker.add(boligArea, BorderLayout.CENTER);

        søkepanel.add(fødselsnummer);
        søkepanel.add(finn);//disse durde byttes rekkefølge på søkepanel og infopane_søker

        infopanel_start_søker.add(eneboligSøker);
        infopanel_start_søker.add(leilighetSøker);

        infopanel_start_utleier.add(eneboligUtleier);
        infopanel_start_utleier.add(leilighetUtleier);

        meldingpanel.add(meldinglabel);

        add(knappepanel, BorderLayout.PAGE_END);
        add(søkepanel, BorderLayout.PAGE_START);

        add(midtpanel, BorderLayout.CENTER);

        boligArea.setBorder(border);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;

        parent.setSize(bredde/2, høyde-300);


        knappepanel.setBackground(Color.decode("#B3D5E3"));
        knappepanel_søker.setBackground(Color.decode("#B3D5E3"));
        knappepanel_utleier.setBackground(Color.decode("#B3D5E3"));

        søkepanel.setBackground(Color.decode("#B3D5E3"));
        infopanel_start_søker.setBackground(Color.decode("#B3D5E3"));
        infopanel_søker.setBackground(Color.decode("#B3D5E3"));
        midtpanel.setBackground(Color.decode("#B3D5E3"));
        feltpanel_søker.setBackground(Color.decode("#B3D5E3"));
        bilde_info.setBackground(Color.decode("#B3D5E3"));
        meldingpanel.setBackground(Color.decode("#B3D5E3"));


        infopanel_utleier.setBackground(Color.decode("#B3D5E3"));
        feltpanel_utleier.setBackground(Color.decode("#B3D5E3"));
        infopanel_start_utleier.setBackground(Color.decode("#B3D5E3"));

        setBackground(Color.decode("#B3D5E3"));


    }
    //Viser eneboliger som matcher søkerens kriterier
    public void visEneboliger(Soker soker) throws IOException {

        soker.matcherEnebolig(bregister);

        eneboligliste = soker.getEneboligliste();

        if(eneboligliste.size()!= 0){

        bolignummer.setText(eneboligliste.get(index).getBolignr());
        eier.setText(eneboligliste.get(index).getEier().getNavn());
        pris.setText(String.valueOf(eneboligliste.get(index).getPris()));
        areal.setText(String.valueOf(eneboligliste.get(index).getBoareal()));

        boligArea.setText(eneboligliste.get(index).toString() + " Dette er en " + String.valueOf(df.format((eneboligliste.get(index).getProsent()))) + " % match etter " + soker.getNavn()
                + " sine ønsker");

        aLeilighetSøker = false;
        aEneboligSøker = true;
        aLeilighetUtleier = false;
        aEneboligUtleier = false;

        bildenavn = eneboligliste.get(index).getBildesti();
        
           

        bildeikon = new ImageIcon(bildenavn);
        bildeikon.getImage().flush();
        bildeLabel.setIcon( bildeikon );

        if(!eneboligliste.get(index).erUtleid()) {
            knappepanel_søker.removeAll();
            knappepanel_søker.revalidate();
            knappepanel_søker.repaint();
            knappepanel_søker.add(ønsketenebolig);
        }
        else if(eneboligliste.get(index).erUtleid()){
            knappepanel_søker.removeAll();
            knappepanel_søker.revalidate();
            knappepanel_søker.repaint();
            knappepanel_søker.add(utleidLabel);
        }}

        else{
            visMelding("ingen leiligheter registrert eller matchet","<html><p>Hvis kunden har sett en enebolig av interesse i vis boliger, <br> kan du fortsatt søke opp eneboligen via boligens nummer</p></html>");

            removeAll();
            revalidate();
            repaint();


            add(søkepanel,BorderLayout.PAGE_START);

            add(meldingpanel, BorderLayout.CENTER);

            add(knappepanel,BorderLayout.PAGE_END);
        }

        visSøkerPANEL();
    }
    //Viser leiligheter som matcher søkerens kriterier.
    public void visLeilighet(Soker soker) throws IOException, IndexOutOfBoundsException {

        soker.matcherLeilighet(legister);

        leilighetliste = soker.getLeilighetliste();

        if(leilighetliste.size()!=0){

        bolignummer.setText(leilighetliste.get(index).getBolignr());
        eier.setText(leilighetliste.get(index).getEier().getNavn());
        pris.setText(String.valueOf(leilighetliste.get(index).getPris()));
        areal.setText(String.valueOf(leilighetliste.get(index).getBoareal()));

        boligArea.setText(leilighetliste.get(index).toString() + " Dette er en " + String.valueOf(df.format((leilighetliste.get(index).getProsent()))) + " % match etter " + soker.getNavn()
                + " sine ønsker");

        aLeilighetSøker = true;
        aEneboligSøker = false;
        aLeilighetUtleier = false;
        aEneboligUtleier = false;

        bildenavn = leilighetliste.get(index).getBildesti();

        bildeikon = new ImageIcon(bildenavn);
        bildeikon.getImage().flush();
        bildeLabel.setIcon(bildeikon);

        if(!leilighetliste.get(index).erUtleid()) {
            knappepanel_søker.removeAll();
            knappepanel_søker.revalidate();
            knappepanel_søker.repaint();
            knappepanel_søker.add(ønsketleilighet);
        }
        else if(leilighetliste.get(index).erUtleid()){
            knappepanel_søker.removeAll();
            knappepanel_søker.revalidate();
            knappepanel_søker.repaint();
            knappepanel_søker.add(utleidLabel);
        }
        }

        else{
        visMelding("ingen leiligheter registrert eller matchet","<html><p>Hvis kunden har sett en leilighet av interesse i vis boliger, <br> kan du fortsatt søke opp leiligheten via boligens nummer</p></html>");

            removeAll();
            revalidate();
            repaint();


            add(søkepanel,BorderLayout.PAGE_START);

            add(meldingpanel, BorderLayout.CENTER);

            add(knappepanel,BorderLayout.PAGE_END);
        }

        visSøkerPANEL();
    }
    //Viser utleiers eneboliger som noen ønsker å leie
    public void visEneboligUtleier(Utleier utleier, int index, int boligindex) throws IndexOutOfBoundsException{

        eneboligliste.clear();

        utleier.oppdaterLister(bregister,legister);

        for(int i = 0; i < utleier.getEideBoliger().size(); i++){
        
            if(utleier.getEideBoliger().get(i) instanceof Enebolig){
            
                eneboligliste.add((Enebolig) utleier.getEideBoliger().get(i));
                
                
                
            }
        }
        
       

        Soker soker = (Soker) eneboligliste.get(boligindex).getSokere().get(index);





        søkernavn.setText(soker.getNavn());
        søkeradresse.setText(soker.getAdresse());
        søkertelefon.setText(soker.getTelefonnummer());
        søkermail.setText(soker.getMail());
        søkerbolignr.setText(eneboligliste.get(boligindex).getBolignr());
        søkerpersnr.setText(soker.getFødselsnummer());

        aLeilighetSøker = false;
        aEneboligSøker = false;
        aLeilighetUtleier = false;
        aEneboligUtleier = true;
        
                        knappepanel.removeAll();
                knappepanel.revalidate();
                knappepanel.repaint();
                
                knappepanel.add(neste);
                knappepanel.add(tilbakeknapp);

        knappepanel_utleier.add(ønsketLeietakerEnebolig);
    }
    //Viser utleiers leiligheter som noen ønsker å leie
    public void visLeilighetUtleier(Utleier utleier,int index,int boligindex) throws IndexOutOfBoundsException{


       utleier.oppdaterLister(bregister,legister);
       
            for(int i = 0; i < utleier.getEideBoliger().size(); i++){
        
            if(utleier.getEideBoliger().get(i) instanceof Leilighet){
            
                leilighetliste.add((Leilighet) utleier.getEideBoliger().get(i));
                
                
                
            }
        }

        Soker soker = (Soker) leilighetliste.get(boligindex).getSokere().get(index);
        

        søkernavn.setText(soker.getNavn());
        søkeradresse.setText(soker.getAdresse());
        søkertelefon.setText(soker.getTelefonnummer());
        søkermail.setText(soker.getMail());
        søkerbolignr.setText(leilighetliste.get(boligindex).getBolignr());
        søkerpersnr.setText(soker.getFødselsnummer());

        aLeilighetSøker = false;
        aEneboligSøker = false;
        aLeilighetUtleier = true;
        aEneboligUtleier = false;

                knappepanel.removeAll();
                knappepanel.revalidate();
                knappepanel.repaint();
                
                knappepanel.add(neste);
                knappepanel.add(tilbakeknapp);
                
        knappepanel_utleier.add(ønsketLeietakerLeilighet);
    }
    //Viser startpanelet når man logger inn som søker
    public void visStartPANELsøker(){

        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();

        midtpanel.add(infopanel_start_søker, BorderLayout.CENTER);
    }
    //Viser startpanelet for når man logger inn som utleier
    public void visStartPANELutleier(){

        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();

        midtpanel.add(infopanel_start_utleier, BorderLayout.CENTER);
    }
    //Viser panel for søker
    public void visSøkerPANEL() throws IOException {

        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();

        søkepanel.removeAll();
        søkepanel.revalidate();
        søkepanel.repaint();

        søkepanel.add(fødselsnummer);
        søkepanel.add(finn);
        søkepanel.add(finnboligfelt);
        søkepanel.add(finnbolig);

        bilde_info.add(boligArea, BorderLayout.PAGE_START);
        bilde_info.add(bildeLabel, BorderLayout.CENTER);

        infopanel_søker.add(feltpanel_søker, BorderLayout.LINE_START);
        infopanel_søker.add(bilde_info, BorderLayout.CENTER);
        infopanel_søker.add(knappepanel_søker,BorderLayout.PAGE_END);

        midtpanel.add(infopanel_søker);
    }
    //Viser panel for utleier
    public void visUtleierpanel(){

        midtpanel.removeAll();
        midtpanel.revalidate();
        midtpanel.repaint();

        knappepanel_utleier.removeAll();
        knappepanel_utleier.revalidate();
        knappepanel_utleier.repaint();

        infopanel_utleier.removeAll();
        infopanel_utleier.revalidate();
        infopanel_utleier.repaint();

        infopanel_utleier.add(feltpanel_utleier, BorderLayout.LINE_START);
        infopanel_utleier.add(knappepanel_utleier,BorderLayout.PAGE_END);

        midtpanel.add(infopanel_utleier);
    }
    //Metode som fanger opp om det har blitt trykketfor å bla til neste bolig, og finner neste bolig i listen.
    public void nextVasClicked(String pnr) throws IOException {

        try{

            index+=frem;
            boligindex += 0;
            
            Soker soker = sregister.get(pnr);
            Utleier eier = pregister.get(fødselsnummer.getText());


            if(aEneboligSøker){
                
                System.out.println(index);
                
                try{visEneboliger(soker);}
                
                catch (IndexOutOfBoundsException ie){
                    index = soker.getEneboligliste().size()-1;
                }
            }

            else if(aLeilighetSøker){
                try{visLeilighet(soker);}
                
                catch (IndexOutOfBoundsException ie){
                    index = soker.getLeilighetliste().size()-1;
                }
            }

            else if(aEneboligUtleier){
                
                
    
                try{
                    
                    visEneboligUtleier(eier,index, boligindex);
                    sisteindex = index;
                
                }
                catch(IndexOutOfBoundsException ie){
                    
                    boligindex += 1;
                    index = 0;
                    
                    if(boligindex > eneboligliste.size()){
                    
                        System.out.println("ikke fler boliger");
                        boligindex = eneboligliste.size();
                        index = sisteindex;//siste før exeption, denne vi vil så på
                        
                        
                        
                        int svar = JOptionPane.showOptionDialog(null,"Vil du starte listen på nytt?","Restart liste",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                        if(svar==JOptionPane.YES_OPTION){
                       boligindex =0;
                       index = 0;
                       
                       
                        }
                        if(svar==JOptionPane.NO_OPTION){
                        tilbakeknapp.doClick();
        }
                       
                    }
                   
                    
                    System.out.println("index : " + index + " boligindex " + boligindex);
                 
                    visEneboligUtleier(eier,index, boligindex);
                    
                }
            }

            else if (aLeilighetUtleier){
                
                try{
                    
                    visLeilighetUtleier(eier,index, boligindex);
                    sisteindex = index;
                
                }
                catch(IndexOutOfBoundsException ie){
                    
                    boligindex += 1;
                    index = 0;
                    
                    if(boligindex > leilighetliste.size()){
                    
                        System.out.println("ikke fler boliger");
                        boligindex = leilighetliste.size();
                        index = sisteindex;//siste før exeption, denne vi vil så på
                        
                        
                        
                        int svar = JOptionPane.showOptionDialog(null,"Vil du starte listen på nytt?","Restart liste",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                        if(svar==JOptionPane.YES_OPTION){
                       boligindex =0;
                       index = 0;
                       
                       
                        }
                        if(svar==JOptionPane.NO_OPTION){
                        tilbakeknapp.doClick();
        }
                       
                    }
                   
                    
                    System.out.println("index : " + index + " boligindex " + boligindex);
                 
                    visLeilighetUtleier(eier,index, boligindex);
                    
                }
            

   
            }
    }
        catch (IndexOutOfBoundsException io){

               index = 0;

        }
    }
    //Metode som fanger opp om det har blitt trykket for å bla til neste bolig, og finner neste bolig i listen.
    public void previousVasClicked(String pnr) throws IOException {

        try {
            
            index -= tilbake;
            
            Soker soker = sregister.get(pnr);
            Utleier eier = pregister.get(fødselsnummer.getText());
            String bnr = bolignummer.getText();

            if (aEneboligSøker) {
                try{visEneboliger(soker);}
                catch(IndexOutOfBoundsException io){
                index = 0;
                }
            } else if (aLeilighetSøker) {
                                try{visLeilighet(soker);}
                catch(IndexOutOfBoundsException io){
                index = 0;
                }
                                
                               
            
            }

            
            
        
        }
                catch (IndexOutOfBoundsException io){

}
        
        

}
    
    //Metode som tar inn innskrevet fødselsnummer fra input og finner ut om brukeren er en utleier eller søker og viser respektivt panel
    public void toggle(String fnr){

        if(sregister.finnes(fnr)){

            visStartPANELsøker();
            Soker soker = sregister.get(fnr);



            søkepanel.removeAll();
            søkepanel.revalidate();
            søkepanel.repaint();

        }

        else if(pregister.finnes(fnr)){

            visStartPANELutleier();

            søkepanel.removeAll();
            søkepanel.revalidate();
            søkepanel.repaint();
        }

        else{

            visMelding("Ingen person med dette fødselsnummeret registrert",null);

        }
    }
    //Metode for å godkjenne en kontrakt
    public void godkjennkontrakt(Bolig b){

        String eierspersnr = fødselsnummer.getText();
        String bolignr = søkerbolignr.getText();
        String leierspersnr = søkerpersnr.getText();
        Leilighet leilighet = legister.get(bolignr);
        Enebolig enebolig = bregister.get(bolignr);
        Utleier eier = pregister.get(eierspersnr);
        Soker soker = sregister.get(leierspersnr);
       

        int pris = b.getPris();
        String fra = b.getLedigDato();
        String til = "01.01.19";
        int siste = kontraktregister.size();
        int kontrakttall = siste +1;
        String kontraktnr = String.valueOf(kontrakttall);

        if(b instanceof Enebolig){


            Kontrakt kontrakt = new Kontrakt(kontraktnr,enebolig,eier,soker,pris,fra,til);
            if(!kontraktregister.finnes(kontraktnr)) {

                kontraktregister.put(kontrakt.getKontraktnr(), kontrakt);
                enebolig.setUtleid(true);
                kontrakt.setAktiv(true);
                parent.doClick(6);


            }
            else if(kontraktregister.finnes(kontraktnr)) {
                visMelding("Kontrakt med kontraktnummer: " + kontraktnr +" finnes allerede",null);

            }
        }

        if(b instanceof Leilighet){


            Kontrakt kontrakt = new Kontrakt(kontraktnr,leilighet,eier,soker,pris,fra,til);
            if(!kontraktregister.finnes(kontraktnr)){
                kontraktregister.put(kontrakt.getKontraktnr(), kontrakt);
                leilighet.setUtleid(true);
                kontrakt.setAktiv(true);
                parent.doClick(6);
                
            }
            else if(kontraktregister.finnes(kontraktnr)){
                visMelding("Kontrakt med kontraktnummer: " + kontraktnr +" finnes allerede",null);
            }
        }
        
        for(int i = 0; i < sregister.size(); i++){
        String key = (String) sregister.keySet().toArray()[i];
        
        sregister.get(key).checkØnsketlist();
        
        }
        
        eier.oppdaterLister(bregister,legister);
        
    }
    //Metode som finner en bolig utifra innkommen parameter bnr
    public void finnBolig(String bnr)  throws Exception{

        finnboligfelt.setText("");

        if(bregister.finnes(bnr)){


            Enebolig enebolig = bregister.get(bnr);

            bolignummer.setText(enebolig.getBolignr());
            eier.setText(enebolig.getEiersNavn());
            pris.setText(String.valueOf(enebolig.getPris()));
            areal.setText(String.valueOf(enebolig.getBoareal()));

            boligArea.setText(enebolig.toString());

            aLeilighetSøker = false;
            aEneboligSøker = true;
            aLeilighetUtleier = false;
            aEneboligUtleier = false;

            bildenavn = enebolig.getBildesti();

            bildeikon = new ImageIcon(getClass().getResource(bildenavn));
            bildeikon.getImage().flush();
            bildeLabel.setIcon( bildeikon );

            if(!enebolig.erUtleid()) {
                knappepanel_søker.removeAll();
                knappepanel_søker.revalidate();
                knappepanel_søker.repaint();
                knappepanel_søker.add(ønsketenebolig);
            }
            else if(enebolig.erUtleid()){
                knappepanel_søker.removeAll();
                knappepanel_søker.revalidate();
                knappepanel_søker.repaint();
                knappepanel_søker.add(utleidLabel);
            }

            visSøkerPANEL();

        }

        else if(legister.finnes(bnr)){

            Leilighet leilighet = legister.get(bnr);

            bolignummer.setText(leilighet.getBolignr());
            eier.setText(leilighet.getEiersNavn());
            pris.setText(String.valueOf(leilighet.getPris()));
            areal.setText(String.valueOf(leilighet.getBoareal()));

            boligArea.setText(leilighet.toString());

            aLeilighetSøker = true;
            aEneboligSøker = false;
            aLeilighetUtleier = false;
            aEneboligUtleier = false;

            bildenavn = leilighet.getBildesti();

            bildeikon = new ImageIcon(getClass().getResource(bildenavn));
            bildeikon.getImage().flush();
            bildeLabel.setIcon(bildeikon);

            if(!leilighet.erUtleid()) {
                knappepanel_søker.removeAll();
                knappepanel_søker.revalidate();
                knappepanel_søker.repaint();
                knappepanel_søker.add(ønsketleilighet);
            }
            else if(leilighet.erUtleid()){
                knappepanel_søker.removeAll();
                knappepanel_søker.revalidate();
                knappepanel_søker.repaint();
                knappepanel_søker.add(utleidLabel);
            }

            visSøkerPANEL();

        }
    }
    //Metode som viser pop-meldinger som skal forekomme i programmet
    public void visMelding(String meldingjoption,String melding){
        JOptionPane.showMessageDialog(null,meldingjoption);

        meldinglabel.setText(melding);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tilbakeknapp){
            parent.visPanel(MainFrame.MAIN_BOARD);
            parent.Size();
        }

        else if (e.getSource() == ønsketenebolig){
            if(bregister.finnes(bolignummer.getText())){

                Enebolig enebolig = bregister.get(bolignummer.getText());
                Soker soker = sregister.getSoker(fødselsnummer.getText());
                Utleier eier = enebolig.getEier();

                soker.addØnskedBolig(enebolig);
                eier.addEnebolig(enebolig);

                visMelding("Kunde har vist interesse\n venligst kontakt utleier",null);

                enebolig.setØnsket(true);
                enebolig.addSoker(soker);
            }
        }

        else if(e.getSource() == ønsketleilighet){

            if(legister.finnes(bolignummer.getText())){

                Leilighet leilighet = legister.get(bolignummer.getText());
                Soker soker = sregister.get(fødselsnummer.getText());
                Utleier eier = leilighet.getEier();

                soker.addØnskedBolig(leilighet);
                eier.addLeilighet(leilighet);

                visMelding("Kunde har vist interesse\n venligst kontakt utleier",null);

                leilighet.setØnsket(true);
                leilighet.addSoker(soker);
            }
        }

        else if (e.getSource() == eneboligSøker){

            Soker soker = sregister.get(fødselsnummer.getText());
            try
            {
                index = 0;

                visEneboliger(soker);
            }
            catch (IOException io){

            }
        }

        else if (e.getSource() == leilighetSøker){

            Soker soker = sregister.get(fødselsnummer.getText());
            try
            {
                index = 0;

                visLeilighet(soker);
            }
            catch (IOException io){

            }
        }

        else if (e.getSource() == leilighetUtleier){
            try{
                Utleier eier = pregister.get(fødselsnummer.getText());
                index = 0;
                boligindex = 0;
                visUtleierpanel();
                visLeilighetUtleier(eier,index,boligindex);
            }

            catch (NoSuchElementException ne){
                visStartPANELutleier();
                visMelding("Ingen nye ønsker registrert, venligst gå tilbake",null);
            }
            catch(IndexOutOfBoundsException ie){
                visStartPANELutleier();
                visMelding("Ingen nye ønsker registrert, venligst gå tilbake",null);
            }
        }

        else if (e.getSource() == eneboligUtleier){

            try{
                Utleier eier = pregister.get(fødselsnummer.getText());
                index = 0;
                boligindex = 0;
                visUtleierpanel();
                visEneboligUtleier(eier,index,boligindex);
            }
            catch(NoSuchElementException ne){
                visStartPANELutleier();
                visMelding("Ingen ønsker registrert, venligst gå tilbake",null);
            }
            catch(IndexOutOfBoundsException ie){
                visStartPANELutleier();
                visMelding("Ingen ønsker registrert, venligst gå tilbake",null);

            }

        }

        try {
            if (e.getSource() == finn) {

                toggle(fødselsnummer.getText());

            } else if (e.getSource() == neste) {

                nextVasClicked(fødselsnummer.getText());

            } else if (e.getSource() == forrige) {
                previousVasClicked(fødselsnummer.getText());

            }
        }
        catch(Exception ex){

        }

        if(e.getSource() == ønsketLeietakerLeilighet){


            Leilighet leilighet = legister.get(søkerbolignr.getText());

            godkjennkontrakt(leilighet);

            
        }

        else if(e.getSource() == ønsketLeietakerEnebolig){

            Enebolig enebolig = bregister.get(søkerbolignr.getText());

            godkjennkontrakt(enebolig);

            
        }

        else if(e.getSource() == finnbolig){

            try{finnBolig(finnboligfelt.getText());}
            catch(Exception s){}

        }
    }
}