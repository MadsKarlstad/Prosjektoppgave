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
    private final String[] kolonner = {"Adresse", "Boareal", "Antall rom", "Byggeår", "Beskrivelse", "Pris", "Ledig fra","Bolignr","Eier"};

    private JButton visInfo;
    private JButton endre;
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

        modell = new Leilighetmodell(kolonner, leilighetliste);

        tabell = new JTable(modell);

        scroll = new JScrollPane(tabell);
        tabellpanel.add(scroll, BorderLayout.CENTER);

        overskrift = new JLabel("Antall leiligheter: " + leilighetliste.size());
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

            //String fødselsnummer = enebolig.getBoareal().toUpperCase();
            //String fornavn = utleier.getFornavn().toUpperCase();
            //String etternavn = utleier.getEtternavn().toUpperCase();
            String adresse = leilighet.getAdresse().toUpperCase();
            //boolean røyke = enebolig.røyke();
            String ledigfra = leilighet.getLedigDato();
            String bolignr = leilighet.getBolignr().toUpperCase();
            utleier = leilighet.getEier();
            String utleierfornavn = utleier.getFornavn().toUpperCase();
            String utleieretternavn = utleier.getEtternavn().toUpperCase();
            /*String mail = utleier.getMail().toUpperCase();
            String telfonnummer = utleier.getTelefonnummer().toUpperCase();
            String firma = utleier.getFirma().toUpperCase();
            String navn = utleier.getNavn().toUpperCase();*/

            if(adresse.startsWith(søk) || bolignr.startsWith(søk) || utleierfornavn.startsWith(søk) || utleieretternavn.startsWith(søk)
                    ){
                temp.add(leilighet);
            }
        }

        if(temp.isEmpty()){

            søkfelt.setBackground(Color.decode("#fd6d6d"));
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

    public void slettLeilighet(int rad){
        modell.delRow(rad);
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
            
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension skjerm = kit.getScreenSize();
            int bredde = skjerm.width;
            int høyde = skjerm.height;
        
            parent.setSize(bredde-1000, høyde-200);
            parent.setLocation(skjerm.width/2-parent.getSize().width/2, skjerm.height/2-parent.getSize().height/2);
        }
        else if(e.getSource() == fjern){
            int rad = tabell.getSelectedRow();
            Leilighet leilighet = modell.getValueAt(rad);
            String bolignr = leilighet.getBolignr();
            register.fjern(bolignr);
            slettLeilighet(rad);


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
