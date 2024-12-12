/*
Luca Bisognin - 5/12/2024
Classe Lista del progetto MyList
*/

import java.util.NoSuchElementException;

public class Lista<T> {
    // attributi
    Nodo<T> head;

    // costruttore
    public Lista(T[] v) {
        head = null; // init lista vuota

        // aggiunta elemento vettore nella lista
        for (int i = v.length - 1; i >= 0; i--) {
            addTail(v[i]);
        }
    }

    // metodo per aggiungere elementi prima in testa alla lista
    public void addHead(Nodo<T> n) {
        n.setNext(head);
        head = n;
    }

    // metodo 2 per aggiungere elementi prima in testa alla lista
    public void addHead(T newValue) {
        Nodo<T> nuovo = new Nodo<>(newValue);
        addHead(nuovo);
    }

    // metodo per aggiungere un elemento in coda alla lista
    public void addTail(Nodo<T> newValue) {

        // lista vuota => il nuovo nodo diventa la testa
        if (head == null) {
            head = newValue;
        } else {
            // scorre fino alla fine e aggiunge il nuovo nodo
            Nodo<T> p = head;
            while (p.getNext() != null) {
                p = p.getNext();
            }
            p.setNext(newValue);
        }
    }

    // metodo 2 per aggiungere un elemento in coda alla lista
    public void addTail(T newValue) {
        Nodo<T> nuovo = new Nodo<>(newValue);
        addTail(nuovo);
    }

    // metodo per rimuovere occorrenze dalla lista
    public void remove(T element) {
        // lista vuota
        if (head == null) {
            return;
        }

        // rimozione occorrenze in testa anche uguali
        while (head.getV() == element) {
            head = head.getNext();
        }

        // rimozione altre occorrenze
        Nodo<T> p = head;
        while (p != null && p.getNext() != null) {
            if (p.getNext().getV().equals(element)) {
                p.setNext(p.getNext().getNext()); // rimozione nodo successivo
            } else {
                p = p.getNext(); // nodo successivo
            }
        }
    }

    // metodo per spostare il primo elemento in coda e viceversa
    public void lastFirst() {
        // lista vuoto o con un solo elemento
        if (head == null || head.getNext() == null) {
            return;
        }

        Nodo<T> p = head;

        // ricerca ultimo nodo e il suo precedente
        while (p.getNext() != null) {
            p = p.getNext();
        }

        Nodo<T> last = p;

        // spostamento valori del primo e dell'ultimo nodo
        T temp = head.getV();
        head.setV(last.getV());
        last.setV(temp);
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
