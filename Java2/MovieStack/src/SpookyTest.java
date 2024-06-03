import static org.junit.Assert.*;

import org.junit.Test;

public class SpookyTest {

	private static final int MAX_CAP = 1000000;

	@Test // 10 points
	public void emptyTest() {
		MovieStack ms = new MovieStack();
		try {
			ms.pop();
		}
		catch(Exception e) {
			fail("pop() from empty stack should return null.");
		}
	}

	@Test // 10 points
	public void stackTest() {
		MovieStack ms = new MovieStack();
		Movie A = new Movie("Annie Hall", "Comedy");
		Movie B = new Movie("Airplane!", "Comedy");
		Movie C = new Movie("Mission Possible", "Action");
		ms.push(A);
		ms.push(B);
		ms.push(C);
		assertEquals(C,(ms.pop()));
		assertEquals(B,(ms.pop()));
		assertEquals(A,(ms.pop()));
	}	

	@Test // 10 points
	public void horrorQueueTest() {
		MovieStack ms = new MovieStack();
		Movie A = new Movie("Texas Chainsaw Massacre", "Horror");
		Movie B = new Movie("Choose or Die", "Horror");
		Movie C = new Movie("No One Gets Out Alive", "Horror");
		ms.push(A);
		ms.push(B);
		ms.push(C);
		assertEquals(A,(ms.pop()));
		assertEquals(B,(ms.pop()));
		assertEquals(C,(ms.pop()));
	}

	@Test // 30 points
	public void mixedMoviesTest() {
		MovieStack ms = new MovieStack();
		Movie A = new Movie("Texas Chainsaw Massacre", "Horror");
		Movie B = new Movie("Choose or Die", "Horror");
		Movie C = new Movie("Annie Hall", "Comedy");
		Movie D = new Movie("Airplane!", "Comedy");
		Movie E = new Movie("No One Gets Out Alive", "Horror");
		Movie F = new Movie("Mission Possible", "Action");
		ms.push(A);
		ms.push(B);
		ms.push(C);
		ms.push(D);
		ms.push(E);
		ms.push(F);
		assertEquals(A,(ms.pop()));
		assertEquals(B,(ms.pop()));
		assertEquals(E,(ms.pop()));
		assertEquals(F,(ms.pop()));
		assertEquals(D,(ms.pop()));
		assertEquals(C,(ms.pop()));
	}

	@Test // 30 points
	public void dynamicInsertTest() {
		MovieStack ms = new MovieStack();
		Movie A = new Movie("Texas Chainsaw Massacre", "Horror");
		Movie B = new Movie("Choose or Die", "Horror");
		Movie C = new Movie("Annie Hall", "Comedy");
		Movie D = new Movie("Airplane!", "Comedy");
		Movie E = new Movie("No One Gets Out Alive", "Horror");
		Movie F = new Movie("Mission Possible", "Action");
		ms.push(A);
		ms.push(B);
		ms.push(C);
		ms.push(D);
		ms.push(E);
		ms.push(F);
		assertEquals(A,(ms.pop()));
		assertEquals(B,(ms.pop()));
		assertEquals(E,(ms.pop()));
		assertEquals(F,(ms.pop()));
		assertEquals(D,(ms.pop()));
		ms.push(A);
		ms.push(D);
		assertEquals(A,(ms.pop()));
		assertEquals(D,(ms.pop()));
		assertEquals(C,(ms.pop()));
	}

	@Test // 10 points
	public void capacityTest() {
		MovieStack ms = new MovieStack();
		try {
			for (int i=0; i<MAX_CAP; ++i) {
				Integer index = (Integer) i;
				ms.push(new Movie(index.toString(), index.toString()));
			}
		}
		catch(Exception e) {
			fail("Capacity test failed.");
		}
	}
}
