/*
Luca Bisognin - 14/1/2025
Classe Rubrica per creare la rubrica con 1000 contatti
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class Rubrica {
    // attributi
    private String nome, cognome, soprannome;

    // hashmap per i 1000 contatti
    HashMap<String, Contatto> rubrica = new HashMap<>();

    // riga random e riga su cui si trova
    private int randomLine, line;

    // costruttore
    Rubrica() {
        try {
            // scanner per i nome
            Scanner scanName = new Scanner(new FileReader("data/nomi.txt"));

            // scanner per i cognomi
            Scanner scanCognome = new Scanner(new FileReader("data/cognomi.txt"));

            // scanner per i soprannomi
            Scanner scanSoprannome = new Scanner(new FileReader("data/soprannomi.txt"));

            // for per popolare la mappa di 1000 contatti
            for (int i=0; i<1000; i++) {
                // fine del file dei nomi raggiunta
                if (!scanName.hasNextLine()) {
                    // fine del file raggiunta
                    scanName.close();
                    scanName = new Scanner(new FileReader("data/nomi.txt"));
                }

                // fine del file dei cognomi raggiunta
                if (!scanCognome.hasNextLine()) {
                    // fine del file raggiunta
                    scanCognome.close();
                    scanCognome = new Scanner(new FileReader("data/cognomi.txt"));
                }

                // fine del file dei soprannomi raggiunta
                if (!scanSoprannome.hasNextLine()) {
                    // fine del file raggiunta
                    scanSoprannome.close();
                    scanSoprannome = new Scanner(new FileReader("data/soprannomi.txt"));
                }

                // lettura dati utente
                nome = scanName.nextLine();
                cognome = scanCognome.nextLine();
                soprannome = sceltaSoprannome(scanSoprannome);

                // creazione contatto
                Contatto c = new Contatto(nome, cognome, soprannome);

                // aggiunta contatto alla mappa con riferimento del soprannome
                rubrica.put(soprannome, c);
            }

            // chiusura scanner
            scanName.close();
            scanCognome.close();
            scanSoprannome.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // metodo per la scelta casuale del soprannome
    public String sceltaSoprannome(Scanner scanSoprannome) {
        line=0; // linea di partenza
        // scelta casuale della riga del soprannome (1-100)
        randomLine = (int) (Math.random()*100)+1;

        // lettura riga per riga delle linee del file per raggiungere quella scelta casualmente
        while (scanSoprannome.hasNextLine()) {
            line++;
            soprannome = scanSoprannome.nextLine();

            if (line == randomLine) break;
        }

        return soprannome;
    }

    // metodo per la stampa del contatto
    public void printContatto(String sp) {
        Contatto c = rubrica.get(sp);
        if (c == null) {
            System.out.println("Nessun contatto in rubrica con il soprannome '" + sp + "'");
        }
        else if (!rubrica.containsValue(c)) {
            System.out.println("Contatto non trovato");
        }
        else {
            System.out.println("Contatto trovato");
            System.out.println(c);
        }
    }
}
