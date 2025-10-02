public class Rettangolo {
    float baseRettangolo; // Attributo
    float altezzaRettangolo; // Attributo

    public Rettangolo (float base , float altezza){ // Parametri
        this.baseRettangolo = base;
        this.altezzaRettangolo = altezza;
    }

    @Override
    public String toString() {
        return "Rettangolo [baseRettangolo=" + baseRettangolo + ", altezzaRettangolo=" + altezzaRettangolo + "]";
    }

    
    
}