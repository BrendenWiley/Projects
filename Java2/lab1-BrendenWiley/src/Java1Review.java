
import java.util.Arrays;
/**
 * 
 * Implement each of the 10 methods tested in JUnitTests.java. Study the tests
 * to determine how the methods should work.
 */
public class Java1Review {
	
	public static void main(String[] args) {
		// If you want to write your own tests, do so here. (Do not modify
		// JUnitTests.java.) To run this method in Eclipse, right-click
		// Java1Review.java in the Package Explorer and select "Run As" >	
		// "Java Application" from the context menu.	
		
	}
 	public static String main(String a) {

     a = ("Overloaded main method was passed \"" + a + 
		"\".");
     
	return a;
		
	}
 	public static double divide(double a, double b) {	
		return a/b; 
	}
	
	public static int divide(int a, int b) {	
		return a/b; 
	}
	
	public static boolean isDivisibleBy7(int a) {
		
		if(a%7==0) {
			return true;
		}
		else return false;
	}
	
	public static int findMin(int a, int b, int c) {
		if(a <= b && a <= c) {
			return a;
		}
		else if(b <= a && b <= c) {
			return b;
		}
		else return c;
		}
	
	public static int findMin(int[] a) {
		int min = 0;
	
		Arrays.sort(a);
		
		min = a[0];
		
		return min;
		}
			
	public static double average(int[] a) {
		double total = 0;
		double average = 0;
		for(int num = 0; num < a.length; ++num) {		
			total = total + a[num];		
		}
		average = (total)/(a.length);
		return average;	
	}
	
	public static void toLowerCase(String[] a) {
		
		for(int i = 0; i < a.length; ++i) {
			
			a[i] = a[i].toLowerCase();
			
		}	
	
	}
	public static String[] toLowerCaseCopy(String[] a) {
		String[] copy = new String[a.length];
		
		for(int num = 0; num < a.length; num++) {
			copy[num] = a[num].toLowerCase();
		}
		return copy;
	}
	
	public static int[] removeDuplicates(int[] a) {
int b = 0;
		for(int i = 0; i < a.length - 1; ++i) {
			for(int n = i + 1; n < a.length; ++n) {
				if(a[i] == a[n] && a[i] != 0) { 
				
					a[n] = 0;
					b = 1;
				}
				if(b == 1 && n == a.length-1) {
				a[i] = 0;
				b = 0;
				}
			}		
		}
	return a;
}
}