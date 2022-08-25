package SongProject;

import java.util.NoSuchElementException;

public class BinarySearchTree {

    Node root;

    int size;

    public BinarySearchTree() {
        root = null;
        size = 0;

    }


    public void insert(int key, int ID) {

        if (root == null) {
            root = new Node(key, ID);
            size++;
        } else
            addNode(key, ID, root);


    }

    private void addNode(int key, int ID, Node root) {


        if (root == null) {
            root = new Node(key, ID);
        } else {
            Node tmp = root;
            Node parent = root;
            while (tmp != null) {
                parent = tmp;
                if (key < tmp.key) {
                    tmp = tmp.left;
                } else if (key > tmp.key) {
                    tmp = tmp.right;
                } else {
                    parent.ID = ID;
                    break;
                }
            }
            Node n = new Node(key, ID);
            if (key < parent.key) {
                parent.left = n;
            } else if (key > parent.key) {
                parent.right = n;
            }

        }

        size++;
    }

    public void insertID(int ID) {

        if (root == null) {
            root = new Node(ID, ID);
        } else
            addNodeID(ID, root);


    }

    private void addNodeID(int ID, Node root) {


        if (root == null) {
            root = new Node(ID, ID);
        } else {
            Node tmp = root;
            Node parent = root;
            while (tmp != null) {
                parent = tmp;
                if (ID < tmp.key) {
                    tmp = tmp.left;
                } else if (ID > tmp.key) {
                    tmp = tmp.right;
                } else {
                    parent.ID = ID;
                    break;
                }
            }
            Node n = new Node(ID, ID);
            if (ID < parent.key) {
                parent.left = n;
            } else if (ID > parent.key) {
                parent.right = n;
            }

        }

    }


    public Node search(int key) {
        return searchRecursive(this.root, key);
    }

    public Node searchRecursive(Node focus, int key) {


        if (focus == null) {
            return null;
        }
        if (focus.key == key) {
            return focus;
        } else if (focus.key > key) {
            return searchRecursive(focus.left, key);
        } else {
            return searchRecursive(focus.right, key);
        }
    }


    public void preTraversal() {
        if (root != null) {
            preOrderTraversal(root);
        }
    }

    private void preOrderTraversal(Node node) {

        System.out.print(node + "  ->  ");

        if (node.left != null) {
            preOrderTraversal(node.left);
        }

        if (node.right != null) {
            preOrderTraversal(node.right);
        }
    }


    private Node delete(Node focus, int key) {
        if (focus == null) {
            return null;
        }
        if (key < focus.key) {
            focus.left = delete(focus.left, key);
        } else if (key > focus.key) {
            focus.right = delete(focus.right, key);
        } else {
            size--;
            if (focus.right == null) {
                return focus.left;
            }
            if (focus.left == null) {
                return focus.right;
            }
            Node t = focus;
            focus = min(t.right);
            focus.right = deleteMin(t.right);
            focus.left = t.left;

        }

        return focus;
    }

    public void delete(int key) {

        if (root != null) {
            root = delete(root, key);
        }

    }

    private Node min(Node x) {
        if (root == null) {
            throw new NoSuchElementException("BST is empty!");
        }
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    private Node deleteMin(Node x) {
        if (root == null) {
            throw new NoSuchElementException("BST is empty!");
        }
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
    }


}