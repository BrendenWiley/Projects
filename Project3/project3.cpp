// Brenden Wiley 113-555-009
// CS 2413 Data Structures
// Spring 2023 Project 3
#include <iostream>
#include <vector> // for array of transactions
#include <list>   // for blockchain

using namespace std;

class transaction //transaction class for defining a transaction 
{
    int tID;          // transaction id
    int fromID;       // source ID
    int toID;         // target ID
    int tAmount;      // how much is being transfered
    string timeStamp; // time of the transaction read from the input file

public:
    transaction()
    { // default constructor
        tID = 100;
        fromID = 100;
        toID = 100;
        tAmount = 100;
        timeStamp = "";
    }

    transaction(int temptID, int tempfromID, int temptoID, int temptAmount, string temptimeStamp)
    { // non default constructor - default 100 for values
        tID = temptID;
        fromID = tempfromID;
        toID = temptoID;
        tAmount = temptAmount;
        timeStamp = temptimeStamp;
    }
    // all setters and getters
    int gettID()
    {
        return tID;
    }

    int getfromId()
    {
        return fromID;
    }

    int gettoID()
    {
        return toID;
    }

    int gettAmount()
    {
        return tAmount;
    }

    string gettimeStamp()
    {
        return timeStamp;
    }
    
    //display method for transaction
    void displayTransaction() {
        cout << gettID() << " ";
        cout << getfromId() << " ";
        cout << gettoID() << " ";
        cout << gettAmount() << " ";
        cout << gettimeStamp() << endl;
    }
};

class block  //transaction class for defining a block (vector of transactions)
{
    int blockNumber;                   // block number of the current block
    int currentNumTransactions;        // how many transactions are currently in the block
    int maxNumTransactions;            // how many maximum transactions can be in the block
    vector<transaction> bTransactions; // vector of transaction objects

public:
    block() // default constructor
    { 
        blockNumber = 0;
        maxNumTransactions = 0;
        currentNumTransactions = 0;
    } 

    block(int bNumber, int maxTransactions) // non default constructor
    {
        blockNumber = bNumber;
        maxNumTransactions = maxTransactions;
        currentNumTransactions = 0;
    }
    
    // insert method to insert a new transaction
    void insertTransactionBlock(transaction a)  {
        bTransactions.push_back(a);
        ++currentNumTransactions;
    }

    //getters
    vector<transaction> getTransactions()
    {
        return bTransactions;
    }
    int getCurrent()
    {
        return currentNumTransactions;
    }

    //display method for blocks
    void displayBlock()
    {

        cout << "Block Number: " << blockNumber << " -- Number of Transaction: " << currentNumTransactions << endl;
        for (int i = 0; i < currentNumTransactions; ++i)
        {
            bTransactions[i].displayTransaction(); //call transaction display
        }
    }
};

class blockChain //blockChain class for defining a blockChain (list of blocks)
{
    int currentNumBlocks; // maintain the size of the list/block chain list
    int transPerBlock; //transactions allowed per block
    list<block> bChain; // blockchain as a linked list
public:
    blockChain(); // default constructor

    blockChain(int tPerB) // non default constructor
    { 
        transPerBlock = tPerB;
        currentNumBlocks = 1;
        block *chainBlock = new block(currentNumBlocks, transPerBlock);
        bChain.push_back(*chainBlock);
        cout << "Adding block #1" << endl;
    }
    // insert method to insert new transaction into the block chain - add new block if existing block is full
    void insertTransaction(transaction a)
    {
        block *chainblock = &bChain.back();

        //if block is full, create new block and add transaction to it
        if (chainblock->getTransactions().size() == transPerBlock)
        {
            ++currentNumBlocks; //increment number of blocks
            cout << "Adding block #" << currentNumBlocks << endl;
            block *newBlock = new block(currentNumBlocks, transPerBlock); //create new block
            bChain.push_back(*newBlock); //push new block
            bChain.back().insertTransactionBlock(a); //insert transaction to new block
            cout << "Inserting transaction to block # " << currentNumBlocks << endl;
        }
        //if block is not full add transaction to current block
        else
        {
            bChain.back().insertTransactionBlock(a); //insert transaction to current block
            cout << "Inserting transaction to block # " << currentNumBlocks << endl;
        }
    }

    //getters
    list<block> getbChain()
    {
        return bChain;
    }
    int getCurrent()
    {
        return currentNumBlocks;
    }

    //display method for blockchain 
    void display()
    {
        cout << "Current number of blocks: " << getCurrent() << endl;
        for (auto it = bChain.begin(); it != bChain.end(); ++it) {
            it->displayBlock(); //call block display
        }
    }
};

int main()
{
    //read and write total transactions per block and total num transactions
    int numTransactionsPerBlock; // max transactions per block
    cin >> numTransactionsPerBlock;
    cout << "Number of transactions per block: " << numTransactionsPerBlock << endl;
    int totalNumTransactions; // total transactions to be read from the input file
    cin >> totalNumTransactions;
    cout << "Total number of transactions: " << totalNumTransactions << endl;

    // object of block chain
    blockChain *b1 = new blockChain(numTransactionsPerBlock);

    // insert transactions into the blockchain
    int temptID, tempfromID, temptoID, temptAmount;
    string temptimeStamp;

    //read data into transaction structure and insert into blockchain
    for (int i = 0; i < totalNumTransactions; ++i)
    {
        cin >> temptID;
        cin >> tempfromID;
        cin >> temptoID;
        cin >> temptAmount;
        cin >> temptimeStamp;

        transaction t = transaction(temptID, tempfromID, temptoID, temptAmount, temptimeStamp);

        b1->insertTransaction(t); //insert
    }

    b1->display(); //call blockchain display

    delete b1; //free memory
    return 0;
}