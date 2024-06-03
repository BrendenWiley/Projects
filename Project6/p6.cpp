// Project 6
// CS 2413 Data Structures
// Spring 2023
#include <iostream>
#include <string> // for storing string
#include <fstream> // for reading a file again and again
#include <map>
#include <unordered_map>
#include <vector>
using namespace std;
// write hash function
// write display functions for map and unordered map
using namespace std;

int main() {

string s = "Valerie";
int UnicodeKey = 0; //store string total unicode
int Unicode = 0; 
 //add unicode values of all chars in string to make key
        for (int i = 0; i < s.size(); ++i)
        {
            Unicode = (int)s.at(i);
            UnicodeKey = UnicodeKey + Unicode;
        }
        cout << UnicodeKey;
}