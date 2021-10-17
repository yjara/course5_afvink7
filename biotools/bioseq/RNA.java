package biotools.bioseq;

import java.awt.*;

public class RNA extends Sequentie{
    String sequentie;
    public RNA(String naam) {
        super(naam);
    }
    public Color getKleur(int positie) {
        sequentie = this.getSeq();
        if (sequentie.charAt(positie) == 'G' || sequentie.charAt(positie) == 'C'){
            return Color.RED;
        }else{
            return Color.BLUE;
        }
    }
}
