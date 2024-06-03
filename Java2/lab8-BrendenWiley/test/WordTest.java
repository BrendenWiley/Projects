import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class WordTest {

	@Test
	void testConstructor() {
		Word w = new Word("Hello", 0);
		
		try {
			w = new Word(null, 0);
			fail();
		}catch(NullPointerException e) {
			
		}
		
		try {
			w = new Word("This", -2);
			fail();
		}catch(IllegalArgumentException e) {
			
		}
		
	}
	
	@Test
	void testFields() {
		Word w = new Word("Spontaneous", 2);
		assertEquals(11, w.length());
		assertEquals(2, w.position());
		assertEquals("Spontaneous", w.toString());
		assertEquals("spontaneous", w.toLowerCase().toString());
		assertEquals(8, w.uniqueChars());
		
		w = new Word("HashMap", 3);
		assertEquals(7, w.length());
		assertEquals(3, w.position());
		assertEquals("HashMap", w.toString());
		assertEquals("hashmap", w.toLowerCase().toString());
		assertEquals(5, w.uniqueChars());
		
		
	}
	
	@Test
	void testHashable() {
		Map<Word, Integer> wordMap = new HashMap<>();
		Word w1 = new Word("First", 0);
		Word w2 = new Word("Second", 1);
		Word w3 = new Word("third", 2);
		
		wordMap.put(w1, 1);
		assertEquals(1,wordMap.size());
		
		wordMap.put(w2, 1);
		assertEquals(2, wordMap.size());
		
		wordMap.put(w3, 1);
		assertEquals(3, wordMap.size());
		
		wordMap.put(w1, 2);
		assertEquals(3, wordMap.size());
		assertEquals(2, wordMap.get(w1));
		assertEquals(1, wordMap.get(w2));
		assertEquals(1, wordMap.get(w3));
		
		Word w4 = new Word("First", 3);
		wordMap.put(w4, 10);
		assertEquals(3, wordMap.size());
		assertEquals(1, wordMap.get(w2));
		assertEquals(1, wordMap.get(w3));
		assertEquals(10, wordMap.get(w4));
		assertEquals(10, wordMap.get(w1));

		Word w5 = new Word("second", 2);
		wordMap.put(w5, 5);
		assertEquals(3, wordMap.size());
		assertEquals(1, wordMap.get(w3));
		assertEquals(10, wordMap.get(w1));
		assertEquals(10, wordMap.get(w4));
		assertEquals(5, wordMap.get(w2));
		
		
	}

}
