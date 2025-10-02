/*
Luca Bisognin - 9/1/2025
Main del progetto Rubrica
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* creazione oggetto contatto
        Contatto c = new Contatto("Luca", "Bisognin", "3206943010");
        Contatto c2 = new Contatto(); // contatto con nome casuale
        */

        // scanner per gli input
        Scanner scanner = new Scanner(System.in);

        // oggetto rubrica per salvare 1000 contatti
        Rubrica r = new Rubrica();

        System.out.print("Scegli un soprannome - (0 per chiudere): ");
        String soprannome = scanner.nextLine();

        while (!soprannome.equals("0")) {
            // stampa info contatto scelto
            r.printContatto(soprannome);

            System.out.print("Scegli un soprannome: ");
            soprannome = scanner.nextLine();
        }

        System.out.println("software spento");
    }
}
