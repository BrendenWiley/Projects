import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class SortTest {

	@Test
	void testCompareToAndComparator() {

		Shape square1 = new Square(1);
		Shape square2 = new Square(1);

		Shape rectangle1 = new Rectangle(2, 2);
		Shape rectangle2 = new Rectangle(1, 3);

		Shape[] shapes = { square2, square1, 
				rectangle1, rectangle2};
		Arrays.sort(shapes);
		assertArrayEquals(new Shape[] {rectangle2, rectangle1, 
				square2, square1}, shapes);

		ShapeIDComparator comparator = new ShapeIDComparator();
		Arrays.sort(shapes, comparator);
		assertArrayEquals(new Shape[] { square1, square2,
				 rectangle1, rectangle2}, shapes);
	}
}
