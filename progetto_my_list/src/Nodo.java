public class Nodo<T> {

    T valore;
    Nodo<T> succ;

    public Nodo() {
        valore = null;
        succ = null;
    }

    public Nodo( T v ) {
        super();
        valore = v;
    }

    @Override
    public String toString() {
        return valore.toString();
    }

    public T getValore() {
        return valore;
    }

    public void setValore(T valore) {
        this.valore = valore;
    }

    public Nodo<T> getSucc() {
        return succ;
    }

    public void setSucc(Nodo<T> succ) {
        this.succ = succ;
    }
}