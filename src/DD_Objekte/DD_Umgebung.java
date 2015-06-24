/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DD_Objekte;

import DD_Spielfeld.DDGUI_SpielFeld;
import XML.StaxStore;
import XML.StaxWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author tw
 */
public class DD_Umgebung implements IDD_MenuAnzeiger, StaxStore {

    String Typ;

    int xpos;
    int ypos;

    public boolean isHasfocus() {
        return hasfocus;
    }

    public void setHasfocus(boolean hasfocus) {
        this.hasfocus = hasfocus;
    }
    boolean hasfocus = false;

    public DD_Umgebung(String Typ) {
        this.Typ = Typ;
    }

    public DD_Umgebung(String Typ, int x, int y) {
        this.Typ = Typ;
        this.xpos = x;
        this.ypos = y;
    }

    public String getTyp() {
        return Typ;
    }

    public void setTyp(String Typ) {
        this.Typ = Typ;
    }

    @Override
    public void showMenu(JPanel panel) {

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
    public int getXpos() {
        return xpos;
    }

    @Override
    public void setXpos(String s) {
        xpos = Integer.parseInt(s);
    }

    @Override
    public int getYpos() {
        return ypos;
    }

    @Override
    public void setYpos(String s) {
        ypos = Integer.parseInt(s);
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
