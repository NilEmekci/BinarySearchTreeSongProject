package SongProject;
import java.util.ArrayList;

public class HashLinkedList<Item> {

    NodeLinkedList<Item> first, last;
    int size = 0;

    public HashLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean contains(Item t) {
        NodeLinkedList tmp = first;
        while (tmp != null) {
            if (tmp.equals(t))
                return true;
            tmp = tmp.next;
        }
        return false;
    }


    // prints the list
    public String toString() {
        NodeLinkedList tmp = first;
        String str = "List with " + size + " elements: ";

        while (tmp != null) {
            str += tmp.value.toString() + "->";
            tmp = tmp.next;
        }
        str += ".";
        return str;
    }

    public String displayAll() {
        NodeLinkedList tmp = first;
        String str = "";

        while (tmp != null) {
            str += tmp.value.toString() + "\n";
            tmp = tmp.next;
        }

        return str;
    }


    public void insertFirst(Item x) {
        NodeLinkedList newNode = new NodeLinkedList<Item>(x);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first = newNode;
        }
        size++;
    }


    public void insertAfter(NodeLinkedList<Item> p, Item x) {
        NodeLinkedList<Item> newNode = new NodeLinkedList<Item>(x);
        if (p == null || size == 0) {

            return;
        }
        if (p == last) {
            insertLast(x);
            return;
        }
        newNode.next = p.next;
        p.next = newNode;
        size++;
    }

    public void insertLast(Item x) {
        NodeLinkedList newNode = new NodeLinkedList(x);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Song findById(int ID) {

        NodeLinkedList<Song> tmp = (NodeLinkedList<Song>) first;


        if (tmp == null) {
            return null;
        }


        while (tmp != null) {

            Integer idRepresentation = Integer.parseInt(tmp.value.ID);
            if (idRepresentation.equals(ID)) {

                return tmp.value;
            }
            tmp = tmp.next;
        }


        return null;

    }

    public int hashString(String hashCode) {
        int hash = 25;
        hash += 25 * hash + hashCode.length() % 25;
        hash += 25 * hash + hashCode.length() % 25;
        hash += 25 * hash + hashCode.hashCode() % 25;

        return hash;
    }

    public ArrayList<Song> findByArtist(int key) {

        NodeLinkedList<Song> tmp = (NodeLinkedList<Song>) first;

        ArrayList<Song> songs = new ArrayList<>();

        if (tmp == null) {
            return null;
        }


        while (tmp != null) {

            Integer hashRepresentationOfArtist = hashString(tmp.value.artist);

            if (hashRepresentationOfArtist.equals(key)) {

                songs.add(tmp.value);

            }
            tmp = tmp.next;
        }


        return songs;

    }

    public ArrayList<Song> findByGenre(int key) {

        NodeLinkedList<Song> tmp = (NodeLinkedList<Song>) first;

        ArrayList<Song> songs = new ArrayList<>();

        if (tmp == null) {
            return null;
        }


        while (tmp != null) {

            Integer hashRepresentationOfGenre = hashString(tmp.value.genre);

            if (hashRepresentationOfGenre.equals(key)) {

                songs.add(tmp.value);

            }
            tmp = tmp.next;
        }


        return songs;

    }

    public Song deleteKthNode(int ID) {


        NodeLinkedList<Song> tmp = (NodeLinkedList<Song>) first;

        int counter = 0;

        if (tmp == null) {
            return null;
        }


        if(Integer.parseInt(tmp.value.ID)  == ID){
            return removeFirst();
        }
        Song removedNode = null;

        while (tmp.next != null && counter != 1) {
            NodeLinkedList<Song> tmpNext = (NodeLinkedList<Song>) tmp.next;
            if (Integer.parseInt(tmpNext.value.ID) == ID && tmpNext.next != null) {
                removedNode = tmpNext.value;
                tmp.next = tmp.next.next;
                counter++;
            }  else {
                tmp = tmp.next;
            }

        }

        size--;

        return removedNode;
    }


    public Song removeLast() {
        NodeLinkedList tmp = first;
        while (tmp.next.next != null) {
            tmp = tmp.next;
        }
        Song removedNode = (Song) tmp.next.value;
        tmp.next = null;
        last = tmp;
        size--;
        return removedNode;
    }

    public Song removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Song tmp = (Song) first.value;
        first = first.next;
        size--;
        return tmp;
    }
}