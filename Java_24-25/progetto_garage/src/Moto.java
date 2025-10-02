/*
Luca Bisognin - 7/11/2024 - classe Moto che eredita Veicolo
*/

public class Moto extends Veicolo {
    int cilindrata;

    public Moto(String marca, String modello, int prezzo, int cilindrata) {
        super(marca, modello, prezzo);
        this.cilindrata = cilindrata;
    }

    @Override
    // metodo toString per stampa informazioni della moto
    public String toString() {
        return "Moto '" + marca + " " + modello + "'. Prezzo: €" + prezzo + " e cilindrata " + cilindrata + " cv.";
    }
}
