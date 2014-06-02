import java.awt.Color;
import java.awt.image.*;

import javax.imageio.*;

import java.io.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

/**
 * BilddateiManager ist eine kleine Hilfsklasse mit statischen Methoden
 * zum Laden und Speichern von Bildern.
 * 
 * Zu lesende Dateien können im JPG- oder im PNG-Format vorliegen.
 * Das Format von Dateien, die von dieser Klasse geschrieben werden,
 * wird durch die Konstante BILDFORMAT festgelegt. 
 * 
 * @author Michael Kölling und David J Barnes 
 * @version 2.0
 */
public class BilddateiManager
{
	// Eine Konstante, die das Format für geschriebene Dateien festgelegt.
	// Zulässige Formate sind "jpg" und "png".
    private static final String BILDFORMAT = "jpg";
    private static final Logger LOGGER = Logger.getLogger(BilddateiManager.class.getName());
    private static final ConsoleHandler CH = new ConsoleHandler();
    /**
     * Lies eine Bilddatei ein und liefere sie als ein Bild zurück.
     * Diese Methode kann Dateien im JPG- und im PNG-Format lesen.
     * Bei Problemen (etwa, wenn die Datei nicht existiert oder ein nicht
     * lesbares Format hat oder es einen sonstigen Lesefehler gibt)
     * liefert diese Methode null.
     * 
     * @param bilddatei  Die zu ladende Bilddatei.
     * @return           Das Bild-Objekt oder null, falls die Datei nicht
     * 					 lesbar ist.
     */
    public static Farbbild ladeBild(File bilddatei)
    {
<<<<<<< HEAD
        try {
            BufferedImage bild = ImageIO.read(bilddatei);
            if(bild == null || (bild.getWidth(null) < 0)) {
                // Bild konnte nicht geladen werden - vermutlich falsches Format
                return null;
            }
            LOGGER.addHandler(CH);
            int w = bild.getWidth();
            int h = bild.getHeight();
            int ins = w * h;
            int gray = 0;
            for(int x=0; x < w; x++){
            	for(int y=0; y < h; y++){
            		int rgb = bild.getRGB(x,y);
            		
            		Color c = new Color(rgb);
            		gray += (c.getRed() + c.getBlue() + c.getGreen()) / 3;
            	}
            }
            int mGray = gray / ins;
            LOGGER.info("Bild geladen..." + "\n" + "GroeÃŸe : " + w + " x " + h +"px" + "\n" + "mittlerer Grauwert: " + mGray);
            return new Farbbild(bild);
        }
        catch(IOException exc) {
            return null;
        }
=======
    	if (bilddatei != null) {
    		try {
    			BufferedImage bild = ImageIO.read(bilddatei);
    			if(bild == null || (bild.getWidth(null) < 0)) {
    				// Bild konnte nicht geladen werden - vermutlich falsches Format
    				return null;
    			}
    			return new Farbbild(bild);
    		}
    		catch(IOException exc) {
    			return null;
    		}
    	} else {
    		return null;
    	}
>>>>>>> e2004955aa603b2b636084d203fb83c7a8bd00eb
    }

    /**
     * Schreibe das gegebene Bild in eine Bilddatei im JPG-Format.
     * Bei etwaigen Problemen beendet sich diese Methode stillschweigend.
     * 
     * @param bild  Das zu speichernde Bild.
     * @param datei Die Datei, in die gespeichert werden soll.
     */
    public static void speichereBild(Farbbild bild, File datei)
    {
        try {
            ImageIO.write(bild, BILDFORMAT, datei);
        }
        catch(IOException exc) {
            return;
        }
    }
}
