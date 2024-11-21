/*
Luca Bisognin - 21/11/2024
Classe Prodotto per creare gli oggetti da inserire nel frigorifero virtuale
*/

public class Prodotto {
    // attributi
    String codice;
    String descrizione;
    int giornoScadenza;
    int meseScadenza;
    int annoScadenza;
    int calorie;

    // costruttore
    Prodotto(String codice, String descrizione, int giornoScadenza, int meseScadenza, int annoScadenza, int calorie) {
        this.codice = codice;
        this.descrizione = descrizione;
        this.giornoScadenza = giornoScadenza;
        this.meseScadenza = meseScadenza;
        this.annoScadenza = annoScadenza;
        this.calorie = calorie;
    }
}
