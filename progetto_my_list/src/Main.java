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
        elenco2.addHead(new Nodo<>("Forza Bologna"));
        elenco2.addHead(new Nodo<> ("Ciaone"));
        elenco2.addHead("Brute Force");
        System.out.println(elenco2);

    }
}
