
package SongProject;

public class Node<Type> {

    Node left;
    Node right;

    int key;
    int ID;
    public Node(int key, int ID) {
        this.key = key;
        this.ID=  ID;




    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", ID=" + ID +
                '}';
    }
}
