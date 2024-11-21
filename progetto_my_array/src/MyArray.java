/*
Luca Bisognin - 21/11/2024
Classe MyArray per creare oggetti array dinamici.
*/

public class MyArray {
    // ATTRIBUTI
    int[] v;
    int len, size, delta;

    // COSTRUTTORE
    public MyArray(int startSize, int deltaSize) {
        // init di size con operatore ternario
        size =  (startSize <= 0 ? 100 : startSize);

        // init dell'array
        v = new int[size];
        len = 0;
        // assegnazione a delta del valore di ingrandimento dell'array
        delta = (deltaSize <= 0 ? 10 : deltaSize);
    }

    // METODI
    // metodo per l'aggiunta di valori all'array
    public void add(int newValue) {
        // caso array al completo
        if (len>=size) {
            // nuovo array con dimensione maggiore
            int[] newV = new int[size+delta];

            // copiatura degli elementi dal vecchio array a quello nuovo
            System.arraycopy(v, 0, newV, 0, len);

            // cambio puntatore di v a newV
            v=newV;

            // incremento di size per prossima creazione
            size += delta;
        }

        // aggiunta valore all'array
        v[len++] = newValue;
    }

    // metodo per recuperare la lunghezza dell'array
    public int size() {
        return len;
    }

    // metodo per la stampa degli elementi
    public String toString() {
        StringBuilder s = new StringBuilder();

        // for per concatenazione alla stringa i suoi valori
        for (int i=0; i<len; i++) {
            s.append(v[i]).append("\n");
        }

        // return della stringa costruita
        return s.toString();
    }
}
