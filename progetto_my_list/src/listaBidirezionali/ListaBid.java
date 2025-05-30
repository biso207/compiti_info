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
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.setSucc(newNode);
            newNode.setPrev(last);
            last = newNode;
        }
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

    // metodo per aggiungere un nodo in coda
    public void addTail(T val) {
        NodoBid<T> newNode = new NodoBid<>(val);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.setSucc(newNode);
            newNode.setPrev(last);
            last = newNode;
        }
    }

    // metodo per aggiungere un nodo a una certa posizione
    public void addInPos(T val, int pos) {
        NodoBid<T> newNode = new NodoBid<>(val);
        if (pos <= 0 || first == null) {
            addHead(val);
            return;
        }

        NodoBid<T> temp = first;
        int i = 0;

        while (temp.getSucc() != null && i < pos - 1) {
            temp = temp.getSucc();
            i++;
        }

        newNode.setSucc(temp.getSucc());
        newNode.setPrev(temp);

        if (temp.getSucc() != null) {
            temp.getSucc().setPrev(newNode);
        } else {
            last = newNode;
        }

        temp.setSucc(newNode);
    }

    public void removeTail() {
        if (first == null) return;

        if (first == last) {
            first = null;
            last = null;
        } else {
            last = last.getPrev();
            last.setSucc(null);
        }
    }

    // metodo per rimuovere un nodo in una certa posizione
    public void removeInPos(int pos) {
        if (first == null) return;

        if (pos <= 0) {
            // rimuovi testa
            if (first == last) {
                first = null;
                last = null;
            } else {
                first = first.getSucc();
                first.setPrev(null);
            }
            return;
        }

        NodoBid<T> temp = first;
        int i = 0;
        while (temp != null && i < pos) {
            temp = temp.getSucc();
            i++;
        }

        if (temp == null) return; // posizione oltre la lunghezza
        
        if (temp.getSucc() != null) temp.getSucc().setPrev(temp.getPrev());
        else last = temp.getPrev();

        if (temp.getPrev() != null) temp.getPrev().setSucc(temp.getSucc());
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
        return result.trim();
    }
}