/*
Classe Campionato - generalizzazione della classe Squadra
- Tramite il main potremo creare diversi oggetti di tipo Campionato -
Luca Bisognin - 17/10/2024
*/

// import librerie esterne
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Campionato {
    int squadre_presenti;
    final int TOTALE_SQUADRE = 20;
    // arrayList delle squadre dell'oggetto campionato creato nel main
    ArrayList<Squadra> teams = new ArrayList<>(TOTALE_SQUADRE);


    // costruttore -> crea gli oggetti Squadra
    public Campionato(String dataFile) {
        // try-catch di controllo esistenza file con la lista delle squadre
        try {
            File f = new File(dataFile); // file lista squadre
            Scanner scanner = new Scanner(f);
            // for per creare gli oggetti Squadra
            for (int i = 0; i < TOTALE_SQUADRE && scanner.hasNextLine(); i++) {
                // creazione dei singoli oggetti Squadra e aggiunta all'array
                teams.add(i, new Squadra(scanner.nextLine()));
                squadre_presenti = i;
            }

        } catch (Exception ex) {
            System.out.println("Errore: " + ex.getMessage());
        }
    }

    // metodo per costruire la stringa da stampare
    public String toString() {
        int i=1; // indice lista
        StringBuilder s = new StringBuilder("\nSquadre nel campionato\n");
        for (Squadra t : teams) {
            s.append("[").append(i).append("] ").append(t).append("\n");
            i++;
        }

        return s.toString();
    }

    // metodo per aggiungere squadre al campionato
    public void addTeam(Scanner scanner) {
        if (squadre_presenti+1 >= TOTALE_SQUADRE) {
            System.out.println("Attenzione: numero massimo di squadre raggiunto");
            return;
        }

        // lettura della nuova squdra
        scanner.nextLine(); // pulizia buffer
        System.out.print("Nome nuova squadra: ");
        String nuovaSquadra = scanner.nextLine();

        // incremento numero di squadre
        squadre_presenti++;
        // aggiunta della nuova squadra all'array
        teams.add(squadre_presenti, new Squadra(nuovaSquadra));
        System.out.println("Squadra " + nuovaSquadra + " aggiunta.");
    }

    // metodo per rimuovere ultima squadra dal campionato
    public void removeLastTeam() {
        if (squadre_presenti<0) {
            System.out.println("Attenzione: nessuna squadra nel campionato.");
            return;
        }

        // rimozione dell'ultima squadra
        System.out.println("Squadra " + teams.get(squadre_presenti) + " rimossa.");
        teams.remove(squadre_presenti);
        squadre_presenti--;
    }

    // metodo per rimuovere una squadra a piacere
    public void removeTeam(int pos) {
        pos--; // decremento --> l'utente seleziona un valore avanti rispetto agli indici lista
        if (pos < 0 || pos >= TOTALE_SQUADRE-1) {
            System.out.println("Attenzione: nessuna squadra nel campionato.");
            return;
        }

        // rimozione squadra scelta
        System.out.println("Squadra " + teams.get(pos) + " rimossa.");
        teams.remove(pos);
        squadre_presenti--;

    }
}
