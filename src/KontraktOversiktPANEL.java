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

/*
 * Panel for å vise inngåtte kontrakter som er registrert i systemet.
 * Skrevet av Mads Karlstad og Erlend Westbye. Sist oppdatert 28.04.14
 */
public class KontraktOversiktPANEL extends JPanel implements ActionListener, DocumentListener {
    private JPanel overskriftpanel;
    private JPanel tabellpanel;
    private JPanel søkpanel;
    private JPanel knapppanel;

    private JLabel overskrift;
    private JTextField søkfelt;
    private JTable tabell;
    private JScrollPane scroll;
    private Kontraktmodell modell;
    private final String[] kolonner = {"Kontraktnr", "Eier", "Leier","Bolignr", "Pris", "Fra", "Til","Aktiv"};

    private JButton visInfo;
    private JButton tilbake;
    private JButton terminer;

    private JTextArea utskriftsområde;

    private LinkedList<Kontrakt> kontraktliste;
    private LinkedList<Kontrakt> temp;//listen som omfatter søket vårt

    private Utleier utleier;
    private Leilighetregister legister;
    private Personregister pregister;
    private Kontraktregister register;
    private Boligregister bregister;
    private Sokerregister sregister;
    private MainFrame parent;

    public KontraktOversiktPANEL(Kontraktregister register,Leilighetregister legister, Personregister pregister, MainFrame parent) {

        super(new BorderLayout());
        this.register = register;
        this.parent = parent;
        this.legister = legister;
        this.pregister = pregister;

        initialiser();
        lagGUI();
        
        parent.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    //Initialiserer panelene,knappene,utskriftsområdet,iterator,listen,osv.
    public void initialiser() {
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        søkpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tabellpanel = new JPanel(new BorderLayout());
        knapppanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        overskrift = new JLabel("Antall kontrakter: " + register.size());
        søkfelt = new JTextField(10);
        tabell = new JTable();

        utskriftsområde = new JTextArea();

        søkfelt.getDocument().addDocumentListener(this);

        Iterator it = register.entrySet().iterator();

        kontraktliste = new LinkedList<Kontrakt>();

        for(Map.Entry<String,Kontrakt> entry : register.entrySet()) {
            kontraktliste.add((Kontrakt) entry.getValue());
        }

        modell = new Kontraktmodell(kolonner, kontraktliste);

        tabell.setModel(modell);

        scroll = new JScrollPane(tabell);

        visInfo = new JButton("Vis info");
        tilbake = new JButton("Tilbake");
        terminer = new JButton("Opphør leieforhold");

        visInfo.addActionListener(this);
        tilbake.addActionListener(this);
        terminer.addActionListener(this);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    }
    //Metode som viser alle objektene som ligger i registeret.
    public void visAlle(){
        overskriftpanel.remove(overskrift);
        tabellpanel.remove(scroll);
        scroll.remove(tabell);
        revalidate();

        modell = new Kontraktmodell(kolonner, kontraktliste);

        tabell = new JTable(modell);

        scroll = new JScrollPane(tabell);
        tabellpanel.add(scroll, BorderLayout.CENTER);

        overskrift = new JLabel("Antall kontrakter: " + kontraktliste.size());
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
        knapppanel.add(tilbake);
        knapppanel.add(terminer);

        add(overskriftpanel, BorderLayout.PAGE_START);
        add(tabellpanel, BorderLayout.CENTER);
        add(knapppanel, BorderLayout.PAGE_END);

        knapppanel.setBackground(Color.decode("#B3D5E3"));
        overskriftpanel.setBackground(Color.decode("#B3D5E3"));
        //tabellpanel.setBackground(Color.decode("#DAEDF5"));
        søkpanel.setBackground(Color.decode("#B3D5E3"));
        setBackground(Color.decode("#B3D5E3"));
    }
    //Metode som søker gjennom listen, og viser objeketene som matcher bruker-inputen.
    public void søk(){

        temp = new LinkedList<Kontrakt>();

        søkfelt.setBackground(Color.WHITE);

        String søk = søkfelt.getText().toUpperCase();

        try{

            if (søk.substring(0,søk.length() - 1).matches("[0-9]+") && søk.substring(søk.length() - 1).equals(".")){

                String kontraktnr = søk.substring(0,søk.length() - 1);

                Kontrakt kontrakt = (Kontrakt) register.getObject(kontraktnr);

                if(kontrakt != null){

                    temp.add(kontrakt);
                    overskriftpanel.remove(overskrift);
                    tabellpanel.remove(scroll);
                    scroll.remove(tabell);
                    revalidate();

                    modell = new Kontraktmodell(kolonner, temp);

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

        Iterator it = kontraktliste.iterator();

        while(it.hasNext()){

            Kontrakt kontrakt = (Kontrakt) it.next();

            //String fødselsnummer = enebolig.getBoareal().toUpperCase();
            //String fornavn = utleier.getFornavn().toUpperCase();
            //String etternavn = utleier.getEtternavn().toUpperCase();
            //boolean røyke = enebolig.røyke();
            //String ledigfra = leilighet.getLedigDato();
            String kontraktnr = kontrakt.getBolignr().toUpperCase();

            /*String mail = utleier.getMail().toUpperCase();
            String telfonnummer = utleier.getTelefonnummer().toUpperCase();
            String firma = utleier.getFirma().toUpperCase();
            String navn = utleier.getNavn().toUpperCase();*/

            if(kontraktnr.startsWith(søk)
                    ){
                temp.add(kontrakt);
            }
        }

        overskriftpanel.remove(overskrift);
        tabellpanel.remove(scroll);
        scroll.remove(tabell);
        revalidate();

        modell = new Kontraktmodell(kolonner, temp);

        tabell = new JTable(modell);

        scroll = new JScrollPane(tabell);
        tabellpanel.add(scroll, BorderLayout.CENTER);

        overskrift = new JLabel("Søk etter: " + søkfelt.getText() + ", ga " + temp.size() + " resultater");
        overskriftpanel.add(overskrift);

        revalidate();
        repaint();
    }
    //Metode som viser kontraktinformasjonen. Her kan det ved senere tidspunkt implementeres en metode for å skrive ut kontrakten
    public void visInfo(int rad){
        Kontrakt kontraktobjekt = modell.getValueAt(rad);
        Utleier eier = kontraktobjekt.getUtleier();
        Soker soker = kontraktobjekt.getLeietager();
        Bolig bolig = kontraktobjekt.getBolig();
        String kontrakt = "";
        kontrakt += "Kontrakt for leie av bolig\n\n\n";
        kontrakt += "Boligformidler: BoligBrowse™\n\n";
        kontrakt += "Boliginformasjon:\n" + "Bolignummer: " + bolig.getBolignr() + "\nBoligens adresse: " + bolig.getAdresse();
        kontrakt += "\n\nBoligens eier: " + eier.getNavn() + "\n\nBoligens leietager: " +soker.getNavn();
        //kontrakt += "\n\n\n\n";
        kontrakt += "";

        JOptionPane.showMessageDialog(null,kontrakt);
    }
    //Metode for å opphøre et leieforhold. Setter boligen til ledig, men sletter ikke kontrakten, men setter den til inaktiv
    public void opphørKontrakt(int rad){
        try{Kontrakt kontrakt = modell.getValueAt(rad);
            if(kontrakt.getAktiv()){
            kontrakt.setAktiv(false);
            kontrakt.getBolig().setUtleid(false);}

        else{
                JOptionPane.showMessageDialog(null,"Kontrakt inaktiv");
            }}
        catch(IndexOutOfBoundsException i){

            JOptionPane.showMessageDialog(null,"Ingen kontrakter registrert");

    }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tilbake){
            parent.visPanel(MainFrame.MAIN_BOARD);

            parent.Size();
        }
        else if(e.getSource() == visInfo){
            int rad = tabell.getSelectedRow();
            visInfo(rad);
        }
        else if(e.getSource()==terminer){

            int rad = tabell.getSelectedRow();
            int svar = JOptionPane.showOptionDialog(null,"Vil du opphøre kontrakten6                                                        5?","Bekreft sletting",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
            if(svar==JOptionPane.YES_OPTION){

            opphørKontrakt(rad);}

            if(svar==JOptionPane.NO_OPTION){
                JOptionPane.showMessageDialog(null,"Handling avbrutt");
            }
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
