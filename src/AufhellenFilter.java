
import java.awt.image.BufferedImage;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import javax.swing.JFrame;


<<<<<<< HEAD
public class AufhellenFilter extends Farbbild implements IFilter {

	private Farbbild aktuellesBild;
	private static final Logger LOGGER = Logger.getLogger(AufhellenFilter.class.getName());
	private static final ConsoleHandler CH = new ConsoleHandler();

	public AufhellenFilter(Farbbild aktuellesBild) {
		super(aktuellesBild);
		this.aktuellesBild = aktuellesBild;
	}
	
	public void anwenden() {
		OwnFormatter f = new OwnFormatter();
		CH.setFormatter(f);
		LOGGER.addHandler(CH);
		LOGGER.info("AufhellenFilter angewendet!");
		int hoehe = getHeight();
        int breite = getWidth();
		
       
			// uf alle Bildpunkte die Operation "brighter" der
	        // Klasse Color anwenden.
	        for(int y = 0; y < hoehe; y++) {
	            for(int x = 0; x < breite; x++) {
	                aktuellesBild.setzePunktfarbe(x, y, gibPunktfarbe(x, y).brighter());
	            }
=======
public class AufhellenFilter implements IFilter {
	
	public boolean anwenden(Farbbild aktuellesBild) {
		if (aktuellesBild != null) {
			int hoehe = aktuellesBild.getHeight();
			int breite = aktuellesBild.getWidth();
			for(int y = 0; y < hoehe; y++) {
				for(int x = 0; x < breite; x++) {
					aktuellesBild.setzePunktfarbe(x, y, aktuellesBild.gibPunktfarbe(x, y).brighter());
				}
			}
			return true;
		} else {
			return false;
>>>>>>> e2004955aa603b2b636084d203fb83c7a8bd00eb
		}
	}
}
