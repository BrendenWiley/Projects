import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackHand {
	
	private static Map<Rank, Integer> CARD_VALUES = initializeClassVariable();
	
	
	private static final Map<Rank, Integer> initializeClassVariable() {
		
	CARD_VALUES = new HashMap<Rank, Integer>();
		
		for(int i = 0; i <= 8; ++ i) {
			
			CARD_VALUES.put(Rank.values()[i], i + 2);
			
		}
		
		for(int i = 9; i <= 11; ++ i) {
			
			CARD_VALUES.put(Rank.values()[i], 10);
			
		}
		
		CARD_VALUES.put(Rank.values()[12], 11);

		
		return CARD_VALUES;
		
	}

	private static final int MAX_VALUE = 21;
	
	private List<Card> cards = new ArrayList<Card>();
	
	private int value = 0;
	
	private int numAcesAs11 = 0;
	
	
	
	public BlackjackHand(Card c1, Card c2) {
		
		cards.add(c1);
		
		cards.add(c2);

		int r = CARD_VALUES.get(c1.getRank());
		
		int p = CARD_VALUES.get(c2.getRank()); 
		
		if(c1.getRank() == Rank.ACE) {
			numAcesAs11++;
		}
		
		if(c2.getRank() == Rank.ACE) {
			numAcesAs11++;
		}
		value = r + p;
	}

	public void addCard(Card card) {
		
		if(card == null) {
			throw new NullPointerException();
		}
		if(value < MAX_VALUE) {
			cards.add(card);
			if(card.getRank() == Rank.ACE) {
				++numAcesAs11;
			}
			
			int r = CARD_VALUES.get(card.getRank());

			value += r;
		}
		
		
	}
	
	public int size() {
			
		return cards.size();
		
	}
	
	public static Map<Rank, Integer> getCardValues() {
		
		Map<Rank, Integer> copy = new HashMap<Rank, Integer>();		
		
		
		copy.putAll(CARD_VALUES);
		
		return copy;
		
	}
	
	public List<Card> getCards() {
		
		
		List<Card> copy = new ArrayList<Card>();
		
		copy.addAll(cards);
		
		return copy;
		
	}
	
	public int getValue() {
		
		while(value > MAX_VALUE && numAcesAs11 > 0) {
			
			value -= 10;
			--numAcesAs11;
			
		}
		
		return value;
	
	}
	
	public String toString() {
		
		return cards.toString();
		
	}
	
}
