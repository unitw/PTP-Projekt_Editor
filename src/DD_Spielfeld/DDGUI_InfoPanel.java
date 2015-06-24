/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DD_Spielfeld;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * http://www.migcalendar.com/miglayout/whitepaper.html
 *
 * @author 3flim
 */
public class DDGUI_InfoPanel extends JPanel implements ActionListener {

    int WIDTH;
    int HEIGHT;
    int ratio;
    int ZELLEN = 30;

    ImageIcon iconstein = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/ziel.png"));
    ImageIcon iconboden = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/boden.png"));
    ImageIcon iconziel = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/stein.png"));
    ImageIcon iconmonster = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/geist.gif"));
    ImageIcon iconplayer = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/playerz.gif"));

    private JButton stein;
    private JButton boden;
    private JButton ziel;
    private JButton monster;
    private JButton player;

    private JCheckBox setone;

    public int currentvalue;
    boolean bodenjn = true;

    public DDGUI_InfoPanel() {
        super();
        this.setBackground(Color.white);
        this.setLayout(new MigLayout());

        this.WIDTH = this.getWidth();
        this.HEIGHT = this.getHeight();
        this.ratio = this.WIDTH / this.ZELLEN;
        this.boden = new JButton(iconboden);
        this.stein = new JButton(iconziel);
        this.ziel = new JButton(iconstein);
        this.monster = new JButton(iconmonster);
        this.player = new JButton(iconplayer);
        this.setone = new JCheckBox("Alles füllen");

        boden.addActionListener(this);
        stein.addActionListener(this);
        ziel.addActionListener(this);
        monster.addActionListener(this);
        player.addActionListener(this);
        setone.addActionListener(this);

        this.add(boden, "cell 0 0, center");
        this.add(stein, "cell 0 1, center");
        this.add(ziel, "cell 0 2, center");
        this.add(monster, "cell 0 3, center");
        this.add(player, "cell 0 4, center");
        this.add(setone, "cell 0 5, center");

        this.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource().equals(stein)) {
            currentvalue = 1;
        }
        if (ae.getSource().equals(boden)) {
            currentvalue = 3;
        }
        if (ae.getSource().equals(ziel)) {
            currentvalue = 2;
        }
        if (ae.getSource().equals(player)) {
            currentvalue = 4;
        }
        if (ae.getSource().equals(monster)) {
            currentvalue = 5;
        }

    }

    public JCheckBox getSetone() {
        return setone;
    }

    public void setSetone(JCheckBox setone) {
        this.setone = setone;
    }

    
     
    
    
    public int getCurrentvalue() {
        return currentvalue;
    }

}
