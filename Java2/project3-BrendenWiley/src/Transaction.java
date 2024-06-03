/**
 * Transaction class for defining a transaction.
 * @author Brenden Wiley
 *
 */
public class Transaction implements Comparable<Transaction>{
	/**
	 * These instance variables hold the information required to define a transaction
	 * blockNumber holds the block number
	 * index holds the index transaction value
	 * gasLimit holds the gasLimit transaction data
	 * gasPrice holds gasPrice transaction data 
	 * fromAdr holds the fromAdr value of the transaction data
	 * toAdr holds the toAdr value of the transaction data
	 */
	private int blockNumber;
	private int index;
	private int gasLimit;
	private long gasPrice;
	private String fromAdr;
	private String toAdr;
	
	/**
	 * General constructors for initializing transaction variables
	 * @param number initialize blocknumber
	 * @param index initialize index
	 * @param gasLimit initialize gasLimit
	 * @param gasPrice initialize gasPrivr
	 * @param fromAdr initialize fromAdr
	 * @param toAdr initialize toAdr
	 */
	public Transaction(int number, int index, int gasLimit, long gasPrice, String fromAdr, String toAdr) {
		
		this.blockNumber = number;
		this.index = index;
		this.gasLimit = gasLimit;
		this.gasPrice = gasPrice;
		this.fromAdr = fromAdr;
		this.toAdr = toAdr;
		
	}
	/**
	 * General "getter" for returning instance variable
	 * @return blocknumber 
	 */
	public int getBlockNumber() {
		return blockNumber;
	}
	/**
	 * General "getter" for returning instance variable
	 * @return index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * General "getter" for returning instance variable
	 * @return gasLimit 
	 */
	public int getGasLimit() {
		return gasLimit;
	}
	/**
	 * General "getter" for returning instance variable
	 * @return gasPrice
	 */
	public long getGasPrice() {
		return gasPrice;
	}
	/**
	 * General "getter" for returning instance variable
	 * @return fromAdr 
	 */
	public String getFromAddress() {
	return fromAdr;
	}
	/**
	 * General "getter" for returning instance variable
	 * @return toAdr
	 */
	public String getToAddress() {
		return toAdr;
	}
	/**
	 * Function that calculates the cost of the transaction in ether
	 * @return cost of the transaction
	 */
	public double transactionCost() {
		
		double p = (this.getGasPrice() * this.getGasLimit()) / (1.0e+18) ;
		
		
		
		return p;
		
		
	}

	/**
	 * Returns a transaction information in string form.
	 * @return transaction in string form
	 */
	public String toString() {
		
		String s = "Transaction " + index + " for Block " + blockNumber;
		
		return s;
		
		
	}
	/**
	 * General compareTo method comparing two transaction indexes
	 * @return int comparing two transaction indexes
	 */
	@Override
	public int compareTo(Transaction T) {
		
		 if (this.getIndex() > T.getIndex()) {
				
	            return 1;
	        }
		 
	        else if (this.getIndex() < T.getIndex()) {
	 
	            return -1;
	            
	        }
		 
	        else {
	 
	          
	            return 0;
	
		
		
	}
	
	
}
}
