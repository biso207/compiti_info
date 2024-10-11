/*
 ** 4CIN - classe "Studente"
 ** Luca Bisognin - 10/10/2024
 */


public class Studente {
    // attributi

    /* MODIFICATORI DI VISIBILITÀ
     ** private --> attributo accessibile solo alla classe di appartenenza
     ** protected --> attributo accessibile dalla classe di appartenenza, dal package di appartenenza e dalle sottoclassi
     ** default --> attributo accessibile da ogni parte del package (default=nessuna dichiarazione)
     */

    private String nome, cognome;

    // costruttore
    public Studente(String nome, String cognome) {
        // this è un riferimento a se stesso, un puntatore verso l'oggetto stesso
        this.nome = nome;
        this.cognome = cognome;
    }

    // getter nome
    public String getName() {
        return nome;
    }

    // getter cognome
    public String getCognome() {
        return cognome;
    }

    public String toString() {
        return nome + " " + cognome;
    }
}
