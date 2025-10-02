/*
Luca Bisognin - 12/12/2024
Classe Paesi del progetto 'Paesi del Mondo'
- la classe permette la creazione e gestione degli oggetti Paese -
*/

public class Country {
    private final String name, continent, numPop;

    // costruttore
    public Country(String name, String continent, String numPop) {
        this.name = name;
        this.numPop = numPop;
        this.continent = continent;
    }

    // toString to print country info
    public String toString() {
        return name + " (" + continent + ") - " + numPop + " population";
    }
}
