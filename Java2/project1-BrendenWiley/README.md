# Project 1: Ethereum Blocks

A blockchain is a database of transactions that is updated and shared across many computers in a network. Every time a new set of transactions is added, it’s called a “block” - hence the name blockchain. Most blockchains are public, and you can only add data, not remove. If someone wanted to alter any of the information or cheat the system, they’d need to do so on the majority of computers on the network. For this project, we will be using a dataset of 100 blocks in the Ethereum blockchain. 

You will be writing the Blocks.java class to accomplish the tasks laid out by the Project 1 pdf and Driver.java.

## Blocks UML Diagram

<img src=./resources/BlocksUML.PNG width=50% height=50%>

Feel free to add your own helper methods as needed. 

## Methods

### Constructors

`Blocks()`: Initialize no fields. When printed using the toString() method it should return "Empty Block" exactly. 

`Blocks(int number)`: Initialize the Block number. When printed using the toString() method it should return "Block Number: `number`" exactly. 
  
`Blocks(int number, String miner)`: Initialize the Block number and miner address. When printed using the toString() method it should return "Block Number: `number` Miner Address: `miner`" exactly. 
  
### Getters
  
`getNumber()`: Should return the Block number. 
  
`getMiner()`: Should return the miner address. 
  
`getBlocks()`: Should return a copy of the blocks ArrayList. 
  
### Other Methods
  
`calUniqMiners()`: Should print to output the number of unique miners in the data, and a pair of lines for each one giving its miner address and the frequency at which it appears. Print your output according to the following sample: 

<img src=./resources/calUniqMinersEx.JPG width=50% height=50%>
  
Make sure the style of your output exactly matches the style of the sample. There should be no spaces at the end of your lines. 
  
For example, "Number of unique Miners: 5" is correct spacing while "Number of unique Miners: 5 " is not. 
  
Also, the empty lines should be newline characters.
  
`blockDiff(Blocks A, Blocks B)`: Should return the difference between A's and B's Block number. The result can be positive or negative depending on the order the Blocks are supplied. For example: 
  
<img src=./resources/blockDiff.JPG>
  
Should return: <br />
-8 <br />
8 <br />
  
`getBlockByNumber(int num)`: Should return the Blocks object you read from the file that corresponds to the given Block number. 
  
## Grading
  
You will be graded on: 
  
  * Zylabs submission: 80 points possible
  * at least 10 github commits: 10 points possible
  * generated Javadocs: 10 points possible
  
  
