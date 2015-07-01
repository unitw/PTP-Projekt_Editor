/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DD_Spielfeld;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
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

    Map<Integer, BufferedImage> curBuffMap = new HashMap();

    ImageIcon iconstein = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/stein.png"));
    ImageIcon iconboden = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/boden.png"));
    ImageIcon iconziel = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/ziel.png"));
    ImageIcon iconmonster = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/geist.gif"));
    ImageIcon iconplayer = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/playerz.gif"));

    private BufferedImage bfstein;
    private BufferedImage bfnboden;
    private BufferedImage bfziel;
    private BufferedImage bfmonster;
    private BufferedImage bfplayer;

    JButton fcgstein = new JButton("Icon:Stein");
    JButton fcgboden = new JButton("Icon:Boden");
    JButton fcgziel = new JButton("Icon:Ziel");
    JButton fcgmonster = new JButton("Icon:Monster");
    JButton fcgplayer = new JButton("Icon:Spieler");

    private JButton stein;
    ;
    private JButton boden;
    private JButton ziel;
    private JButton monster;
    private JButton player;

    private JCheckBox setone;

    public int currentvalue;
    boolean bodenjn = true;

    public DDGUI_InfoPanel() {
        super();
        try {
            this.bfstein = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/stein.png"));
            this.bfziel = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/ziel.png"));
            this.bfnboden = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/boden.png"));
        } catch (IOException ex) {
            Logger.getLogger(DDGUI_InfoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        curBuffMap.put(1, bfstein);
        curBuffMap.put(2, bfziel);
        curBuffMap.put(3, bfnboden);
        curBuffMap.put(4, bfplayer);
        curBuffMap.put(5, bfmonster);

        this.setBackground(Color.white);
        this.setLayout(new MigLayout());

        this.WIDTH = this.getWidth();
        this.HEIGHT = this.getHeight();
        this.ratio = this.WIDTH / this.ZELLEN;
        this.boden = new JButton(iconboden);
        this.stein = new JButton(iconstein);
        this.ziel = new JButton(iconziel);
        this.monster = new JButton(iconmonster);
        this.player = new JButton(iconplayer);
        this.setone = new JCheckBox("Alles füllen");

        boden.addActionListener(this);
        stein.addActionListener(this);
        ziel.addActionListener(this);
        monster.addActionListener(this);
        player.addActionListener(this);
        setone.addActionListener(this);

        fcgboden.addActionListener(this);
        fcgstein.addActionListener(this);
        fcgziel.addActionListener(this);
        fcgmonster.addActionListener(this);
        fcgplayer.addActionListener(this);

        this.add(boden, "cell 0 0, center");
        this.add(fcgboden, "cell 1 0, center");
        this.add(stein, "cell 0 1, center");
        this.add(fcgstein, "cell 1 1, center");
        this.add(ziel, "cell 0 2, center");
        this.add(fcgziel, "cell 1 2, center");
        this.add(monster, "cell 0 3, center");
        this.add(fcgmonster, "cell 1 3, center");
        this.add(player, "cell 0 4, center");
        this.add(fcgplayer, "cell 1 4, center");
        this.add(setone, "cell 0 5, center");

        this.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource().equals(stein)) {
            currentvalue = 1;
        }
        if (ae.getSource().equals(ziel)) {
            currentvalue = 2;
        }
        if (ae.getSource().equals(boden)) {
            currentvalue = 3;
        }
        if (ae.getSource().equals(player)) {
            currentvalue = 4;
        }
        if (ae.getSource().equals(monster)) {
            currentvalue = 5;
        }

        if (ae.getSource().equals(fcgboden)) {
            final JFileChooser fc1 = new JFileChooser("C:/");

            int a1 = fc1.showOpenDialog(null);
            if (fc1.getSelectedFile().getPath() == null) {
                return;
            }
            URL path1 = null;
            try {
                path1 = fc1.getSelectedFile().toURI().toURL();
            } catch (MalformedURLException ex) {
                Logger.getLogger(DDGUI_InfoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            iconboden = new ImageIcon(path1);

            try {
                bfnboden = ImageIO.read(path1);
            } catch (IOException ex) {
                Logger.getLogger(DDGUI_InfoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            boden.setIcon(iconboden);

        }
        if (ae.getSource().equals(fcgmonster)) {
            final JFileChooser fc1 = new JFileChooser("C:/");

            int a1 = fc1.showOpenDialog(null);
            if (fc1.getSelectedFile().getPath() == null) {
                return;
            }
            URL path1 = null;
            try {
                path1 = fc1.getSelectedFile().toURI().toURL();
            } catch (MalformedURLException ex) {
                Logger.getLogger(DDGUI_InfoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            iconmonster = new ImageIcon(path1);

            try {
                bfmonster = ImageIO.read(path1);
            } catch (IOException ex) {
                Logger.getLogger(DDGUI_InfoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            monster.setIcon(iconmonster);

        }
        if (ae.getSource().equals(fcgplayer)) {
            final JFileChooser fc1 = new JFileChooser("C:/");

            int a1 = fc1.showOpenDialog(null);
            if (fc1.getSelectedFile().getPath() == null) {
                return;
            }
            URL path1 = null;
            try {
                path1 = fc1.getSelectedFile().toURI().toURL();
            } catch (MalformedURLException ex) {
                Logger.getLogger(DDGUI_InfoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            iconplayer = new ImageIcon(path1);

            try {
                bfplayer = ImageIO.read(path1);
            } catch (IOException ex) {
                Logger.getLogger(DDGUI_InfoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            player.setIcon(iconplayer);

        }
        if (ae.getSource().equals(fcgstein)) {
            final JFileChooser fc1 = new JFileChooser("C:/");

            int a1 = fc1.showOpenDialog(null);
            if (fc1.getSelectedFile().getPath() == null) {
                return;
            }
            URL path1 = null;
            try {
                path1 = fc1.getSelectedFile().toURI().toURL();
            } catch (MalformedURLException ex) {
                Logger.getLogger(DDGUI_InfoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            iconstein = new ImageIcon(path1);

            try {
                bfstein = ImageIO.read(path1);
            } catch (IOException ex) {
                Logger.getLogger(DDGUI_InfoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            stein.setIcon(iconstein);

        }
        if (ae.getSource().equals(fcgziel)) {
            final JFileChooser fc1 = new JFileChooser("C:/");

            int a1 = fc1.showOpenDialog(null);
            if (fc1.getSelectedFile().getPath() == null) {
                return;
            }
            URL path1 = null;
            try {
                path1 = fc1.getSelectedFile().toURI().toURL();
            } catch (MalformedURLException ex) {
                Logger.getLogger(DDGUI_InfoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            iconziel = new ImageIcon(path1);

            try {
                bfziel = ImageIO.read(path1);
            } catch (IOException ex) {
                Logger.getLogger(DDGUI_InfoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            ziel.setIcon(iconziel);

        }
        curBuffMap.put(1, bfstein);
        curBuffMap.put(2, bfziel);
        curBuffMap.put(3, bfnboden);
        curBuffMap.put(4, bfplayer);
        curBuffMap.put(5, bfmonster);

    }

    public BufferedImage getBfstein() {
        return bfstein;
    }

    public BufferedImage getBfnboden() {
        return bfnboden;
    }

    public BufferedImage getBfziel() {
        return bfziel;
    }

    public BufferedImage getBfmonster() {
        return bfmonster;
    }

    public BufferedImage getBfplayer() {
        return bfplayer;
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

    public BufferedImage getCurrentvalueImage() {
        return curBuffMap.get(currentvalue);
    }

}
