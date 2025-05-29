package listaBidirezionali;

public class ListaBid<T> {

    protected NodoBid<T> first; // nodo iniziale
    protected NodoBid<T> last; // nodo finale

    // costruttore
    public ListaBid() {
        first = null;
    }

    // metodo per aggiungere un nodo alla lista passando un nodo
    public void add(NodoBid<T> newnode) {
        if (first==null) {
            first = newnode;
        } else {
            NodoBid<T> temp = first;
            while (temp.getSucc()!=null) temp=temp.getSucc();
            temp.setSucc(newnode);
        }
    }

    // metodo per aggiungere un nodo alla lista passando il valore di un nodo da creare
    public void add(T newval) {
        NodoBid<T> newNode = new NodoBid<>(newval);
        if (first==null) first = newNode;
        else first.setPrev(newNode);
    }

    // metodo per aggiungere un nodo in testa alla lista
    public void addHead( T newVal) {
        NodoBid<T> newNodo = new NodoBid<>(newVal);
        if (first==null) {
            first = newNodo;
            last = newNodo;
        }
        else {
            first.setPrev(newNodo);
            newNodo.setSucc(newNodo);
            first = newNodo;
        }
    }
    
    // metodo per recuperare la lunghezza della lista
    public int getLength() {
        int l=0;
        NodoBid<T> temp = first;
        while (temp.getSucc()!=null) {
            l++;
            temp = temp.getSucc();
        }
        return l;
    }

    @Override
    public String toString() {
        String result = "Lista: ";
        NodoBid<T> tmp = first;
        while (tmp!=null) {
            result += " --> " + tmp;
            tmp = tmp.getSucc();      
        }
        return result;
    }
}