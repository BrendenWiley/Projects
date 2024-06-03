import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
	
	/**
	 * Search a sorted list of strings using binary search. Return a list of
	 * the indices of the strings checked during the search in the order they
	 * are checked. If the target string is not found, append -1 to the end of
	 * the list. Otherwise, the last element is the index of the target.
	 *
	 * @param strings  the list to be searched
	 * @param target  the string to be searched for
	 * @param fromIdx  the index of the first string in the range of strings to
	 *                 be searched (inclusive)
	 * @param toIdx  the index of the last string in the range of strings to be
	 *               searched (inclusive)
	 * @return a list of the indices of the strings checked during the search.
	 *         If the target is not in the list, the last element is -1.
	 */
	
	public static List<Integer> binarySearch(List<String> strings,
			String target, int fromIdx, int toIdx) {
		
		List<Integer> indices = new ArrayList<Integer>();
		
		int middle = (fromIdx + toIdx) / 2;
		
		if(strings.get(middle).equals(target)) {
			
			indices.add(middle);
			
			return indices;
			
		}
		
		
		else if(fromIdx > toIdx) {
			toIdx = -1;
			indices.add(toIdx);
			return indices;
		}
			
			
		
			if(strings.get(middle).compareTo(target) < 0){
				
				fromIdx = middle + 1;
				
				indices.add(middle);
				
				indices.addAll(binarySearch(strings, target, fromIdx, toIdx));
				
			}
		
			if(strings.get(middle).compareTo(target) > 0) {
				
				toIdx = middle - 1 ;
				
				indices.add(middle);

				indices.addAll(binarySearch(strings, target, fromIdx, toIdx));


			}
		
		return indices;
		
		}
	}


	
