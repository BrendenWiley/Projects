// Brenden Wiley 113-555-009
// Project 5
// CS 2413 Data Structures
// Spring 2023

#include <iostream>
#include <string> // for the fields in the class
#include <vector> // for the flowchart structures
#include <stack>  // for conversion
using namespace std;

// class to store node information
class Node
{

private:
    int nodeNumber;    // to store the node number
    string type;       // can either be START, END, IF, BLOCK
    string textWithin; // can be expression or statement - for START and END this will be empty string

public:
    Node()
    { // default constructor
        nodeNumber = 0;
        type = "0";
        textWithin = "";
    }

    Node(int nodeNumberin, string typein)
    { // nondefault constructor
        nodeNumber = nodeNumberin;
        type = typein;
    }

    Node(int nodeNumberin, string typein, string textWithinin)
    { // nondefault constructor
        nodeNumber = nodeNumberin;
        type = typein;
        textWithin = textWithinin;
    }

    // general getters
    int getNodeNumber()
    {
        return nodeNumber;
    }
    string getType()
    {
        return type;
    }
    string getTextWithin()
    {
        return textWithin;
    }
};

class convert // class to contain convert and run functions
{
private:
    int trigger = -1; // global variable to trigger when a node is visited

public:
    convert() {} // empty constructor

    // function to convert the given flowchart to generate code
    void convertFlowChart(vector<Node> allNodes, vector<vector<int>> adjList)
    {
        // Uses stack to convert the flowchart into psuedo code
        int remove;
        stack<int> allNodesStack; // stack of nodes
        allNodesStack.push(0);

        while (!allNodesStack.empty()) // execute until all allnodes is emptied into stack
        {
            int current = allNodesStack.top();                      // top of stack is current node to be indexed
            allNodesStack.pop();                                    // after saved, pop it to set stack back to empty
            string currentType = allNodes[current].getType();       // current node type
            string currentText = allNodes[current].getTextWithin(); // current node text

            // if START
            if (currentType == "START")
            {
                cout << "start" << endl;
                allNodesStack.push(adjList[current][0]);
            }

            // if IF
            else if (currentType == "IF")
            {
                cout << "if (" << currentText << ")\n";
                cout << "{"
                     << endl;

                // push element for "true" case into stack
                allNodesStack.push(adjList[current][0]);

                // call run method to reiterate through same logic on "true" tree
                int remove = run(allNodesStack.top(), adjList, allNodes, allNodesStack);

                // pop stack element
                allNodesStack.pop();

                cout << "}" << endl;

                cout << "else" << endl;
                cout << "{" << endl;

                // push element for "false" case into stack
                allNodesStack.push(adjList[current][1]);

                // call run method to reiterate through same logic on "false" tree
                remove = run(allNodesStack.top(), adjList, allNodes, allNodesStack);

                cout << "}" << endl;
            }

            // if BLOCK
            else if (currentType == "BLOCK")
            {
                bool trig = false;

                // if node has been visited before (trigger found in allnodes)
                for (int i = 0; i < allNodes.size(); ++i)
                {
                    if (trigger == i)
                    {
                        trig = true;
                        allNodesStack.push(adjList[current][0]);
                        trigger = 100;
                    }
                }

                // if not visited, print like normal
                if (!trig)
                {
                    cout << currentText << "\n";
                    allNodesStack.push(adjList[current][0]);
                }
            }

            // if END
            else if (currentType == "END")
            {
                cout << "end" << endl;
            }
        }
    }

    // Calculates the inner true/false paths of an if else node
    int run(int num, vector<vector<int>> adjList, vector<Node> allNodes, stack<int> allNodesStack)
    {
        int remove;
        string currentType = allNodes[num].getType();       // current node type
        string currentText = allNodes[num].getTextWithin(); // current text type

        // if IF
        if (currentType == "IF")
        {
            cout << "if (" << currentText << ")\n";
            cout << "{" << endl;

            // call method again (recursive) to check true tree
            remove = run(adjList[num][0], adjList, allNodes, allNodesStack);

            cout << "}" << endl;

            cout << "else" << endl;
            cout << "{" << endl;

            // call method again (recursive) to check false tree
            remove = run(adjList[num][1], adjList, allNodes, allNodesStack);

            cout << "}\n"
                 << endl;
        }

        // if BLOCk
        else if (currentType == "BLOCK")
        {
            // print like normal and return node index to save that it is visited
            cout << currentText << "\n"
                 << endl;
            trigger = num;
            return num;
        }
        // return random value if none visited
        return -1;
    }
};

int main()
{
    int numNodesInFlowChart;    // number of nodes in the flow chart
    cin >> numNodesInFlowChart; // read in numNodes
    cout << "Number of nodes: " << numNodesInFlowChart << endl;

    // Node objects array to store all the information
    vector<Node> allNodes;

    string num, type, textWithin;

    // read in data for allNodes
    for (int i = 0; i < numNodesInFlowChart; ++i)
    {
        cin >> num;
        cin >> type;

        // if start then add to Node and push to allNodes
        if (type == "START")
        {
            Node n = Node(i, type);
            allNodes.push_back(n);
        }

        // if IF then add to Node and push to allNodes
        else if (type == "IF")
        {
            cin >> textWithin;
            Node n = Node(i, type, textWithin);
            allNodes.push_back(n);
        }

        // if BLOCK then add to Node and push to allNodes
        else if (type == "BLOCK")
        {
            cin >> textWithin;
            Node n = Node(i, type, textWithin);
            allNodes.push_back(n);
        }

        // if END then add to Node and push to allNodes
        else if (type == "END")
        {
            Node n = Node(i, type);
            allNodes.push_back(n);
        }
    }

    // prints allnodes
    for (int i = 0; i < numNodesInFlowChart; ++i)
    {
        string string;
        if (allNodes.at(i).getType() == "END" || allNodes.at(i).getType() == "START")
        {
            string = " node";
        }
        else
        {
            string = " node - ";
        }

        cout << i << ": ";
        cout << allNodes.at(i).getType() + string;
        cout << allNodes.at(i).getTextWithin() << endl;
    }

    // adjacency list to store the flow chart
    vector<vector<int>> adjList;

    int info1, info2, val;

    // read in data for adjList
    for (int i = 0; i < numNodesInFlowChart; ++i)
    {

        vector<int> data;
        cin >> val;
        type = allNodes.at(i).getType();

        // if start or block, read in the value it flows to and pushback
        if (type == "START" || type == "BLOCK")
        {
            cin >> info1;
            data.push_back(info1);
            adjList.push_back(data);
        }

        // if IF, read in the true tree and false tree nodes and pushback
        else if (type == "IF")
        {
            cin >> info1;
            cin >> info2;
            data.push_back(info1);
            data.push_back(info2);
            adjList.push_back(data);
        }

        // if END, pushback null vector
        else if (type == "END")
        {
            adjList.push_back(data);
        }
    }

    // prints adjList
    cout << "AdjList:" << endl;
    for (int i = 0; i < numNodesInFlowChart; ++i)
    {
        cout << i << ": ";
        for (int q = 0; q < adjList.at(i).size(); ++q)
        {
            cout << adjList.at(i).at(q) << " ";
        }
        cout << endl;
    }

    // Call the convertFlowChart function with the allNodes and adjList parameters (with convert constructor so that run method works)
    convert c = convert();
    c.convertFlowChart(allNodes, adjList);

    return 0;
}
