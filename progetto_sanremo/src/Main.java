/*
Luca Bisognin - 14/11/2024
classe Main progetto Sanremo
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // file con i concorrenti
            File file = new File("progetto_sanremo/data/concorrenti.txt");

            // scanner per leggere il contenuto del file
            Scanner scanFile = new Scanner(file);
            // arraylist per contenere i concorrenti
            ArrayList<String> concorrenti = new ArrayList<>();

            /*metodo avanzato
            ArrayList<String> concorrenti = new ArrayList<>(Arrays.asList(Objects.requireNonNull(file.list())));
            */

            // lettura file e aggiunta concorrenti all'ArrayList
            while (scanFile.hasNextLine()) {
                concorrenti.add(scanFile.nextLine());
            }

            // stampa elenco concorrenti
            int j=1;
            for (String s : concorrenti) {
                System.out.println(j + ") " + s);
                j++;
            }

            scanFile.close();

        } catch (FileNotFoundException e) {
            System.out.println("File non trovato: " + e.getMessage());
        }
    }
}
