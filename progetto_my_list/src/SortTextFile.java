// Luca Bisognin - 20/5/2025
// classe per eseguire operazioni sul file di testo letto

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class SortTextFile {
    // percorso dei file
    private String path, newFilePath;
    // lista ordinata
    private ListaOrdinata<String> orderedFile;

    // costruttore
    public SortTextFile(String path, String newFilePath) {
        this.path = path;
        this.newFilePath = newFilePath;
    }

    // metodo per leggere il file e salvarlo in una lista ordinata
    public void sort() throws IOException {
        // creazione oggetti
        orderedFile = new ListaOrdinata<>(); // lista ordinata
        FileReader fr = new FileReader(path); // reader del file
        Scanner sc = new Scanner(fr); // scanner del file

        // lettura righe file e salvataggio in lista ordinata
        while (sc.hasNextLine()) {
            orderedFile.add(sc.nextLine());
        }
    }

    // metodo per salvare i valori ordinata in un file
    public void saveFile() throws IOException {
        // salvataggio valori in un nuovo file
        try (FileWriter fw = new FileWriter(newFilePath);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (int i=0; i<orderedFile.size(); i++) {
                bw.write(orderedFile.get(i));
                bw.newLine();
            }
        }
    }
}
