public class Playlist {

	//instance variables
	private Song[] songs;
	private int numSongs;
	private final static int MIN_CAPACITY = 3;

	public Playlist() {

		songs = new Song[MIN_CAPACITY];

	}

	public Playlist(int capacity) {

		if(capacity > MIN_CAPACITY) {
			songs = new Song[capacity];

		}

		else {

			songs = new Song[MIN_CAPACITY];

		}

	}

	public int getCapacity() {

		int capacity = songs.length;

		return capacity;

	}

	public int getNumSongs() {

		return numSongs;
	}

	public Song getSong(int index) {

		if(index < 0 || index >= numSongs) {
			return null;
		}

		else {

			return songs[index];

		}

	}

	public Song[] getSongs() {

		Song[] copy = new Song[numSongs];


		for(int i = 0; i < numSongs; ++i) {

			copy[i] = songs[i];


		}

		return copy;

	}

	public boolean addSong(Song song) {

		if(addSong(numSongs, song)) {
			return true;		
		}
		else {
			return false;
		}



	}

	public boolean addSong(int index, Song song) {

		if(song == null || numSongs == songs.length || index < 0 || index > numSongs) {

			return false;	

		}
		else {
			for(int i = numSongs; i > index; i--) {

				songs[i] = songs[i-1];

			}
			songs[index] = song;
			numSongs++;
			return true;
		}	

	}

	public int addSongs(Playlist playlist) {

		int x = 0;
		int counter = 0;
		
		if(playlist == null || numSongs == songs.length) {
			return 0;
		}

		for(int i = numSongs; i < songs.length; ++i) {
			
			if(playlist.getSong(x) != null) {
				
			songs[i] = playlist.getSong(x);
			
			++counter;
			
			++x;
			
			}
			
		 
			else {
				
				numSongs = numSongs + counter;
				
				return counter;
			}
		
		}
		
		numSongs = numSongs + counter;
		
		return counter;
		
	}

	public Song removeSong() {

		if(numSongs == 0) {
			return null;
		}

		Song temp = songs[numSongs - 1];

		for(int i = numSongs - 1; i < songs.length - 1; i++) {

			songs[i] = songs[i+1];

		}

		numSongs--;
		return temp;
	}	


	public Song removeSong(int index) {


		if(getSong(index) == null || index >= songs.length || index < 0) {

			return null;

		}

		Song temp = songs[index];



		for(int i = index; i < songs.length - 1; i++) {

			songs[i] = songs[i+1];

		}

		numSongs--;
		return temp;
	}	


}
