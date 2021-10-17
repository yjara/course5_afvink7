package biotools.bioseq;

import java.awt.*;

public class DNA extends Sequentie {
    String sequentie;
    float gc;

    public DNA(String naam) {
        super(naam);
    }



    public Color getKleur(int positie) {
        sequentie = this.getSeq();
        if (sequentie.charAt(positie) == 'C' || sequentie.charAt(positie) == 'G'){
            return Color.RED;
        }else {
            return Color.YELLOW;
        }
    }

    @Override
    public float getGCperc(){
        float lengte = this.getLength();
        sequentie = this.getSeq();
        for (int i=0;i<lengte;i++){
            if (sequentie.charAt(i) == 'C' || sequentie.charAt(i) == 'G'){
                gc++;
        }}
        return (gc/lengte)*100;
    }
}
