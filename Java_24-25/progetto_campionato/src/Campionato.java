/*
Classe Campionato - generalizzazione della classe Squadra
- Tramite il main potremo creare diversi oggetti di tipo Campionato -
Luca Bisognin - 17/10/2024
*/

// import librerie esterne
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Campionato {
    public static final String RED = "\033[0;31m"; // colore rosso per gli avvisi
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String RESET = "\033[0m";  // reset colore

    int squadre_presenti, pos, numTeams;
    final int TOTALE_SQUADRE = 20;
    String name;
    ArrayList<Squadra> teams; // arrayList di oggetto Squadra


    // costruttore -> crea gli oggetti Squadra
    public Campionato(String name, int numTeams, String dataFile) {
        this.name = name;

        // init dell'arrayList di 'numTeams' posizioni
        teams = new ArrayList<>(numTeams);

        // try-catch di controllo esistenza file con la lista delle squadre
        try {
            File f = new File(dataFile); // file lista squadre
            Scanner scanner = new Scanner(f);
            // for per creare gli oggetti Squadra
            for (int i = 0; scanner.hasNextLine(); i++) {
                // creazione dei singoli oggetti Squadra e aggiunta all'ArrayList
                teams.add(i, new Squadra(scanner.nextLine()));
                squadre_presenti = i;
            }
        } catch (Exception ex) {
            System.out.println(RED + "Errore: " + ex.getMessage() + RESET);
        }
    }

    // case 2 -> toString per la stampa dell'elenco delle squadre
    public String toString() {
        int i=1; // indice lista
        StringBuilder s = new StringBuilder(name + "\n");
        for (Squadra t : teams) {
            s.append("[").append(i).append("] ").append(t).append("\n");
            i++;
        }

        return s.toString();
    }

    // case 3 -> aggiungere squadre al campionato
    public void addTeam(Scanner scanner) {
        if (squadre_presenti+1 >= TOTALE_SQUADRE) {
            System.out.println(RED + "Attenzione: numero massimo di squadre raggiunto\n" + RESET);
            return;
        }

        // lettura della nuova squadra
        scanner.nextLine(); // pulizia buffer
        System.out.print("Nome nuova squadra: ");
        String nuovaSquadra = scanner.nextLine();

        // incremento numero di squadre
        squadre_presenti++;
        // aggiunta della nuova squadra all'array
        teams.add(squadre_presenti, new Squadra(nuovaSquadra));
        System.out.println(GREEN + "Squadra " + nuovaSquadra + " aggiunta.\n" + RESET);
    }

    // case 4 -> aggiunta squadra a determinata posizione
    public void addTeamInPos(Scanner scanner) {
        if (squadre_presenti+1 >= TOTALE_SQUADRE) {
            System.out.println(RED + "Attenzione: numero massimo di squadre raggiunto\n" + RESET);
            return;
        }

        // scelta posizione
        System.out.print("Posizione in cui aggiungere la squadra: ");
        pos = scanner.nextInt();
        // lettura della nuova squadra
        scanner.nextLine(); // pulizia buffer
        System.out.print("Nome nuova squadra: ");
        String nuovaSquadra = scanner.nextLine();

        // incremento numero di squadre
        squadre_presenti++;
        // aggiunta della nuova squadra all'array
        teams.add(pos-1, new Squadra(nuovaSquadra));
        System.out.println(GREEN + "Squadra '" + nuovaSquadra + "' aggiunta.\n" + RESET);
    }

    // case 5 -> rimozione ultima squadra
    public void removeLastTeam() {
        if (squadre_presenti<0) {
            System.out.println(RED + "Attenzione: nessuna squadra nel campionato.\n" + RESET);
            return;
        }

        // rimozione dell'ultima squadra
        System.out.println(GREEN + "Squadra '" + teams.get(squadre_presenti) + "' rimossa.\n" + RESET);
        teams.remove(squadre_presenti);
        squadre_presenti--; // decremento numero squadre presenti
    }

    // case 6 -> rimozione squadra a determinata posizione
    public void removeTeam(Scanner scanner) {
        if (pos < 0 || pos >= TOTALE_SQUADRE-1) {
            System.out.println(RED + "Attenzione: nessuna squadra nel campionato.\n" + RESET);
            return;
        }

        System.out.print("Numero squadra da rimuovere: ");
        pos = scanner.nextInt();
        // rimozione squadra scelta
        System.out.println(GREEN + "Squadra '" + teams.get(pos-1) + "' rimossa.\n" + RESET);
        teams.remove(pos-1);
        squadre_presenti--;

    }

    // case 7 -> rinomina determinata squadra
    public void changeTeamName(Scanner scanner) {
        // scelta posizione
        System.out.print("Numero squadra di cui cambiare nome: ");
        pos = scanner.nextInt();

        // recupero nome squadra di cui cambiare nome
        String oldName = teams.get(pos-1).getNome();

        // lettura del nuovo nome
        scanner.nextLine(); // pulizia buffer
        System.out.print("Nuovo nome squadra: ");
        String newName = scanner.nextLine();

        // cambio nome squadra alla posizione 'pos-1'
        teams.set(pos-1, new Squadra(newName));

        System.out.println(GREEN + "Squadra '" + oldName + "' rinominata in '" + newName + "'.\n" + RESET);
    }

    // case 8 -> salvataggio modifiche
    public void saveChanges() {
        try {
            // creazione oggetti writer per salvare le modifiche
            FileWriter writeChanges = new FileWriter("progetto_campionato/data/serieA.csv");
            // sovrascrittura del file csv che contiene l'elenco delle squadre
            for (Squadra t : teams) {
                writeChanges.write(t.toString() + "\n");
            }
            // chiusura del writer
            writeChanges.close();
        } catch (Exception ex) {
            System.out.println("Errore: " + ex.getMessage());
        }
        System.out.println(GREEN + "Modifiche Salvate Correttamente\n" + RESET);
    }
}
