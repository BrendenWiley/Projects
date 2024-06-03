import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TimeZone;

/**
 * Blocks class for defining, reading and printing a set of transactions (blockchains) from a file.
 * @author Brenden Wiley
 *
 */
public class Blocks implements Comparable<Blocks>{
	
	/**
	 * These instance variables hold the information required to define a "block"
	 * Number holds the block number
	 * Miner holds the mining address
	 * Timestamp holds the number seconds in UNIX
	 * Transactions holds the transaction count.
	 * Blocks holds all of the blocks and groups them together "hence blockchain"
	 */
	private int number;
	private String miner;
	private long timestamp;
	private int transactions;
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
	 * General constructors for initializing number, miner, timestamp, and transactions
	 * @param number initialize number
	 * @param miner initialize miner
	 * @param timestamp initialize timestamp
	 * @param transactions initialize transactions
	 */
	public Blocks(int number, String miner, long timestamp, int transactions ) {
		
		this.number = number;
		this.miner = miner;
		this.timestamp = timestamp;
		this.transactions = transactions;
		
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
	 * General "getter" for returning instance variable
	 * @return transaction
	 */
	public int getTransactions() {
			
			return this.transactions;
			
		}

	/**
	 * Method for returning the date of a block in central standard time format given UNIX time.
	 * @return String of date containing day, month, year, and time
	 */
	public String getDate() {
		
		SimpleDateFormat date = new SimpleDateFormat("EEE, dd MMMMM YYYY HH:mm:ss z");
		
		date.setTimeZone(TimeZone.getTimeZone("CST"));
		
		Date d = new Date(timestamp * 1000);
		
		return date.format(d);
			
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
	 * @throws IOException throws exception
	 */
	public static void readFile(String filename) throws FileNotFoundException, IOException  {
		
	

			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			blocks = new ArrayList<Blocks>();
			String[] info;
		String DELIMITER = ",";

			try {

				line = reader.readLine();

				while(line != null) {

					info = line.split(DELIMITER);

					Blocks c = new Blocks((Integer.parseInt(info[0])), info[9], 
							Long.parseLong(info[16]), Integer.parseInt(info[17]));

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

	/**
	 * Method that sorts the list of blocks in ascending order based on Block number.
	 */
	public static void sortBlocksByNumber() {
				
		Collections.sort(blocks);
		
	}
	
	/**
	 * Finds the difference in time between two blocks in hours, minutes and seconds and outputs to console.
	 * @param first block one
	 * @param second block two
	 */
	public static void timeDiff(Blocks first, Blocks second) {
		
		if(first == null || second == null) {
			System.out.println("A given Block is null.");
			return;
		}
		
		long totalSeconds = (Math.abs(first.timestamp - second.timestamp));

		String h;
		String m;
		String s;
	
		long hours = totalSeconds / 3600;
		long minutes = (totalSeconds / 60) % 60;
		long seconds = totalSeconds % 60;
		
		if(hours == 1 && minutes == 1 && seconds == 1) {
			
			h = " hour, ";
			m = " minute, ";
			s = " second, ";
			
		}
		if(hours == 1 && minutes == 1) {
			
			h = " hour, ";
			m = " minute, ";
			
			
		}
		if(hours == 1 && seconds == 1) {
			
			h = " hour, ";
			s = " second, ";
			
		}
		
		if(seconds == 1 && minutes == 1) {
			
			m = " minute, ";
			s = " second, ";
			
		}

		if(hours == 1) {
			h = " hour, ";
		}
		else {
			h = " hours, ";
		}
		
		if(minutes != 1) {
			m = " minutes, ";
		}
		else {
			m = " minute, ";
		}
		
		if(seconds == 1) {
			s = " second.";
		}
		else {
			s = " seconds.";
		}
	
		System.out.println("The difference in time between Block " + first.number + " and Block " + second.number + " is " + 
				hours + h + minutes + m + "and " + seconds + s);
		
	}
	
	/**
	 * Takes two blocks as input and calculates the number of total transactions between the two blocks.
	 * @param first block one
	 * @param second block two
	 * @return int num of transactions between the two blocks
	 */
	public static int transactionDiff(Blocks first, Blocks second) {
		
		sortBlocksByNumber();
		int sum = 0;
		if(second == null || first == null) {
			return -1;
		
		}
		
		if(blocks.indexOf(first) >= blocks.indexOf(second)) {
			return -1;
		}
		
		
		for(int i = (blocks.indexOf(first) + 1); i < blocks.indexOf(second); ++i) {
			
			sum += blocks.get(i).getTransactions();
			
		}

		return sum;
		
	}

	/**
	 * Override compareTo method in the comparable interface to be used in sortBlocksByNumber()
	 *@return int that determines which block number is greater between the two block objects being compared.
	 */
	@Override
	public int compareTo(Blocks o) {
	

		 if (this.getNumber() > o.getNumber()) {
		
	            return 1;
	        }
		 
	        else if (this.getNumber() < o.getNumber()) {
	 
	            return -1;
	            
	        }
		 
	        else {
	 
	          
	            return 0;
	
	}
	
}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

