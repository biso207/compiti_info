/*
Luca Bisognin - 5/12/2024
Classe Lista del progetto MyList
*/

import java.util.NoSuchElementException;

public class Lista<T> {
    // attributi
    Nodo<T> head;

    // costruttore
    public Lista() {
        head = null;
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

    // metodo per rimuovere un nodo
    public void remove(T element) {
        if (head == null) {
            throw new NoSuchElementException();
        }

        // rimozione valore in testa
        Nodo<T> p = head;
        if (head.getV() == element) {
            head = head.getNext();
            return;
        }

        // rimozione altro valore nella lista
        Nodo<T> p2 = head.getNext();
        while (p2!=null) {
            if (p2.getV().equals(element)) {
                p.setNext(p2.getNext());
                return;
            }
            else {
                p = p2;
                p2 = p2.getNext();
            }
        }
    }

    // metodo per spostare il primo elemento in coda e viceversa
    public void lastFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        Nodo<T> p = head;
        Nodo<T> pLast=p;

        pLast.setV(p.getV());
        T pSave = head.getV();

        // nessun elemento presente
        while (p.getNext() != null) {
            pLast.setV(p.getNext().getV());
            p = p.getNext();
        }

        pSave = pLast.getV();

        // spostamento primo elemento in coda e viceversa
        pLast.setV(head.getV());
        head.setV(pSave);
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
