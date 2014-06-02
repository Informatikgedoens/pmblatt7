
import java.awt.image.BufferedImage;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;


public class DunkelFilter extends Farbbild implements IFilter {
	
	private static final Logger LOGGER = Logger.getLogger(DunkelFilter.class.getName());
	private static final ConsoleHandler CH = new ConsoleHandler();
	
	public DunkelFilter(Farbbild aktuellesBild) {
		super(aktuellesBild);
		this.aktuellesBild = aktuellesBild;
	}

	private Farbbild aktuellesBild;
	
	public void anwenden() {
		OwnFormatter f = new OwnFormatter();
		CH.setFormatter(f);
		LOGGER.addHandler(CH);
		LOGGER.info("DunkelFilter angewendet!");
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
