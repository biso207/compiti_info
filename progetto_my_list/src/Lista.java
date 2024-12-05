/*
Luca Bisognin - 5/12/2024
Classe Lista del progetto MyList
*/

public class Lista<T> {
    // attributi
    Nodo<T> head;

    // costruttore
    public Lista() {
        head = null;
    }

    // metodo per aggiungere elementi prima dell'head
    public void addHead(Nodo<T> n) {
        n.setNext(head);
        head = n;

    }

    // metodo 2 per aggiungere elementi prima dell'head
    public void addHead(T newValue) {
        head = new Nodo<>(newValue);
    }

    // metodo toString
    public String toString() {
        StringBuilder r = new StringBuilder("La lista contiene: ");
        Nodo<T> p = head;

        while (p != null) {
            r.append(p);
            p = p.getNext();
        }

        return r.toString();
    }

}
