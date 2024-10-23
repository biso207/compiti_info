/*
progetto Serie A -
Luca Bisognin - 17/10/2024
*/

import java.util.Scanner;

public class Main  {
    // stampa menù
    public static void printMenu() {
        System.out.print("""
               \nMenù
               ------------------------------------------------
               | [0] Termina Programma                        |
               | [1] Stampa Menù                              |
               | [2] Stampa Squadre del Campionato di Serie A |
               | [3] Aggiungi Squadra al campionato           |
               | [4] Aggiungi Squadra in Posizione a Piacere  |
               | [5] Rimuovi Ultima Squadra del campionato    |
               | [6] Rimuovi Squadra a Piacere dal campionato |
               | [7] Modifica Nome Squadra                    |
               | [8] Salva Modifiche                          |
               ------------------------------------------------
               """);
    }

    // main
    public static void main(String[] args) {

        // creazione scanner e variabile selezione
        Scanner scanner = new Scanner(System.in);

        // choice per scelta dal menù
        int choice;

        printMenu();
        // costruzione oggetto campionato
        Campionato serieA = new Campionato("progetto_campionato/data/serieA.csv");

        do {
            System.out.print("Scelta: ");
            choice = scanner.nextInt();

            switch(choice) {
                // stampa elenco squadre
                case 1:
                    printMenu();
                    break;
                // stampa elenco squadre
                case 2:
                    System.out.println(serieA);
                    break;
                // aggiungi squadra
                case 3:
                    serieA.addTeam(scanner);
                    break;
                // aggiunga squadra in una posizione
                case 4:
                    serieA.addTeamInPos(scanner);
                    break;
                // rimuovi ultima squadra
                case 5:
                    serieA.removeLastTeam();
                    break;
                // rimuovi squadra a piacere
                case 6:
                    serieA.removeTeam(scanner);
                    break;
                // modifica nome squadra
                case 7:
                    serieA.changeTeamName(scanner);
                    break;
                // salva modifiche
                case 8:
                    serieA.saveChanges();
                    break;
                default:
                    System.out.print("- Programma Chiuso Correttamente -");
                    break;
            }
        } while(choice!=0);
    }
}
