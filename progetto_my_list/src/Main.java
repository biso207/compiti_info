/*
Luca Bisognin - 5/12/2024
Classe Main del progetto MyList
*/

import java.io.IOException;

import listeUnidirezionali.*;
import listaBidirezionali.*;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        // vettore generico con Object
        String[] v = {"E1", "E2", "E3"};

        // nodo 1
        Nodo<String> nodoS = new Nodo<>("Luca");

        // nodo 2
        nodoS.setNext(new Nodo<>("Biso"));

        // stampa nodi creati
        System.out.println(nodoS);

        // lista 1 (String)
        Lista<String> elenco = new Lista<>();
        elenco.addHead("Biso"); // aggiunta elemento in testa

        // lista 2 (String)
        Lista<String> elenco2 = new Lista<>();
        System.out.println("\nLista 2\n" + elenco2);
        // aggiunta nodi in testa
        elenco2.addHead(new Nodo<>("Forza Bologna"));
        elenco2.addHead(new Nodo<>("Ciaone"));
        elenco2.addHead(new Nodo<>("Ciao"));
        elenco2.addHead("Brute Force");
        System.out.println("\nLista 2 dopo aggiunta nodi in 'head' => nodi aggiunti in testa\n" + elenco2);

        // aggiunta nodi in coda alla lista 2
        elenco2.addTail("Butt");
        elenco2.addTail("Coda");
        elenco2.addTail("Fine");
        System.out.println("\nLista 2 dopo aggiunta nodi in 'tail' => nodi aggiunti in coda\n" + elenco2);

        // chimata metodi per rimuovere occorrenze dalla lista 2
        elenco2.remove("Ciaone");
        elenco2.remove("Coda");
        System.out.println("\nLista dopo chiamata metodo 'remove' => occorrenze 'Ciaone' & 'Coda' rimosse\n" + elenco2);

        // chiamata metodo per lo switch primo-ultimo elemento
        elenco2.lastFirst();
        System.out.println("\nLista dopo lo switch 'lastFirst' => primo e ultimo elemento scambiati\n" + elenco2);
        */

        // test Hello World
        System.out.println("Hello, World!");

        Lista<String> l = new Lista<>();
        l.add("Bologna");
        l.add("Milan");
        l.addHead("Catanzaro");
        System.out.println(l);

        ListaOrdinata<String> lo = new ListaOrdinata<>();
        lo.add("Bologna");
        lo.add("Milan");
        lo.add("Inter");
        lo.add("Bayern");
        lo.add("Real Madrid");
        lo.add("Barcellona");
        lo.add("Manchester United");
        lo.add("Liverpool");
        lo.add("PSG");
        System.out.println(lo);

        // lettura di un file di testo e salvataggio dei valori ordinati in un nuovo file
        SortTextFile s = new SortTextFile("file.txt", "newFile.txt");
        s.sort(); // lettura + ordinamento file
        s.saveFile(); // salvataggio lista in un nuovo file


        // test MyIntArray
        /*
        MyIntArray a = new MyIntArray();
        for (int i=0; i<10; i++) a.add(i);
        System.out.println(a);
        */
    }
}
