import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;



/**
 * Bildbetrachter ist die Hauptklasse der Bildbetrachter-Anwendung. Sie
 * erstellt die GUI der Anwendung, zeigt sie an und initialisiert alle
 * anderen Komponenten.
 * 
 * Erzeugen Sie ein Exemplar dieser Klasse, um die Anwendung zu starten.
 * 
 * @author Michael Kölling und David J. Barnes 
 * @version 1.0
 */
public class Bildbetrachter
{
    // statische Datenfelder (Klassenkonstanten und -variablen)
    private static final String VERSION = "Version 1.0";
    private static JFileChooser dateiauswahldialog = new JFileChooser(System.getProperty("user.dir"));
    private static final Logger LOGGER = Logger.getLogger(Bildbetrachter.class.getName());
    private static final ConsoleHandler CH = new ConsoleHandler();
    
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
        try {
			loggerAnlegen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void loggerAnlegen() throws IOException {
    	OwnFormatter x = new OwnFormatter();
    	CH.setFormatter(x);
    	
    	LOGGER.addHandler(CH);
    	LOGGER.log(Level.FINEST, "logging finest");
    	LOGGER.setLevel(Level.FINEST);
    	
    	//Workbook anlegen
    	Workbook wb = new HSSFWorkbook();
        
        
        //create a sheet
        Sheet sheet1 = wb.createSheet("Mein Sheet");
        CreationHelper createHelper = wb.getCreationHelper();
        
        Row row = sheet1.createRow(0);
        Cell cell = row.createCell((short)0);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    	
    }

    // ---- Implementierung der Menü-Funktionen ----
    
    /**
     * 'Datei oeffnen'-Funktion: Öffnet einen Dateiauswahldialog zur 
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
        
        if(aktuellesBild == null) {   // Bilddatei nicht im gültigen Format
            JOptionPane.showMessageDialog(fenster,
                    "Die Datei hat keines der unterstützten Formate.",
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
     * 'Schliessen'-Funktion: Schließt das aktuelle Bild.
     */
    private void schliessen()
    {
        aktuellesBild = null;
        bildflaeche.loeschen();
        dateinameAnzeigen(null);
        LOGGER.info("Bild geschlossen!");
    }
    
    
    /**
     * 'Beenden'-Funktion: Beendet die Anwendung.
     */
    private void beenden()
    {
    	LOGGER.info("Programm verlassen.");
        System.exit(0);
    }
    
    // ---- Hilfsmethoden ----

    /**
     * Zeigt den Dateinamen des aktuellen Bildes auf dem Label für den
     * Dateinamen.
     * Der Parameter sollte 'null' sein, wenn kein Bild geladen ist. 
     * 
     * @param dateiname  Der anzuzeigende Dateiname, oder null für 'keine Datei'.
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
        
        // Ein Layout mit hübschen Abständen definieren
        contentPane.setLayout(new BorderLayout(6, 6));
        
        // Die Bildfläche in der Mitte erzeugen
        bildflaeche = new Bildflaeche();
        contentPane.add(bildflaeche, BorderLayout.CENTER);

        // Zwei Labels oben und unten für den Dateinamen und Statusmeldungen
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
     * Die Menüzeile des Hauptfensters erzeugen.
     * @param fenster  Das Fenster, in das die Menüzeile eingefügt werden soll.
     */
    private void menuezeileErzeugen(final JFrame fenster)
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menuezeile = new JMenuBar();
        fenster.setJMenuBar(menuezeile);
        
        JMenu menue;
        JMenuItem eintrag;
        
<<<<<<< HEAD
        // Das Datei-Menue erzeugen + Onclick options 
=======
        // Das Datei-Menü erzeugen
>>>>>>> e2004955aa603b2b636084d203fb83c7a8bd00eb
        menue = new JMenu("Datei");
        menuezeile.add(menue);
        
        eintrag = new JMenuItem("Öffnen...");
            eintrag.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
            eintrag.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { dateiOeffnen(); }
                           });
        menue.add(eintrag);

        eintrag = new JMenuItem("Schließen");
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


        // Das Filter-Menü erzeugen
        menue = new JMenu("Filter");
        menuezeile.add(menue);
        File file = new File("/Users/kristoffer/Documents/blatt7/bin");
        String[] classes = file.list();
        
        for (String st: classes) {
        	final String[] x = st.split("\\.");
        	if (x[0].contains("Filter") && !x[0].equals("IFilter")) {
        		if (x[1].equals("class")) {
        			String[] y = x[0].split("Filter");
        			eintrag = new JMenuItem(y[0]);
        			eintrag.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent e) { //on click
        					if (aktuellesBild != null) {
        						Class<?> c = null;
        			   			try {
        			   				c = Class.forName(x[0]);
        			   			} catch (ClassNotFoundException ex) {
        			   				System.err.println("Klasse existiert nicht.");
        			   			}
        			   			if (c != null) {
        			   				IFilter f = null;
        			   				try {
        			   					f = (IFilter) c.newInstance();
        			   				} catch (Exception ex) {
        			   					System.err.println("Fehler.");
        			   				}
        			   				if (f != null) {
        			   					f.anwenden(aktuellesBild);
        			   					fenster.repaint();
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
