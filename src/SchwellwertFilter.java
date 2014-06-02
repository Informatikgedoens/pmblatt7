
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;


public class SchwellwertFilter implements IFilter {
	
<<<<<<< HEAD
	private Farbbild aktuellesBild;
	private static final Logger LOGGER = Logger.getLogger(SchwellwertFilter.class.getName());
	private static final ConsoleHandler CH = new ConsoleHandler();
		
	public SchwellwertFilter(Farbbild aktuellesBild) {
		super(aktuellesBild);
		this.aktuellesBild = aktuellesBild;
	}

	public void anwenden() {
		OwnFormatter f = new OwnFormatter();
		CH.setFormatter(f);
		LOGGER.addHandler(CH);
		LOGGER.info("SchwellwertFilter angewendet!");
		 
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

=======
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
>>>>>>> e2004955aa603b2b636084d203fb83c7a8bd00eb
	}
}
