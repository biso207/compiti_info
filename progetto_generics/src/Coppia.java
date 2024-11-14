/*
Luca Bisognin - 14/11/2024
Classe Coppia del progetto Generics
*/



public class Coppia<T1, T2> {
    T1 x;
    T2 y;

    public Coppia(T1 x, T2 y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "x vale " + x + " y vale " + y;
    }
}
