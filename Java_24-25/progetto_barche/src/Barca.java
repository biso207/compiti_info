/*
Luca Bisognin - 15/11/2024
classe Barca del progetto Barche. Contiene metodi e attributi per creare oggetti 'Barca'
*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class Barca {
    // attributi
    private final String nome, nazione, tipologia;
    private final int lunghezza, stazza;

    Barca(String nome, String nazione, String tipologia, int lunghezza, int stazza) {
        this.nome = nome;
        this.nazione = nazione;
        this.tipologia = tipologia;
        this.lunghezza = lunghezza;
        this.stazza = stazza;
    }

    // metodo per creare un oggetto
    public static Barca createBarca(Scanner scanner) {
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

        String tipologia="vela"; // tipologia di default

        // controllo input
        try {
            int t = scanner.nextInt();
            // condizione di selezione
            if (t == 1) tipologia="vela";
            else tipologia="motore";

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

        return new Barca(nome, nazione, tipologia, lunghezza, stazza);
    }

    public int getStazza() {
        return stazza;
    }

    public int getLunghezza() {
        return lunghezza;
    }

    public String getTipologia() {
        return tipologia;
    }

    public String getNazione() {
        return nazione;
    }

    public String getNome() {
        return nome;
    }

    public String toString() {
        return (" Barca a " + tipologia + " '" + nome + "'.\n Nazione: " + nazione + "\n Lunghezza: " +
                lunghezza + " metri\n Stazza: " + stazza + " tonnellate.\n");
    }

}
