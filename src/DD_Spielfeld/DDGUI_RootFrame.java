/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DD_Spielfeld;

import XML.StaxWriter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import net.miginfocom.swing.MigLayout;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author 3flim
 */
public class DDGUI_RootFrame extends JFrame {

    private DDGUI_SpielFeld feld = new DDGUI_SpielFeld(this, 800, 500);
    private DDGUI_InfoPanel infopanel = new DDGUI_InfoPanel();
   
    private JMenuBar spielmenuBar = new JMenuBar();

       public DDGUI_SpielFeld getFeld() {
        return feld;
    }

    public void setFeld(DDGUI_SpielFeld feld) {
        this.feld = feld;
    }

    public DDGUI_InfoPanel getInfopanel() {
        return infopanel;
    }

    public DDGUI_RootFrame() {
        this.setLayout(new BorderLayout());

        setUpMenubar(spielmenuBar);
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.white);
        contentPanel.setLayout(new MigLayout());

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        feld.setPreferredSize(new Dimension(800, 810));

        infopanel.setPreferredSize(new Dimension(200, 600));
     
        JScrollPane sp_feld = new JScrollPane(feld);
        sp_feld.setBorder(null);
        JScrollPane sp_info = new JScrollPane(infopanel);
        sp_info.setBorder(null);
    
        //  contentPanel.add(spielmenuBar,"span 3");
        contentPanel.add(feld, "span 2");
        contentPanel.add(infopanel, "span 1,wrap");
    
        Image img = null;
        try {
            img = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/logo.png"));

        } catch (IOException ex) {
            Logger.getLogger(DDGUI_RootFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.setIconImage(img);
        this.add(spielmenuBar, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        this.setSize(new Dimension(1100, 900));
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    public final void setUpMenubar(JMenuBar menu) {

        JMenu spielmenu = new JMenu("Spiel Optionen");
        JButton speichern = new JButton("Spiel speichern");
        speichern.setBorder(null);
        speichern.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String path = null;
                try {
                    StaxWriter staxwriter = new StaxWriter();
                    final JFileChooser fc = new JFileChooser("C:/");
                    FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter(
                            ".xml", ".xml");
                    fc.setFileFilter(xmlFilter);
                    int a = fc.showSaveDialog(speichern);

                    if (fc.getSelectedFile().getPath() != null) {
                        path = fc.getSelectedFile().getPath();
                        if (path.endsWith(path)) {
                            path = path + ".xml";
                        }
                    }

                    if (path != null) {
                        staxwriter.writer = staxwriter.outputFactory.createXMLEventWriter(
                                new FileOutputStream(path));

                        StoreProject(staxwriter);
                    }
                } catch (Exception ex) {
                    System.out.println("kein Pfad ausgewählt");

                }
                System.out.println("Gespeichert in " + path);
            }
        });

        spielmenu.add(speichern);

        spielmenu.add(new JMenu("Spiel Optionen"));

        menu.add(spielmenu);

        menu.setBackground(Color.white);

    }

    JDialog dia;
    JProgressBar xmlprogress;

    public void ProgressXML(JDialog dia) {

        xmlprogress = new JProgressBar();
        xmlprogress.setPreferredSize(new Dimension(50, 25));
        dia.add(xmlprogress);
        dia.pack();
        dia.setLocationRelativeTo(this);
        dia.setVisible(true);
    }

    public void StoreProject(StaxWriter staxwriter) throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

        // create an EventFactory
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        // create and write Start Tag
        StartDocument startDocument = eventFactory.createStartDocument();
        staxwriter.writer.add(startDocument);

        // create config open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", "config");
        staxwriter.writer.add(configStartElement);
        staxwriter.writer.add(end);

        feld.STAXStore(staxwriter, eventFactory);
        staxwriter.writer.add(eventFactory.createEndElement("", "", "config"));
        staxwriter.writer.add(end);
        staxwriter.writer.add(eventFactory.createEndDocument());

        staxwriter.writer.close();
        dia.setVisible(false);
    }

}
