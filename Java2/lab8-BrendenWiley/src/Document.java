import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document {

	private List<Sentence> _document = new ArrayList<Sentence>();
	private String _documentStr;
	private Map<Word, Integer> wordFreq;
	
	public Document(String document) {
		
		if(document == null) {
			throw new NullPointerException();
		}
		
		this._documentStr = document;
		
		String docu[] = document.split("[.]");
		
		for(int i = 0; i < docu.length; ++i) {
			
			Sentence c = new Sentence(docu[i], i);
			
			_document.add(c);
		}
	
	}
	
	public int length() {
		
		return _document.size();
		
	}
	
	public String toString() {
		
		return this._documentStr;
		
	}
	
	public int getNumWords() {
		
		int count = 0;
		
		for(int i = 0; i < _document.size(); ++i) {
			
			Sentence sent = _document.get(i);
			
			count =+ sent.length();
			
		}
		
		return count;
		
	}
	
	public Sentence get(int idx) {
		
		if(idx > length()) {
			throw new IndexOutOfBoundsException();
		}
		return _document.get(idx);
		
		
	}

	public Map<Word, Integer> wordFrequency() {
		
		Map<Word, Integer> wordFrequencies = new HashMap<>();
		
		for(int q = 0; q < _document.size(); ++q) {
			
			Sentence sent = _document.get(q);
		
		for(int i = 0; i < sent.length(); ++i) {
			
			int wordCount = 0;

			for(int j = 0; j < sent.length(); ++j) {
				
				if(sent.get(i).toLowerCase().equals(sent.get(j).toLowerCase())) {
					
					++wordCount;
				}
							
			}
			
			wordFrequencies.put(sent.get(i), wordCount);

			
		}
		
		}
		
		return wordFrequencies;
		
	}
}
