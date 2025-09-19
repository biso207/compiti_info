/*
Classe Main per la lettura dei comuni italiani e la stampa del numero di province per città
Luca Bisognin - 19/09/2025 - 5CIN
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ComuniItaliani {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Percorso del file CSV (cartella "data" nella root del progetto)
        String csvFile = "data/comuni.CSV";

        // Mappa provincia -> set di comuni
        Map<String, Set<String>> provinciaToComuni = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(csvFile), StandardCharsets.ISO_8859_1)) {
            String header = br.readLine(); // intestazione
            if (header == null) {
                System.err.println("⚠️ Il file è vuoto!");
                return;
            }

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                // Usa il separatore ';' (non la virgola)
                String[] parts = line.split(";");
                if (parts.length < 3) continue; // ci servono almeno Comune e Provincia

                String comune = parts[0].trim();
                String provincia = parts[2].trim();

                provinciaToComuni
                        .computeIfAbsent(provincia, k -> new HashSet<>())
                        .add(comune);
            }
        } catch (IOException e) {
            System.err.println("Errore durante la lettura: " + e.getMessage());
            return;
        }

        // Stampa dei risultati
        System.out.println("Digita una città italiana: ");
        String city = sc.nextLine();

        // nome tutto minuscolo
        city = city.toLowerCase();
        // nome tutto maiuscolo
        city = city.toUpperCase();

        boolean found = false;

        System.out.println("📊 Numero di comuni per provincia:");
        for (Map.Entry<String, Set<String>> entry : provinciaToComuni.entrySet()) {
            if (entry.getKey().equals(city)) {
                System.out.println(entry.getKey() + " → " + entry.getValue().size() + " comuni");
                found = true; break;
            }
        }

        if (!found) System.out.println(city + " non trovata!");
    }
}



