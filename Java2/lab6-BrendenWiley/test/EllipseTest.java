import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class EllipseTest {

	// Define the margin of error for double comparisons.
	public static final double EPSILON = 0.001;
	
	@Test
	void testInhertiance() {
		Ellipse ellipse = new Ellipse(5.0, 4.0);
		assertTrue(ellipse instanceof Shape);

	}
	
	@Test
	void testOverrides() {
		try {
			Ellipse.class.getDeclaredMethod("getID");
			fail("Do not override getID in Ellipse.");
		} catch (NoSuchMethodException e) {}

		try {
			Ellipse.class.getDeclaredMethod("compareTo", Shape.class);
			fail("Do not override compareTo in Ellipse.");
		} catch (NoSuchMethodException e) {}

		try {
			Ellipse.class.getDeclaredMethod("toString");
			fail("Do not override toString in Ellipse.");
		} catch (NoSuchMethodException e) {}
	}

	@Test
	void testConstructor() {
		Ellipse ellipse = new Ellipse(5.0, 4.0);
		assertEquals(5.0, ellipse.getA(), EPSILON);
		assertEquals(4.0, ellipse.getB(), EPSILON);

		ellipse = new Ellipse(4.2, 2.5);
		assertEquals(4.2, ellipse.getA(), EPSILON);
		assertEquals(2.5, ellipse.getB(), EPSILON);
		

		try {
			new Ellipse(0,3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Nonpositive value(s) provided for the constructor", e.getMessage());
		}

		try {
			new Ellipse(2,-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Nonpositive value(s) provided for the constructor", e.getMessage());
		}
		
		try {
			new Ellipse(3.0,4.0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Semi-major axis length cannot be less than semi-minor axis length", e.getMessage());
		}
	}

	@Test
	void testPerimeterAndArea() {
		Shape ellipse = new Ellipse(6.2, 5.0);
		// Approximation method used for calculating ellipse's perimeter provides an approx.
		// answer within 5% of the true answer. Allow for 6% in the JUnit test
		assertEquals(35.38722094782547, ellipse.getPerimeter(), 0.06 * 35.38722094782547);
		assertEquals(97.38937226128358, ellipse.getArea(), EPSILON);

		ellipse = new Ellipse(3.0, 2.0);
		assertEquals(16.01904224441409, ellipse.getPerimeter(), 0.06 * 16.01904224441409 );
		assertEquals(18.84955592153876, ellipse.getArea(), EPSILON);
	}
}
