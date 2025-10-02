public class Calcolatrice {
    // attributi
    private double a;
    private double b;

    // costruttore calcolatrice
    public Calcolatrice(double a, double b) {
        this.a = a;
        this.b = b;
    }

    // metodi getter e setter
    // getter a
    public double getA() {
        return a;
    }

    // getter b
    public double getB() {
        return b;
    }

    // setter a
    public void setA(double a) {
        this.a = a;
    }

    // setter b
    public void setB(double b) {
        this.b = b;
    }

    // METODI PER CALCOLI
    // addizione
    public double addizione() {
        return a+b;
    }

    // sottrazione
    public double sottrazione() {
        return a-b;
    }

    // moltiplicazione
    public double moltiplicazione() {
        return a*b;
    }

    // divisione
    public double divisione() {
        if (b==0) {
            System.out.println("Impossibile dividere per 0");
            return 0;
        }
        return a/b;
    }

    // elevazione a potenza
    public double potenza() { return Math.pow(a, b); }
}