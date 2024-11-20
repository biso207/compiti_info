/*
Luca Bisognin - 15/11/2024
classe Porto del progetto Porto. Contiene i metodi gestire le azioni dell'utente.
*/

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Porto {
    // arraylist per contenere le barche nel porto
    ArrayList<Barca> listaBarche = new ArrayList<>(100);

    // variabile 'posti1' per tenere traccia dei posti delle barche di lunghezza minore o uguale a 10 metri
    private int posti1; // per posti 0-19 (1-20)

    // variabile 'posti2' per tenere traccia del posti2 della barca a vela nei posti tra 20 e 49
    private int posti2; // per posti 20-49 (21-50)

    // variabile 'posti3' per tenere traccia dei posti3 delle barche a vela
    private int posti3; // per posti 50-99 (51-100)

    // variabile per tenere traccia della somma dei posti occupati
    private int sommaPosti;

    // variabile per selezionare il posto da ricercare
    int pos;

    // costruttore
    Porto() {
        // init dell'ArrayList di barche
        while (listaBarche.size()<100) {
            listaBarche.add(null);
        }

        // init dei posti
        posti1=posti2=posti3=0;
    }

    // metodo 1 - aggiunta barca al porto
    public void addShip(Scanner scanner) {
        // input nome barca
        scanner.nextLine(); // pulizia buffer
        System.out.print("Nome barca: ");
        String nome = scanner.nextLine();

        // input nazione barca
        System.out.print("Nazione barca: ");
        String nazione = scanner.nextLine();

        // input lunghezza barca
        System.out.print("Lunghezza barca (metri): ");
        int lunghezza = scanner.nextInt();

        // input stazza barca
        System.out.print("Stazza barca (t): ");
        int stazza = scanner.nextInt();

        // selezione tipologia barca
        System.out.print("""
                Tipologia barca
                [1] VELA
                [2] MOTORE""");
        System.out.print("\nTipo: ");

        String tipo="vela"; // tipologia di default

        // controllo input
        try {
            int t = scanner.nextInt();
            // condizione di selezione
            if (t == 1) tipo="vela";
            else tipo="motore";

            while (t>2 || t<1) {
                System.out.println("\nTipologia selezionata non valida. Riprova");
                System.out.print("""
                    Tipologia barca
                    [1] VELA
                    [2] MOTORE""");
                System.out.print("\nTipo: ");
                t = scanner.nextInt();

            }

        } catch (InputMismatchException e) {
            System.out.println("Input invalido.");
        }

        // creazione oggetto barca e aggiunta all'arraylist
        Barca b = new Barca(nome, nazione, tipo, lunghezza, stazza);

        // somma posti occupati
        sommaPosti = posti1+posti2+posti3;
        if (sommaPosti < 100) {

            // AGGIUNTA BARCA ALL'ARRAYLIST IN BASE ALLE CONDIZIONI DEL PORTO
            // aggiunta nei posti 0-19 delle barche di lunghezza 10 metri
            if (lunghezza <=10 && posti1 < 20) {
                listaBarche.set(posti1, b); // set dell'oggetto a posti1
                posti1++;
            }
            // aggiunta barche nei posti 50-99
            else if (tipo.equals("vela") && (posti3+50)<100) {
                listaBarche.set((posti3+50), b); // set oggetto a posti3
                posti3++;
            }
            // aggiunta default delle barche
            else {
                listaBarche.set((posti2+20), b); // set oggetto a posti2
                posti2++;
            }

            System.out.println("\nBarca aggiunta al porto con successo.");
        }
        else System.out.println("Porto al completo. Nessun'altra barca può essere aggiunta.");
    }

    // metodo 2 - rimozione barca dal porto
    public void removeShip(Scanner scanner) {
        // somma posti occupati
        sommaPosti = posti1+posti2+posti3;

        // rimozione barca
        if (sommaPosti >= 1) {
            // input dell'indice della barca da rimuovere
            System.out.print("Posto della barca: ");
            pos = scanner.nextInt();

            // controllo digitazione errata
            while (pos > 100 || pos < 0) {
                System.out.println("Posto invalido");
                pos = scanner.nextInt();
            }
            pos--; // l'utente digita la posizione che si visualizza

            // oggetto barca recuperato dall'arraylist
            Barca bSelected = listaBarche.get(pos);

            // uscita in caso di ricerca di un posto vuoto
            if (bSelected==null) {
                System.out.println("Posto vuoto! Nessuna barca presente al posto " + (pos+1) + ".");
                return;
            }

            // input numero di giorni di sosta
            System.out.print("Numero di giorni di sosta: ");
            int numGiorni = scanner.nextInt();

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
            System.out.println("Avendo una barca a " + bSelected.tipologia + " ed essendo stata in porto per " +
                    numGiorni + " giorni.\nIl costo dell'affitto del posto è di €" + basePrice + ".");

            // rimozione barca
            listaBarche.set(pos, null);

            // decremento posti
            if (pos>=0 && pos<20) posti1--;
            else if (pos>=20 && pos<50) posti2--;
            else if (pos>=50 && pos<100) posti3--;

            System.out.println("\nBarca rimossa con successo.");
        }
        else System.out.println("Nessuna barca presente nel porto.");
    }

    // metodo 3 - ricerca barca
    public void searchShip(Scanner scanner) {
        // somma posti occupati
        sommaPosti = posti1+posti2+posti3;

        if (sommaPosti ==0) {
            System.out.println("Nessuna barca presente nel porto.");
        }
        else {
            System.out.print("posti1 della barca: ");
            pos = scanner.nextInt(); // posto della basta della quale recuperare le informazioni

            // controllo digitazione errata
            while (pos > 100 || pos < 0) {
                System.out.println("posti1 invalido");
                pos = scanner.nextInt();
            }
            pos--; // l'utente digita la posizione che si visualizza

            // oggetto barca recuperato dall'arraylist
            Barca bSelected = listaBarche.get(pos);

            // uscita in caso di ricerca di un posto vuoto
            if (bSelected==null) {
                System.out.println("Posto vuoto! Nessuna barca presente al posto " + (pos+1) + ".");
                return;
            }

            // stampa informazioni barca selezionata
            System.out.println("Barca a " + bSelected.tipologia + " '" + bSelected.nome + "'.\nNazione: " + bSelected.nazione + "\nLunghezza: " +
                    bSelected.lunghezza + " metri\nStazza: " + bSelected.stazza + " tonnellate.\n");
        }
    }

    // metodo 4 - salvataggio stato del porto
    public void saveState() {
        // salvataggio attributi barche nel porto
        try {
            FileWriter writeName = new FileWriter("progetto_porto/data/nomeBarche.txt");
            FileWriter writeNazione = new FileWriter("progetto_porto/data/nazioneBarche.txt");
            FileWriter writeLen = new FileWriter("progetto_porto/data/lunghezzaBarche.txt");
            FileWriter writeStazza = new FileWriter("progetto_porto/data/stazzaBarche.txt");
            FileWriter writeTipo = new FileWriter("progetto_porto/data/tipoBarche.txt");

            for (Barca b: listaBarche) {
                if (b!=null) {
                    System.out.println("Nome: " + b.nome);
                    System.out.println("Nazione: " + b.nazione);
                    System.out.println("Lunghezza: " + b.lunghezza);
                    System.out.println("Stazza: " + b.stazza);
                    System.out.println("Tipo: " + b.tipologia);

                    writeName.write(b.nome + "\n"); // nome
                    writeNazione.write(b.nazione + "\n"); // nazione
                    writeLen.write(b.lunghezza + "\n"); // lunghezza
                    writeStazza.write(b.stazza + "\n"); // stazza
                    writeTipo.write(b.tipologia + "\n"); // tipo
                }
            }
        }
        catch (IOException e) {
            System.out.println("Errore! " + e.getMessage());
        }

        System.out.println("\nSalvataggio completato.");
    }

    // metodo 5 - stampa elenco barche nel porto
    public void printLista() {
        // somma posti occupati
        sommaPosti = posti1+posti2+posti3;

        // indice di riferimento al numero del posto
        int index=1;
        if (sommaPosti>=1) {
            for (Barca b : listaBarche) {
                if (b != null) {
                    System.out.println("Posto [" + index + "] - Barca a " + b.tipologia + " '" + b.nome + "'.\nNazione: " + b.nazione + "\nLunghezza: " +
                            b.lunghezza + " metri\nStazza: " + b.stazza + " tonnellate.\n");
                }
                index++;
            }
        }
        else System.out.println("Nessuna barca presente nel porto.");
    }
}
