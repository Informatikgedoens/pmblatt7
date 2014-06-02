import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.Dimension;


public class BildflaecheTest extends Bildflaeche {

	@Test
	public void testGetPreferredSize() {
		System.out.println("Methodtest : getPreferredSize() : Dimension");
		assertEquals(new Dimension(360, 240), getPreferredSize());
		System.out.println("Done!");
	}
	
	@Test
	public void testGetPreferredSizeFalse() {
		System.out.println("Methodtest : getPreferredSize() : False Dimension");
		try {
			assertEquals(new Dimension(800, 600), getPreferredSize());
			System.out.println("Failed!");
		} catch (AssertionError e) {
			System.out.println("Done!");
		}
	}

}
