import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.io.IOException;

public class Playlist {

	private ArrayList<Song> songs;

	public Playlist() {
		songs = new ArrayList<Song>();
	}

	public Playlist(String filename) {

		this();
		addSongs(filename);

	}


	public int getNumSongs() {
		return songs.size();
	}

	public Song getSong(int index) {
		if (index < 0 || index >= getNumSongs()) {
			return null;
		}
		return songs.get(index);
	}

	public Song[] getSongs() {
		return songs.toArray(new Song[0]);
	}

	public boolean addSong(Song song) {
		return addSong(getNumSongs(), song);
	}

	public boolean addSong(int index, Song song) {

		if(song == null || index < 0 || index > getNumSongs()) {

			return false;	

		}

		else {
			songs.add(index, song);

			return true;
		}	

	}

	public int addSongs(Playlist playlist) {

		if(playlist == null) {
			return 0;
		}

		Song[] newSongs = playlist.getSongs();

		for(int i = 0; i <= playlist.getNumSongs() - 1; ++i) {

			if(i != newSongs.length) {

				songs.add(newSongs[i]);

			}

			else {
				int addedSongs = newSongs.length;

				return addedSongs;
			}


		}
		int addedSongs = playlist.getNumSongs();

		return addedSongs;
	}


	public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}

	public Song removeSong(int index) {
		// TODO: Update the Lab 3 method.

		if(getSong(index) == null || index >=  getNumSongs() 
				|| index < 0) {

			return null;

		}

		Song removed = songs.get(index);
		songs.remove(index);

		return removed;

	}

	public int addSongs(String filename) {

		int numSongs = 0;
		try {BufferedReader reader = 
		new BufferedReader(new FileReader(filename));
		
		String line = reader.readLine();
		while(line != null) {
			
			Song temp = new Song(line);
			
			songs.add(temp);
			++numSongs;
			
			line = reader.readLine();
		}
		reader.close();
		return numSongs;

		}
		catch (IOException e){
			
			e.printStackTrace();
			return 0;
		}
	
	}
	
	public void saveSongs(String filename) throws IOException {
		
		Song temp;
		String song;
		BufferedWriter writer = new BufferedWriter(new
				FileWriter(filename));
		
		for(int i = 0; i < songs.size(); ++ i) {
			
			temp = songs.get(i); 
				
		    song = temp.toString();
			
			writer.write(song);
			
			writer.newLine();
		}
		writer.close();
		
		
	}

	public String toString() {
		
	    String DELIMITER = System.lineSeparator();
		StringJoiner sj = new StringJoiner(DELIMITER);
		String song = "";

		for(int i = 0; i < songs.size(); ++i) {
			
		
			
		Song temp = songs.get(i);
		
		String string = temp.toString();
		
		sj.add(string);
		
		
		

		song = sj.toString();
		}
		
		return song;

	}

	public int[] getTotalTime() {
		int hours = 0;
		int minutes = 0;
		int seconds = 0;
		int[] holder;
		int add = 0;
		
		for(int i = 0; i < songs.size(); ++i) {
			
			Song temp = songs.get(i);
			
			if((temp.getTime()).length == 1) {
				
				holder = temp.getTime();
				seconds = seconds + holder[0];
				
			}
			if((temp.getTime()).length == 2) {
				
				holder = temp.getTime();
				
				seconds = seconds + holder[0];
				minutes = minutes + holder[1];
				
			}
			if((temp.getTime()).length == 3) {
				
				holder = temp.getTime();
				
				seconds = seconds + holder[0];
				minutes = minutes + holder[1];
				hours = hours + holder[2];
			}
			
		}
		//converting seconds to minutes 
		
		add = seconds/60;
		
		seconds = seconds - (add*60);
		
		minutes = minutes + add;
		
		//minutes to hours
		add = minutes/60;
		
		minutes = minutes - (add*60);
		
		hours = hours + add;
	
		if(hours > 0) {
		int[] total = new int[3];
		total[2] = hours;
		total[1] = minutes;
		total[0] = seconds;
		return total;
		}
		if(minutes > 0) {
		int[] total = new int[2];
		total[1] = minutes;
		total[0] = seconds;
		return total;	
		}
		else {
		int[] total = new int[1];
		total[0] = seconds;
		return total;
		}

	}

}
