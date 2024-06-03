// Brenden Wiley 113-555-009
// CS 2413 Data Structures
// Project 4 Spring 2023
#include <iostream>
#include <vector> // for array of transactions and array of blockChains
#include <list>   // for blockchain
using namespace std;

class transaction
{
    int tID;          // transaction id
    int fromID;       // source ID
    int fromValue;    // P4: how much funds does the source ID person have? If first time, then initialize with 100 coins
    int toID;         // target ID
    int toValue;      // P4: how much funds does the source ID person have? If first time, then initialize with 100 coins
    int tAmount;      // how much is being transfered
    string timeStamp; // time of the transaction read from the input file

public:
    transaction() { // default constructor P4: default 100 for from and to values
     tID = 100;          
     fromID = 100;     
     fromValue = 100;  
     toID = 100;         
     toValue = 100;     
     tAmount = 100;      
     timeStamp = "";
    }

    transaction(int temptID, int tempfromID, int temptoID, int temptAmount, string temptimeStamp); // non default constructor
    
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
    int getfromValue() {
        return fromValue;
    }
    int gettoValue() {
        return toValue;
    }
    string gettimeStamp()
    {
        return timeStamp;
    }
    void setfromValue() {

        fromValue = fromValue + 2;

    }
    void settoValue() {

        toValue = toValue + 1;

    }

    void displayTransaction();
};
transaction::transaction(int temptID, int tempfromID, int temptoID, int temptAmount, string temptimeStamp)
{
    tID = temptID;
    fromID = tempfromID;
    toID = temptoID;
    tAmount = temptAmount;
    timeStamp = temptimeStamp;
    fromValue = 100;
    toValue = 100;
}
void transaction::displayTransaction()
{
    cout << tID << " " << fromID << " " << fromValue << " " << toID << " " << toValue << " " << tAmount << " " << timeStamp << endl;
}

class block
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

    // P4: search for an ID in the bTransaction vector

    void searchIDVector(int ID) {

        

        for(int i = 0; i < bTransactions.size(); ++i) {

           if(bTransactions.at(i).getfromId() == ID) {

            bTransactions.at(i).setfromValue();

           }
            if(bTransactions.at(i).gettoID() == ID) {

            bTransactions.at(i).settoValue();

           }

        }

    }



    // other methods as needed
};

class blockChain
{
  int currentNumBlocks; // maintain the size of the list/block chain list
    int transPerBlock; //transactions allowed per block
    list<block> bChain; // blockchain as a linked list
public:
    blockChain() { // default constructor
    currentNumBlocks = 0;
    transPerBlock = 0;
    }
    blockChain(int tPerB) // non default constructor
    { 
        transPerBlock = tPerB;
        currentNumBlocks = 1;
        block *chainBlock = new block(currentNumBlocks, transPerBlock);
        bChain.push_back(*chainBlock);
    }

    // insert method to insert new transaction into the block chain - add new block if existing block is full
    void insertTransaction(transaction a, int node)
    {
        block *chainblock = &bChain.back();

        //if block is full, create new block and add transaction to it
        if (chainblock->getTransactions().size() == transPerBlock)
        {
            ++currentNumBlocks; //increment number of blocks
            block *newBlock = new block(currentNumBlocks, transPerBlock); //create new block
            bChain.push_back(*newBlock); //push new block
            bChain.back().insertTransactionBlock(a); //insert transaction to new block
            cout << "Inserting transaction to block # " << currentNumBlocks << " in node " << node << endl;
        }
        //if block is not full add transaction to current block
        else
        {
            bChain.back().insertTransactionBlock(a); //insert transaction to current block
            cout << "Inserting transaction to block # " << currentNumBlocks << " in node " << node << endl;
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
    void display(int node)
    {
        cout << "~~~ Node " << node << ":" << endl;
        cout << "Current number of blocks: " << getCurrent() << endl;
        for (auto it = bChain.begin(); it != bChain.end(); ++it) {
            it->displayBlock(); //call block display
        }
    }

    // P4: search for an ID across all blocks in bChain

    void searchFromIDBlock(int ID) {

        for (auto it = bChain.begin(); it != bChain.end(); ++it) {
            it->searchIDVector(ID); //call block display
        }

    }

    void searchToIDBlock(int ID) {

        for (auto it = bChain.begin(); it != bChain.end(); ++it) {
            it->searchIDVector(ID); //call block display
        }

    }


    // other methods as needed
};

class blockNetwork
{
   
    int numNodes;                // maintain the number of nodes in the network
    vector<blockChain> allNodes; // vector of all the blockChains in the network
    vector<int> u;               // edge list u node
    vector<int> v;               // edge list v node

public:
    blockNetwork();                 // default constructor

    blockNetwork(int numberOfNodes, int numTransactionsPerBlock) { // non default constructor
    for(int i = 0; i < numberOfNodes; ++i) {
    blockChain b1 = blockChain(numTransactionsPerBlock);
    allNodes.push_back(b1);
    }

    } 
    // insert method to insert new transaction into the block chain in a specific node - there will be a block chain existent in each node (allNodes vector)
    void insertBlock(int node, transaction t) {
  
        allNodes.at(node).insertTransaction(t, node);

  
        allNodes.at(node).searchFromIDBlock(t.getfromId());
        allNodes.at(node).searchToIDBlock(t.gettoID());

    }
    // setters and getters as needed
    vector<blockChain> getAllNodes() {
        return allNodes;
    }


    // other methods as needed
void netDisplay() {
    for(int i = 0; i < allNodes.size(); ++i) {
        allNodes.at(i).display(i);


    }
}

void initialize(int uNode, int vNode, int i) {
    u.push_back(uNode);
    v.push_back(vNode);
}


};

int main()
{
    int numNodesInNetwork; // number of nodes in the network
    cin >> numNodesInNetwork;
    cout << "Number of nodes: " << numNodesInNetwork << endl;
    int numTransactionsPerBlock; // max transactions per block
    cin >> numTransactionsPerBlock;
    cout << "Number of transactions per block: " << numTransactionsPerBlock << endl;
    int totalNumTransactions; // total transactions to be read from the input file
    cin >> totalNumTransactions;
    cout << "Total number of transactions: " << totalNumTransactions << endl;
    int uNode, vNode, numConnections;

    // object of block network
    blockNetwork *n1 = new blockNetwork(numNodesInNetwork, numTransactionsPerBlock);

    cin >> numConnections;
    //Read in v and u edges and initialize vector in network class
   for(int i = 0; i < numConnections; ++i) {
    cin >> uNode;
    cin >> vNode;
    n1->initialize(uNode, vNode, i);
   }

    int node, temptID, tempfromID, temptoID, temptAmount;
    string temptimeStamp;
  
   for (int i = 0; i < totalNumTransactions; ++i)
    {
        cin >> node;
        cin >> temptID;
        cin >> tempfromID;
        cin >> temptoID;
        cin >> temptAmount;
        cin >> temptimeStamp;
        transaction t = transaction(temptID, tempfromID, temptoID, temptAmount, temptimeStamp);
        n1->insertBlock(node, t); //insert
       
    }
      n1->netDisplay(); // make sure you write display methods for each class 
    return 0;
}
