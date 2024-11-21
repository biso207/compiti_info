/*
Luca Bisognin - 21/11/2024
Progetto MyArray per creare una classe che crea array dinamici.
*/

public class Main {
    public static void main(String[] args) {
        MyArray array = new MyArray(5, 5);
        array.add(10);
        array.add(20);
        array.add(30);
        array.add(40);
        array.add(50);
        array.add(60);

        System.out.println(array);
    }
}
