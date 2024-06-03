import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sentence {
	
	private List<Word> _sentence = new ArrayList<Word>();
	private int _pos;	
	private String _sentenceStr;
	private Map<Word, Integer> wordFreq;
	
	public Sentence(String sentence, int pos) {
		
		if(sentence == null) {
			throw new NullPointerException();
		}
		if(pos < 0) {
			throw new IllegalArgumentException();
		}
		this._pos = pos;
		this._sentenceStr = sentence;
		
		String word[] = sentence.split(" ");
		
		for(int i = 0; i < word.length; ++i) {
			
			Word c = new Word(word[i], i);
			
			_sentence.add(c);
		}
		
	}
	
	public int length() {
		
		return _sentence.size();
		
	}
	
	public String toString() {
		
		return _sentenceStr;
		
	}
	
	public int position() {
		
		return _pos;
	
	}
	
	public Word get(int idx) {
		
		if(idx < 0 || idx > _sentence.size()) {
			throw new IndexOutOfBoundsException();
		}
		
		return _sentence.get(idx);
		
		
		
	}
	
	public List<Integer> getWordIndices(Word w) {
		
		List<Integer> indices = new ArrayList<Integer>();
		
		if(w == null) {
			throw new NullPointerException();
		
		}
		
		for(int i = 0; i < _sentence.size(); ++i) {
			
			Word word = _sentence.get(i);
			
			if (word.toLowerCase().equals(w.toLowerCase())) {
				
				indices.add(i);
				
			}
			
		}
		
		return indices;
		
	}
	
	public Map<Word, Integer> wordFrequency() {
		
		Map<Word, Integer> wordFrequencies = new HashMap<>();
				
		for(int i = 0; i < _sentence.size(); ++i) {
			
			int wordCount = 0;

			for(int j = 0; j < _sentence.size(); ++j) {
				
				if(_sentence.get(i).equals(_sentence.get(j))) {
					
					++wordCount;
				}
							
			}
			
			wordFrequencies.put(_sentence.get(i), wordCount);

			
		}
		
		return wordFrequencies;
		
	}
	
	public Word mostFrequent() {

	    int maxcount = 0;
	    
	    Word freqWord = new Word(" ", 0);
	    
	    for (int i = 0; i < _sentence.size(); i++) {
	    	
	      int count = 0;
	      
	      for (int j = 0; j < _sentence.size(); j++) {
	    	  
	        if (_sentence.get(i).equals(_sentence.get(j))) {
	        	
	          count++;
	          
	        }
	        
	      }
	 
	      if (count > maxcount) {
	    	  
	        maxcount = count;
	        
	        freqWord = _sentence.get(i);
	        
	      }
	      
	    }
	 
	    return freqWord.toLowerCase();

	  }
	
	}
	
	
	

