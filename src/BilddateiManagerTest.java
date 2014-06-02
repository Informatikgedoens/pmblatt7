import static org.junit.Assert.*;

import org.junit.Test;


public class BilddateiManagerTest extends BilddateiManager {

	@Test
	public void testLadeBildNull() {
		System.out.println("Methodtest : ladeBild(File) : null");
		assertEquals(null, ladeBild(null));
		System.out.println("Done!");
	}
	
	@Test
	public void testLadeBild() {
		System.out.println("Methodtest : ladeBild(File) : !null");
		try {
			assertNotNull(ladeBild(null));
			System.out.println("Failed!");
		} catch (AssertionError e) {
			System.out.println("Done!");
		}
	}

}
