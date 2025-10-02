/*
Classe Main per la lettura dei comuni italiani e la stampa del numero di province per città
Luca Bisognin - 19/09/2025 - 5CIN
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ComuniItaliani {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String csvFile = "data/Elenco-comuni-italiani.csv";

        // Liste per province e comuni
        ArrayList<String> province = new ArrayList<>();
        ArrayList<String> comuni = new ArrayList<>();

        // try-catch per la lettura del file
        try {
            Scanner fileScanner = new Scanner(new File(csvFile));

            // salta intestazione
            if (fileScanner.hasNextLine()) fileScanner.nextLine();

            while (fileScanner.hasNextLine()) {
                // scanner delle righe
                String line = fileScanner.nextLine();
                if (line.trim().isEmpty()) continue;

                // splitter del csv
                String[] parts = line.split(";");
                if (parts.length < 3) continue;

                String comune = parts[0].trim();
                String provincia = parts[2].trim();

                comuni.add(comune);
                province.add(provincia);
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Errore: file non trovato!");
            return;
        }

        // input provincia da cercare
        System.out.print("Digita una provincia: ");
        String citta = input.nextLine();

        // nome tutto minuscolo
        citta = citta.toLowerCase();
        // nome tutto maiuscolo
        citta = citta.toUpperCase();

        int cont = 0;
        // for-each sulle province
        for (String s : province) {
            if (s.equals(citta)) cont++;
        }

        if (cont > 0) System.out.println(citta + " → " + cont + " comuni");
        else System.out.println("Provincia '" + citta + "' non trovata!");
    }
}




