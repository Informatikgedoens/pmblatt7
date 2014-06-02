import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

/**
 * Bildflaeche ist eine Swing-Komponente, die ein Farbbild anzeigen
 * kann. Sie ist konstruiert als eine Subklasse von JComponent und  
 * bietet die zusaetzliche Funktionalitaet, dass ein eingetragenes 
 * Farbbild an der Oberflaeche dieser Komponente angezeigt wird. 
 * 
 * @author  Michael Koelling und David J. Barnes
 * @version 1.0
 */
public class Bildflaeche extends JComponent
{
    private static final long serialVersionUID = 20060330L;

    // Die aktuelle Breite und Hoehe dieser Bildflaeche
    private int breite, hoehe;

    // Ein interner Bildpuffer, der zum Zeichnen verwendet wird.
    // Wenn die Flaeche tatsaechlich angezeigt werden soll, wird dieser
    // Puffer auf den Bildschirm kopiert.
    private Farbbild bild;

    /**
     * Erzeuge eine neue, leere Bildflaeche.
     */
    public Bildflaeche()
    {
        breite = 360;    // beliebig gewaehlte Groessee 
        hoehe = 240;
        bild = null;
    }

    /**
     * Setze das Bild, das diese Bildflaeche anzeigen soll.
     * 
     * @param bild das anzuzeigende Bild.
     */
    public void setzeBild(Farbbild bild)
    {
        if(bild != null) {
            breite = bild.getWidth();
            hoehe = bild.getHeight();
            this.bild = bild;
            repaint();
        }
    }
    
    /**
     * Loesche die Bildflaeche.
     */
    public void loeschen()
    {
        Graphics bildGraphics = bild.getGraphics();
        bildGraphics.setColor(Color.LIGHT_GRAY);
        bildGraphics.fillRect(0, 0, breite, hoehe);
        repaint();
    }
    
    // Die folgenden Methoden redefinieren Methoden, die aus
    // Superklassen geerbt wurden.
    
    /**
     * Teile dem Layout-Manager mit, wie gross diese Komponente sein soll.
     * (Diese Methode wird vom Layout-Manager aufgerufen, um
     * die Komponenten geeignet platzieren zu koennen.)
     * 
     * @return Die bevorzugte Groesse (als Dimension) dieser Komponente.
     */
    public Dimension getPreferredSize()
    {
        return new Dimension(breite, hoehe);
    }
    
    /**
     * Diese Komponente soll erneut angezeigt werden. Kopiere das
     * intern gehaltene Bild auf den Bildschirm.
     * (Diese Methode wird von Swing aus jedesmal aufgerufen, wenn
     * diese Komponente angezeigt werden soll.)
     * 
     * @param g Der Grafik-Kontext, mit dem auf dieser Komponente
     * gezeichnet werden kann.
     */
    public void paintComponent(Graphics g)
    {
        Dimension size = getSize();
        g.clearRect(0, 0, size.width, size.height);
        if(bild != null) {
            g.drawImage(bild, 0, 0, null);
        }
    }
}
