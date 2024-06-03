import java.util.Random;

public class RandomPlayer extends Player{
	
	private Random generator;
	
	public RandomPlayer(String name) {
		
		super(name);
		
	}
	
	public int[] getMove(int[] pileSizes) {

		Random generator = new Random();
		int index = generator.nextInt(pileSizes.length);
		while(pileSizes[index] == 0) {
			index = generator.nextInt(pileSizes.length);
		}
		
		int[] move = {index , generator.nextInt(pileSizes[index]) + 1};
		
		return move;

			}
			
	int randomInRange(int min, int max) {
		
	    return generator.nextInt((max - min) + 1) + min;
	    
	}	
	
	}