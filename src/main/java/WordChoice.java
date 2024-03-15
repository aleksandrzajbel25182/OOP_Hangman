import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class WordChoice {

    private ArrayList<String> wordList = new ArrayList<>();

    public WordChoice() {
        ReaderFile();
    }

    private void ReaderFile() {

        try (InputStream is = this.getClass().getResourceAsStream("/words.txt");
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr)
        ) {
            //чтение построчно
            String str;
            while ((str = br.readLine()) != null) {
                wordList.add(str.toLowerCase());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String RandomWord() {

        Random random_method = new Random();
        int index = random_method.nextInt(wordList.size());
        return wordList.get(index);
    }
}
