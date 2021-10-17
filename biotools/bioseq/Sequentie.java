package biotools.bioseq;

import java.awt.*;

public abstract class Sequentie {
    String seq;
    Color kleur;
    String name;

    Sequentie(String naam){
        this.seq = naam;
        this.kleur = Color.pink;
    }

    // set
    public String getSeq() {
        return seq;
    }
    public void setSeq(String seq) {
        this.seq = seq;
    }
    public void setName(String name) {this.name = name;}
    // get
    public int getLength(){
        return seq.length();
    }
    public abstract Color getKleur(int i);
    public String getName(){return name;}

    public float getGCperc() {
        return 0;
    }
}
