/*
Luca Bisognin - 14/11/2024
classe Main del progetto Sanremo
*/

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner file = new Scanner("concorrenti.txt");
        ArrayList<String> concorrenti = new ArrayList<>();

        /*metodo avanzato
        ArrayList<String> concorrenti = new ArrayList<>(Arrays.asList(Objects.requireNonNull(file.list())));
        */

        while (file.hasNextLine()) {
            concorrenti.add(file.nextLine());
        }

        System.out.println(concorrenti);
    }
}