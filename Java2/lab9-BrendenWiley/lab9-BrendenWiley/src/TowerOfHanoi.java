import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import java.util.Iterator;
import java.util.List;

public class TowerOfHanoi{
	
	private Map<Peg, Deque<Integer>> diskStacks = new HashMap<>();
	
	public TowerOfHanoi(int numDisks, Peg start) {
		
		if(numDisks <= 0) {
			throw new IllegalArgumentException();
		}
		
		if(start == null) {
			throw new NullPointerException();
		}
		
		Deque<Integer> num1 = new ArrayDeque<>();
		
		Deque<Integer> num2 = new ArrayDeque<>();

		Deque<Integer> num3 = new ArrayDeque<>();

		
		
		for(int i = 1; i <= numDisks; ++i) {
			num1.add(i);
		}

		if(start.equals(Peg.LEFT)) {
			
			this.diskStacks.put(Peg.LEFT, num1);
			
			this.diskStacks.put(Peg.RIGHT, num2);
			
			this.diskStacks.put(Peg.MIDDLE, num3);
			
		}
		else if (start.equals(Peg.MIDDLE)) {
			
			
			this.diskStacks.put(Peg.LEFT, num2);
			
			this.diskStacks.put(Peg.RIGHT, num3);
			
			this.diskStacks.put(Peg.MIDDLE, num1);	
			
		}
		
		else {
			
			this.diskStacks.put(Peg.LEFT, num2);
			
			this.diskStacks.put(Peg.RIGHT, num1);
			
			this.diskStacks.put(Peg.MIDDLE, num3);
			
			
		}
		
	}
	
	public Deque<Integer> getDiskStack(Peg peg){

		if(peg.equals(null)) {
			throw new NullPointerException();
		}
	
		Deque<Integer> copyDiskStack = new ArrayDeque<>();
		
		copyDiskStack.addAll(diskStacks.get(peg));
		
		return copyDiskStack;
		
		
	}
	
	public void moveDisk(Move move) {
		
		if(move.equals(null)) {
			throw new NullPointerException();
		}
		
		if(diskStacks.get(move.from).peek() == null) {
			throw new IllegalArgumentException();
		}
		
		if(diskStacks.get(move.to).peek() != null) {
			
		
		if(diskStacks.get(move.from).peek().compareTo(diskStacks.get(move.to).peek()) > 0 || diskStacks.get(move.to).peek() == null) {
			throw new IllegalArgumentException();
		}
		
		}
		
		diskStacks.get(move.to).push(diskStacks.get(move.from).pop());
		
		
	}
	
	public static List<Move> solve(int numDisks, Peg start, Peg end) {
		
		List<Move> move = new ArrayList<Move>();
		
		if(start == null || end == null) {
			throw new NullPointerException();
		}
		
		if(numDisks < 0) {
			throw new IllegalArgumentException();			
		}
		
		if(start.equals(end)) {
			return move;
		}
		
		if(numDisks == 0) {
			return move;
		}
		
		
		Peg p =	Peg.other(start, end);
		
		
		if(numDisks == 3) {
			
			move.add(Move.move(start, end));

			
			
				
				move.add(Move.move(start, p));
				move.add(Move.move(end, p));

				
			
			
			move.add(Move.move(start, end));
			
		
				
				move.add(Move.move(p, start));
				move.add(Move.move(p, end));

				
			
			move.add(Move.move(start, end));
			return move;
		}
		
		
		else for(int i = numDisks; i > 1; --i) {
			
			move.add(Move.move(start, p));
			
			
		}
		
		move.add(Move.move(start, end));
		
		for(int i = numDisks; i > 1; --i) {
			
			move.add(Move.move(p, end));
			
			
		}
		
		
		return move;
	}
	
	public String toString() {
		
		Deque<Integer> left = new ArrayDeque<>();
	
		Iterator<Integer> l = diskStacks.get(Peg.LEFT).descendingIterator();
		
		while(l.hasNext()) {
		    
			left.add(l.next());
			
		}
		
		Deque<Integer> right = new ArrayDeque<>();
		
		Iterator<Integer> r = diskStacks.get(Peg.RIGHT).descendingIterator();
		
		while(r.hasNext()) {
		    
			right.add(r.next());
			
		}
		
		Deque<Integer> middle = new ArrayDeque<>();
		
		Iterator<Integer> m = diskStacks.get(Peg.MIDDLE).descendingIterator();
		
		while(m.hasNext()) {
		    
			middle.add(m.next());
			
		}

		String s = "  LEFT: " + left + System.lineSeparator() + "MIDDLE: " + middle + System.lineSeparator() + 
				" RIGHT: " + right;
		
		return s;
		
	}

}

