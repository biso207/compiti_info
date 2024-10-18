/*
progetto Serie A -
Luca Bisognin - 17/10/2024
*/

public class Main  {
    public static void main(String[] args) {
        // costruzione oggetti campionato
        Campionato serieA = new Campionato("files/serieA.csv");
        Campionato bundesliga = new Campionato("files/bundesliga.csv");
        System.out.println(serieA);
        System.out.println(bundesliga);
    }
}

