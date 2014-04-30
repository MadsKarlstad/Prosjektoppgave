import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.awt.*;
import javax.swing.*;

/**
 SLANGEDREAM COPYRIGHT
 */
public class BoligBrowse {
    public static void main(String[] args){
        Personregister register = new Personregister();
        Sokerregister sregister = new Sokerregister();
        Boligregister bregister = new Boligregister();
        Leilighetregister legister = new Leilighetregister();
        Kontraktregister kregister = new Kontraktregister();

        MainFrame frame;


        String[] fornavns = {"Ole", "Vetle","Simen","Karl", "Nils","Trine", "Kari", "Tina", "Hamed","Per-Arne","Mads","Erlend","Christoffer"};

        String[] etternavns = {"Larsen", "Moradi", "Thommasen", "Kulterud", "Bror", "McFlurry", "Olsen", "Pettersen","Baggins","Lannister","Targaryen"};

        String adresses[] = {"Tollbodgata 18", "Pilestredet 45", "Storgata 65", "Ingesteder 12", "Andeby City", "Eventyrdalen 762","King's Landing, The Red Keep","The Shire 43B"};

        String[] firma = {"","Illuminati","Monstermasten","Überkul A/S","Pilen Dytt og Flytt Inc."};

        String[] antPersoner = {"0","1","2","3","4","5"};

        String[] sivilstatus = {"Gift","Samboer","Singel"};

        String[] yrke = {"Snekker","Ingeniør","Sykepleier","Lege","Advokat","Økonom","Freakonom","Hobbyelektriker/brannstarter","Sosionom","Arbeidsledig","Student"};

        String[] arbeidsforhold = {"UIO","HIOA","UIB","NTNU","Aker Solutions","Microsoft","Apple Inc.","Monstermasten","Illuminati"};

        String[] domene = {"hotmail.com","me.com","gmail.com","yahoo.com","live.no","hotmail.no"};

        String[] fradato = {"01.06.14","01.07.14","01.08.14"};

        String[] tildato = {"01.06.19","01.07.19","01.08.19"};


        int[] boareal = {20,30,40,50,60,70,80};
        int[] antrom = {1,2,3,4,5,6};
        int[] byggår = {1970,1980,1990,2000,2010};
        String[] beskrivelse = {"Flott enebolig!","Nyoppusset enebolig","Lys og fin enebolig","Gammel herskapelig enebolig"};
        String[] beskrivLeil = {"Nyoppusset leilighet","Penthouse suite","Lys og fin leilighet"};
        int[] pris = {5000,6000,6500,7000,7499,7999,8750,10899};
        String[] ledigfra = {"Snarest","01.08.14","01.07.14","01.09.14","01.10.14","01.11.14"};
        boolean[] røyke = {true,false};
        boolean[] husdyr ={true,false};
        boolean[] balkong ={true,false};
        boolean[] terasse ={true,false};
        boolean[] tv ={true,false};
        boolean[] nett ={true,false};
        boolean[] strøm ={true,false};
        boolean[] parkering ={true,false};
        boolean[] heis = {true,false};
        int[] antetg = {1,2,3,4,5,6};
        boolean[] kjeller ={true,false};
        double[] tomt = {90,100,110,120,130,140,150};
        int[] antbad = {1,2,3,4};
        int[] etgleil = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        int[] boder = {1,2,3};

        int minareal = 0;
        int maksareal = 100;
        int minpris = 0;
        int[] makspris = {1000,2000,3000,4000,5000,6000,7000,8000,9000,10000,11000,12000,13000,14000,15000};

        Random r = new Random();

        for(int i = 0; i < 1000; i++){
            String fornavn = fornavns[r.nextInt(fornavns.length)];
            String etternavn = etternavns[r.nextInt(etternavns.length)];
            String adresse = adresses[r.nextInt(adresses.length)];
            String mail = fornavn.toLowerCase() + "."+etternavn.toLowerCase()+ "@" + domene[r.nextInt(domene.length)];
            String firm = firma[r.nextInt(firma.length)];

            Utleier utleier = new Utleier(String.valueOf(i+1), fornavn, etternavn, adresse, mail, String.valueOf(i+100), firm);

            register.leggTil(utleier);
        }

        for(int i=1000;i<2000; i++){
            String fornavn = fornavns[r.nextInt(fornavns.length)];
            String etternavn = etternavns[r.nextInt(etternavns.length)];
            String adresse = adresses[r.nextInt(adresses.length)];
            String mail = fornavn.toLowerCase() + "." + etternavn.toLowerCase() + "@" + domene[r.nextInt(domene.length)];
            String antpersoner = antPersoner[r.nextInt(antPersoner.length)];
            String sivstat = sivilstatus[r.nextInt(sivilstatus.length)];
            String work = yrke[r.nextInt(yrke.length)];
            String arbforhold = arbeidsforhold[r.nextInt(arbeidsforhold.length)];
            boolean røykeinn = røyke[r.nextInt(røyke.length)];
            boolean husdyrinn = husdyr[r.nextInt(husdyr.length)];
            boolean balkonginn = balkong[r.nextInt(balkong.length)];
            boolean terasseinn = terasse[r.nextInt(terasse.length)];
            boolean TVinn = tv[r.nextInt(tv.length)];
            boolean internetinn = nett[r.nextInt(nett.length)];
            boolean strøminn = strøm[r.nextInt(strøm.length)];
            boolean parkeringinn = parkering[r.nextInt(parkering.length)];
            boolean kjellerinn = kjeller[r.nextInt(kjeller.length)];
            boolean heisinn = kjeller[r.nextInt(kjeller.length)];
            int mpris = makspris[r.nextInt(makspris.length)];

            Soker soker = new Soker(String.valueOf(i+1),fornavn,etternavn,adresse,mail,String.valueOf(i+100),antpersoner,sivstat,work,arbforhold,minareal,maksareal,minpris,mpris,
                    røykeinn,husdyrinn,balkonginn,terasseinn,TVinn,internetinn,strøminn,parkeringinn,kjellerinn,heisinn,bregister,legister);
            sregister.leggTil(soker);
        }

        for(int i=0;i<500; i++){
            int bareal = boareal[r.nextInt(boareal.length)];
            String adresse = adresses[r.nextInt(adresses.length)];
            int rom = antrom[r.nextInt(antrom.length)];
            int år = byggår[r.nextInt(byggår.length)];
            int price = pris[r.nextInt(pris.length)];
            String pnr = String.valueOf(i+1);
            Utleier utleier = register.get(pnr);
            boolean smoke = røyke[r.nextInt(røyke.length)];
            boolean dyr = husdyr[r.nextInt(husdyr.length)];
            boolean balk = balkong[r.nextInt(balkong.length)];
            boolean ter = terasse[r.nextInt(terasse.length)];
            boolean TV = tv[r.nextInt(tv.length)];
            boolean internet = nett[r.nextInt(nett.length)];
            boolean s = strøm[r.nextInt(strøm.length)];
            boolean park = parkering[r.nextInt(parkering.length)];
            int etg = antetg[r.nextInt(antetg.length)];
            boolean kj = kjeller[r.nextInt(kjeller.length)];
            double tmt = tomt[r.nextInt(tomt.length)];
            int bad = antbad[r.nextInt(antbad.length)];
            String ledig = ledigfra[r.nextInt(ledigfra.length)];
            String beskriv = beskrivelse[r.nextInt(beskrivelse.length)];
            String bildesti = "Bilder/boligbilder/"+ String.valueOf((1 + r.nextInt(46))) + ".jpg";

            Enebolig enebolig = new Enebolig(bildesti,adresse,bareal,rom,år,beskriv,price,ledig,String.valueOf(i+1),utleier,smoke,dyr,balk,ter,
                    TV,internet,s,park,etg,kj,tmt,bad);
            bregister.leggTil(enebolig);
        }

        for(int i=0;i<500; i++){
            int bareal = boareal[r.nextInt(boareal.length)];
            String adresse = adresses[r.nextInt(adresses.length)];
            int rom = antrom[r.nextInt(antrom.length)];
            int år = byggår[r.nextInt(byggår.length)];
            int price = pris[r.nextInt(pris.length)];
            String pnr = String.valueOf(i+1);
            Utleier utleier = register.get(pnr);
            boolean smoke = røyke[r.nextInt(røyke.length)];
            boolean dyr = husdyr[r.nextInt(husdyr.length)];
            boolean balk = balkong[r.nextInt(balkong.length)];
            boolean ter = terasse[r.nextInt(terasse.length)];
            boolean TV = tv[r.nextInt(tv.length)];
            boolean internet = nett[r.nextInt(nett.length)];
            boolean s = strøm[r.nextInt(strøm.length)];
            boolean park = parkering[r.nextInt(parkering.length)];
            String ledig = ledigfra[r.nextInt(ledigfra.length)];
            String beskriv = beskrivLeil[r.nextInt(beskrivLeil.length)];
            int antboder = boder[r.nextInt(boder.length)];
            int etasje = etgleil[r.nextInt(etgleil.length)];
            boolean elevator = heis[r.nextInt(heis.length)];
            String bildesti = "Bilder/boligbilder/"+ String.valueOf((1 + r.nextInt(46))) + ".jpg";


            Leilighet leilighet = new Leilighet(bildesti,adresse,bareal,rom,år,beskriv,price,ledig,String.valueOf(i+1),utleier,smoke,dyr,balk,ter,
                    TV,internet,s,park,antboder,etasje,elevator);
            legister.leggTil(leilighet);
        }

        for(int i = 0; i < 10; i++){
            String eierpnr = String.valueOf(i+1);
            String sokerpnr = String.valueOf(i+1001);
            String bolignr = String.valueOf(i+1);
            Utleier eier = register.get(eierpnr);
            Soker soker = sregister.get(sokerpnr);
            Bolig bolig = legister.get(bolignr);
            int p = pris[r.nextInt(pris.length)];
            String fra = fradato[r.nextInt(fradato.length)];
            String til = tildato[r.nextInt(tildato.length)];

            Kontrakt kontrakt = new Kontrakt(String.valueOf(i+1), bolig, eier, soker,p,fra,til);

            kregister.leggTil(kontrakt);

        }
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;
        
        frame = new MainFrame(register,bregister,sregister,legister,kregister);
        frame.setVisible(true);
        //frame.setSize();
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocation(skjerm.width/2-frame.getSize().width/2, skjerm.height/2-frame.getSize().height/2);

        frame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
    }


}
