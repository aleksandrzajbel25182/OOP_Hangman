import Source.WordSource;
import hagman.AttemptsHangman;
import stdin.StdinCharSource;
import stdout.StdoutAnswer;

public class Application {

  public static void main(String[] args) {
    var wordChoice = new WordSource("src/main/resources/words.txt");
    wordChoice.readFile();
    var charSource = new StdinCharSource();
    var answer = new StdoutAnswer();

    do {
      var word = wordChoice.randomWord();
      var hangman = new AttemptsHangman();
      var game = new Game(charSource, hangman, word, answer);

      var hasWon = game.play();

      if (hasWon) {
        System.out.println("Win");
      } else {
        System.out.println("Game Over");
      }

      System.out.println("Press 'q' to exit or any key to start a new game");
    } while (charSource.nextChar() != 'q');
  }
}


