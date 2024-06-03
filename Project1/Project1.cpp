// Data Structures Spring 2023
// CS 2413
// Brenden Wiley 113-555-009
#include <iostream>
#include <time.h>
using namespace std;

// Function prototypes
int *copySortArray(int **, int);
int binarySearch(int **, int, int, int);
int linearSearch(int **, int, int);
string sumPair(int **, int, int);
int *remove(int *, int, int &x);
int *insertSort(int *, int, int &x);
int *insertUnsort(int *, int, int &x);

// main function
int main()
{
    // Local Variables
    int maxValues;     // max number of values to store / length of the arrays
    int currentValues; // as we add, the number of values in the arrays
    int tempVal;       // variable used to read the number
    string temp;       // variable used to hold string

    cin >> maxValues; // read the maximum number of values from the redirected input file
    cout << "Length of the array: " << maxValues << endl;

    // Dynamic array A of size maxValues
    int *A = new int[maxValues]();

    // Reads in the number from the redirected input into A and displays
    cout << "Array A: ";
    for (int i = 0; i < maxValues; i++)
    {
        cin >> tempVal;
        A[i] = tempVal;
        cout << A[i] << " ";
    }
    cout << endl;

    // Copies array A into array B and sorts using Bubble Sort
    int *B = copySortArray(&A, maxValues);

    // Prints out sorted array B
    cout << "Array B: ";
    for (int i = 0; i < maxValues; i++)
    {
        cout << B[i] << " ";
    }
    cout << endl
         << ("\n");

    // Read the commands/options till the end of the file
    while (cin.eof() != 1)
    {
        int val;
        cin >> temp; // Read in query/operation command

        // findSearch
        if (temp == "F")
        {
            cout << "Find: " << endl;

            cin >> val;

            // Organize input data into array
            int *findVal = new int[val]();
            for (int i = 0; i < val; ++i)
            {
                cin >> tempVal;

                findVal[i] = tempVal;
            }

            clock_t findB = clock();
            // Run binary search on sorted array B
            for (int i = 0; i < val; ++i)
            {
                // if element not found in array
                if (binarySearch(&B, 0, maxValues, findVal[i]) == -1)
                {
                    cout << "Element " << findVal[i] << " not found in B " << endl;
                }

                // element found in array
                else
                    cout << "Element " << findVal[i] << " found at " << binarySearch(&B, 0, maxValues, findVal[i]) << " in B" << endl;
            }
            findB = clock() - findB;
            cout << ("\n");

            clock_t findA = clock();
            // Run linear search on unsorted array B
            for (int i = 0; i < val; ++i)
            {

                // if element not found in array
                if (linearSearch(&A, findVal[i], maxValues) == -1)
                {
                    cout << "Element " << findVal[i] << " not found in A" << endl;
                }

                // element found in array
                else
                    cout << "Element " << findVal[i] << " found at " << linearSearch(&A, findVal[i], maxValues) << " in A" << endl;
            }
            findA = clock() - findA;
            cout << ("\n");

            // Print timing data, commented out of main program
            //  cout << (float)(findB) / CLOCKS_PER_SEC << endl;
            //  cout << (float)(findA) / CLOCKS_PER_SEC << endl;
            //  cout << ("\n");

            // Delete input data array
            delete[] findVal;
        }

        // sumPair
        if (temp == "A")
        {
            cin >> val;
            cout << "SumPairs: " << endl;

            // Organize input data into array
            int *sumVal = new int[val]();
            for (int i = 0; i < val; ++i)
            {
                cin >> tempVal;

                sumVal[i] = tempVal;
            }

            clock_t sumB = clock();
            // Find sumPairs of all data in Array B
            cout << "B: " << endl;
            for (int i = 0; i < val; ++i)
            {
                cout << sumVal[i] << endl;

                cout << sumPair(&B, sumVal[i], maxValues) << endl;
            }
            cout << ("\n");
            sumB = clock() - sumB;

            clock_t sumA = clock();
            // Find sumPairs of all data in Array A
            cout << "A: " << endl;
            for (int i = 0; i < val; ++i)
            {
                cout << sumVal[i] << endl;

                cout << sumPair(&A, sumVal[i], maxValues) << endl;
            }
            sumA = clock() - sumA;

            // Print timing data, commented out of main program
            //  cout << (float)(sumB) / CLOCKS_PER_SEC << endl;
            //  cout << (float)(sumA) / CLOCKS_PER_SEC << endl;
            //  cout << ("\n");

            // Delete input data array
            delete[] sumVal;
        }

        // Remove
        if (temp == "R")
        {
            // set Currentvalues to max
            currentValues = maxValues;
            cin >> val;

            cout << "Remove: " << endl;

            // Organize input data into array
            int *removeVal = new int[val]();
            for (int i = 0; i < val; ++i)
            {
                cin >> tempVal;

                removeVal[i] = tempVal;
            }

            clock_t removeB = clock();
            // Remove targets in sorted Array B
            for (int i = 0; i < val; ++i)
            {
                // if element not found in array
                if (binarySearch(&B, 0, currentValues, removeVal[i]) == -1)
                {
                    cout << "Element " << removeVal[i] << " not found in B" << endl;
                }
                // if element found in array
                else
                {
                    cout << "Removing " << removeVal[i] << " at " << binarySearch(&B, 0, currentValues, removeVal[i]) << " in B" << endl;

                    B = remove(B, binarySearch(&B, 0, currentValues, removeVal[i]), currentValues);
                }
            }
            cout << ("\n");
            removeB = clock() - removeB;

            // reset currentvalues for array A calculations
            currentValues = maxValues;

            clock_t removeA = clock();
            // Remove targets in unsorted Array A
            for (int i = 0; i < val; ++i)
            {
                // if element not found in array
                if (linearSearch(&A, removeVal[i], currentValues) == -1)
                {
                    cout << "Element " << removeVal[i] << " not found in A" << endl;
                }

                // if element found in array
                else
                {
                    cout << "Removing " << removeVal[i] << " at " << linearSearch(&A, removeVal[i], currentValues) << " in A" << endl;
                    A = remove(A, linearSearch(&A, removeVal[i], currentValues), currentValues);
                }
            }
            cout << ("\n");
            removeA = clock() - removeA;

            // Print new Array A
            cout << "After removal Array A: ";
            for (int i = 0; i < currentValues; i++)
            {
                cout << A[i] << " ";
            }
            cout << ("\n");

            // Print new Array B
            cout << "After removal Array B: ";
            for (int i = 0; i < currentValues; i++)
            {
                cout << B[i] << " ";
            }

            // Print timing data, commented out of main program
            //  cout << ("\n");
            //  cout << (float)(removeB) / CLOCKS_PER_SEC << endl;
            //  cout << (float)(removeA) / CLOCKS_PER_SEC << endl;
            //  cout << ("\n");

            // Delete input data array
            delete[] removeVal;
        }
        cout << ("\n");

        // Insert
        if (temp == "I")
        {
            // used to hold the value of currentvalues so that it may be reset back to original size to calculate array A
            int tempValues = currentValues;
            cin >> val;
            cout << "Insert: " << endl;

            // Organize input data into array
            int *insertVal = new int[val]();
            for (int i = 0; i < val; ++i)
            {
                cin >> tempVal;

                insertVal[i] = tempVal;
            }

            clock_t insertB = clock();
            // insert target elements into right positions in sorted Array B
            for (int i = 0; i < val; ++i)
            {

                cout << "Inserting " << insertVal[i] << " in B" << endl;
                B = insertSort(B, insertVal[i], currentValues);
            }
            insertB = clock() - insertB;
            cout << ("\n");

            // reset currentvalues back to original size for array a calculations
            currentValues = tempValues;

            clock_t insertA = clock();
            // insert target elements at end positions in unsorted Array A
            for (int i = 0; i < val; ++i)
            {

                cout << "Inserting " << insertVal[i] << " in A" << endl;
                A = insertUnsort(A, insertVal[i], currentValues);
            }
            insertA = clock() - insertA;
            cout << ("\n");

            // print new Array A
            cout << "After insertions Array A: ";
            for (int i = 0; i < currentValues; i++)
            {
                cout << A[i] << " ";
            }
            cout << ("\n");

            // print new Array B
            cout << "After insertions Array B: ";
            for (int i = 0; i < currentValues; i++)
            {
                cout << B[i] << " ";
            }
            cout << ("\n");

            // Print timing data, commented out of main program
            //  cout << (float)(insertB) / CLOCKS_PER_SEC << endl;
            //  cout << (float)(insertA)  / CLOCKS_PER_SEC << endl;
            //  cout << ("\n");

            // Delete input data array
            delete[] insertVal;
            // read in -1 to end the while loop
            cin >> temp;
        }
    }
    // Delete all the dynamically created array
    delete[] A;
    delete[] B;
    return 0;
}

// Copies Array A into Array B, sorts and returns
// Bubble sort function idea referenced from https://cplusplus.com/forum/beginner/12731/
int *copySortArray(int **A, int maxValues)
{
    // used to hold data
    int temp;
    // new array to return
    int *B = new int[maxValues]();

    for (int i = 0; i < maxValues; i++)
    {
        // copies all values from A into B
        B[i] = (*A)[i];
    }

    // embedded for loop to bubble sort B
    for (int k = 0; k < maxValues - 1; k++)
    {

        for (int q = 0; q < maxValues - k - 1; q++)
        {

            if (B[q] > B[q + 1])
            {

                temp = B[q];

                B[q] = B[q + 1];

                B[q + 1] = temp;
            }
        }
    }
    return B;
}

// Recursive Binary Search to find target in sorted Array B
// Idea referenced from https://www.geeksforgeeks.org/binary-search/
int binarySearch(int **B, int low, int max, int target)
{

    if (max >= low) // as to not search infinitely
    {

        int mid = low + (max - low) / 2;

        if ((*B)[mid] == target)
        {
            // if target is middle element
            return mid;
        }

        if ((*B)[mid] < target)
            // if target is located on left side of array
            return binarySearch(B, mid + 1, max, target);
        // if target is located on right side of array
        return binarySearch(B, low, mid - 1, target);
    }
    // if target not found
    return -1;
}

// Linear search to find target in unsorted Array A
int linearSearch(int **A, int target, int maxValues)
{

    for (int i = 0; i < maxValues; ++i)
    {

        if ((*A)[i] == target)
        {
            return i;
        }
    }
    // if target not found
    return -1;
}

// Returns two numbers in array that add up to the target
// Embedded for loop idea referenced from https://stackoverflow.com/questions/65033031/two-sum-of-2-numbers-target
string sumPair(int **A, int target, int maxValues)
{

    for (int i = 0; i < maxValues - 1; ++i)
    {

        for (int k = i + 1; k < maxValues; ++k)
        {

            if (((*A)[i] + (*A)[k]) == target)
            {

                string r = " = " + to_string((*A)[i]) + " + " + to_string((*A)[k]);

                return r;
            }
        }
    }

    return ""; // no pairs found
}

// takes an input array/target location and returns a new array with the removed element
int *remove(int *array, int target, int &currentValue)
{
    // array to return of size input array - 1
    int *temp = new int[currentValue - 1];

    // copies until target is found
    for (int i = 0; i < target; ++i)
    {

        temp[i] = array[i];
    }
    // shifts old array past target element while still copying into temp
    for (int i = target; i < currentValue - 1; ++i)
    {

        temp[i] = array[i + 1];
    }

    currentValue--; // deincrement size
    delete[] array; // delete old array
    return temp;    // returns new array
}

// inserts a number into the given sorted array in the correct spot and returns
int *insertSort(int *array, int target, int &currentValue)
{
    // holds location of index where target needs to go
    int hold;
    // new array to return of size input array + 1
    int *temp = new int[currentValue + 1];

    for (int i = 0; i < currentValue; ++i)
    {
        // target index found
        if (array[i] > target)
        {

            hold = i;

            break;
        }
        // index not found, copy value over and continue
        else if (array[i] < target)
        {

            temp[i] = array[i];
        }
    }

    // completes copying other values other to new array after new index is found
    for (int i = hold + 1; i < currentValue + 1; ++i)
    {

        temp[i] = array[i - 1];
    }
    // insert new number into found index
    temp[hold] = target;

    currentValue++; // increment size
    delete[] array; // delete old array
    return temp;    // returns new array
}

// inserts a number into the given unsorted array at the end and returns
int *insertUnsort(int *array, int target, int &currentValue)
{
    // new array to return of size input array + 1
    int *temp = new int[currentValue + 1];

    // copies values over until end
    for (int i = 0; i < currentValue; ++i)
    {

        temp[i] = array[i];
    }

    // adds target element at last index
    temp[currentValue] = target;

    currentValue++; // increments size
    delete[] array; // deletes old array
    return temp;    // returns new array
}