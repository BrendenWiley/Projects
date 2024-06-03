public class Movie {
	
	private final String name;
	private final String genre;
	
	Movie(String n, String g) {
		this.name = n;
		this.genre = g;		
	}
	
	public String getName() {		
		return name;
	}
	
	public String getGenre() {		
		return genre;
	}
	
	public String toString() {
		return name;
	}
}