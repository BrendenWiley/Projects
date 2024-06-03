import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Word{
	
	
	private String _word;
	private int _pos;
	
	public Word(String word, int pos) {
		
		if(word == null) {
			throw new NullPointerException();
		}
		if(pos < 0) {
			throw new IllegalArgumentException();
		}
		
		this._word = word;
		this._pos = pos;
		
		
	}
	
	public int length() {
		
		return _word.length();
		
	}
	
	public String toString() {
		
		return _word;
		
	}
	
	public Word toLowerCase() {
		
		Word word = new Word(_word.toLowerCase(), _pos);
		
		return word;
		
	}
	
	public int position() {
		
		return _pos;
		
	}
	 
	public int uniqueChars() {
		
		Set<Character> set = new HashSet<Character>();
		
		for(int i = 0; i < _word.length() - 1; ++ i) {
			char c = _word.charAt(i);
			set.add(c);
		}
		
		return set.size();
		
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj == null) {
			return false;
		}
		
		if(obj.getClass() != this.getClass()) {
			return false;
		}
		Word c = Word.class.cast(obj);

		return this.toString().toLowerCase().
		equals(c.toString().toLowerCase());

	}
	
	@Override
	public int hashCode() {
		
		String s = _word.toLowerCase();
		
		return s.hashCode();
		
	}
}
