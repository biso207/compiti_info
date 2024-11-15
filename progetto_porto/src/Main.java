/*
Luca Bisognin - 15/11/2024
classe Main del progetto Port
*/

import java.util.*;


public class Main {

    // menù delle azioni
    public static void menu() {
        System.out.println("""
                1) assegna posto
                2) libera posto occupato
                3) ricerca barca per posto
                4) salva stato del porto
                5) stampa elenco barche nel porto
                6) mostra menù
                7) chiudi software
                """);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int choice;

        // oggetto porto
        Porto porto = new Porto();

        menu();
        System.out.print("Scelta: ");
        choice = scan.nextInt();
        while (choice != 7) {
            switch (choice) {
                case 1:
                    porto.addShip(scan);
                    break;
                case 2:
                    porto.removeShip(scan);
                    break;
                case 3:
                    porto.searchShip(scan);
                    break;
                case 4:
                    porto.saveState();
                    break;
                case 5:
                    porto.printLista();
                    break;
                case 6:
                    menu();
                    break;
                default:
                    System.out.println("Errore nella lettura del programma");
                    break;
            }
            System.out.print("\nNuova scelta: ");
            choice = scan.nextInt();
        }
    }
}