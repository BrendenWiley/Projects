// Brenden Wiley 113-555-009
// Project 6
// CS 2413 Data Structures
// Spring 2023
#include <iostream>
#include <string>  // for storing string
#include <fstream> // for reading a file again and again
#include <map>
#include <unordered_map>
#include <vector>
using namespace std;

// Hash Function
class HashFunc
{
public:
    int operator()(int key) const
    {
        return hash<int>{}(key);
    }
};

// Display the contents of an ordered map
void display_map(map<int, vector<string>> &map)
{
    for (auto it : map)
    {
        cout << it.first << ": ";
        for (auto it2 : it.second)
        {
            cout << it2 << " ";
        }
        cout << endl;
    }
}

// Display the contents of an unordered map
void display_unordered_map(unordered_map<int, vector<string>, HashFunc> &unmap)
{
    for (auto it : unmap)
    {
        cout << it.first << ": ";
        for (auto it2 : it.second)
        {
            cout << it2 << " ";
        }
        cout << endl;
    }
}

int main()
{
    string fileName; // to read in the name of the fileInput from redirected input
    cin >> fileName;

    cout << "Name of fileInput: " << fileName << endl;

    // MAP
    map<int, vector<string>> m1;

    // open a fileName in read mode
    ifstream fI;
    fI.open(fileName);
    char option;
    string temp;

    // reading and performing operations
    // start timer
    clock_t mapInsert = clock();

    int UnicodeKey = 0;        // store string total unicode
    int Unicode = 0;           // store individual char unicodse
    vector<string> mapremoval; // to store elements to be removed

    while (fI >> option) //while file has data
    {
        fI >> temp; //read string

        // add string to be removed to a vector
        if (option == 'R')
        {
            mapremoval.push_back(temp);
        }

        // add unicode values of all chars in string to make key
        for (int i = 0; i < temp.size(); ++i)
        {
            Unicode = (int)temp.at(i);
            UnicodeKey = UnicodeKey + Unicode;
        }

        // create the structure - map - m1
        m1[UnicodeKey].push_back(temp);
        UnicodeKey = 0;
        Unicode = 0;
    }
    // stop timer
    mapInsert = clock() - mapInsert;

    // close the opened file
    fI.close();

    // UNORDERED MAP
    unordered_map<int, vector<string>, HashFunc> m2;

    // open a file again in read mode
    fI.open(fileName);

    // start timer
    clock_t unmapInsert = clock();

    // reset values
    UnicodeKey = 0;
    Unicode = 0;

    // to store strings to be removed
    vector<string> unmapremoval;
    while (fI >> option) //while file has data
    {
        fI >> temp; //read string

        // add string to be removed to a vector
        if (option == 'R')
        {
            unmapremoval.push_back(temp);
        }

        // add unicode values of all chars in string to make key
        for (int i = 0; i < temp.size(); ++i)
        {
            Unicode = (int)temp.at(i);
            UnicodeKey = UnicodeKey + Unicode;
        }

        // create the structure - unordered map - m2
        m2[UnicodeKey].push_back(temp);
        UnicodeKey = 0;
        Unicode = 0;
    }

    // stop timer
    unmapInsert = clock() - unmapInsert;

    // display normal map/unordered map
    cout << "Map:" << endl;
    display_map(m1);
    cout << "Unordered Map:" << endl;
    display_unordered_map(m2);

    // reset values
    UnicodeKey = 0;
    Unicode = 0;

    // start timer
    clock_t mapDelete = clock();

    // delete elements
    for (int i = 0; i < mapremoval.size(); ++i)
    {
        // retrieve string to be removed
        string remove = mapremoval.at(i);

        // find strings unicode value
        for (int i = 0; i < remove.size(); ++i)
        {
            Unicode = (int)remove.at(i);
            UnicodeKey = UnicodeKey + Unicode;
        }
        // erase by key
        m1.erase(UnicodeKey);
        UnicodeKey = 0;
        Unicode = 0;
    }

    // stop timer
    mapDelete = clock() - mapDelete;

    // start timer
    clock_t unmapDelete = clock();

    // reset values
    UnicodeKey = 0;
    Unicode = 0;

    // delete elements
    for (int i = 0; i < unmapremoval.size(); ++i)
    {
        // retrieve string to be removed
        string remove = unmapremoval.at(i);

        // find strings unicode value
        for (int i = 0; i < remove.size(); ++i)
        {
            Unicode = (int)remove.at(i);
            UnicodeKey = UnicodeKey + Unicode;
        }

        // erase by key
        m2.erase(UnicodeKey);
        UnicodeKey = 0;
        Unicode = 0;
    }

    // stop timer
    unmapDelete = clock() - unmapDelete;

    // // Print insert timing data
    // cout << "map insert time:" << (float)(mapInsert) / CLOCKS_PER_SEC << endl;
    // cout << "unmap insert time: " << (float)(unmapInsert) / CLOCKS_PER_SEC << endl;

    // // Print delete timing data
    // cout << "map deletion time:" << (float)(mapDelete) / CLOCKS_PER_SEC << endl;
    // cout << "unmap deletion time: " << (float)(unmapDelete) / CLOCKS_PER_SEC << endl;

    // close the opened file
    fI.close();
    return 0;
}