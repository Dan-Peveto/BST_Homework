import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BST <T extends Comparable<T>> {
    // fields
    Node<T> root; // create root

    // methods
    // Empty Method
    public boolean isEmpty() {
        return root == null; //if root is null BST is empty
    }

    // find method
    public Node<T> find(T value) {
        Node<T> finger = root; // point finger at root
        while(finger != null) { // create loop
            if(finger.value == value) { // if we found value return node
                return finger;
            } else if(value.compareTo(finger.value) < 0) {// move left if less than
                finger=finger.left;
            } else {
                finger = finger.right;
            }
        }
        return null; //if not found return null
    }
    // insert method
    public Node<T> insert(T value) {
        if (isEmpty()) { // if empty node is root
            return root = new Node<T>(value);
        } else {
            Node<T> finger = root;
            while(true) { //turn into infinite loop // we will break below with return statment
                if(value.compareTo(finger.value) <= 0) { // compare values with compareTo method
                    if(finger.left != null) { // when less than move left if possible
                        finger = finger.left; 
                    } else {
                        return finger.left =new Node<T>(value); // if node is empty we found the new node
                    }
                } else {
                    if(finger.right != null) { // same as above but move right
                        finger = finger.right;
                    } else {
                        return finger.right = new Node<T>(value); // return statement breaks the loop
                    }
                }
            }
        }
    }
    // Traverse Method
    //In Order Method Left Node Right
    public void inOrder() { // In order Left Node Right
        inOrderHelper(root); // call helper function
    }
    // "Helper function"
    public void inOrderHelper(Node<T> finger) {
        if(finger != null) {
            inOrderHelper(finger.left); // left
            System.out.println(finger.value + ", "); //node
            inOrderHelper(finger.right); // right
        }
    }
    // Pre-order Method Node Left Right
    public void preOrder() {
        preOrderHelper(root); // start  helper on the root
    }
    public void preOrderHelper(Node<T> finger) {
        if(finger != null) {
            System.out.println(finger.value + ", "); // display
            preOrderHelper(finger.left); // move left
            preOrderHelper(finger.right); // move right
        }
    }
    // Post-order Method Left Right Node
    public void postOrder() {
        postOrderHelper(root); // start helper method at root
    }

    public void postOrderHelper(Node<T> finger) {
        if(finger != null) {
            postOrderHelper(finger.left); // move left
            postOrderHelper(finger.right); // move right
            System.out.println(finger.value + ", "); //display node
        }
    }
    // max method
    public T max() throws Exception {
        if(isEmpty()) {
            throw new Exception("Tree is empty");
        }
        Node<T> finger = root; // create finger
        while(finger.right != null) {
            finger = finger.right; // move right
        }
        return finger.value; // return most right wich is always largest in BST
    }
    // min
    public T min() throws Exception {
        if (isEmpty()) {
            throw new Exception("Tree is empty");
        } 
        Node<T> finger = root; //create finger
        while (finger.left != null) {
            finger = finger.left; // move left
        }
        return finger.value; // return min value
    }

    // count nodes
    public int countNodes() {
        return countNodesHelper(root); // call helper method with root
    }
    public int countNodesHelper(Node<T> finger) {
        if(finger == null) {
            return 0; // if empty there are no nodes
        }
        return countNodesHelper(finger.left) + countNodesHelper(finger.right) + 1; //count left + count right + root
    }

    // count leaves
    public int countLeaf() {
        return countLeafHelper(root); // calll helper method with root passed
    }

    public int countLeafHelper(Node<T> finger) {
        if(finger == null) {
            return 0; //if empty return 0
        }
        if (finger.left == null && finger.right == null) {
            return 1; // if leaf return 1
        }
        return countLeafHelper(finger.left) + countLeafHelper(finger.right); // call the method left and right
    }
    // display leaf
    public void displayLeafs() {
        displayLeafHelper(root); // call helper method with root passed
    }
    public void displayLeafHelper(Node<T> finger) {
        if(finger == null) {
            ; //if the finger is not there do nothing
        } else if (finger.left == null && finger.right == null) {
            System.out.println(finger.value + ", "); //if leave display leaf
        } else {
            displayLeafHelper(finger.left); // move left
            displayLeafHelper(finger.right); // move right
        }
    }
    // height mothod
    public int height() {
        return heightHelper(root); // call helper method and pass root
    }
    public int heightHelper(Node<T> finger) {
        if(finger==null) {
            return -1;
        }
        return Math.max(heightHelper(finger.left), heightHelper(finger.right) + 1);
    }

    // Breadth First Search
    public void BFS(){
        // create que
        Queue<Node<T>> que = new LinkedList<Node<T>>();
        if(!isEmpty()){// if not empty tree
        que.add(root);// add root to que
        }
        while(!que.isEmpty()) {// while que is not empty
            Node<T> node = que.remove(); //deque
            System.out.println(node);// print value
            if(node.left!=null) {// if left not empty
                que.add(node.left); // add left
            } 
            if(node.right!=null) {// if rigth not empty
                //add left
                que.add(node.right);
            }
        }    
    }

    // List of Depths  Method
    public ArrayList<LinkedList<Node<T>>> listOfDepths() {
        ArrayList<LinkedList<Node<T>>> listOfList = new ArrayList<LinkedList<Node<T>>>(); // create ArrayList for return
        // if isEmpty()
        if(isEmpty()) {
            // return empty list
            return listOfList;
        }
        LinkedList<Node<T>> levelList = new LinkedList<Node<T>>();// create list for each level
        levelList.add(root);// add root to list
        while(levelList.size() > 0) {// Start Loop that stops if  levelList becomes empty
            listOfList.add(levelList);// add levelList to listOfList
            LinkedList<Node<T>> parentsList = levelList;// keep track of parent nodes
            levelList = new LinkedList<Node<T>>(); // clear levellist so child nodes can be added
            for (Node<T> parent : parentsList) { // for each element in list
                if(parent.left !=null) { // if there is something left
                    levelList.add(parent.left); //add left
                }
                if(parent.right != null) { // if there is something right
                    levelList.add(parent.right); // add right
                }
            }
        }
        return listOfList;
    }
    public void printListOfDepths() {
        for(LinkedList<Node<T>> list : listOfDepths()) {
            for(Node<T> node : list) {
                System.out.print(node.value + ", ");
            }
        }
    }
    public int countParentNodes() {
        return countParentNodesHelper(root);//call helper function with root passed
    }
    public int countParentNodesHelper(Node<T> finger) {
    
        if(finger == null || (finger.left == null && finger.right == null)) {
            return 0;
        }
        return countParentNodesHelper(finger.left) + countParentNodesHelper(finger.right) + 1;
    }
    
    public boolean isChild(Node<T> node1, Node<T> node2) {
        return isChildHelper(node1, node2) || isChildHelper(node2, node1);
    }

    public boolean isChildHelper(Node<T> parent, Node<T> child) {
        while(parent != null) {
            if(parent == child) {
                return true;
            } else if (child.value.compareTo(parent.value) <= 0){
                parent = parent.left;
            } else {
                parent = parent.right;
            }
        }
        return false;
        }
    }
        
        
        


