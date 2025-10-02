/*
Luca Bisognin - 12/12/2024
Classe CountriesOne del progetto 'Paesi del Mondo'
- la classe permette la memorizzazione degli oggetti Paese tramite ArrayList -
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CountriesOne {
    // arraylist per la memorizzazione degli oggetti Paese
    private final ArrayList<Country> countries;

    private final File f;

    // costruttore
    public CountriesOne(File csvFile) {
        countries = new ArrayList<>();
        f = csvFile;

        // metodo per popolare l'arraylist
        populateCountries();
    }

    // metodo per popolamento dell'arraylist con gli oggetti paese
    public void populateCountries() {
        try (Scanner scanner = new Scanner(f)) {
            // skip prima e seconda riga
            scanner.nextLine();

            // iterazione per creare oggetti e popolare l'arraylist
            while (scanner.hasNextLine()) {
                // array string per leggere le righe del file csv
                String[] data = scanner.nextLine().split(";");

                // errore del numero errato di colonne nella riga recuperata
                if (data.length != 5) {
                    continue;
                }

                // popolamento arraylist con singoli oggetti Country
                countries.add(new Country(data[1], data[2], data[3]));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + f.getPath());
        }
    }

    // metodo per stampare l'elenco dei paesi
    public String toString() {
        // nessun paese presente
        if (countries.isEmpty()) {
            return "Nessun paese presente.";
        }

        // almeno un paese presente
        StringBuilder s = new StringBuilder("Elenco dei paesi:\n");
        for (Country c : countries) {
            s.append(c).append("\n");
        }

        return s.toString();
    }
}
