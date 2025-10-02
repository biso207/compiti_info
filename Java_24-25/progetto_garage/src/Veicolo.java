/*
Luca Bisognin - 7/11/2024 - classe Veicolo
*/

public class Veicolo {
    String marca;
    String modello;
    int prezzo;

    // costruttore
    public Veicolo(String marca, String modello, int prezzo) {
        this.marca = marca;
        this.modello = modello;
        this.prezzo = prezzo;
    }

    @Override
    // metodo toString per stampa informazioni veicolo
    public String toString() {
        return "Veicolo '" + marca +  " " + modello + "'. Prezzo: €" + prezzo;
    }
}
