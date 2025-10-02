/*
Luca Bisognin - esercizio sull'ereditarietà che simula un garage di 100 veicoli
7/11/2024 - classe Main
*/

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // creazione ArrayList che contenga massimo 100 oggetti Veicolo
        ArrayList<Veicolo> garage = new ArrayList<Veicolo>(100);

        // aggiunta oggetti all'ArrayList
        Scanner scanFile = new Scanner("data\\garage.csv");
        /*while (scanFile.hasNextLine()) {
            Veicolo v = new Veicolo("Fiat", "Panda", 100, 15000);
            garage.add(v);
        }

         */

        // oggetto veicolo (generico)
        Veicolo v = new Veicolo("Fiat", "Panda 500", 15000);
        // oggetto moto (specifico estende veicolo)
        Moto m1 = new Moto("Fantic", "XMF", 5000, 125);
        // oggetto auto (specifico estende veicolo)
        Auto a1 = new Auto("Ferrari", "Purosangue", 400000, 725);

        garage.add(v);
        garage.add(m1);
        garage.add(a1);

        // stampa info oggetti nell'ArrayList
        for (Veicolo veicolo : garage) {
            System.out.println(veicolo);
        }
    }
}