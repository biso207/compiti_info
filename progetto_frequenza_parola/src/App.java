import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        BookParser parser = new BookParser("data/commedia.txt");
        
        HashMap<String, Integer> freq = new HashMap<>();
        while (true) {
            List<String> words = parser.parseLine();
            if(words == null) break;
            
            for (String word : words) {
                int value;
                if(freq.containsKey(word)){
                    value = freq.get(word)+1;
                }else{
                    value = 1;
                }
                freq.put(word, value);
            }          
        }

        /*
         creazione di una coda per ordinare l'hashmap 'freq'
         gli elementi String sono ordinati in base al valore Integer di riferimento
        */
        PriorityQueue<HashMap.Entry<String, Integer>> sortedQueue = new PriorityQueue<>(
                (a, b) -> b.getValue().compareTo(a.getValue())
        );
        sortedQueue.addAll(freq.entrySet());

        // conteggio 10 parole più frequenti
        HashMap<String, Integer> topWords = new LinkedHashMap<>(); // hashmap per le 10 parole più frequenti
        int otherCount = 0; // conteggio ripetizione altre parole (da 11 in avanti)
        int index = 0; // indice delle 10 parole più frequenti

        while (!sortedQueue.isEmpty()) {
            // rimozione e restituzione elemento con Integer più alto
            HashMap.Entry<String, Integer> entry = sortedQueue.poll();

            if (index < 10) {
                topWords.put(entry.getKey(), entry.getValue());
            } else {
                otherCount += entry.getValue();
            }
            index++;
        }

        // scrittura risultati dell'ordinamento nel file csv
        try (PrintWriter writer = new PrintWriter(new FileWriter("frequenza_parole.csv"))) {
            writer.println("Parola: Frequenza"); // "titolo" iniziale

            // stampa 10 parole più frequenti
            for (HashMap.Entry<String, Integer> entry : topWords.entrySet()) {
                writer.println(entry.getKey() + ": " + entry.getValue());
            }

            // stampa somma frequenza parole restanti
            writer.println("Altre: " + otherCount);
        }

        // messaggio di creazione corretta del csv
        System.out.println("CSV generato con successo: frequenza_parole.csv");
    }
}
