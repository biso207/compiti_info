import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BookReader {
    private final String FILE_PATH;
    private Scanner book_scanner;

    public BookReader(String file_path) throws FileNotFoundException, NullPointerException {
        FILE_PATH = file_path;
        openBook();
    }
    
    private void openBook() throws FileNotFoundException, NullPointerException{
        File file = new File(FILE_PATH);
        book_scanner = new Scanner(file);
    }

    public boolean hasNextLine(){
        return book_scanner.hasNextLine();
    }

    public String nextLine(){
        return book_scanner.nextLine();
    }
    
}
