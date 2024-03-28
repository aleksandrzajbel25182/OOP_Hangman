/**
 * @autor Alexander Zaybel
 * @version 1.1
 */

import java.util.HashSet;
import stdin.CharSource;
import stdout.Answer;
import stdout.Gallows;


/**
 * The class of the game in which the logic of the game Gallows is located
 */
public class Game {

  /**
   * The field containing the hidden word
   */
  final private String word;

  /**
   * The constant of the maximum allowed attempts
   */
  private final static int MAX_ATTEMPT = 6;

  /**
   * User's input source
   */
  private CharSource source;

  /**
   * The mask of the hidden word in the form of "____"
   */
  private StringBuilder maskWord;

  /**
   * The answer is for the user
   */
  private Answer answer;

  /**
   * The gallows condition field
   */
  private Gallows gallow;

  /**
   * Characters used by the user
   */
  private HashSet<Character> usedChar = new HashSet<>();

  private int attempt = 0;

  /**
   * Creates a new Game, taking into account the {@link CharSource}, the hidden {@link Game#word},
   * the {@link Answer} for the user
   */
  public Game(CharSource source, String word, Answer answer) {
    this.source = source;
    this.word = word;
    this.answer = answer;
    this.maskWord = new StringBuilder("_".repeat(word.length()));
    this.gallow = new Gallows();
  }

  /**
   * The play method contains the logic of the game
   *
   * @return Returns the result of the game in the form of truth or false
   */
  public boolean play() {
    while (this.gameOver() && !this.isMatch()) {

      // Message Assembly
      String message = this.gallow.getGallow(this.attempt)
          + "\nВы использоватли следующие буквы: " + this.usedChar.toString()
          + "\nНа данный момент слово выглядит так: " + this.maskWord
          + "\nВведите свое предположение:";

      this.answer.print(message);

      var userAnswer = this.source.nextChar();

      if (!isDuplicate(userAnswer)) {
        if (!hasWordChar(userAnswer)) {
          this.registerAttempt();
          this.answer.print("Извините, такой буквы в слове нету");

        } else {
          this.answer.print("Да такая буква имеется ");
        }
      } else {
        this.answer.print("Вы уже вводили такую букву");
      }
      this.usedChar.add(userAnswer);
    }

    return this.isMatch();
  }

  /**
   * The method of checking the correspondence of the mask-word to the hidden word
   *
   * @return Returns true if the word is matched, false otherwise
   */
  private boolean isMatch() {
    return this.maskWord.toString().equals(this.word);
  }

  /**
   * The method of checking a character in a word
   *
   * @param hasChar The character being checked
   * @return Returns true if the character is in the word, false otherwise
   */
  private boolean hasWordChar(char hasChar) {

    var index = 0;
    boolean flag = false;
    // Finding a character in a word and changing the mask
    while (index > -1) {
      index = this.word.indexOf(hasChar, index);

      if (index != -1) {
        this.maskWord.setCharAt(index, hasChar);
        index++;
        flag = true;
      }
    }

    return flag;
  }

  /**
   * The method of checking if the character has been used before
   *
   * @param hasChar The character to check
   * @return Returns true if the character was previously used, otherwise false
   */
  private boolean isDuplicate(char hasChar) {
    return this.usedChar.contains(hasChar);
  }

  /**
   * Register attempt.
   */
  public void registerAttempt() {
    this.attempt++;
  }

  /**
   * This method checks if the game is over by comparing the current number of attempts with the
   * maximum number of attempts allowed.
   *
   * @return Returns True if the current attempt does not exceed the maximum value, False otherwise
   */
  public boolean gameOver() {
    return this.attempt < MAX_ATTEMPT;
  }

}
