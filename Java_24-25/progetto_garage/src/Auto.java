/*
Luca Bisognin - 7/11/2024 - classe Auto che eredita Veicolo
*/

public class Auto extends Veicolo {
    int cavalli;

    Auto(String marca, String modello, int prezzo, int cavalli) {
        super(marca, modello, prezzo);
        this.cavalli = cavalli;
    }

    @Override
    // metodo toString per stampa informazioni dell'auto
    public String toString() {
        return "Auto '" + marca + " " + modello + "'. Prezzo: €" + prezzo + " e potenza " + cavalli + " cv.";
    }
}
