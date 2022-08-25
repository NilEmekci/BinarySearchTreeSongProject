package SongProject;



public class MyHashTable<Item> {

    int M;
    HashLinkedList<Item>[] table;


    public MyHashTable(int M) {
        table = new HashLinkedList[M];
        for (int i = 0; i < M; i++) {
            table[i] = new HashLinkedList<Item>();
        }
        this.M = M;

    }

    public int hashString(String t) {
        return (t.hashCode() & 0x7ffffff % M);
    }


    public int hashID(Integer t) {
        return (t.hashCode() % M);
    }

    public void insertForArtist(Item t) {


        Song s = (Song) t;
        int i = hashString(s.artist);



        if (!table[i].contains(t)) {
            table[i].insertFirst(t);
        }

    }

    public void insertForGenre(Item t) {
        Song s = (Song) t;
        int i = hashString(s.genre);


        if (!table[i].contains(t)) {
            table[i].insertFirst(t);
        }
    }


    public Song deleteSongTable(int ID) {

        int hashedID = hashID(ID);

        Song song = table[hashedID].deleteKthNode(ID);

        return song;

    }

    public Song deleteSongTableForArtist(String ID) {

        int hashedID = hashString(ID);

        Song song = table[hashedID].deleteKthNode(hashedID);

        return song;

    }

    public Song deleteSongTableForGenre(Song song) {

        int hashedID = hashString(song.genre);

        Song songD = table[hashedID].deleteKthNode(Integer.parseInt(song.ID));

        return songD;

    }


    public void insert(Item t) {
        int i = 0;
        if (t instanceof Song) {


            String[] song;
            int newID;
            Song s = (Song) t;
            if (s.ID.contains(";")) {
                song = s.ID.split(";");
                newID = Integer.parseInt(song[0]);
                i = hashID(newID);
            } else {
                int stringParse = Integer.parseInt(s.ID);
                i = hashID(stringParse);

            }
        }



        if (!table[i].contains(t))
            table[i].insertFirst(t);
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < M; i++) {
            if (table[i] != null) {
                s += i + "th " + table[i].toString() + "\n";
            }
        }
        return s;
    }

    public String displayAll() {
        String s = "";
        for (int i = 0; i < M; i++) {
            if (table[i] != null) {
                s += table[i].displayAll();
            }
        }
        return s;

    }
}