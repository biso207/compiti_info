package listeUnidirezionali;
public class ListaOrdinata<T extends Comparable<T>> extends Lista<T> {

    // metodo per aggiungere un valore alla lista
    @Override
    public void add(T newval) {
        Nodo<T> newNode = new Nodo<>(newval);
        if (root==null) { root=newNode; return; }
        Nodo<T> temp = root;

        if (root.getValore().compareTo(newval)>0) {
            newNode.setSucc(root);
            root=newNode;
            return;
        }
        while (temp.getSucc()!=null && temp.getSucc().getValore().compareTo(newval)<0) temp=temp.getSucc();

        newNode.setSucc(temp.getSucc());
        temp.setSucc(newNode);
    }

    // I METODI SEGUENTI SERVONO PER LE ITERAZIONI SULLA LISTA //

    // metodo per recuperare la lunghezza della lista
    public int size() {
        int count = 0;
        Nodo<T> temp = root;
        while (temp != null) {
            count++;
            temp = temp.getSucc();
        }
        return count;
    }

    // metodo per recuperare un valore della lista
    public T get(int index) {
        int count = 0;
        Nodo<T> temp = root;
        while (temp != null) {
            if (count == index) return temp.getValore();
            temp = temp.getSucc();
            count++;
        }
        throw new IndexOutOfBoundsException("Indice fuori dal range: " + index);
    }

} 