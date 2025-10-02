import java.util.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BookParser {
    private static final String[] SPECIAL_CHARS = {",", ";", ":", ".", "!", "?", "\""};
    BookReader book;

    public BookParser(String file_path) throws FileNotFoundException, NullPointerException  {
        this.book = new BookReader(file_path);
    }

    public List<String> parseLine(){
        if (!book.hasNextLine()) return null;
        String line = book.nextLine();
        String[] words = line.split(" "); 
        return removeSpecialChars(words) // ah una cosa nuova!
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toUnmodifiableList());
    }

    private ArrayList<String> removeSpecialChars(String[] words){
        ArrayList<String> parsed_words = new ArrayList<>(words.length);
        for (String word : words) {
            for (String special_char : SPECIAL_CHARS) {
                word = word.replace(special_char, "");
            }
            parsed_words.add(word);
        }
        return parsed_words;
    }

    
}
