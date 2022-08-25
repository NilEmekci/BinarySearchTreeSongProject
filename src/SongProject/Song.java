package SongProject;

public class Song {


    public String name;
    public String artist;
    public String ID;
    public String genre;
    public String year;


    public Song(String name, String artist, String ID, String genre, String year) {
        this.name = name;
        this.artist = artist;
        this.ID = ID;
        this.genre = genre;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", ID='" + ID + '\'' +
                ", genre='" + genre + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
