/*
Classe Squadra - permette la creazione delle squadre di un campionato
Luca Bisognin - 17/10/2024
*/


public class Squadra {
    private String nome;

    // costruttore
    public Squadra(String nome) {
        this.nome = nome;
    }

    // getter nome squadra
    public String getNome() {
        return nome;
    }

    // stampa nome squadra
    public String toString() {
        return "Squadra: " + nome;
    }
}
