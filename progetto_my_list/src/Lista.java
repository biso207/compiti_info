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

// metodo per aggiungere un elemento in coda alla lista
    public void addTail(T newValue) {
        Nodo<T> nuovoNodo = new Nodo<>(newValue);

        // lista è vuota, il nuovo nodo diventa la testa
        if (head == null) {
            head = nuovoNodo;
        } else {
            // scorre fino alla fine e aggiungi il nuovo nodo
            Nodo<T> p = head;
            while (p.getNext() != null) {
                p = p.getNext();
            }
            p.setNext(nuovoNodo);
        }
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
