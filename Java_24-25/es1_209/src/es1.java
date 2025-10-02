/*
 * luca bisognin
 * scuola - esercizio 1 - 20/09/2024
*/

// import librerie esterne
import java.util.Scanner; // scanner

public class es1 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in); // creazione scanner
        System.out.println("Stampa rettangolo a dimensione personalizzata");

        /* dichiarazione varabili di base
        int a = 1;
        int b = 2;
        int c = a+b;

        // prove output
        System.out.println("\nPROVE OUTPUT");
        System.out.println("i valori di base sono a " + a + ", b " + b + ", c " + c +":");
        System.out.println("Hello, World!");
        
        // stampa divertente
        System.out.println("\nSTAMPA DIVERTENTE");
        for (int i=0; i<10; i++) {
            System.out.print("* * ");
            for (int j=0; j<len; j++) {
                if (i==0 || i==9)System.out.print("* ");
                else System.out.print("  ");
            }
            System.out.println("* *");
        }

        System.out.print("\nPROVE INPUT/OUTPUT\ndigita un numero: ");
        int num = scanner.nextInt();
        System.out.println("hai digitato " + num);
        */

        // stampa con lunghezza personalizzata

        // input delle dimensioni
        System.out.print("\nQuanto vuoi largo il rettangolo? ");
        int width = scanner.nextInt();
        System.out.print("Quanto vuoi alto il rettangolo? ");
        int height = scanner.nextInt();
        System.out.println("\nEcco il tuo rettangolo");

        // stampa rettangolo con dimensione personalizzata
        for (int i=0; i<height; i++) {
            System.out.print("**");
            for (int j=0; j<width-4; j++) {
                if (i==0 || i==height-1)System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println("**");
        }

        // chiusura scanner
        scanner.close();
    }
}
