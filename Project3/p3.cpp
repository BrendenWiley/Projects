// Brenden Wiley 113-555-009
// CS 2413 Data Structures
// Spring 2023 Project 3
#include <iostream>
#include <vector> // for array of transactions
#include <list>   // for blockchain

using namespace std;

class transaction
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
};

class block
{
    int blockNumber;                   // block number of the current block
    int currentNumTransactions;        // how many transactions are currently in the block
    int maxNumTransactions;            // how many maximum transactions can be in the block
    vector<transaction> bTransactions; // vector of transaction objects

public:
    block();                                // default constructor
    block(int bNumber, int maxTransactions) // non default constructor
    {
        blockNumber = bNumber;
        maxNumTransactions = maxTransactions;
        currentNumTransactions = 0;
     
    }
    // search method for searching through array of transactions
    // insert method to insert a new transaction
    void insertTransactionBlock(transaction *a)
    {
        ++currentNumTransactions;
        bTransactions.insert(bTransactions.end(), *a);
        //bTransactions.push_back(*a);

    }

    // setters and getters as needed
    vector<transaction> getTransactions()
    {
        return bTransactions;
    }

    int getCurrent()
    {
        return currentNumTransactions;
    }

    void setCurrent()
    {
        currentNumTransactions = bTransactions.size();
    }
};

class blockChain
{
    int currentNumBlocks; // maintain the size of the list/block chain list
    int transPerBlock;
    list<block> bChain; // blockchain as a linked list

public:
    blockChain(); // default constructor
    blockChain(int tPerB)
    { // non default constructor
        transPerBlock = tPerB;
        currentNumBlocks = 1;
        block *chainBlock = new block(currentNumBlocks, tPerB);
        bChain.push_back(*chainBlock);
        cout << "Adding block #1" << endl;
    }
    // insert method to insert new transaction into the block chain - add new block if existing block is full
    void insertTransaction(transaction *a)
    {
        block *chainblock = &bChain.back();

        if (chainblock->getTransactions().size() == transPerBlock)
        {

            ++currentNumBlocks;
            cout << "Adding block #" << currentNumBlocks << endl;

            block *newBlock = new block(currentNumBlocks, 0);

            bChain.push_back(*newBlock);

            bChain.back().insertTransactionBlock(a);

            cout << "Inserting transaction to block # " << currentNumBlocks << endl;

            newBlock->setCurrent();
        }

        else
        {

            bChain.back().insertTransactionBlock(a);

            cout << "Inserting transaction to block # " << currentNumBlocks << endl;
        }
    }

    // while inserting new block into list, insert front
    // setters and getters as needed
    list<block> getbChain()
    {
        return bChain;
    }

    int getCurrent()
    {
        return currentNumBlocks;
    }
};

int main()
{
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

    for (int i = 0; i < totalNumTransactions; ++i)
    {

        cin >> temptID;
        cin >> tempfromID;
        cin >> temptoID;
        cin >> temptAmount;
        cin >> temptimeStamp;

        transaction *t = new transaction(temptID, tempfromID, temptoID, temptAmount, temptimeStamp);

        b1->insertTransaction(t);
        delete t;
    }

    cout << "Current number of blocks: " << b1->getCurrent() << endl;

    for (int i = 0; i < b1->getbChain().size(); ++i)
    {

        auto it = std::next(b1->getbChain().begin(), i);
        block t = *it;

        int count = 0;

        for (int i = 0; i < t.getCurrent(); ++i)
        {
            if (t.getTransactions()[i].getfromId() != 0)
            {
                ++count;
            }
        }

        cout << "Block Number: " << i + 1 << " -- Number of Transactions: " << count << endl;

        for (int i = 0; i < t.getCurrent(); ++i)
        {

            cout << t.getTransactions()[i].gettID() << " ";
            cout << t.getTransactions()[i].getfromId() << " ";
            cout << t.getTransactions()[i].gettoID() << " ";
            cout << t.getTransactions()[i].gettAmount() << " ";
            cout << t.getTransactions()[i].gettimeStamp() << endl;
        }
    }
    delete b1;
    return 0;
}