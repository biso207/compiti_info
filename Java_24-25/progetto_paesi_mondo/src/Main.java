/*
Luca Bisognin - 12/12/2024
Classe Main del progetto 'Paesi del Mondo'
*/

import java.io.File;

public class Main {
    public static void main(String[] args) {
        // file elenco paesi
        String file = "data/elenco_dei_paesi_mondo.csv";
        File f = new File(file);

        // oggetto CountriesOne
        CountriesOne c = new CountriesOne(f);

        System.out.println(c);
    }
}
