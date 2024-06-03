import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
/**
 * Blocks class for defining, reading and printing blockchains from files.
 * @author Brenden Wiley
 *
 */
public class Blocks {

	/**
	 * These instance variables hold the information required to define a "block"
	 * Number holds the block number
	 * Miner holds the mining address
	 * Blocks holds all of the blocks and groups them together "hence blockchain"
	 */
	private int number;
	private String miner;
	private static ArrayList<Blocks> blocks;


	/**
	 * General constructors that doesn't initialize any variables.
	 */

	public Blocks() {

	}

	/**
	 * General constructors for initializing number
	 * @param number initialize number
	 */
	public Blocks(int number) {

		this.number = number;

	}

	/**
	 * General constructors for initializing number and miner
	 * @param number initialize number
	 * @param miner initialize miner
	 */
	public Blocks(int number, String miner) {

		this.number = number;
		this.miner = miner;

	}


	/**
	 * General "getter" for returning instance variable
	 * @return number 
	 */

	public int getNumber() {

		return number;

	}

	/**
	 * General "getter" for returning instance variable
	 * @return miner
	 */

	public String getMiner() {

		return miner;

	}

	/**
	 * Getter for returning a copy of the blocks arraylist
	 * @return arraylist of block objects
	 */
	public static ArrayList<Blocks> getBlocks() {

		ArrayList<Blocks> copy = new ArrayList<>(blocks.size());


		for(int i = 0; i < blocks.size(); ++i ) {

			copy.add(blocks.get(i));

		}

		return copy;

	}

	/**
	 * Prints the output of unique miners in the dataset
	 * Also returns the mining address and frequency of each miner in 
	 * order as they appear in the file.
	 */
	public static void calUniqMiners() {

		ArrayList<String> miners = new ArrayList<>();

		for(int i = 0; i < blocks.size(); ++i) {

			miners.add(i, blocks.get(i).getMiner());

		}

		HashSet<String> hset = new HashSet<String>(miners);

		System.out.println("Number of unique Miners: " + hset.size());

		System.out.print("\n");

		System.out.println("Each unique Miner and its frequency:");	

		for(int i = 0; i < 20; ++ i) {
			int minerFreq = Collections.frequency(miners, miners.get(0));

			ArrayList<String> temp = new ArrayList<>(); 

			temp.add(miners.get(0));

			System.out.println("Miner Address: " + miners.get(0));

			System.out.println("Miner Frequency: " + minerFreq);

			System.out.print("\n");

			miners.removeAll(temp);

		}

	}

	/**
	 * This method will return the difference between a's and b's block number
	 * @param a the first block object
	 * @param b the second block object
	 * @return the difference between a's and b's block number
	 */
	public static int blockDiff(Blocks a, Blocks b) {

		int blockNum1 = a.getNumber();

		int blockNum2 = b.getNumber();

		int sum = blockNum1 - blockNum2;

		return sum;

	}

	/**
	 * Will return the block object that correspondes to the given block number.
	 * @param num block number
	 * @return block object
	 */
	public static Blocks getBlockByNumber(int num) {

		for(int i = 0; i < blocks.size(); ++ i) {

			if(blocks.get(i).getNumber() == num) {


				int index = blocks.indexOf(blocks.get(i));


				return blocks.get(index);

			}

		}

		return null;

	}

	/**
	 * Returns a block's information in string form.
	 */
	public String toString() {

		if(number == 0) {

			return "Empty Block";

		}
		else if(miner == null) {

			return "Block Number: " + number;

		}

		else return "Block Number: " + number + " Miner Address: " + miner;

	}

	/**
	 * Reads the given file and assigns values to the blocks arraylist.
	 * @param filename name of file
	 * @throws FileNotFoundException throws exception
	 */
	public static void readFile(String filename) throws FileNotFoundException  {

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		blocks = new ArrayList<Blocks>();
		String[] info;
		String DELIMITER = ",";

		try {

			line = reader.readLine();

			while(line != null) {

				info = line.split(DELIMITER);

				Blocks c = new Blocks((Integer.parseInt(info[0])), info[9]);

				blocks.add(c);

				line = reader.readLine();

			}
			reader.close();
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}