/*
Luca Bisognin - 9/1/2025
Classe Contatto per creare il singolo contatto per la rubrica
*/

public class Contatto {
    // attributi
    private final String nome, cognome, soprannome;

    // costruttore
    Contatto(String nome, String cognome, String soprannome) {
        this.nome = nome;
        this.cognome = cognome;
        this.soprannome = soprannome;
    }

    // costruttore secondario per creare nomi casuali
    Contatto() {
        nome = makeString();
        cognome = makeString();
        soprannome = "biso";
    }

    // metodo per generare stringhe random
    private String makeString() {
        int len = (int) (Math.random()*10+2);

        String res = "";

        for (int i = len; i > 0; i--) {
            res += "A" + ((char) (Math.random()*26+65));
        }

        return res;
    }

    // metodo toString
    public String toString() {
        return "'" + soprannome + "', " + nome + " " + cognome + ".";
    }

    // GETTER //
    // nome
    public String getNome() {
        return nome;
    }

    // cognome
    public String getCognome() {
        return cognome;
    }

    // soprannome
    public String getSoprannome() {
        return soprannome;
    }
}
