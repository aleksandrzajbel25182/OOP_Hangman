package Source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class WordSource implements IntputSource {

    private String conection;
    private ArrayList<String> wordList;

    public WordSource(String conection) {
        this.conection = conection;
        wordList = new ArrayList<>();
    }

    public String RandomWord() {

        Random random_method = new Random();
        int index = random_method.nextInt(wordList.size());
        return wordList.get(index);
    }

    @Override
    public ArrayList<String> readerFile() {
        try (InputStream is = this.getClass().getResourceAsStream(this.conection);
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr)
        ) {
            //чтение построчно
            String str;
            while ((str = br.readLine()) != null) {
                wordList.add(str.toLowerCase());
            }

            return wordList;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }
}
