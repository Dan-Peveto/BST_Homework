public class Node <T> {
    public T value; // create a value variable
    public Node<T> left, right; // create left and right nodes

    public Node(T value) {
        this.value = value; // create constructor
    }
}
