/*
Luca Bisognin - 15/11/2024
classe Porto del progetto Porto. Contiene i metodi gestire le azioni dell'utente.
*/

import java.util.*;

public class Porto {
    // arraylist per contenere le barche nel porto
    ArrayList<Barca> listaBarche = new ArrayList<>(100);

    // variabile 'posto' per tenere traccia del posto della barca di lunghezza 10 metri
    private int posto=0;
    // variabile 'posto2' per tenere traccia del posto della barca a vela nei posti dopo 50
    private int posto2=50;
    // variabile 'genPosto' per tenere traccia del posto della barca nei posti da 21 a 49
    private int genPosto=1;

    public void addShip(Scanner scanner) {
        // input nome barca
        scanner.nextLine(); // pulizia buffer
        System.out.print("Nome barca: ");
        String nome = scanner.nextLine();

        // input nazione barca
        System.out.print("Nazionalità barca: ");
        String nazionalita = scanner.nextLine();

        // input lunghezza barca
        System.out.print("Lunghezza barca (metri): ");
        int lunghezza = scanner.nextInt();

        // input stazza barca
        System.out.print("Stazza barca (t): ");
        int stazza = scanner.nextInt();

        // selezione tipologia barca
        System.out.print("Tipologia barca\n" +
                "[1] VELA\n" +
                "[2] MOTORE");
        System.out.print("\nTipo: ");

        String tipo="vela"; // tipologia di default
        try {
            int t = scanner.nextInt();
            // condizione di selezione
            if (t == 1) tipo="vela";
            else tipo="motore";

        } catch (InputMismatchException e) {
            System.out.println("Tipologia seleziona non valida.");
        }

        // creazione oggetto barca e aggiunta all'arraylist
        Barca b = new Barca(nome, nazionalita, tipo, lunghezza, stazza);

        if (listaBarche.size() < 100) {

            // AGGIUNTA BARCA ALL'ARRAYLIST IN BASE ALLE CONDIZIONI DEL PORTO
            // aggiunta nei posti 0-19 delle barche di lunghezza 10 metri
            if (lunghezza <=10 && posto <20) {
                listaBarche.set(posto, b);
                posto++;
            }
            // aggiunta in via prioritaria nei posti da 50 in avanti delle barche a vela
            else if (tipo.equals("vela") && posto2>=50) {
                listaBarche.set(posto2, b);
                posto2++;
            }
            else {
                listaBarche.add(b);
            }

            System.out.println("Barca aggiunta al porto con successo");
        }
        else System.out.println("Porto al completo. Nessun'altra barca può essere aggiunta");
    }

    // metodo 2 - rimozione barca dal porto
    public void removeShip(Scanner scanner) {

        // rimozione barca
        if (!listaBarche.isEmpty()) {
            // input dell'indice della barca da rimuovere
            System.out.print("Posto della barca: ");
            int pos = scanner.nextInt();

            // input numero di giorni di sosta
            System.out.print("Numero di giorni di sosta: ");
            int numGiorni = scanner.nextInt();

            // oggetto barca recuperato dall'arraylist
            Barca bSelected = listaBarche.get(pos);

            int basePrice=0; // costo dell'affitto

            if (bSelected.tipologia.equals("vela")) {
                basePrice=10*bSelected.lunghezza;
                basePrice*=numGiorni;
            }
            else if (bSelected.tipologia.equals("motore")) {
                basePrice=20*bSelected.stazza;
                basePrice*=numGiorni;
            }

            // stampa costo dell'affitto
            System.out.println("Avendo una barca a " + bSelected.tipologia + " ed essendo stato in porto per " +
                    numGiorni + ". Il costo dell'affitto del posto è di €" + basePrice + ".");

            // rimozione barca
            listaBarche.remove(pos);
            System.out.println("Barca rimossa con successo");
        }
        else System.out.println("Nessuna barca presente nel porto.");
    }

    // metodo 3 - ricerca barca
    public void searchShip(Scanner scanner) {

        if (listaBarche.isEmpty()) {
            System.out.println("Nessuna barca presente nel porto.");
        }
        else {
            System.out.print("Posto della barca: ");
            int pos = scanner.nextInt(); // posto della basta della quale recuperare le informazioni
            while (pos > 100 || pos < 0) {
                System.out.println("Posto invalido");
                pos = scanner.nextInt();
            }

            Barca b = listaBarche.get(pos); // oggetto barca recuperato

            // stampa informazioni barca selezionata
            System.out.println("Barca a " + b.tipologia + " " + b.nome + ". Nazionalità: " + b.nazionalita + ", lunghezza" +
                    b.lunghezza + " metri e " + b.stazza + " tonnellate.");
        }
    }

    // metodo 4 - salvataggio stato del porto
    public void saveState() {
        System.out.println("salvataggio completato");
    }

    // metodo 5 - stampa elenco barche nel porto
    public void printLista() {
        int index=1;
        if (!listaBarche.isEmpty()) {
            for (Barca b : listaBarche) {
                System.out.println("Posto [" + index + "] - Barca a " + b.tipologia + " '" + b.nome + "'. Nazionalità: " + b.nazionalita + ", lunghezza: " +
                        b.lunghezza + " metri e stazza: " + b.stazza + " tonnellate.");
                index++;
            }
        }
        else System.out.println("Nessuna barca presente nel porto.");
    }
}
