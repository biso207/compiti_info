 public class Motorino {
    // INFORMATION HIDING o INCAPSULAMENTO

     // attributi classe
    // settati come "private" perché possono essere letti solo dalla classe stessa
    private float cilindrata;
    private String marca;
    private String modello;
    private String colore;

    // costruttore motorino
    Motorino(int c, String m, String model, String colour) {
        cilindrata = (float) c/1000000;
        marca = m;
        modello = model;
        colore = colour;
    }

    // settato come "public" così da poter prendere (get) il valore da ogni classe, anche esterna
    // il metodo "getter" permette di prendere il valore di un attributo privato
    public int getCilindrata() {
        return (int)(cilindrata*1000000);
    }

    public String getModel() {

        return modello;
    }

    public String getMarca() {
        return marca;
    }

    public String getColore() {
        return colore;
    }
}
