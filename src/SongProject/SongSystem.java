package SongProject;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class SongSystem {

    BinarySearchTree songNameTree;
    BinarySearchTree artistTree;
    BinarySearchTree IDTree;
    BinarySearchTree genreTree;


    MyHashTable<Song> songHashTable;
    MyHashTable<Song> artistHashTable;
    MyHashTable<Song> genreHashTable;

    public SongSystem() throws IOException {
        this.songNameTree = new BinarySearchTree();
        this.artistTree = new BinarySearchTree();
        this.IDTree = new BinarySearchTree();
        this.genreTree = new BinarySearchTree();

        artistHashTable = new MyHashTable<>(51);
        songHashTable = new MyHashTable<>(51);
        genreHashTable = new MyHashTable<>(51);
    }


    public int counter() throws IOException {

        FileReader fileReader = new FileReader("songs.txt");

        BufferedReader bf = new BufferedReader(fileReader);


        int counter = 0;
        while (bf.readLine() != null) {
            counter++;
        }
        bf.close();
        fileReader.close();

        return counter;
    }


    public void reader() throws IOException {


        FileReader fileReader = new FileReader("songs.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);


        for (int i = 0; i < counter(); i++) {

            String[] fullText = bufferedReader.readLine().split(";");


            songHashTable.insert(new Song(fullText[0], fullText[1], fullText[2], fullText[3], fullText[4]));

            artistHashTable.insertForArtist(new Song(fullText[0], fullText[1], fullText[2], fullText[3], fullText[4]));
            genreHashTable.insertForGenre(new Song(fullText[0], fullText[1], fullText[2], fullText[3], fullText[4]));

            int keyName = hashCodeMy(fullText[0]);
            int keyArtist = hashCodeMy(fullText[1]);
            int keyGenre = hashCodeMy(fullText[3]);

            int IDValue = Integer.parseInt(fullText[2]);

            songNameTree.insert(keyName, IDValue);
            artistTree.insert(keyArtist, IDValue);
            genreTree.insert(keyGenre, IDValue);


            IDTree.insertID(IDValue);

        }

        bufferedReader.close();
        fileReader.close();

    }

    public String displayAllTheSongs() {

        String s = songHashTable.displayAll();

        int size = songNameTree.size;

        return s+"\nThere are "+size+" songs in the system...";


    }

    public Song findByName(String songName) {

        int key = hashCodeMy(songName);

        Node tmp = songNameTree.search(key);

        if (tmp == null) {
            return null;
        }


        int thListElementInHashed = songHashTable.hashID(tmp.ID);

        Song song = songHashTable.table[thListElementInHashed].findById(tmp.ID);

        return song;
    }

    public Song findByArtist(String artistName) {


        int key = semiColumnControl(artistName);

        Node tmp = artistTree.search(key);

        if (tmp == null) {
            return null;
        }


        int thListElementInHashed = songHashTable.hashID(tmp.ID);

        Song song = songHashTable.table[thListElementInHashed].findById(tmp.ID);

        return song;
    }

    public Song findByIDTree(String ID) {

        Integer Id = Integer.parseInt(ID);
        Node m = IDTree.search(Id);

        if (m == null) {
            return null;
        }

        int thListElementInHashed = songHashTable.hashID(m.ID);

        Song song = songHashTable.table[thListElementInHashed].findById(m.ID);
        return song;
    }

    public ArrayList<Song> findByArtistAll(String artistName) throws Exception {
        int value = semiColumnControl(artistName);
        Node m = artistTree.search(value);


        if (m == null) {
            throw new Exception("There is no artist in the system Exception");
        }
        int thListElementInHashed = artistHashTable.hashString(artistName);
        ArrayList<Song> songsList = artistHashTable.table[thListElementInHashed].findByArtist(m.key);


        return songsList;
    }

    public ArrayList<Song> findByGenreAll(String genreName) throws Exception {
        int value = hashCodeMy(genreName);
        Node m = genreTree.search(value);

        if (m == null) {
            throw new Exception("There is no genre in the system Exception");
        }
        int thListElementInHashed = genreHashTable.hashString(genreName);
        ArrayList<Song> songsList = genreHashTable.table[thListElementInHashed].findByGenre(m.key);


        return songsList;
    }

    public void findByIDInRange(String IDInRange) throws Exception {

        String[] stringArray = IDInRange.split("-");
        Integer lowerBound = Integer.parseInt(stringArray[0]);
        Integer upperBound = Integer.parseInt(stringArray[1]);

        if (lowerBound > upperBound) {
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        Song[] songs = new Song[upperBound - lowerBound];


        int counter = 0;
        for (Integer i = lowerBound; i < upperBound; i++) {

            int thListElementInHashed = songHashTable.hashID(i);
            songs[counter] = songHashTable.table[thListElementInHashed].findById(i);
            counter++;
        }


        for (Integer i = 0; i < songs.length; i++) {
            if (songs[i] == null) {
                continue;
            } else {
                System.out.print(songs[i]);
            }
        }


    }


    public void insertSong(Song newSong) throws Exception {
        String[] songArray;
        int newID;
        if (newSong.ID.contains(";")) {
            songArray = newSong.ID.split(";");
            newID = Integer.parseInt(songArray[0]);
        } else {
            newID = Integer.parseInt(newSong.ID);
        }


        Node<Song> tmp = IDTree.search(newID);
        if (tmp != null) {
            throw new Exception("There Is a Song with same ID Exception");
        }

        songHashTable.insert(newSong);
        artistHashTable.insertForArtist(newSong);
        genreHashTable.insertForGenre(newSong);

        songNameTree.insert(hashCodeMy(newSong.name), newID);
        genreTree.insert(hashCodeMy(newSong.genre), newID);
        artistTree.insert(hashCodeMy(newSong.artist), newID);




        IDTree.insertID(newID);
    }


    public Song deleteFromSystem(int ID) {

        Node controlNode = IDTree.search(ID);

        if(controlNode == (null)){
            System.out.println("The ID is not in the song system!!");
            return null;

        }

        IDTree.delete(ID);



        Song song = songHashTable.deleteSongTable(ID);

        int songNameID = hashCodeMy(song.name);
        songNameTree.delete(songNameID);
        int artistNameID = hashCodeMy(song.artist);
        artistTree.delete(artistNameID);


        artistHashTable.deleteSongTableForArtist(song.artist);
        genreHashTable.deleteSongTableForGenre(song);


        if (song.equals(null)) {
            System.out.println("There is no song with releated ID");
            return null;
        }
        return song;

    }


    public int hashCodeMy(String hashCode) {

        int hash = 25;
        hash += 25 * hash + hashCode.length() % 25;
        hash += 25 * hash + hashCode.length() % 25;
        hash += 25 * hash + hashCode.hashCode() % 25;

        return hash;

    }


    public int semiColumnControl(String value) {
        String[] songArray;
        int newID;
        if (value.contains(";")) {
            songArray = value.split(";");
            newID = hashCodeMy(songArray[0]);
        } else {
            newID = hashCodeMy(value);
        }
        return newID;
    }


}