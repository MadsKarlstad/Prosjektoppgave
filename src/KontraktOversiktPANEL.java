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

/**
 SLANGEDREAM COPYRIGHT
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
    private final String[] kolonner = {"Adresse", "Boareal", "Antall rom", "Byggeår", "Beskrivelse", "Pris", "Ledig fra","Bolignr"};

    private JButton visInfo;
    private JButton endre;
    private JButton fjern;
    private JButton tilbake;

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
    }

    public void initialiser() {
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        søkpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tabellpanel = new JPanel(new BorderLayout());
        knapppanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        overskrift = new JLabel("Antall kontrakter: " + register.size());
        søkfelt = new JTextField(10);
        tabell = new JTable();

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

        modell = new Kontraktmodell(kolonner, kontraktliste);

        tabell = new JTable(modell);

        scroll = new JScrollPane(tabell);
        tabellpanel.add(scroll, BorderLayout.CENTER);

        overskrift = new JLabel("Antall leiligheter: " + kontraktliste.size());
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

        if(temp.isEmpty()){

            søkfelt.setBackground(Color.decode("#fd6d6d"));
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

    public void slettUtleier(){
        JOptionPane.showMessageDialog(null,"Not yet supported");
    }

    public void endreUtleier(){
        JOptionPane.showMessageDialog(null,"Not yet supported");
    }

    public void visInfo(){
        JOptionPane.showMessageDialog(null,"Not yet supported");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tilbake){
            parent.visPanel("VIS PROMPT");
        }
        else if(e.getSource() == fjern){
            slettUtleier();
        }
        else if(e.getSource() == endre){
            endreUtleier();
        }
        else if(e.getSource() == visInfo){
            visInfo();
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
