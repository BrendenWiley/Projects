// Data Structures Spring 2023
// CS 2413 Project 2
// Brenden Wiley 113-555-009
#include <iostream>
#include <string>
#include <fstream> //for reading fileInput

using namespace std;

// class to handle custom exception cases
class CustomExceptions
{

public:
    // exception function when findmin operation is used on wrong data type
    string type()
    {
        cout << "Wrong data type" << endl;
        return "";
    }

    // exception function when findmin is given incorrect column length
    string length(int col)
    {
        cout << "Column Number " << to_string(col) << " out of bounds" << endl;
        return "";
    }

    // exception function when search cannot find row that starts with string in matrix
    string recordNotFound()
    {
        cout << "Record not found" << endl;
        return "";
    }

    // exception function when search cannot find value in matrix
    string valueNotFound()
    {
        cout << "Value not found" << endl;
        return "";
    }
};

// class to define functions and operations for a csv input matrix
class tableClass
{

protected:
    string **myTable;   // 2D string array to store all values (strings and numbers)
    string *DTarray;    // 1D array to store the data types of each column
    int noRows, noCols; // number of rows and columns

public:
    // Constructors
    tableClass();

    // Initializes rows and cols with input, creates myTable matrix with correct size with null values
    tableClass(int rows, int cols)
    {
        // initialize global variables
        noRows = rows;
        noCols = cols;

        // create mytable matrix rows size
        // idea referenced from https://stackoverflow.com/questions/70192457/initialize-2d-array-in-constructor-of-cpp-class
        myTable = new string *[noRows];

        for (int x = 0; x < noRows; ++x)
        {
            // create mytable matrix column size
            myTable[x] = new string[noCols];

            for (int y = 0; y < noCols; ++y)

                // fill matrix with null values
                myTable[x][y] = "";
        }
        // initialize empty dtarray to size 6
        DTarray = new string[6];
    }

    // Overload the [] operator to access a row in myTable
    string *operator[](int i)
    {

        return myTable[i];
    }

    // File reading Method
    void readCSV(string fileName)
    {

        ifstream file(fileName);
        string line;      // string to hold csv data by line
        int x = 0, y = 0; // row and column

        // read all lines in file
        // idea referenced from https://stackoverflow.com/questions/14265581/parse-split-a-string-in-c-using-string-delimiter-standard-c
        while (getline(file, line))
        {

            size_t delim = 0;

            // set delimiter to , to parse line into words
            while ((delim = line.find(',')) != string::npos)
            {
                // add word into matrix
                myTable[x][y] = line.substr(0, delim);
                // delete delim character from line
                line.erase(0, delim + 1);
                // increase column
                y++;
            }

            // increment row and set column to 0
            myTable[x][y] = line;
            y = 0;
            x++;

            // stop when rows are filled
            if (x == noRows)
                break;
        }
    }

    // Output Display Method
    void display()
    {
        // display dtarray data type values
        for (int i = 0; i < 6; ++i)
        {
            cout << DTarray[i] << " ";
        }
        cout << endl;

        // embedded forloop to display contents of mytable matix
        for (int x = 0; x < noRows; x++)
        {

            for (int y = 0; y < noCols; y++)
            {

                cout << myTable[x][y] + " ";
            }

            cout << endl;
        }
    }

    // Sort the table using selection sort
    // idea referenced from https://stackoverflow.com/questions/44526234/sorting-2d-array-using-selection-sort
    void sortTable()
    {

        // goes over rows of table and finds index of lowest element
        for (int i = 0; i < noRows - 1; i++)
        {
            int min = i;

            for (int p = i + 1; p < noRows; p++)
            {

                if (myTable[p][0] < myTable[min][0])
                {

                    min = p;
                }
            }

            // if min element is already not in right position
            if (min != i)
            {

                // swaps current row for with row containing minimum element
                for (int k = 0; k < noCols; k++)
                {

                    string temp = myTable[i][k];

                    myTable[i][k] = myTable[min][k];

                    myTable[min][k] = temp;
                }
            }
        }
    }

    // Search record to find and return row with given first string
    void searchRecord(string str) // str will be from the first column
    {
        try
        { // loop through whole matrix row by row
            for (int i = 0; i < noRows; ++i)
            {
                // set a string to the entire row
                string word = *myTable[i];

                // find first word
                string target = word.substr(0, word.find(" "));

                // check if first word of current row equals str
                if (target == str)
                {
                    cout << "Record found: " << endl;
                    for (int k = 0; k < noCols; ++k)
                    {
                        cout << myTable[i][k] << " ";
                    }
                    cout << endl;
                    return;
                }
            }
            throw CustomExceptions(); // if word is not found throw exception
        }
        catch (CustomExceptions exception)
        {
            // throw recordNotFound exception function
            cout << exception.recordNotFound();
        }
    }

    // Search value from table and return coordinate positions in matrix
    void searchValue(string str)
    {
        bool search = false; // trigger to determine if value is found

        cout << "Searching for " << str << endl;
        try
        {
            // embedded for loop to compare values inside matrix to target
            for (int i = 0; i < noRows; ++i)
            {

                for (int k = 0; k < noCols; ++k)
                {

                    string target = myTable[i][k].substr(0, str.length());

                    if (str.compare(target) == 0)
                    {
                        // return coordinate position and continue to loop and attempt to find others
                        cout << "  found in (" << i << ", " << k << ")" << endl;
                        search = true;
                    }
                }
            }
            if (!search) // if value is not found throw exception
            {
                throw CustomExceptions();
            }
        }
        catch (CustomExceptions exception)
        {
            // throw valueNotFound exception function
            cout << exception.valueNotFound();
        }
    }

    // Getters
    int getNumberRows()
    { // returns the number of rows

        return noRows;
    }

    int getNumberCols()
    { // returns the number of columns

        return noCols;
    }

    // returns a tableClass with a set of columns from colLeft to colRight indices
    tableClass *getColumns(int colLeft, int colRight)
    {
        // new tableclass object to return which is a subset of mytable
        tableClass *subset = new tableClass(getNumberRows(), colRight - colLeft);

        // datatypes of new tableclass object
        for (int i = colLeft; i < colRight; ++i)
        {

            cout << DTarray[i] + " ";
        }

        cout << endl;

        // fill new subset with mytable values in correct bounds
        for (int i = 0; i < getNumberRows(); ++i)
        {

            for (int k = colLeft, q = 0; k < colRight, q < colRight - colLeft; ++k, ++q)
            {

                (*subset)[i][q] = myTable[i][k];
            }
        }

        // display newly filled subset
        for (int x = 0; x < getNumberRows(); x++)
        {

            for (int y = 0; y < colRight - colLeft; y++)
            {

                cout << (*subset)[x][y] << " ";
            }

            cout << endl;
        }
        // return subset
        return subset;
    }

    // returns a tableClass with a set of rows from rowTop to rowBottom indices
    tableClass *getRows(int rowTop, int rowBottom)
    {
        // new tableclass object to return which is a subset of mytable
        tableClass *subset = new tableClass(rowBottom - rowTop, getNumberCols());

        // datatypes of new tableclass object
        for (int i = 0; i < getNumberCols(); ++i)
        {

            cout << DTarray[i] + " ";
        }

        cout << endl;

        // fill new subset with mytable values in correct bounds
        for (int i = 0, q = rowTop; i < rowBottom - rowTop, q < rowBottom; ++i, ++q)
        {

            for (int k = 0; k < getNumberCols(); ++k)
            {

                (*subset)[i][k] = myTable[q][k];
            }
        }

        // display newly filled subset
        for (int x = 0; x < rowBottom - rowTop; x++)
        {

            for (int y = 0; y < getNumberCols(); y++)
            {

                cout << (*subset)[x][y] << " ";

            }

            cout << endl;
        }

        // return subset
        return subset;
    }

    // returns a tableClass with the data between the cols and rows given
    tableClass *getRowsCols(int colLeft, int colRight, int rowTop, int rowBottom)
    {
        // new tableclass object to return which is a subset of mytable
        tableClass *subset = new tableClass(rowBottom - rowTop, getNumberCols());

        // datatypes of new tableclass object
        for (int i = colLeft; i < colRight; ++i)
        {

            cout << DTarray[i] + " ";
        }

        cout << endl;

        // fill new subset with mytable values in correct bounds
        for (int i = 0, q = rowTop; i < rowBottom - rowTop, q < rowBottom; ++i, ++q)
        {

            for (int k = colLeft, r = 0; k < colRight, r < colRight - colLeft; ++k, ++r)
            {

                (*subset)[i][r] = myTable[q][k];
            }
        }

        // display newly filled subset
        for (int x = 0; x < rowBottom - rowTop; x++)
        {

            for (int y = 0; y < colRight - colLeft; y++)
            {

                if (y < colRight - colLeft - 1)
                {
                    cout << (*subset)[x][y] + " ";
                }
                else
                    cout << (*subset)[x][y];
            }

            cout << endl;
        }

        // return subset
        return subset;
    }

    // returns the min of the given column
    double findMin(int colNumber)
    {
        try
        {                         // variables
            string hold;          // current min to replace if new is found
            string current;       // value to compare against hold
            int type = colNumber; // index of column number to know what datatype to use in DTarray

            // throw exception if given column is first three because it would be the wrong datatype
            if (type < 3)
            {
                throw CustomExceptions();
            }

            // if the column number is out of bounds throw exception
            if (colNumber > noCols || colNumber < 0)
            {
                throw CustomExceptions();
            }

            // hold first value column
            hold = myTable[0][colNumber];

            // loop through and if lower value is found, replace hold with that value
            // keep track of row number index so that
            for (int i = 1; i < noRows; ++i)
            {

                current = myTable[i][colNumber];

                // convert strings to int to compare correctly
                if (stoi(current) < stoi(hold))
                {

                    hold = current;
                }
            }

            // if finding min in 3, return string value of type int
            if (type == 3)
            {
                int min = stoi(hold);
                cout << "Min of " << colNumber << " is " << min << endl;
                return min;
            }
            // if finding min in 4, return string value of type int
            else if (type == 4)
            {
                int min = stoi(hold);
                cout << "Min of " << colNumber << " is " << min << endl;
                return min;
            }

            // if finding min in 3, return value of type float
            else if (type == 5)
            {
                float min = stof(hold);
                cout << "Min of " << colNumber << " is " << min << endl;
                return min;
            }

            return 0;
        }
        // catch block for exceptions
        // Exceptions help referenced from https://rollbar.com/blog/cpp-custom-exceptions/
        catch (CustomExceptions exception)
        {
            // if exception was thrown because of out of bounds column length throw this function
            if (colNumber > noCols || colNumber < 0)
            {
                cout << exception.length(colNumber);
            }

            // exception for finding min on incorrect data type in matrix
            else
            {
                cout << exception.type();
            }
            return 0;
        }
    }

    // fill DTarray with datatype values after reading input txt file
    void storeDT(string *values)
    {

        for (int i = 0; i < 6; ++i)
        {

            string val = values[i];

            DTarray[i] = val;
        }
    }
    // Destructor for deleting mytable matrix and dtarray
    ~tableClass()
    {
        for (int i = 0; i < noCols; ++i)
        {
            delete[] myTable[i];
        }
        delete[] DTarray;
        delete[] myTable;
    }
};

int main()
{
    // variables
    int numRows, numCols; // size of matrix
    string fileName, val; // csv file and query value holder
    char option;          // query value holder

    // read in data from input1 into variables
    cin >> numRows >> numCols >> fileName;

    // print out data to console
    cout << "NumRows: " << numRows << endl;
    cout << "NumCols: " << numCols << endl;
    cout << "FileName: " << fileName << endl;

    // create tableclass matrix with input data
    tableClass *d = new tableClass(numRows, numCols);

    // read CSV file which fills tableclass matrix
    d->readCSV(fileName);

    // sort tableclass matrix with respect to first column
    d->sortTable();

    // Read the data types and store in DTarray
    string *values = new string[6](); // read input data into array
    for (int i = 0; i < 6; ++i)
    {
        cin >> val;

        values[i] = val;
    }
    d->storeDT(values);

    // Start reading the options in input file till the end of the file
    while (cin.eof() != 1)
    {
        // variables
        int val, val1, val2, val3, val4;
        string name;

        cin >> option; // Read in query/operation command

        // Find string and return entire row where found
        if (option == 'F')
        {
            cin >> name;

            d->searchRecord(name);
        }

        // Find value and return row/col
        if (option == 'V')
        {

            cin >> name;

            d->searchValue(name);
        }

        // Display matrix data
        if (option == 'D')
        {
            d->display();
        }

        // Find the min of the column number given
        if (option == 'I')
        {
            cin >> val;

            d->findMin(val);
        }

        // Return a new matrix with a subset of all rows from the two given column numbers
        if (option == 'C')
        {
            cin >> val1;
            cin >> val2;

            d->getColumns(val1, val2);
        }

        // Return a new matrix with a subset of all columns from the two given row numbers
        if (option == 'R')
        {
            cin >> val1;
            cin >> val2;

            d->getRows(val1, val2);
        }
        // Return a new matrix with a subset of the two given row numbers and two given column numbers
        if (option == 'S')
        {

            cin >> val1;
            cin >> val2;
            cin >> val3;
            cin >> val4;

            d->getRowsCols(val1, val2, val3, val4);

            delete[] values;
            return 0;
        }
    }
    // delete values used to initialize dtarray
    delete[] values;
    return 0;
}