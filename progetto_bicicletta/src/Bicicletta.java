
public class Bicicletta {

    // attributi classe bicicletta
    int diamRuota;
    String colore;
    String taglia;
    String modello;

    // costruttore classe bicicletta
    Bicicletta(int d, String c, String t, String m) {
        diamRuota = d;
        colore = c;
        taglia = t;
        modello = m;
    }
    
    // metodo "presentazione" bici
    void presentanti() {
        System.out.println("La bici modello " + modello + " di colore " + colore + " e taglia " +  taglia + ", ha un diametro ruota di " + diamRuota + "cm.");
    }

    public static void main(String[] args) throws Exception {

        // creazione oggetti bicicletta
        Bicicletta bici1 = new Bicicletta(13, "Blu", "L", "Mountain-Bike");
        Bicicletta bici2 = new Bicicletta(15, "Bianca", "M", "Tandem");
        // stampa informazioni bicicletta
        bici1.presentanti();
        bici2.presentanti();

        // creazione oggetti motorino
        Motorino moto1 = new Motorino(125, "Kawasaki", "Model S", "nero");
        Motorino moto2 = new Motorino(250, "Honda", "Model 3", "blu");

        // stampa "presentazione" oggetti moto
        System.out.println("La " + moto1.getMarca() + " " + moto1.getModel() + " è di colore " + moto1.getColore() + " ed ha una cilindrata di " + moto1.getCilindrata() + " cc.");
        System.out.println("La " + moto2.getMarca() + " " + moto2.getModel() + " è di colore " + moto2.getColore() + " ed ha una cilindrata di " + moto2.getCilindrata() + " cc.");
    }
}
