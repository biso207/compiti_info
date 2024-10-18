/*
Classe Campionato - generalizzazione della classe Squadra
- Tramite il main potremo creare diversi oggetti di tipo Campionato -
Luca Bisognin - 17/10/2024
*/

// import librerie esterne
import java.io.File;
import java.util.Scanner;

public class Campionato {
    // array delle squadre dell'oggetto campionato creato nel main
    Squadra[] teams;

    // costruttore -> crea gli oggetti Squadra
    public Campionato(String dataFile) {
        teams = new Squadra[dataFile.length()];

        // try-catch di controllo esistenza file con la lista delle squadre
        try {
            File f = new File(dataFile); // file lista squadre
            Scanner scanner = new Scanner(f); // scanner per lettura file

            // for per creare gli oggetti Squadra
            for (int i = 0; i < teams.length && scanner.hasNextLine(); i++) {
                // creazione dei singoli oggetti Squadra e aggiunta all'array
                teams[i] = new Squadra(scanner.nextLine());
            }

            // chiusura scanner
            scanner.close();
        } catch (Exception ex) {
            System.out.println("Errore: " + ex.getMessage());
        }
    }

    // metodo per costruire la stringa da stampare
    public String toString() {
        String s = "Squadre nel campionato\n";
        for (Squadra t : teams) {
            s += t + "\n";
        }

        return s;
    }
}
