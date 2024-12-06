/*
Luca Bisognin - 5/12/2024
Classe Nodo del progetto MyList
*/

public class Nodo<T> {
    // attributi
    private T v;
    private Nodo<T> next;
    
    // costruttore
    public Nodo(T v){
        this.v = v;
        this.next = null;
    }

    // getter v
    public T getV() {
        return v;
    }

    // getter next
    public Nodo<T> getNext() {
        return next;
    }

    // setter v
    public void setV(T v) {
        this.v = v;
    }

    // setter next
    public void setNext(Nodo<T> n) {
        this.next = n;
    }

    public String toString(){
        return "\n" + v.toString();
    }
}
