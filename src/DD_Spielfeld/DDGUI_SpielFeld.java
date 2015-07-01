/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DD_Spielfeld;

import DD_Editorlistener.DD_Statuslistener;
import DD_Objekte.DD_Monster;
import DD_Objekte.DD_Schatztruhe;
import DD_Objekte.DD_Spieler;
import DD_Objekte.DD_Umgebung;
import XML.StaxStore;
import XML.StaxWriter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.xml.stream.XMLEventFactory;

/**
 *
 * @author 3flim
 */
public class DDGUI_SpielFeld extends JPanel implements StaxStore {

    public final int WIDTH;
    private final int HEIGHT;

    private BufferedImage stein;
    private BufferedImage boden;
    private BufferedImage player;
    Image image = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource("resources/feuerball1.gif"));
    Image geist = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource("resources/geist.gif"));

    private BufferedImage monster;
    private int playerX = 2;
    private int playerY = 2;

    private BufferedImage ziel;
    private int zielX = 25;
    private int zielY = 25;

    public int x;
    public int y;

    ArrayList<DD_Monster> monsterlist = new ArrayList();
    DD_Spieler dd_player = new DD_Spieler(2, 2);

    public DD_Spieler getDD_player() {
        return dd_player;
    }

    public void setDd_player(DD_Spieler dd_player) {
        this.dd_player = dd_player;
    }

    public Object[][] field;

    public Object[][] getField() {
        return field;
    }

    public void setField(Object[][] field) {
        this.field = field;
    }
    private final int ZELLEN = 30;
    public final int ratio;

    public int getRatio() {
        return ratio;
    }

    DD_Statuslistener status = new DD_Statuslistener(this);

    DDGUI_RootFrame root;

    private int runde = 0;

    public DDGUI_SpielFeld(DDGUI_RootFrame root, int width, int height) {
        this.setLayout(null);
        this.root = root;

        this.addMouseMotionListener(status);
        this.setBackground(Color.white);
        this.WIDTH = width;
        this.HEIGHT = height;
        this.ratio = this.WIDTH / this.ZELLEN;

        try {
            this.boden = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/boden.png"));
            this.stein = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/stein.png"));
            this.ziel = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/ziel.png"));
            this.monster = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/monster.png"));

        } catch (IOException ex) {
            Logger.getLogger(DDGUI_SpielFeld.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.field = new Object[ZELLEN][ZELLEN];

//        this.field[this.zielX][this.zielY] = new DD_Umgebung("boden", this.zielX, this.zielY);
//        this.field[dd_player.getXpos()][dd_player.getYpos()] = dd_player;
////        DD_Monster mon = new DD_Monster(3, 3);
////        monsterlist.add(mon);
        //   this.field[3][3] = mon;//Monster
        this.setFocusable(true);
        this.setSize(width, height);

    }

    public BufferedImage getStein() {
        return stein;
    }

    public void setStein(BufferedImage stein) {
        this.stein = stein;
    }

    public BufferedImage getBoden() {
        return boden;
    }

    public void setBoden(BufferedImage boden) {
        this.boden = boden;
    }

    public Image getGeist() {
        return geist;
    }

    public void setGeist(Image geist) {
        this.geist = geist;
    }

    public BufferedImage getZiel() {
        return ziel;
    }

    public void setZiel(BufferedImage ziel) {
        this.ziel = ziel;
    }

    public DDGUI_RootFrame getRoot() {
        return root;
    }

    public int getRunde() {
        return runde;
    }

    public void setRunde(int runde) {
        this.runde = runde;
    }

    public void setRoot(DDGUI_RootFrame root) {
        this.root = root;
    }

    JDialog optiondia = new JDialog();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < ZELLEN; i++) {
            for (int j = 0; j < ZELLEN; j++) {

                if (this.field[i][j] instanceof DD_Umgebung) {

                    DD_Umgebung umgebung = (DD_Umgebung) this.field[i][j];

                    switch (umgebung.getTyp()) {
                        case "boden":
                            g.drawImage(umgebung.getBackgroundImg(), i * this.ratio, j * this.ratio, this.ratio, this.ratio, null);
                            break;
                        case "baum":
                            g.drawImage(umgebung.getBackgroundImg(), i * this.ratio, j * this.ratio, this.ratio, this.ratio, null);
                            break;
                    }
                    if (umgebung.isHasfocus()) {
                        g.setColor(Color.white);
                        g.drawRect(i * this.ratio, j * this.ratio, this.ratio, this.ratio);
                    }

                } else if (this.field[i][j] instanceof DD_Schatztruhe) {
                    DD_Schatztruhe schatz = (DD_Schatztruhe) this.field[i][j];

                    g.drawImage(schatz.getBackgroundImg(), i * this.ratio, j * this.ratio, this.ratio, this.ratio, null);

                } else if (this.field[i][j] instanceof DD_Monster) {

                    DD_Monster mon = (DD_Monster) this.field[i][j];

                    this.remove(mon.getL_gif());

                    final int x = i;
                    final int y = j;

                    mon.getL_gif().setBounds(x * DDGUI_SpielFeld.this.getRatio(), y * DDGUI_SpielFeld.this.getRatio(), DDGUI_SpielFeld.this.getRatio(), DDGUI_SpielFeld.this.getRatio());

                    this.add(mon.getL_gif());

                } else if (this.field[i][j] instanceof DD_Spieler) {

                    DD_Spieler sp = (DD_Spieler) this.field[i][j];
                    this.remove(sp.getL_gif());
                    sp.getL_gif().setBounds(i * DDGUI_SpielFeld.this.getRatio(), j * DDGUI_SpielFeld.this.getRatio(), DDGUI_SpielFeld.this.getRatio(), DDGUI_SpielFeld.this.getRatio());
                    this.add(sp.getL_gif());

                } else if (this.field[i][j] == null) {

                    g.setColor(Color.black);
                    g.drawRect(i * this.ratio, j * this.ratio, this.ratio, this.ratio);
                }
            }
        }

    }

    int gesamt;
    int aktvalue;

    @Override
    public void STAXStore(StaxWriter staxwriter, XMLEventFactory eventFactory) {

        Integer xpos = x;

        Integer ypos = y;

        Integer breite = this.getWidth();

        Integer hoehe = this.getHeight();

        String linespos = "";
        String Identifier = null;

        gesamt = ZELLEN * ZELLEN;

        for (int i = 0; i < ZELLEN; i++) {
            for (int j = 0; j < ZELLEN; j++) {
                if (this.field[i][j] instanceof StaxStore) {

                    StaxStore stax = (StaxStore) this.field[i][j];

                    stax.STAXStore(staxwriter, eventFactory);
                    aktvalue++;
//                   

                    System.out.println((aktvalue / gesamt) * 100);
                    //root.xmlprogress.setValue(aktvalue / gesamt);
//                    root.xmlprogress.repaint();
                }
            }
        }

    }

    public int getGesamt() {
        return gesamt;
    }

    public int getAktvalue() {
        return aktvalue;
    }

    @Override
    public String getIdentifier() {

        return null;
    }

    @Override
    public void setIdentifier(String s
    ) {

    }

    @Override
    public int getXpos() {
        return this.getX();
    }

    @Override
    public void setXpos(String s
    ) {

    }

    @Override
    public int getYpos() {
        return this.getY();
    }

    @Override
    public void setYpos(String s
    ) {

    }

    @Override
    public int getbreite() {
        return this.getWidth();
    }

    @Override
    public void setbreite(String s
    ) {

    }

    @Override
    public int gethoehe() {
        return this.getHeight();
    }

    @Override
    public void sethoehe(String s
    ) {

    }

}
