/*
 ** 4CIN - classe "Classe" che contiene 3 oggetti Studente
 ** Luca Bisognin - 10/10/2024
 */

import java.util.Arrays;
import java.util.Scanner;

public class Classe {
    //private Studente uno, due, tre;
    Studente[] listaStudenti;
    int index;

    public Classe(int len) {
        listaStudenti = new Studente[len];
        index = 0;
    }

    public void leggiStudente(Scanner scanner) {

        // input valori nome e cognome
        System.out.print("nome: ");
        String nome = scanner.nextLine();
        System.out.print("cognome: ");
        String cognome = scanner.nextLine();

        listaStudenti[index] = new Studente(nome, cognome);
        index++;
    }

    // metodo per stampare le info degli oggetti studente
    public void infoClass() {
        System.out.println("\nLa classe è composta da: ");
        for (Studente studente : listaStudenti) {
            System.out.println(studente);
        }
    }

    // metodo per rimuovere l'ultimo studente automaticamente
    public void autoRemoveStudent() {

        if (listaStudenti.length < 1) {
            System.out.println("Nessuno studente per la classe!");
        }
        else {
            listaStudenti[index-1] = null;

            System.out.println("Lista Studenti Aggiornata");
            for (int i=0; i< listaStudenti.length; i++) {
                if (listaStudenti[i] == null) {
                    System.out.println("[" + (i + 1) + "] Studente Rimosso");
                }
                else {
                    System.out.println("[" + (i + 1) + "] " + listaStudenti[i]);
                }
            }
        }
    }

    // metodo per rimuovere uno studente specifico
    public void removeStudent(Scanner scanner) {
        System.out.println("Studenti");
        for (int i=0; i< listaStudenti.length; i++) {
            System.out.println("[" + (i+1) + "] " + listaStudenti[i]);
        }

        System.out.print("\nSeleziona uno studente da rimuovere: ");
        int numStudent;
        try {
            numStudent = scanner.nextInt();
            while(numStudent>index || numStudent<index) {
                System.out.println("studente inesistente per il numero " + numStudent + ".\nScegli di nuovo: ");
                numStudent = scanner.nextInt();
            }
            listaStudenti[numStudent-1] = null;

        } catch (Exception e) {
            System.out.println("Input invalido!");
        }

        System.out.println("Lista Studenti Aggiornata");
        for (int i=0; i< listaStudenti.length; i++) {
            if (listaStudenti[i] == null) {
                System.out.println("[" + (i + 1) + "] Studente Rimosso");
            }
            else {
                System.out.println("[" + (i + 1) + "] " + listaStudenti[i]);
            }
        }
    }


}