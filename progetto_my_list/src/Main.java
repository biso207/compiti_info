/*
Luca Bisognin - 5/12/2024
Classe Main del progetto MyList
*/

public class Main {
    public static void main(String[] args) {
        // nodo 1
        Nodo<String> nodoS;
        nodoS = new Nodo<>("Luca");
        System.out.println(nodoS);

        // nodo 1
        Nodo<String> nodoS2;
        nodoS2 = new Nodo<>("Biso");
        nodoS.setNext(nodoS2);

        // lista 1
        Lista<String> elenco = new Lista<>();
        System.out.println(elenco);
        elenco.addHead("Biso");
        System.out.println(elenco);

        // lista 2
        Lista<String> elenco2 = new Lista<>();
        // aggiunta in testa
        elenco2.addHead(new Nodo<>("Forza Bologna"));
        elenco2.addHead(new Nodo<> ("Ciaone"));
        elenco2.addHead("Brute Force");

        // aggiunta in coda
        elenco2.addTail("Butt");
        elenco2.addTail("Coda");
        elenco2.addTail("Fine");
        System.out.println("\nCoda prima delle rimozioni\n" + elenco2);

        // rimozione nodo
        elenco2.remove("Brute Force");
        elenco2.remove("Coda");
        System.out.println("\nLista dopo le rimozioni\n" + elenco2);

    }
}
