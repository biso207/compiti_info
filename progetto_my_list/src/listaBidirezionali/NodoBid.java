package listaBidirezionali;

public class NodoBid<T> {

    T valore;
    NodoBid<T> succ;
    NodoBid<T> prev;

    // costruttore 1
    public NodoBid() {
        valore = null;
        succ = prev = null;
    }
    // costruttore 2
    public NodoBid( T v ) {
        super();
        valore = v;
    }

    @Override
    public String toString() {
        return valore.toString();
    }

    // getter del valore di un nodo
    public T getValore() {
        return valore;
    }
    // setter del valore di un nodo
    public void setValore(T valore) {
        this.valore = valore;
    }

    // getter del nodo seguente di un nodo
    public NodoBid<T> getSucc() {
        return succ;
    }
    // getter del nodo precedente di un nodo
    public NodoBid<T> getPrev() {
        return prev;
    }

    // setter del nodo successivo di un nodo
    public void setSucc(NodoBid<T> succ) {
        this.succ = succ;
    }
    // setter del nodo precedente di un nodo
    public void setPrev(NodoBid<T> prev) {
        this.prev = prev;
    }
}