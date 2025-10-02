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
        HashMap<String, Integer> classifica = getStringIntegerHashMap();

        // lettura punti squadra da input del nome della squadra
        System.out.println("""
                Digita il nome della squadra per conoscerne i punti
                ! La prima lettera deve essere maiuscola !
                0 per chiudere.
                """);
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.print("Nome squadra: ");
            String n = s.nextLine();

            // chiusura ciclo
            if (n.equals("0")) break;

            // squadra non presente
            else if (!classifica.containsKey(n)) System.out.println("Squadra NON in Serie A!");

            // stampa punti squadra scelta
            else System.out.println(n + " - " + classifica.get(n) + " punti.");
        }
    }

    private static HashMap<String, Integer> getStringIntegerHashMap() {
        HashMap<String, Integer> classifica = new HashMap<>();

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
        return classifica;
    }
}
