package linkedlistik.list;

public class Node<T> {

    T value;

    Node<T> next;
    Node<T> prev;

    public Node(Node<T> prev, T value, Node<T> next) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}
