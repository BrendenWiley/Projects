import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

class SentenceTest {

	@Test
	void testConstructor() {
		try {
			Sentence s1 = new Sentence(null, 0);
			fail();
		}catch(NullPointerException e) {
			
		}
		
		try {
			Sentence s1 = new Sentence("This should fail", -2);
			fail();
		}catch(IllegalArgumentException e) {
			
		}
		
		Sentence s2 = new Sentence("Java Collections provides implementations of different data structures and algorithms", 0);
	}
	
	@Test
	void testFields() {
		Sentence s1 = new Sentence("Java Collections provides implementations of different data structures and algorithms", 2);
		assertEquals("Java Collections provides implementations of different data structures and algorithms", s1.toString() );	
		assertEquals(2, s1.position());
		assertEquals(10, s1.length());
		
		
		Sentence s2 = new Sentence("A HashMap implements the Map interface", 3);
		assertEquals("A HashMap implements the Map interface", s2.toString());
		assertEquals(3, s2.position());
		assertEquals(6, s2.length());
		
	}

	@Test
	void testWordFrequency() {
		
		
		Sentence s1 = new Sentence("In the Collections class you can find numerous methods to facilitate working with collections in Java", 0);
		Map<Word, Integer> freq1 = s1.wordFrequency();
		assertEquals(2, freq1.get(s1.get(13)));
		assertEquals(1, freq1.get(s1.get(1)));
		
		Sentence s2 = new Sentence("One should choose the appropriate data structure for the specific application", 1);
		Map<Word, Integer> freq2 = s2.wordFrequency();
		assertEquals(2, freq2.get(s2.get(3)));
		assertEquals(2, freq2.get(s2.get(8)));
		assertEquals(1, freq2.get(s2.get(0).toLowerCase()));
	}
	
	
	@Test
	void testWordIndices() {
		
		Sentence s1 = new Sentence("The ArrayList class implements the List interface", 0);
		assertArrayEquals(new int[] {0, 4}, s1.getWordIndices(s1.get(0)).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] {5}, s1.getWordIndices(s1.get(5)).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] {}, s1.getWordIndices(new Word("Hello", 3)).stream().mapToInt(i->i).toArray());
		
		
		Sentence s2 = new Sentence("A set cannot store duplicate values", 0);
		assertArrayEquals(new int[] {0}, s2.getWordIndices(s2.get(0)).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] {1}, s2.getWordIndices(s2.get(1)).stream().mapToInt(i -> i).toArray());
		assertArrayEquals(new int[] {}, s2.getWordIndices(new Word("Interface", 2)).stream().mapToInt(i->i).toArray());

	}
	
	@Test
	void testMostFrequent() {
		
		Sentence s1 = new Sentence("Overriding equals() and hashcode() methods for a class that is used as a key in a HashMap is necessary to ensure correct key comparison", 4);
		Word mostFrequent = s1.mostFrequent();
		assertEquals("a", mostFrequent.toString());
		
		Sentence s2 = new Sentence("The HashMap entryset() method returns a set view of all entries in that hashmap", 4);
		Word mostFrequent2 = s2.mostFrequent();
		assertEquals("hashmap", mostFrequent2.toString());
		
	}
}
