
import java.awt.Color;
import java.awt.image.BufferedImage;


public class SchwellwertFilter implements IFilter {
	
	public boolean anwenden(Farbbild aktuellesBild) {
		if (aktuellesBild != null) {
			int hoehe = aktuellesBild.getHeight();
			int breite = aktuellesBild.getWidth();
	        	for(int y = 0; y < hoehe; y++) {
	        		for(int x = 0; x < breite; x++) {
	        			Color farbe = aktuellesBild.gibPunktfarbe(x, y);
	        			int helligkeit = (farbe.getRed() + farbe.getBlue() + farbe.getGreen()) / 3;
	        			if(helligkeit <= 85) {
	        				aktuellesBild.setzePunktfarbe(x, y, Color.BLACK);
	        			}
	        			else if(helligkeit <= 170) {
	        				aktuellesBild.setzePunktfarbe(x, y, Color.GRAY);
	        			}
	        			else {
	        				aktuellesBild.setzePunktfarbe(x, y, Color.WHITE);
	        			}
	        		}
	        	}
	        return true;
		} else {
			return false;
		}
	}
}
