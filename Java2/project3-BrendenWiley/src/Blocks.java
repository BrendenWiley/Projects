import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.TreeSet;
import java.text.SimpleDateFormat;
/**
 * Blocks class for defining, reading and printing a set of transactions (blockchains) from a file.
 * @author Brenden Wiley
 */

public class Blocks implements Comparable<Blocks> {
	/**
	 * These instance variables hold the information required to define a "block"
	 * Number holds the block number
	 * Miner holds the mining address
	 * Timestamp holds the number seconds in UNIX
	 * transactionCount holds the transaction count.
	 * Blocks holds all of the blocks and groups them together "hence blockchain"
	 * transactions holds all of the transactions together
	 */
	private int number;				// Block number
	private String miner;			// Miner address
	private long timestamp; 		// Unix timestamp
	private int transactionCount;	// Transaction count
	private static ArrayList<Blocks> blocks = null;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private StringBuilder returnString = new StringBuilder();
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMMM yyyy HH:mm:ss z");
	private Date date;				// date in the format of "dateFormat
	
	/**
	 * General constructors that doesn't initialize any variables.
	 */
	public Blocks() {
		returnString.append("Empty Block");
	}
	/**
	 * General constructors for initializing number, miner, timestamp, and transactions
	 * @param number initialize number
	 */
	public Blocks(int number) {
		this.number = number;
		returnString.append("Block Number: " + number);
	}
	/**
	 * General constructors for initializing number, miner, timestamp, and transactions
	 * @param number initialize number
	 * @param miner initialize miner
	 */
	public Blocks(int number, String miner) {
		this.number = number;
		this.miner = miner;
		returnString.append("Block Number: " + number + " Miner Address: " + miner);
	}
	/**
	 * General constructors for initializing number, miner, timestamp, transactionCount, and transaction arrayList
	 * @param number initialize number
	 * @param miner initialize miner
	 * @param timestamp initialize timestamp
	 * @param transactionCount initialize transactionCount
	 */
	public Blocks(int number, String miner, long timestamp, int transactionCount) throws IOException {
		this.number = number;
		this.miner = miner;
		this.timestamp = timestamp;
		this.transactionCount = transactionCount;
		returnString.append("Block Number: " + number + " Miner Address: " + miner);

		readTransactions("ethereumtransactions1.csv");

	}
	/**
	 * General "getter" for returning instance variable
	 * @return number 
	 */
	public int getNumber() {
		return this.number;
	}
	/**
	 * General "getter" for returning instance variable
	 * @return miner
	 */
	public String getMiner() {
		return this.miner;
	}
	/**
	 * General "getter" for returning instance variable
	 * @return transactionCount
	 */
	public int getTransactionCount() {

		return this.transactionCount;

	}

	/**
	 * General "getter" for returning a copy of all transactions
	 * @return transactions (copy)
	 */
	public ArrayList<Transaction> getTransactions() {

		ArrayList<Transaction> copy = new ArrayList<>();

		copy.addAll(transactions);

		return copy;

	}
	/**
	 * General "getter" for returning instance variable
	 * @return blocks
	 */
	public static ArrayList<Blocks> getBlocks() {
		return new ArrayList<>(blocks);
	}

	/**
	 * Prints the output of unique miners in the dataset
	 * Also returns the mining address and frequency of each miner in 
	 * order as they appear in the file.
	 */
	
	// given an ArrayList of Blocks, find each unique miner address and
	// the frequency of times it appears and print according to output
	public static void calUniqMiners() throws FileNotFoundException, IOException {	
		// if blocks ArrayList has not been read, do so now
		if (blocks == null)
		{
			readFile("ethereumP1data.txt");
		}

		// initialize ArrayLists to store addresses and frequencies
		ArrayList<String> uniqMiners = new ArrayList<String>();
		ArrayList<Integer> uniqMinersFreq = new ArrayList<Integer>();
		// holds each miner address
		String miner;
		// loop through all Blocks
		for (int i = 0; i < blocks.size(); ++i)
		{
			miner = blocks.get(i).getMiner();
			// enter if the miner is new
			if (!(uniqMiners.contains(miner)))
			{
				// add the miner and add the frequency of 1
				uniqMiners.add(miner);
				uniqMinersFreq.add(1);
			}
			// otherwise increment the frequency of that miner
			else
			{
				for (int j = 0; j < uniqMiners.size(); ++j)
				{
					if (uniqMiners.get(j).equals(miner))
					{
						uniqMinersFreq.set(j, uniqMinersFreq.get(j) + 1);
					}
				}
			}
		}

		// print according to output
		System.out.println("Number of unique Miners: " + uniqMiners.size() + "\n");
		System.out.println("Each unique Miner and its frequency:");
		for (int i = 0; i < uniqMiners.size(); ++i)
		{
			System.out.println("Miner Address: " + uniqMiners.get(i) + "\nMiner Frequency: " + uniqMinersFreq.get(i) + "\n");
		}
	}
	/**
	 * This method will return the difference between a's and b's block number
	 * @param a the first block object
	 * @param b the second block object
	 * @return the difference between a's and b's block number
	 */
	// calculate the difference in the block numbers of two blocks
	public static int blockDiff(Blocks minuend, Blocks subtrahend) {
		int diff = minuend.getNumber() - subtrahend.getNumber();

		return diff;
	}
	/**
	 * Will return the block object that correspondes to the given block number.
	 * @param num block number
	 * @return block object
	 */
	// given the Block number retrieve the Blocks object that corresponds to that number from blocks ArrayList and return it
	public static Blocks getBlockByNumber(int num) throws FileNotFoundException, IOException {

		if(blocks == null) {
			Blocks.readFile("ethereumP1data.txt");
		}

		for(int i = 0; i < blocks.size(); ++i) {
			if (blocks.get(i).getNumber() == num) {
				return blocks.get(i);
			}
		}

		return null;
	}
	/**
	 * Returns a block's information in string form.
	 */
	public String toString() {
		return returnString.toString();
	}
	/**
	 * Reads the given file and assigns values to the blocks arraylist.
	 * @param filename name of file
	 * @throws FileNotFoundException throws exception
	 * @throws IOException throws exception
	 */
	// reads a file of given filename and fills an ArrayList of Blocks
	public static ArrayList<Blocks> readFile(String filename) throws FileNotFoundException, IOException {
		// construct a file object for the file with the given name.
		File file = new File(filename);

		// construct a scanner to read the file.
		Scanner fileScanner = new Scanner(file);

		// blocks ArrayList to store Blocks objects
		ArrayList<Blocks> b = new ArrayList<Blocks>();

		// create the Array that will store each lines data so we can grab the required fields
		String[] fileData = null;

		// Store each line of the file into the ArrayList.
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();

			// split each line along the commas
			fileData = line.trim().split(",");

			// fileData[0] corresponds to block number, fileData[9] to miner address
			// fileData[16] corresponds to unix timestamp, fileData[17] corresponds to transaction count
			b.add(new Blocks(Integer.parseInt(fileData[0]), fileData[9], Integer.parseInt(fileData[16]), Integer.parseInt(fileData[17])));
		}

		fileScanner.close();

		blocks = new ArrayList<>(b);

		return b;
	}
	
	/**
	 * Method that sorts the list of blocks in ascending order based on Block number.
	 */
	// sort the blocks ArrayList in ascending order based on block number
	public static void sortBlocksByNumber() throws FileNotFoundException, IOException {
		if (blocks==null) {
			readFile("ethereumP1.txt");
		}

		Collections.sort(blocks);
	}

	/**
	 * General compareTo method comparing two block numbers
	 * @return int comparing two block numbers
	 */
	@Override
	public int compareTo(Blocks b) {
		Integer x = number;
		Integer y = b.getNumber();
		return x.compareTo(y);
	}
/** 
 * @return string form of date
 */
	// print the date with the correct format
	public String getDate() {
		// initialize date in milliseconds
		date = new Date(timestamp * 1000);
		dateFormat.setTimeZone(TimeZone.getTimeZone("CST"));
		return dateFormat.format(date);
	}
	/**
	 * Finds the difference in time between two blocks in hours, minutes and seconds and outputs to console.
	 * @param first block one
	 * @param second block two
	 */
	// find the difference in time between two given Blocks in hours, minutes, and seconds
	public static void timeDiff(Blocks first, Blocks second) {
		//make sure given Blocks aren't null
		if ((first == null) || (second == null)) {
			System.out.println("A given Block is null.");
		}
		else {
			String hours = " hours, ";
			String minutes = " minutes, and ";
			String seconds = " seconds.";
			// use timestamps to find hours, minutes, seconds
			int diffInSeconds = (int) Math.abs(first.timestamp - second.timestamp);
			int diffInMinutes = diffInSeconds / 60;
			int diffInHours = diffInMinutes / 60;
			diffInSeconds = diffInSeconds % 60;
			diffInMinutes = diffInMinutes % 60;

			if (diffInHours == 1) {
				hours = " hour, ";
			}
			if (diffInMinutes == 1) {
				minutes = " minute, and ";
			}
			if (diffInSeconds == 1) {
				seconds = " second.";
			}


			System.out.println("The difference in time between Block " + first.getNumber() + " and Block " + second.getNumber() + " is "
					+ diffInHours + hours + diffInMinutes + minutes + diffInSeconds + seconds);
		}
	}

	/**
	 * Takes two blocks as input and calculates the number of total transactions between the two blocks.
	 * @param first block one
	 * @param second block two
	 * @return int num of transactions between the two blocks
	 */
	// return the number of transactions between two Blocks not inclusive
	// return -1 if the Blocks are null/not in the blocks ArrayList
	// or if second is before first in the ArrayList
	public static int transactionDiff(Blocks first, Blocks second) throws FileNotFoundException, IOException {

		// if blocks ArrayList has not been read, do so now and sort it
		if (blocks == null)
		{
			readFile("ethereumP1data.txt");
			sortBlocksByNumber();
		}

		// make sure given Blocks aren't null
		if ((first == null) || (second == null)) {
			return -1;
		}

		int indexA = -1;		// index of first in blocks ArrayList
		int indexB = -1;		// index of second in blocks ArrayList
		int count = 0;			// number of transactions between the two Blocks


		// for loop to find indexA and indexB
		for (int i = 0; i < blocks.size(); ++i) {
			if (first.getNumber() == blocks.get(i).getNumber()) {
				indexA = i;
			}
			if (second.getNumber() == blocks.get(i).getNumber()) {
				indexB = i;
			}
		}

		if ((indexA < 0) || (indexB < 0)) {
			return -1;
		}
		// make sure first comes before second
		if (indexA >= indexB) {
			return -1;
		}

		// for loop to count the transactions
		for (int i = indexA+1; i < indexB; ++i) {
			count += blocks.get(i).getTransactionCount();
		}

		return count;
	}

	/**
	 * Reads the given file and assigns values to the transactions arraylist
	 * @param filename name of file
	 * @throws FileNotFoundException throws exception
	 * @throws IOException throws exception
	 */
	private void readTransactions(String filename) throws IOException {

		File file = new File(filename);
		Scanner data = new Scanner(file);
		String[] info;
		TreeSet<Transaction> set = new TreeSet<Transaction>();
		transactions.clear();

		while(data.hasNextLine()) {

			info = data.nextLine().split(",");
			int number = Integer.parseInt(info[3]);
			int index = Integer.parseInt(info[4]);
			String fromAdr = info[5];
			String toAdr = info[6];
			int gasLimit = Integer.parseInt(info[8]);
			long gasPrice = Double.valueOf(info[9]).longValue();

			Transaction transaction = new Transaction(number, index, gasLimit, gasPrice, fromAdr, toAdr);

			if(this.getNumber() == transaction.getBlockNumber()) {

				set.add(transaction);

			}
		}
		data.close();
		transactions.addAll(set);
		set.clear();

	}
	/**
	 * Calculates the average cost of transactions in the transaction arraylist
	 *@return average cost of transactions
	 */
	public double avgTransactionCost() {

		ArrayList<Transaction> list = new ArrayList<Transaction>();
		list.addAll(this.getTransactions());

		double total = 0;

		for(int i = 0; i < list.size(); ++i) {

			Transaction t = list.get(i);

			total = total +  t.transactionCost();

		}

		return total / list.size();

	}
	/**
	 * Prints the output of unique transactions from address and its corressponding
	 * unique to addresses to console.
	 * Also calculates and prints the total cost of the given
	 * transaction in ether.
	 */
	public void uniqFromTo() {

		System.out.print("Each transaction by from address for Block " + this.getNumber() + ":");

		System.out.print("\n");

		System.out.print("\n");

		ArrayList<String> fromAdr = new ArrayList<String>();

		for(int i = 0; i < this.transactions.size(); ++i) {

			fromAdr.add(i, this.transactions.get(i).getFromAddress());

		}

		LinkedHashSet<String> hset = new LinkedHashSet<String>();

		hset.addAll(fromAdr);

		String arr[] = new String[hset.size()];
		arr = hset.toArray(arr);

		int counter = hset.size();

		for(int i = 0; i < counter; ++i) {

			LinkedHashSet<String> toAdr = new LinkedHashSet<String>();

			String from = arr[i];

			System.out.print("From " + from);

			System.out.print("\n");

			for(int q = 0; q < this.transactions.size(); ++q) {

				if(this.transactions.get(q).getFromAddress().equals(from)) {

					toAdr.add(this.transactions.get(q).getToAddress());

				}

			}

			String to[] = new String[toAdr.size()];
			to = toAdr.toArray(to);

			double cost = 0;

			for(int p = 0; p < to.length; ++p) {

				System.out.print(" -> " + to[p]);

				System.out.print("\n");

				for(int l = 0; l < this.transactions.size(); ++l) {

					if(this.transactions.get(l).getToAddress().equals(to[p]) && this.transactions.get(l).getFromAddress().equals(arr[i])) {

						cost = cost + this.transactions.get(l).transactionCost();
						break;
					}

				}

			}
			String formattedCost = String.format("%.8f", cost);

			System.out.print("Total cost of transactions: " + formattedCost + " ETH");

			System.out.print("\n");

			System.out.print("\n");

			fromAdr.clear();
			toAdr.clear();
		}
	}
}