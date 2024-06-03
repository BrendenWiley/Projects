import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

class DocumentTest {

	@Test
	void testConstructor() {
		try {
			Document doc = new Document(null);
			fail();
		}catch (NullPointerException e) {
			
		}
		
		Document doc = new Document("This is the first sentence.That is the second sentence.And this is the last");

	}

	@Test 
	void testFields() {
		Document doc = new Document("LinkedList and ArrayList are both implementations of the List interface.A HashSet is a Set implementation.");
		assertEquals(2, doc.length());
		assertEquals(16,doc.getNumWords());
		assertEquals("LinkedList and ArrayList are both implementations of the List interface.A HashSet is a Set implementation.", doc.toString());
		
		doc = new Document("A Set does not allow duplicate elements.However, a List normally allows duplicates.Multiple other structures are avaialable.");
		assertEquals(3, doc.length());
		assertEquals(18,doc.getNumWords());
		assertEquals("A Set does not allow duplicate elements.However, a List normally allows duplicates.Multiple other structures are avaialable.", doc.toString());
		
	}
	
	@Test
	void testGetSentence() {
		
		Document doc = new Document("Collections have lots of useful methods such as max.");
		try {
			doc.get(1);
			fail();
		}catch(IndexOutOfBoundsException e) {
		}
		
		assertEquals("Collections have lots of useful methods such as max", doc.get(0).toString());
		
		doc = new Document("Using Java Collections reduces programming effort.It provides a set of data structures and algorithms ready to be used.");
		
		try {
			doc.get(-1);
			fail();
		}catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			doc.get(2);
			fail();
		}catch (IndexOutOfBoundsException e) {
			
		}
		
		assertEquals("It", doc.get(1).get(0).toString());
	}
	
	@Test
	void testFrequency() {
		Document doc = new Document("HashMap uses finds an entry by calculating the hashcode of the key.This hashcode is computed using the hashcode method of the key class.HashMaps also compares keys based on the key class-provided equals() override if any.");
		Map<Word, Integer> wordFrequencies = doc.wordFrequency();
		
		assertEquals(28, wordFrequencies.size());
		assertEquals(5, wordFrequencies.get(new Word("the",0)));
		assertEquals(1, wordFrequencies.get(new Word("any", 0)));
		assertEquals(1, wordFrequencies.get(new Word("hashmap",0)));
		
		
		doc = new Document("A Map entrySet() is useful method provided by Java Collections.It returns a Set view of the mappings contained in this map.Each entry in the returned set is a key-value pair.");
		wordFrequencies = doc.wordFrequency();
		
		assertEquals(25, wordFrequencies.size());
		assertEquals(3, wordFrequencies.get(new Word("a",0)));
		assertEquals(2, wordFrequencies.get(new Word("map", 0)));
		assertEquals(1, wordFrequencies.get(new Word("key-value",0)));
		
		
	}
}
