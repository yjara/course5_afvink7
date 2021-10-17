package biotools.bioseq;
import java.awt.*;


public class Peptide extends Sequentie{
    static String apolair = "[GAVLIMFWP]";
    static String polair = "[STCYNQ]";
    static String charged = "[DERKH]";


    String sequentie;
    public Peptide(String naam) {
        super(naam);
    }

    public Color getKleur(int positie) {
        sequentie = this.getSeq();
        String aminoAcid = String.valueOf(sequentie.charAt(positie));
        if (aminoAcid.matches(apolair)){
            return Color.RED;
        }else if (aminoAcid.matches(charged)){
            return Color.BLUE;
        }else{
            return Color.LIGHT_GRAY;
        }
    }
}
