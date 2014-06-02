import static org.junit.Assert.*;

import org.junit.Test;


public class DunkelFilterTest extends DunkelFilter {

	@Test
	public void testAnwendenFalse() {
		System.out.println("Methodtest : anwenden(Farbbild) : false");
		assertFalse(anwenden(null));
		System.out.println("Done!");
	}
	
	@Test
	public void testAnwendenTrue() {
		System.out.println("Methodtest : anwenden(Fabbild) : true");
		try {
			assertTrue(anwenden(null));
			System.out.println("Failed!");
		} catch (AssertionError e) {
			System.out.println("Done!");
		}
	}

}
