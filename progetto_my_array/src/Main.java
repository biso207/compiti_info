/*
Luca Bisognin - 21/11/2024
Progetto MyArray per creare una classe che crea array dinamici.
*/

public class Main {
    public static void main(String[] args) {
        MyArray array = new MyArray(100000, 100000);

        for (int i = 0; i < 100000000; i++) {array.add(i);}

        System.out.println(array);
    }
}
