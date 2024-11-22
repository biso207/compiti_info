/*
Luca Bisognin - 15/11/2024
classe Barca del progetto Barche. Contiene metodi e attributi per creare oggetti 'Barca'
*/

public class Barca {
    // attributi
    private final String nome, nazione, tipologia;
    private final int lunghezza, stazza;

    Barca(String nome, String nazione, String tipologia, int lunghezza, int stazza) {
        this.nome = nome;
        this.nazione = nazione;
        this.tipologia = tipologia;
        this.lunghezza = lunghezza;
        this.stazza = stazza;
    }

    public int getStazza() {
        return stazza;
    }

    public int getLunghezza() {
        return lunghezza;
    }

    public String getTipologia() {
        return tipologia;
    }

    public String getNazione() {
        return nazione;
    }

    public String getNome() {
        return nome;
    }

    public String toString() {
        return (" Barca a " + tipologia + " '" + nome + "'.\n Nazione: " + nazione + "\n Lunghezza: " +
                lunghezza + " metri\n Stazza: " + stazza + " tonnellate.\n");
    }

}
