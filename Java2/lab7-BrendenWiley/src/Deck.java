import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Deck {
	
	private LinkedList<Card> cards;
	
	public Deck() {

		cards = new LinkedList<Card>();
		
		for(int i = 0; i < 4; ++ i) {
			
			for(int p = 0; p < 13; ++p) {
	
		Card c = new Card(Rank.values()[p], Suit.values()[i]);
		
		cards.add(c);
				
			}

		}
		
	}
	
	public int size() {
		
		return cards.size();
		
	}
	
	public String toString() {
		
		return cards.toString();
	
	}
	
	public Card draw() {
		
		if(cards.isEmpty()) {
			
			return null;
			
		}
		
		else {
			Card c = cards.get(0);
			
			cards.remove(0);
			
			return c;
			
		}
		
	}
	
	public LinkedList<Card> draw(int count) {
		
		 LinkedList<Card> deck = new LinkedList<Card>();
		
		if (count < 0 ) {

		 return deck;
		 
		}
		if(count > cards.size()) {
			
			count = cards.size();
			
		}
		
		for(int i = 0; i < count; ++i) {
			
			Card c = cards.get(i);
			
			deck.add(c);
			
		}
		
		cards.removeAll(deck);
		
		return deck;
		
	}
	
	public void shuffle() {
		
		Collections.shuffle(cards);
		
	}
	
	public LinkedList<Card> getCardsByRank(Rank rank) {
		
		cards = new LinkedList<Card>();

			for(int i = 0; i < 4; ++ i) {
	
		Card c = new Card(rank, Suit.values()[i]);
		
		cards.add(c);
		
		}	
			return cards;
	}
}
