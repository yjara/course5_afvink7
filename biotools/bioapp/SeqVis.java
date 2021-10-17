package biotools.bioapp;

import biotools.bioseq.DNA;
import biotools.bioseq.Peptide;
import biotools.bioseq.RNA;
import biotools.bioseq.Sequentie;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

public class SeqVis extends JFrame implements ActionListener {
    //info
    static String fileName;
    Sequentie seq;

    //type
    static String patternDNA = "[ATGC]*";
    static String patternRNA = "[AUGC]*";
    static String patternProtein = "[^BJOUXZ0-9]*";

    //GUI
    JLabel bestand,informatie,percentage;
    JTextField input;
    JButton blader,analyseer;
    JTextArea info;
    JPanel panel;

    public static void main(String[] args){
        SeqVis frame = new SeqVis();
        frame.setSize(500,450);
        frame.createGui();
        frame.setVisible(true);
    }

    public void createGui(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setBackground(Color.pink);
        window.setLayout(new FlowLayout());

        blader = new JButton("blader");
        blader.setBounds(290,10,80,20);
        blader.addActionListener(this);

        analyseer = new JButton("analyseer");
        analyseer.setBounds(375,10,120,20);
        analyseer.addActionListener(this);

        input = new JTextField(60);
        input.setBounds(85,10,200,20);
        input.setText("Kies bestand");
        input.setEditable(false);

        bestand = new JLabel("Bestand");
        bestand.setBounds(10,10,100,20);

        informatie = new JLabel("Informatie");
        informatie.setBounds(10,30,200,20);

        percentage = new JLabel("Visualisatie");
        percentage.setBounds(10,250,200,20);

        info = new JTextArea();
        info.setEditable(false);
        info.setBounds(85,50,400,200);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(480,50));
        panel.setBounds(10,280,480,50);
        panel.setBackground(Color.white);

        window.setLayout(null);
        window.add(blader);
        window.add(analyseer);
        window.add(bestand);
        window.add(informatie);
        window.add(info);
        window.add(percentage);
        window.add(input);
        window.add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent e){

        if (e.getSource().equals(blader)){
            JFileChooser gekozenBestand = new JFileChooser(FileSystemView.getFileSystemView());
            gekozenBestand.showSaveDialog(null);
            fileName = gekozenBestand.getSelectedFile().getAbsolutePath();
            input.setText(fileName);
        }else if(e.getSource().equals(analyseer)) {
            try {
                openfile();
                drawRecsetText();
            } catch (InvalidFileException ex) {
                ex.printStackTrace();
            }
        }

    }
    public void drawRecsetText() throws InvalidFileException{
        Graphics paper = panel.getGraphics();
        try {
            float lengte = seq.getLength();
            for (int i=0;i<lengte;i++){
                paper.setColor(seq.getKleur(i));
                //paper.drawLine(0+i,0,0+i,50); another option
                paper.drawRect(0,0,480,50);
                if (i == 0){
                    paper.fillRect(0,0,480,50);
                }else paper.fillRect((int) (480 * i / lengte), 0, (int) (480 * (i / lengte)), 50);
            }
            info.setText("Type:"+seq.getName()+"\nLengte van de sequentie:"+seq.getLength());

        }catch (NullPointerException ex){
            throw new InvalidFileException("Wrong file",fileName,ex);
        }
    }
    public void openfile()throws InvalidFileException{
        try{
            File bestand = new File(fileName);
            String content = new Scanner(bestand, StandardCharsets.UTF_8)
                    .useDelimiter(UUID.randomUUID().toString()).next();
            String[] sequentieStukken = content.split("\n");
            String sequentie = "";
            for (int i=0;i<sequentieStukken.length;i++){
                if(i!=0) {
                    sequentie += sequentieStukken[i];
                }
            }
            if (sequentie.toUpperCase(Locale.ROOT).matches(patternDNA)){
                System.out.println("DNA");
                seq = new DNA("DNA");
                seq.setSeq(sequentie);
                seq.setName("DNA");
                System.out.println("GC%:"+seq.getGCperc());
            }else if(sequentie.toUpperCase(Locale.ROOT).matches(patternRNA)){
                System.out.println("RNA");
                seq = new RNA("RNA");
                seq.setSeq(sequentie);
                seq.setName("RNA");
            }else if(sequentie.toUpperCase(Locale.ROOT).matches(patternProtein)){
                System.out.println("protein");
                seq = new Peptide("Peptide");
                seq.setSeq(sequentie);
                seq.setName("Peptide");
            }else{
                System.out.println("Verkeerde format en/of verkeerde bestand");
                throw new InvalidFileException("Incorrect file",fileName);
            }
        }catch (FileNotFoundException e){
            System.out.println("error");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
