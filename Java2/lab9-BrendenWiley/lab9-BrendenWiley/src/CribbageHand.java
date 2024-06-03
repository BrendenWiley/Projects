import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CribbageHand {
	
	public static final Map<Rank, Integer> CARD_VALUES = Map.ofEntries(

			Map.entry(Rank.ACE, 1),
			
			Map.entry(Rank.TWO, 2),
			
			Map.entry(Rank.THREE, 3),
			
			Map.entry(Rank.FOUR, 4),
			
			Map.entry(Rank.FIVE, 5),
			
			Map.entry(Rank.SIX, 6),
			
			Map.entry(Rank.SEVEN, 7),
			
			Map.entry(Rank.EIGHT, 8),
			
			Map.entry(Rank.NINE, 9),
			
			Map.entry(Rank.TEN, 10),
			
			Map.entry(Rank.JACK, 10),
			
			Map.entry(Rank.QUEEN, 10),
			
			Map.entry(Rank.KING, 10));
	
	public final List<Card> cards;

	public CribbageHand(Card c1, Card c2, Card c3, Card c4) {
		
		if(c1 == null|| c2 == null || c3 == null || c4 == null) {
			throw new NullPointerException();
		}
		
		cards = List.of(c1, c2, c3, c4);

	
	}
	
	public Set<Set<Card>> fifteens(Card starter) {
	
		List<Card> hand = new ArrayList<Card>();
		
		Set<Set<Card>> fifteen= new HashSet<>();
		
		hand.add(cards.get(0));
		
		hand.add(cards.get(1));
		
		hand.add(cards.get(2));
		
		hand.add(cards.get(3));
		
		hand.add(starter);
		
		Set<Set<Card>> sets = powerSet(hand);
		
		for(Set<Card> r : sets) {
			
			int count = 0;
			
			List<Card> set = new ArrayList<Card>();
			
			set.addAll(r);
			
			for(int i = 0; i < set.size(); ++i) {
				
			count = count + CARD_VALUES.get(set.get(i).getRank());
				
			}
			
			if(count == 15) {
				
				Set<Card> b = new HashSet<>();
				
				b.addAll(set);
				
				fifteen.add(b);
		
			}
			
		}

		return fifteen;
	}
	
	public static Set<Set<Card>> powerSet(List<Card> cards) {
		
		Set<Set<Card>> sets = new HashSet<Set<Card>>();
		
	    if (cards.isEmpty()) {
	    
	        sets.add(new HashSet<Card>());
	        
	        return sets;
	        
	    }
	
	    List<Card> list = new ArrayList<Card>(cards);
	    
	    Card head = list.get(0);
	    
	    List<Card> rest = new ArrayList<Card>(list.subList(1, list.size())); 
	    
	    for (Set<Card> set : powerSet(rest)) {
	    	
	        Set<Card> newSet = new HashSet<Card>();
	        
	        newSet.add(head);
	        
	        newSet.addAll(set);
	        
	        sets.add(newSet);
	        
	        sets.add(set);
	      
	    }    
	    
	    return sets;
}
}
