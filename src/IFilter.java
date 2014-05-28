import java.awt.image.BufferedImage;

public interface IFilter {
	
	BufferedImage aktuellesBild = null ;
	
	public void anwenden();
}