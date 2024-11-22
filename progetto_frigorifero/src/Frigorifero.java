/*
Luca Bisognin - 21/11/2024
Classe Frigorifero per gestire le azioni utente nel frigorifero virtuale
*/


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Frigorifero {
    // arraylist per contenere i prodotti
    ArrayList<Prodotto> listaProdotti = new ArrayList<>();
    // arraylist per contenere i codici
    ArrayList<String> listaCodici = new ArrayList<>();
    // hashmap per mappare i codici
    HashMap<String, Integer> mapCodici = new HashMap<>();

    // variabile per tenere traccia del numero di prodotti inseriti
    int numProdotti=0, index;

    // costruttore
    Frigorifero() {

        // AGGIUNTA BARCHE ESISTENTI ALL'ARRAYLIST
        // creazione FileReader
        FileReader readCodice = null;
        FileReader readDescrizione = null;
        FileReader readGScad = null;
        FileReader readMScad = null;
        FileReader readAScad = null;
        FileReader readCalorie = null;
        // dichiarazione scanner per lettura
        Scanner scanCodice;
        Scanner scanDescrizione;
        Scanner scanGScad;
        Scanner scanMScad;
        Scanner scanAScad;
        Scanner scanCalorie;

        // lettura prodotti da file e aggiunta all'arraylist
        try {
            readCodice = new FileReader("data/codici.txt");
            readDescrizione = new FileReader("data/descrizioni.txt");
            readGScad = new FileReader("data/giorni_scadenza.txt");
            readMScad = new FileReader("data/mesi_scadenza.txt");
            readAScad = new FileReader("data/anni_scadenza.txt");
            readCalorie = new FileReader("data/calorie.txt");

            // assegnazione dei file agli scanner
            scanCodice = new Scanner(readCodice);
            scanDescrizione = new Scanner(readDescrizione);
            scanGScad = new Scanner(readGScad);
            scanMScad = new Scanner(readMScad);
            scanAScad = new Scanner(readAScad);
            scanCalorie = new Scanner(readCalorie);

            // lettura attributi e aggiunta all'arraylist
            while (scanCodice.hasNextLine() && scanDescrizione.hasNextLine() && scanGScad.hasNextLine()
                    && scanMScad.hasNextLine() && scanAScad.hasNextLine() && scanCalorie.hasNextLine()) {

                // lettura singola riga da ogni scanner con trim()
                String codice = scanCodice.nextLine().trim();
                String descrizione = scanDescrizione.nextLine().trim();
                String giornoScadenza = scanGScad.nextLine().trim();
                String meseScadenza = scanMScad.nextLine().trim();
                String annoScadenza = scanAScad.nextLine().trim();
                String calorie = scanCalorie.nextLine().trim();

                // controllo righe vuote
                if (codice.isEmpty() || descrizione.isEmpty() || giornoScadenza.isEmpty() || meseScadenza.isEmpty()
                        || annoScadenza.isEmpty() || calorie.isEmpty()) {
                    continue; // salto al ciclo successivo
                }

                // conversione variabili da string a integer
                int g = Integer.parseInt(giornoScadenza);
                int m = Integer.parseInt(meseScadenza);
                int a = Integer.parseInt(annoScadenza);
                int cal = Integer.parseInt(calorie);

                // creazione oggetto
                Prodotto p = new Prodotto(codice, descrizione, g, m, a, cal);

                // aggiunta codice all'arraylist e hashmap
                listaCodici.add(codice);
                mapCodici.put(codice, numProdotti);

                // aggiunta prodotto all'arraylist
                listaProdotti.add(p);
                numProdotti++;
            }
        }
        catch (IOException e) {
            System.out.println("Errore durante la scrittura dei file: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // chiusura reader
            try {
                if (readCodice != null) readCodice.close();
                if (readDescrizione != null) readDescrizione.close();
                if (readGScad != null) readGScad.close();
                if (readMScad != null) readMScad.close();
                if (readAScad != null) readAScad.close();
                if (readCalorie != null) readCalorie.close();
            } catch (IOException e) {
                System.out.println("Errore durante la chiusura dei file: " + e.getMessage());
            }
        }
    }

    // metodo per verificare se un prodotto è scaduto
    public boolean calcoloData(int g, int m, int a) {

        // creazione oggetto localdate per recuperare la data odierna
        LocalDate dataOggi = LocalDate.now();

        // recupero giorno, mese e anno odierno
        int oggi = dataOggi.getDayOfMonth();
        int mese = dataOggi.getMonthValue();
        int anno = dataOggi.getYear();

        // return del calcolo scadenza. true=>scaduto; false=>non scaduto
        return g<=oggi && m<=mese && a<=anno;
    }

    // metodo 1 - aggiunta prodotto
    public void addProdotto(Scanner scanner) {
        // pulizia buffer
        scanner.nextLine();

        System.out.println("Inserisci un prodotto");

        // digitazione descrizione
        System.out.print("\nScrivi una sua descrizione (max 20 caratteri - tutto ciò oltre viene troncato): ");
        String descrizione = scanner.nextLine();

        // troncamento della stringa entro 20 caratteri
        if (descrizione.length() > 20) descrizione = descrizione.substring(0, 20);

        // inserimento data di scadenza
        System.out.print("\nInserisci la data di scadenza.\nMese: ");
        int m = scanner.nextInt();

        // controllo input mese
        while (m < 0 || m > 12) {
            System.out.print("Mese invalido. Riprova: ");
            m = scanner.nextInt();
        }

        System.out.print("Giorno: ");
        int g = scanner.nextInt();
        boolean invalid = true;

        // controllo digitazione data di scadenza
        while (invalid) {
            if (g<1) {
                System.out.print("Giorno invalido. Giorno: ");
                g = scanner.nextInt();
            }
            else if ((m == 11 || m==6 || m==4 || m==9) && g > 30) {
                System.out.print("Giorno invalido. Giorno: ");
                g = scanner.nextInt();
            }
            else if ((m == 12 || m==10 || m==8 || m==7 || m==5 || m==3 || m==1) && g > 31) {
                System.out.print("Giorno invalido. Giorno: ");
                g = scanner.nextInt();
            }
            else if (m==2 && g > 28) {
                System.out.print("Giorno invalido. Giorno: ");
                g = scanner.nextInt();
            }
            else invalid = false;
        }

        System.out.print("Anno: ");
        int a = scanner.nextInt();

        // inserimento calorie
        System.out.print("\nCalorie Prodotto: ");
        int cal = scanner.nextInt();

        // creazione codice univoco
        scanner.nextLine(); // pulizia buffer
        System.out.print("\nInserisci codice numerico (il codice rappresenta il prodotto): ");
        String codice = scanner.nextLine();
        listaCodici.add(codice); // aggiunta all'arraylist
        mapCodici.put(codice, numProdotti++);

        // creazione oggetto
        Prodotto p = new Prodotto(codice, descrizione, g, m, a, cal);
        listaProdotti.add(p);

        System.out.println("\nProdotto aggiunto con successo!");
    }

    // metodo 2 - rimozione prodotto
    public void removeProdotto(Scanner scanner) {
        if (listaProdotti.isEmpty()) System.out.println("Nessun prodotto in frigo!");
        else {
            scanner.nextLine(); // pulizia buffer
            System.out.println("Rimozione prodotto");
            System.out.print("\nDigita il codice prodotto (per vedere la lista prodotti digitare 'lista'): ");
            String codice = scanner.nextLine();

            // stampa della lista prodotti
            if (codice.equals("lista") || codice.equals("Lista")) {
                printProdotti();
                System.out.print("\nCodice: ");
                codice = scanner.nextLine();
            }

            // input codice inesistente
            while (!listaCodici.contains(codice)) {
                System.out.print("Codice inesistente. Riprova.\nCodice: ");
                codice = scanner.nextLine();

                if (codice.equals("lista")) {
                    printProdotti();
                    System.out.print("\nCodice: ");
                    codice = scanner.nextLine();
                }
            }

            // digitazione data di scadenza
            System.out.print("Inserisci la data di scadenza.\nMese: ");
            int m = scanner.nextInt();
            while (m < 1 || m > 12) {
                System.out.print("Mese invalido. Riprova: ");
                m = scanner.nextInt();
            }

            System.out.print("Giorno: ");
            int g = scanner.nextInt();
            boolean invalid = true;

            // controllo digitazione giorno di scadenza
            while (invalid) {
                if (g<1) {
                    System.out.print("Giorno invalido. Giorno: ");
                    g = scanner.nextInt();
                }
                else if ((m == 11 || m==6 || m==4 || m==9) && g > 30) {
                    System.out.print("Giorno invalido. Giorno: ");
                    g = scanner.nextInt();
                }
                else if ((m == 12 || m==10 || m==8 || m==7 || m==5 || m==3 || m==1) && g > 31) {
                    System.out.print("Giorno invalido. Giorno: ");
                    g = scanner.nextInt();
                }
                else if (m==2 && g > 28) {
                    System.out.print("Giorno invalido. Giorno: ");
                    g = scanner.nextInt();
                }
                else invalid = false;
            }

            System.out.print("Anno: ");
            scanner.nextInt();

            // recupero posizione del prodotto tramite la mappa del codice di riferimento
            int pos = mapCodici.get(codice);
            // rimozione prodotto
            listaProdotti.remove(pos);
        }

        System.out.println("Prodotto rimosso con successo!");
    }

    // metodo 3 - stampa prodotti
    public void printProdotti() {
        if (listaProdotti.isEmpty()) System.out.println("Nessun prodotto in frigo!");
        else {
            System.out.println("Lista prodotti");

            // indice per numero prodotti
            index = 1;

            // stampa con for-each dei prodotti
            for (Prodotto p : listaProdotti) {
                System.out.println("Prodotto [" + index + "]\n" + p);
                index++;
            }
        }
    }

    // metodo 4 - stampa prodotti scaduti
    public void printScaduti() {
        if (listaProdotti.isEmpty()) System.out.println("Nessun prodotto in frigo!");
        else {
            System.out.println("Lista prodotti scaduti");

            // indice per numero prodotti
            index = 1;
            int contScaduti=0;

            // stampa con for-each dei prodotti
            for (Prodotto p : listaProdotti) {
                boolean isScaduto = calcoloData(p.getGiornoScadenza(), p.getMeseScadenza(), p.getAnnoScadenza());
                if (isScaduto) {
                    System.out.println("Prodotto [" + index + "]\n" + p);
                    index++;
                    contScaduti++;
                }
            }

            if (contScaduti==0) System.out.println("\nNessun prodotto scaduto!");
        }
    }

    // metodo 5 - calcolo numero confezioni stesso prodotto
    public void calcConf(Scanner scanner) {
        if (listaProdotti.isEmpty()) System.out.println("Nessun prodotto in frigo!");
        else {
            scanner.nextLine(); // pulizia buffer
            System.out.println("Ricerca prodotto");
            System.out.print("\nDigita il codice prodotto (per vedere la lista prodotti digitare 'lista'): ");
            String codice = scanner.nextLine();

            // stampa della lista prodotti
            if (codice.equals("lista") || codice.equals("Lista")) {
                printProdotti();
                System.out.print("\nCodice: ");
                codice = scanner.nextLine();
            }

            // input codice inesistente
            while (!listaCodici.contains(codice)) {
                System.out.print("Codice inesistente. Riprova.\nCodice: ");
                codice = scanner.nextLine();

                if (codice.equals("lista")) {
                    printProdotti();
                    System.out.print("\nCodice: ");
                    codice = scanner.nextLine();
                }
            }

            int contProdotti=0;
            for (String c : listaCodici) {
                if (c.equals(codice)) {
                    contProdotti++;
                }
            }


            // miglioria del testo
            String verbo="sono";
            String parola1="presenti";
            String parola2="prodotti";
            if (contProdotti==1){
                verbo="è";
                parola1="presente";
                parola2="prodotto";
            }

            System.out.println("Nel frigorifero " + verbo + " " + parola1 + " " + contProdotti + " " + parola2 + " con codice " + codice + ".");
        }
    }

    // metodo 6 - salvataggio stato frigorifero
    public void save() {
        FileWriter writeCodice = null;
        FileWriter writeDescrizione = null;
        FileWriter writeG = null;
        FileWriter writeM = null;
        FileWriter writeA = null;
        FileWriter writeCalorie = null;

            // salvataggio attributi barche nel porto
        try {
            writeCodice = new FileWriter("data/codici.txt");
            writeDescrizione = new FileWriter("data/descrizioni.txt");
            writeG = new FileWriter("data/giorni_scadenza.txt");
            writeM = new FileWriter("data/mesi_scadenza.txt");
            writeA = new FileWriter("data/anni_scadenza.txt");
            writeCalorie = new FileWriter("data/calorie.txt");

            for (Prodotto p: listaProdotti) {
                if (p!=null) {
                    writeCodice.write(p.getCodice() + "\n"); // codice
                    writeDescrizione.write(p.getDescrizione() + "\n"); // descrizione
                    writeG.write(p.getGiornoScadenza() + "\n"); // giorno scadenza
                    writeM.write(p.getMeseScadenza() + "\n"); // mese scadenza
                    writeA.write(p.getAnnoScadenza() + "\n"); // anno scadenza
                    writeCalorie.write(p.getCalorie() + "\n"); // calorie
                }
            }
        }
        catch (IOException e) {
            System.out.println("Errore durante la scrittura dei file: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // chiusura writer
            try {
                if (writeCodice != null) writeCodice.close();
                if (writeDescrizione != null) writeDescrizione.close();
                if (writeG != null) writeG.close();
                if (writeM != null) writeM.close();
                if (writeA != null) writeA.close();
                if (writeCalorie != null) writeCalorie.close();
            } catch (IOException e) {
                System.out.println("Errore durante la chiusura dei file: " + e.getMessage());
            }
        }

        System.out.println("\nSalvataggio completato.");
    }
}
