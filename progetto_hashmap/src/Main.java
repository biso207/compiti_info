/*
Luca Bisognin - 8/1/2025
Progetto hashmap per mappare le squadre di Serie A e il loro punteggio e crearne poi la classifica
*/

import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // hashmap per le squadre
        HashMap<String, Integer> classifica = new HashMap<String, Integer>();

        try {
            FileReader file = new FileReader("data/serieA.csv"); // file con la classifica
            Scanner scanFile = new Scanner(file); // scanner per lettura del file

            // lettura file
            while (scanFile.hasNextLine()) {
                String line = scanFile.nextLine();
                String[] fields = line.split(",");

                // recupero valori singola linea csv
                String nome = fields[0].trim();
                int punti = Integer.parseInt(fields[1].trim());

                // aggiunta squadra con rispettivi punti
                classifica.put(nome, punti);
            }

            // chiusura scanner
            scanFile.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Classifica Serie A");
        // stampa squadre
        for (String key : classifica.keySet()) {
            System.out.println(key + " - " + classifica.get(key));
        }
        System.out.println("""
                La classifica è aggiornata al 8/1/2025.
                Non tutte le squadre hanno giocato lo stesso numero di partite.
                """);
    }
}
