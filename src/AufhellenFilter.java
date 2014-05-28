
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class AufhellenFilter extends Farbbild implements IFilter {

	private Farbbild aktuellesBild;

	public AufhellenFilter(Farbbild aktuellesBild) {
		super(aktuellesBild);
		this.aktuellesBild = aktuellesBild;
	}
	
	public void anwenden() {
		int hoehe = getHeight();
        int breite = getWidth();
		
       
			// uf alle Bildpunkte die Operation "brighter" der
	        // Klasse Color anwenden.
	        for(int y = 0; y < hoehe; y++) {
	            for(int x = 0; x < breite; x++) {
	                aktuellesBild.setzePunktfarbe(x, y, gibPunktfarbe(x, y).brighter());
	            }
		}
		
	}
}
