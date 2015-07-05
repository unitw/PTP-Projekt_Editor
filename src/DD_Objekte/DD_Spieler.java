/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DD_Objekte;

import DD_Spielfeld.DDGUI_SpielFeld;
import XML.StaxStore;
import XML.StaxWriter;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author tw
 */
public class DD_Spieler implements StaxStore {

    int xpos;
    int ypos;
    int dir;
    JLabel l_gif = new JLabel();
    int i_leben = 100;
    int i_mana = 30;
    int i_ruestung = 50;
    int i_schaden = 15;

    String Typ = "Spieler";

    Map<Number, Number> faehigkeitenrange = new HashMap<>();

    private BufferedImage playerUnten;
    private BufferedImage playerOben;
    private BufferedImage playerRechts;
    private BufferedImage playerLinks;
    private BufferedImage playerImageAktuell;

    boolean hasfocus = false;

    public DD_Spieler() {
    }

    public DD_Spieler(int xpos, int ypos) {
        faehigkeitenrange.put(1, 1);
        faehigkeitenrange.put(2, 1);
        faehigkeitenrange.put(3, 5);

        dir = 1;
        this.xpos = xpos;
        this.ypos = ypos;
//            this.playerUnten = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/playerUnten.png"));
//            this.playerOben = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/playerOben.png"));
//            this.playerRechts = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/playerRechts.png"));
//            this.playerLinks = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/playerLinks.png"));

        playerImageAktuell = playerUnten;
        l_gif.setIcon(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/playerz.gif")));
        l_gif.setBorder(null);
        l_gif.setOpaque(true);

    }

    public int getFaehigkeitRange(int attackNr) {

        return (int) faehigkeitenrange.get(attackNr);

    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public JLabel getL_gif() {
        return l_gif;
    }

    public BufferedImage getPlayerImage() {
        return playerImageAktuell;
    }

    public void setPlayerImage(BufferedImage image) {
        playerImageAktuell = image;
    }

    public BufferedImage getPlayerUnten() {
        return playerUnten;
    }

    public BufferedImage getPlayerOben() {
        return playerOben;
    }

    public BufferedImage getPlayerRechts() {
        return playerRechts;
    }

    public BufferedImage getPlayerLinks() {
        return playerLinks;
    }

    public int getL_leben() {
        return i_leben;
    }

    public void setL_leben(int l_leben) {
        this.i_leben = l_leben;
    }

    public int getL_mana() {
        return i_mana;
    }

    public void setL_mana(int l_mana) {
        this.i_mana = l_mana;
    }

    public int getL_ruestung() {
        return i_ruestung;
    }

    public void setL_ruestung(int l_ruestung) {
        this.i_ruestung = l_ruestung;
    }

    public int getL_schaden() {
        return i_schaden;
    }

    public void setL_schaden(int l_schaden) {
        this.i_schaden = l_schaden;
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    @Override
    public void STAXStore(StaxWriter staxwriter, XMLEventFactory eventFactory) {

        Integer xpos1 = xpos;

        Integer ypos1 = ypos;

        String linespos = "";

   
        try {
            staxwriter.StoreDD_Objekt(staxwriter.writer, Typ, "xPos", xpos1.toString(), "yPos", ypos1.toString(), 1);
        } catch (XMLStreamException ex) {
            Logger.getLogger(DDGUI_SpielFeld.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getIdentifier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIdentifier(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setXpos(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setYpos(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getbreite() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setbreite(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int gethoehe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sethoehe(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
