import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver 
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{	
		// Empty constructor.
		// The toString method should return
		// "Empty Block" since no input is given.
		Blocks blockA = new Blocks();
		System.out.println(blockA);
		
		// Constructor with given Block number.
		// The toString method should return 
		// "Block Number: 11054890" 
		Blocks blockB = new Blocks(11054890);
		System.out.println(blockB);
		
		// Constructor with given Block number
		// and given miner address.
		// The toString method should return 
		// "Block Number: 11054890 Miner Address: 0xad7c84b9"
		Blocks blockC = new Blocks(11054890, "0xad7c84b9");
		System.out.println(blockC);
		System.out.println();
		
		// Read in data file to create an ArrayList of Blocks.
		// See section 2.2 in project pdf.
		Blocks.readFile("ethereumP1data.txt");
		
		// Print the number of unique miners as well as
		// print each unique miner and its frequency.
		// See section 2.3 in project pdf.
		Blocks.calUniqMiners();
		
		// Find the difference between two Blocks
		// given by their Block number.
		// See section 2.4 in project pdf.
		System.out.println("The difference between Block 15049407 and Block 15049308: " + Blocks.blockDiff(Blocks.getBlockByNumber(15049407), Blocks.getBlockByNumber(15049308)));
		System.out.println("The difference between Block 15049308 and Block 15049407: " + Blocks.blockDiff(Blocks.getBlockByNumber(15049308), Blocks.getBlockByNumber(15049407)));

	}
}
