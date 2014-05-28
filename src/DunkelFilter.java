
import java.awt.image.BufferedImage;


public class DunkelFilter extends Farbbild implements IFilter {
	
	public DunkelFilter(Farbbild aktuellesBild) {
		super(aktuellesBild);
		this.aktuellesBild = aktuellesBild;
	}

	private Farbbild aktuellesBild;
	
	public void anwenden() {
		int hoehe = getHeight();
        int breite = getWidth();
        
        // Auf alle Bildpunkte die Operation "darker" der
        // Klasse Color anwenden.
        for(int y = 0; y < hoehe; y++) {
            for(int x = 0; x < breite; x++) {
                aktuellesBild.setzePunktfarbe(x, y, gibPunktfarbe(x, y).darker());
            }
        }

	}

}
