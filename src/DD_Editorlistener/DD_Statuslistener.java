/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DD_Editorlistener;

import DD_Objekte.DD_Monster;
import DD_Objekte.DD_Spieler;
import DD_Objekte.DD_Umgebung;
import DD_Spielfeld.DDGUI_SpielFeld;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Stack;

/**
 *
 * @author tw
 */
public class DD_Statuslistener implements MouseMotionListener {

    DDGUI_SpielFeld feld;
    Stack<Object> focusstack = new Stack();

    public DD_Statuslistener(DDGUI_SpielFeld feld) {
        this.feld = feld;
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        this.feld.requestFocus();
        me.getSource();
        int posx = me.getX() / feld.ratio;
        int posy = me.getY() / feld.ratio;

        if (posx > 30 || posy > 30) {
            return;
        }

        if (feld.getRoot().getInfopanel().getSetone().isSelected()) {
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    feld.field[i][j] = new DD_Umgebung("boden", i, j);

                }

            }
            feld.revalidate();
            feld.repaint();
            return;
        }

        System.out.println("X:" + posx + "|" + "Y:" + posy);

        int select = feld.getRoot().getInfopanel().getCurrentvalue();

        switch (select) {
            case 1:
                feld.field[posx][posy] = new DD_Umgebung("baum", posx, posy);
                feld.revalidate();
                feld.repaint();
                break;
            case 2:
                feld.field[posx][posy] = new DD_Umgebung("boden", posx, posy);
                feld.revalidate();
                feld.repaint();
                break;
            case 3:

                feld.field[posx][posy] = new DD_Umgebung("boden", posx, posy);
                feld.revalidate();
                feld.repaint();
                break;
            case 4:
                feld.field[posx][posy] = new DD_Spieler(posx, posy);
                feld.revalidate();
                feld.repaint();
                break;
            case 5:
                feld.field[posx][posy] = new DD_Monster(posx, posy);
                feld.revalidate();
                feld.repaint();
                break;
            default:

        }

    }

    @Override
    public void mouseMoved(MouseEvent me
    ) {

    }

}
