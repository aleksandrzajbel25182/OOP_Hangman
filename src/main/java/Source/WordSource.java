/**
 * @autor Alexander Zaybel
 * @version 1.1
 */

package Source;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type Word source.
 * WordSource class that implements the interface {@link IntputSource}
 */
public class WordSource implements IntputSource {

  private String conection;

  private ArrayList<String> wordList;

  /**
   * Instantiates a new Word source.
   *
   * @param conection the connection string to the source
   */
  public WordSource(String conection) {
    this.conection = conection;
    wordList = new ArrayList<>();
  }

  @Override
  public String randomWord() {

    Random random_method = new Random();
    int index = random_method.nextInt(wordList.size());
    return wordList.get(index);

  }


  /**
   * A method that reads line-by-line data from a file
   */
  public void readFile() {

    BufferedReader reader;

    try {

      reader = new BufferedReader(new FileReader(this.conection));
      String line;

      while ((line = reader.readLine()) != null) {
        wordList.add(line.toLowerCase());
      }

      reader.close();

    } catch (FileNotFoundException e) {
      System.out.println("Error! The file was not found!");
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
