public class Piles {

	private int[] sizes;

	public Piles(int... initSizes) throws IllegalArgumentException {


		if(initSizes == null) {
			throw new IllegalArgumentException();
		}
		if(initSizes.length == 0) {
			throw new IllegalArgumentException();
		}

		if(containsNegative(initSizes)) {
			throw new IllegalArgumentException();
		}

		sizes = new int[initSizes.length];

		for(int i = 0; i < initSizes.length; ++i) {


			sizes[i] = initSizes[i];

		}
	}

	public int[] getSizes() {	
		
		int[] copy = new int[sizes.length];

		for(int i = 0; i < copy.length; ++ i) {

			copy[i] = sizes[i];

		}
		return copy;

	}

	public void removeObjects(int[] move) throws IllegalMoveException{
		
		if(move == null) {
			throw new IllegalMoveException("null move");
		}
		if(move.length != 2) {
			throw new IllegalMoveException("Invalid length: " + move.length);
		}
		if(move[0] > sizes.length - 1 || move[0] < 0) {
			throw new IllegalMoveException("Index out of bounds: " + move[0]);
		}
		if(sizes[move[0]] == 0) {
			throw new IllegalMoveException("Pile " + move[0] + " is empty.");
		}
		if(move[1] <= 0) {
			throw new IllegalMoveException("Nonpositive object number: " + move[1]);
		}
		if(move[1] > sizes[move[0]]) {
			throw new IllegalMoveException("Object number greater than pile size: " 
		+ move[1] + " > " + sizes[move[0]]);
		}
			
			int temp = sizes[move[0]];
			
			temp = temp - move[1];
			
			sizes[move[0]] = temp;
			
	}
	
	public boolean isEmpty() {
		int counter = 0;
		for(int i = 0; i < sizes.length; ++ i) {
			
		if(sizes[i] == 0) {
			++counter;
		}
		}
		if(counter == sizes.length) {
			return true;
		}
		else return false;
	}

	public boolean containsNegative(int[] array) {

		for(var i = 0; i < array.length; i++)
		{
			if(array[i] <= 0){
				return true;
			}
		}
		return false;
	}
}
