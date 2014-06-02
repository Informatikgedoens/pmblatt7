import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;

import java.io.File;
import java.util.ArrayList;

import java.lang.Class;
import java.lang.reflect.Constructor;

/**
 * Bildbetrachter ist die Hauptklasse der Bildbetrachter-Anwendung. Sie
 * erstellt die GUI der Anwendung, zeigt sie an und initialisiert alle
 * anderen Komponenten.
 * 
 * Erzeugen Sie ein Exemplar dieser Klasse, um die Anwendung zu starten.
 * 
 * @author Michael Koelling und David J. Barnes 
 * @version 1.0
 */
public class Bildbetrachter
{
    // statische Datenfelder (Klassenkonstanten und -variablen)
    private static final String VERSION = "Version 1.0";
    private static JFileChooser dateiauswahldialog = new JFileChooser(System.getProperty("user.dir"));

    // Datenfelder
    private JFrame fenster;
    private Bildflaeche bildflaeche;
    private JLabel dateinameLabel;
    private JLabel statusLabel;
    private Farbbild aktuellesBild;
    /**
     * Erzeuge einen Bildbetrachter und zeige seine GUI auf
     * dem Bildschirm an.
     */
    public Bildbetrachter()
    {
        aktuellesBild = null;
        fensterErzeugen();
    }

    // ---- Implementierung der Menue-Funktionen ----
    
    /**
     * 'Datei oeffnen'-Funktion: oeffnet einen Dateiauswahldialog zur 
     * Auswahl einer Bilddatei und zeigt das selektierte Bild an.
     */
    private void dateiOeffnen()
    {
        int ergebnis = dateiauswahldialog.showOpenDialog(fenster);

        if(ergebnis != JFileChooser.APPROVE_OPTION) { // abgebrochen
            return;  
        }
        File selektierteDatei = dateiauswahldialog.getSelectedFile();
        aktuellesBild = BilddateiManager.ladeBild(selektierteDatei);
        
        if(aktuellesBild == null) {   // Bilddatei nicht im gueltigen Format
            JOptionPane.showMessageDialog(fenster,
                    "Die Datei hat keines der unterstuetzten Formate.",
                    "Fehler beim Bildladen",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        bildflaeche.setzeBild(aktuellesBild);
        dateinameAnzeigen(selektierteDatei.getPath());
        statusAnzeigen("Datei geladen.");
        fenster.pack();
    }

    /**
     * 'Schliessen'-Funktion: Schliesst das aktuelle Bild.
     */
    private void schliessen()
    {
        aktuellesBild = null;
        bildflaeche.loeschen();
        dateinameAnzeigen(null);
    }
    
    
    /**
     * 'Beenden'-Funktion: Beendet die Anwendung.
     */
    private void beenden()
    {
        System.exit(0);
    }
    
    // ---- Hilfsmethoden ----

    /**
     * Zeigt den Dateinamen des aktuellen Bildes auf dem Label fuer den
     * Dateinamen.
     * Der Parameter sollte 'null' sein, wenn kein Bild geladen ist. 
     * 
     * @param dateiname  Der anzuzeigende Dateiname, oder null fuer 'keine Datei'.
     */
    private void dateinameAnzeigen(String dateiname)
    {
        if(dateiname == null) {
            dateinameLabel.setText("Keine Datei angezeigt.");
        }
        else {
            dateinameLabel.setText("Datei: " + dateiname);
        }
    }
    
    
    /**
     * Zeige den gegebenen Text in der Statuszeile am unteren
     * Rand des Fensters.
     * @param text der anzuzeigende Statustext.
     */
    private void statusAnzeigen(String text)
    {
        statusLabel.setText(text);
    }
    
    
    // ---- Swing-Anteil zum Erzeugen des Fensters mit allen Komponenten ----
    
    /**
     * Erzeuge das Swing-Fenster samt Inhalt.
     */
    private void fensterErzeugen()
    {
        fenster = new JFrame("Bildbetrachter");
        menuezeileErzeugen(fenster);
        
        Container contentPane = fenster.getContentPane();
        
        // Ein Layout mit huebschen Abstaenden definieren
        contentPane.setLayout(new BorderLayout(6, 6));
        
        // Die Bildflaeche in der Mitte erzeugen
        bildflaeche = new Bildflaeche();
        contentPane.add(bildflaeche, BorderLayout.CENTER);

        // Zwei Labels oben und unten fuer den Dateinamen und Statusmeldungen
        dateinameLabel = new JLabel();
        contentPane.add(dateinameLabel, BorderLayout.NORTH);

        statusLabel = new JLabel(VERSION);
        contentPane.add(statusLabel, BorderLayout.SOUTH);
        
        // Aufbau abgeschlossen - Komponenten arrangieren lassen
        dateinameAnzeigen(null);
        fenster.pack();
        
        // Das Fenster in der Mitte des Bildschirms platzieren und anzeigen
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        fenster.setLocation(d.width/2 - fenster.getWidth()/2, d.height/2 - fenster.getHeight()/2);
        fenster.setVisible(true);
    }
    
    /**
     * Die Menuezeile des Hauptfensters erzeugen.
     * @param fenster  Das Fenster, in das die Menuezeile eingefuegt werden soll.
     */
    private void menuezeileErzeugen(final JFrame fenster)
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menuezeile = new JMenuBar();
        fenster.setJMenuBar(menuezeile);
        
        JMenu menue;
        JMenuItem eintrag;
        
        // Das Datei-Men� erzeugen
        menue = new JMenu("Datei");
        menuezeile.add(menue);
        
        eintrag = new JMenuItem("Oeffnen...");
            eintrag.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
            eintrag.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { dateiOeffnen(); }
                           });
        menue.add(eintrag);

        eintrag = new JMenuItem("Schliessen");
            eintrag.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, SHORTCUT_MASK));
            eintrag.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { schliessen(); }
                           });
        menue.add(eintrag);
        menue.addSeparator();

        eintrag = new JMenuItem("Beenden");
            eintrag.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            eintrag.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { beenden(); }
                           });
        menue.add(eintrag);


        // Das Filter-Menue erzeugen
        menue = new JMenu("Filter");
        menuezeile.add(menue);
        File file = new File("/home/niklas/workspace/PM7/bin");
        String[] classes = file.list();
        
        for (String st: classes) {
        	final String[] x = st.split("\\.");
        	if (x[0].contains("Filter") && !x[0].equals("IFilter")) {
        		if (x[1].equals("class")) {
        			String[] y = x[0].split("Filter");
        			eintrag = new JMenuItem(y[0]);
        			eintrag.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent e) {
        					if (aktuellesBild != null) {
        						Class<?> c = null;
        			   			try {
        			   				c = Class.forName(x[0]);
        			   			} catch (ClassNotFoundException ex) {
        			   				System.err.println("Klasse existiert nicht.");
        			   			}
        			   			if (c != null) {
        			   				IFilter f = null;
        			   				Constructor<?> ctor = null;
        			   				Class<?>[] paramTypes = new Class<?>[] {Farbbild.class};
        			   				try {
        			   					ctor = c.getDeclaredConstructor(paramTypes);
        			   				} catch (Exception ex) {
        			   					System.err.println("Konstruktor existiert nicht.");
        			  				}
        			   				if (ctor != null) {
        			   					try {
        			   						f = (IFilter) ctor.newInstance(aktuellesBild);
        			   					} catch (Exception ex) {
        			   						System.err.println("Fehler.");
        			   					}
        			   					if (f != null) {
        			   						f.anwenden();
        			   						fenster.repaint();
        			   					}
        			   				}
        			   			}
        					}
        				}
        			});
        			menue.add(eintrag);
        		}
        	}
        }        
    }
}
