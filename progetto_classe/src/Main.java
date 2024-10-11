/*
 ** 4CIN - programma che simula la classe più strana del mondo
 ** Luca Bisognin - 10/10/2024
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Programma 4CIN avviato!");

        // oggetto per l'input
        Scanner scanner = new Scanner(System.in);

        // input numero studenti
        System.out.print("Numero studenti: ");
        int len = scanner.nextInt();

        // creazione oggetto classe
        Classe quartaCin = new Classe(len);

        scanner.nextLine(); // pulizia buffer
        for (int i=0; i<len; i++) {
            System.out.println("Studente " + (i+1));
            quartaCin.leggiStudente(scanner);
        }

        // stampa info oggetti della classe "Classe"
        quartaCin.infoClass();

        System.out.print("\n[1] Auto Remove Student\n[2] Remove Student\nScegli: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            // rimozione automatica studente
            quartaCin.autoRemoveStudent();
        }
        else if (choice == 2) {
            // rimozione manuale studente
            quartaCin.removeStudent(scanner);
        }
        else System.out.println("Scelta errata");

        System.out.println("\nProgramma 4CIN terminato!");

        scanner.close();
    }
}
