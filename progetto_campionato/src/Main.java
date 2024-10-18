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
               ---------------------------------
               | [0] Termina Programma         |
               | [1] Stampa Menù               |
               | [2] Stampa Squadre            |
               | [3] Aggiungi Squadra          |
               | [4] Rimuovi Ultima Squadra    |
               | [5] Rimuovi Squadra a Piacere |
               | [6] Modifica Nome Squadra     |
               | [7] Salva Modifiche           |
               ---------------------------------
               """);
    }

    // main
    public static void main(String[] args) {

        // creazione scanner e variabile selezione
        Scanner scanner = new Scanner(System.in);
        int choice;
        printMenu();
        // costruzione oggetto campionato
        Campionato serieA = new Campionato("data/serieA.csv");

        do {
            System.out.print("Scelta: ");
            choice = scanner.nextInt();

            switch(choice) {
                // stampa elenco squadre
                case 1:
                    printMenu();
                    break;
                case 2:
                    System.out.println(serieA);
                    break;
                case 3:
                    serieA.addTeam(scanner);
                    break;
                case 4:
                    serieA.removeLastTeam();
                    break;
                case 5:
                    System.out.print("Numero squadra da rimuovere: ");
                    int pos = scanner.nextInt();
                    serieA.removeTeam(pos);
                    break;
                default:
                    System.out.print("- Programma Chiuso Correttamente -");
                    break;
            }
        } while(choice!=0);
    }
}
