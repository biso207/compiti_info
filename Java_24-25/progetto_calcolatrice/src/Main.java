import java.util.Scanner; // libreria per l'input

public class Main {
    // metodo per stampare il menù di scelta
    public static void printMenu() {
        System.out.print("""
                
                """ + """
                -----------------------------
                | [1] Addizione.            |
                | [2] Sottrazione.          |
                | [3] Moltiplicazione.      |
                | [4] Divisione.            |
                | [5] Elevazione a potenza. |
                | [6] Vedi menù             |
                | [0] Chiudi software.      |
                -----------------------------
                """);
    }

    // metodo main
    public static void main(String[] args) {
        System.out.println("Calcolatrice");
        Scanner scanner = new Scanner(System.in);

        int a, b; // var intere per calcoli

        printMenu();
        System.out.print("Numero Calcolo: ");
        int choice = scanner.nextInt(); // var scelta calcolo

        while (choice!=0) {
            System.out.println("\nScegli i valori per 'a' e 'b'");
            System.out.print("a: ");
            a=scanner.nextInt();
            System.out.print("b: ");
            b=scanner.nextInt();

            // creazione oggetto calcolatrice
            Calcolatrice calcolo = new Calcolatrice(a, b);
            try {
                switch(choice) {
                    case 1:
                        System.out.println(a + "+" + b + " = " + calcolo.addizione());
                        break;
                    case 2:
                        System.out.println(a + "-" + b + " = " + calcolo.sottrazione());
                        break;
                    case 3:
                        System.out.println(a + "*" + b + " = " + calcolo.moltiplicazione());
                        break;
                    case 4:
                        System.out.println(a + "/" + b + " = " + calcolo.divisione());
                        break;
                    case 5:
                        System.out.println(a + "^" + b + " = " + calcolo.potenza());
                        break;
                    case 6:
                        printMenu();
                        break;
                    default:
                        System.out.println("Numero inesistente.");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            System.out.print("\nNumero Calcolo: ");
            // nuova scelta calcolo
            choice = scanner.nextInt();
        }

        System.out.println("\nCalcolatrice spenta!");
    }
}