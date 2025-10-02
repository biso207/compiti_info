/*
Luca Bisognin - 14/11/2024
Classe Main del progetto Generics
*/

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // creazione istanze di Coppia
        Coppia<String, Integer> c1 = new Coppia<>("Luca", 17);
        Coppia<Double, Long> c2 = new Coppia<>(1.87, 200L);

        // creazione dell'array
        ArrayList<Double> v = new ArrayList<>();
        v.add(35.7);
        v.add((double)69);

        System.out.println(v);
        System.out.println(c1);
        System.out.println(c2);
    }
}
