public class Node<T> {
    protected T value;
    protected Node<T> prev, next;

    // costruttore
    public Node() {
        value=null;
        prev=null; next=null;
    }

    // costruttore 2
    public Node(T value) {
        super();
        this.value = value;
    }

    // GETTER e SETTER //
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
    
    // metodo toString
    public String toString() {
        return value.toString();
    }
}
