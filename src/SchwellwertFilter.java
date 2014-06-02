
import java.awt.Color;
import java.awt.image.BufferedImage;


public class SchwellwertFilter extends Farbbild implements IFilter {
	
	private Farbbild aktuellesBild;
		
	public SchwellwertFilter(Farbbild aktuellesBild) {
		super(aktuellesBild);
		this.aktuellesBild = aktuellesBild;
	}

	public void anwenden() {
		 
		int hoehe = getHeight();
	    int breite = getWidth();
	        for(int y = 0; y < hoehe; y++) {
	            for(int x = 0; x < breite; x++) {
	                Color farbe = gibPunktfarbe(x, y);
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

	}
}
