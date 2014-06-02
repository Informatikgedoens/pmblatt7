
import java.awt.image.BufferedImage;


public class DunkelFilter implements IFilter {
	
	public boolean anwenden(Farbbild aktuellesBild) {
		if (aktuellesBild != null) {
			int hoehe = aktuellesBild.getHeight();
			int breite = aktuellesBild.getWidth();
			for(int y = 0; y < hoehe; y++) {
				for(int x = 0; x < breite; x++) {
					aktuellesBild.setzePunktfarbe(x, y, aktuellesBild.gibPunktfarbe(x, y).darker());
				}
			}
			return true;
		} else {
			return false;
		}
	}

}
