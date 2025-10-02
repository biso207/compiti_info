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
               | [2] Stampa Squadre del Campionato            |
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
        // costruzione oggetti campionato
        Campionato serieA = new Campionato("Seria A",20, "progetto_campionato/data/serieA.csv");
        Campionato bundesliga = new Campionato("Bundesliga",18, "progetto_campionato/data/bundesliga.csv");
        Campionato lba = new Campionato("LBA",16, "progetto_campionato/data/LBA.csv");
        Campionato campionato = serieA; // campionato di default

        System.out.println("""
        Scegli il campionato su cui lavorare
        [1] Serie A
        [2] Bundesliga
        [3] LBA
        """);


        int choiceCamp; // var per scegliere il campionato
        try {
            System.out.print("Campionato: ");
            choiceCamp = scanner.nextInt();
            switch (choiceCamp) {
                case 1:
                    break;
                case 2:
                    campionato = bundesliga;
                    break;
                case 3:
                    campionato = lba;
                    break;
                default:
                    System.out.println("Valore inesistente");
            }

            System.out.println("\nCampionato " + campionato.name + " selezionato correttamente.");


            do {
                System.out.print("\nScelta: ");
                choice = scanner.nextInt();
                switch(choice) {
                    // stampa elenco squadre
                    case 1:
                        printMenu();
                        break;
                    // stampa elenco squadre
                    case 2:
                        System.out.println(campionato);
                        break;
                    // aggiungi squadra
                    case 3:
                        campionato.addTeam(scanner);
                        break;
                    // aggiunga squadra in una posizione
                    case 4:
                        campionato.addTeamInPos(scanner);
                        break;
                    // rimuovi ultima squadra
                    case 5:
                        campionato.removeLastTeam();
                        break;
                    // rimuovi squadra a piacere
                    case 6:
                        campionato.removeTeam(scanner);
                        break;
                    // modifica nome squadra
                    case 7:
                        campionato.changeTeamName(scanner);
                        break;
                    // salva modifiche
                    case 8:
                        campionato.saveChanges();
                        break;
                    default:
                        System.out.print("- Programma Chiuso Correttamente -");
                        break;
                }
            } while(choice!=0);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
