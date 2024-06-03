import java.util.ArrayList; 
import java.util.List;
import java.util.Stack;

class MovieStack {

	
	Stack<Movie> MovieStack = new Stack<>();
	

	public MovieStack() {
		
//		this.MovieStack.push(Movie a);
		
//		for(int i = 0; i < MovieStack.size(); ++i) {
//			
//			if(MovieStack.get(i).getGenre() == "Horror") {
//				Movie a = MovieStack.get(i);
//				MovieStack.add(0, a);
//				MovieStack.remove(i);
//			}
//			
//			
//			
//		}
		
	}

	public Movie pop() {
		
		if(MovieStack.isEmpty()) {
			return null;
		}
		
		int count = 0;
		
		for(int i = 0; i < MovieStack.size(); ++i) {
			
			if(MovieStack.get(i).getGenre() == "Horror") {
				
				Movie a = MovieStack.get(i);
				
				MovieStack.remove(i);
				
				return a;
				
			}
			
			
		}
		return MovieStack.get(0);
		
		
	}
	
	public void push(Movie a) {
		
		MovieStack.add(a);
		
	}
	

}
