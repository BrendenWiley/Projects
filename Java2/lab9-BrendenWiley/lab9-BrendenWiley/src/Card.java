import java.util.Objects;

public class Card implements Comparable<Card>{
	
	private Rank rank;
	
	private Suit suit;
	
	public Card(Rank rank, Suit suit) {
		
		if(rank == null || suit == null) {
			throw new NullPointerException();
		}
		
		this.rank = rank;
		this.suit = suit;
		
	}
	
	public int compareTo(Card card) {

		if (getSuit() != card.getSuit()) {
			
			return getSuit().compareTo(card.getSuit());

		}
		else if(getRank() != card.getRank()) {
			
			return getRank().compareTo(card.getRank());
			
		}
		
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof Card)) {
			return false;
		}
		
		Card c = Card.class.cast(obj);
		
		if(c.getRank() == getRank() && c.getSuit() == getSuit()) {
			return true;
		}
		
		else return false;
	}
	
	public Rank getRank() {
		
		return rank;
		
	}
	
	public Suit getSuit() {
		
		return suit;
		
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(getRank(), getSuit());
		
	}
	
	public String toString() {
		
		Rank r = getRank();
		
		Suit s = getSuit();
		
		return r.toString() + s.toString();
		
	}

}
