/*
Luca Bisognin - 21/11/2024
Classe Prodotto per creare gli oggetti da inserire nel frigorifero virtuale
*/

public class Prodotto {
    // attributi
    private final String codice, descrizione;
    private final int giornoScadenza, meseScadenza, annoScadenza, calorie;

    // costruttore
    Prodotto(String codice, String descrizione, int giornoScadenza, int meseScadenza, int annoScadenza, int calorie) {
        this.codice = codice;
        this.descrizione = descrizione;
        this.giornoScadenza = giornoScadenza;
        this.meseScadenza = meseScadenza;
        this.annoScadenza = annoScadenza;
        this.calorie = calorie;
    }

    // getter
    public int getMeseScadenza() {
        return meseScadenza;
    }

    public String getCodice() {
        return codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getGiornoScadenza() {
        return giornoScadenza;
    }

    public int getAnnoScadenza() {
        return annoScadenza;
    }

    public int getCalorie() {
        return calorie;
    }
}
