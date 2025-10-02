/*
Luca Bisognin - 21/11/2024
Classe Main del progetto frigorifero (virtuale)
 */


import java.util.Scanner;

public class Main {

    // menù delle azioni
    public static void menu() {
        System.out.println("""
                ----------------------------------------------
                | (1) Inserimento prodotto                   |
                | (2) Rimozione prodotto                     |
                | (3) Stampa elenco prodotti                 |
                | (4) Stampa elenco prodotti scaduti         |
                | (5) Calcolo num confezioni stesso prodotto |
                | (6) Salva stato frigorifero                |
                | (7) Mostra menù                            |
                | (8) Chiusura del frigorifero               |
                ----------------------------------------------
                """);
    }

    // main
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int choice;

        // oggetto frigo
        Frigorifero frigo = new Frigorifero();

        menu();
        System.out.print("Scelta - (7 per vedere il menù): ");
        try {
            choice = scan.nextInt();
            while (choice != 8) {
                switch (choice) {
                    case 1:
                        frigo.addProdotto(scan);
                        break;
                    case 2:
                        frigo.removeProdotto(scan);
                        break;
                    case 3:
                        frigo.printProdotti();
                        break;
                    case 4:
                        frigo.printScaduti();
                        break;
                    case 5:
                        frigo.calcConf(scan);
                        break;
                    case 6:
                        frigo.save();
                        break;
                    case 7:
                        menu();
                        break;
                    default:
                        System.out.println("Input fuori menù.");
                        break;
                }
                System.out.print("\nNuova scelta - (7 per vedere il menù): ");
                choice = scan.nextInt();
            }
        }
        catch (Exception e) {
            System.out.println("Digitazione errata.");
        }

        System.out.println("\nFRIGO CHIUSO");
    }
}
